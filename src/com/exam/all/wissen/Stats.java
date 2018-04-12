package com.exam.all.wissen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This demonstrates a simple way to achieve synchronization.
 */
public class Stats {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            final StatisticsAggregator stats = new StatisticsAggregatorImpl();
            final Set<String> symbols = new TreeSet<>();

            String line = scanner.nextLine();
            String[] inputs = line.split(",");
            int threads = Integer.parseInt(inputs[0]);
            ExecutorService pool = Executors.newFixedThreadPool(threads);
            for (int i = 1; i < inputs.length; ++i) {
                String[] tokens = inputs[i].split(" ");
                final String symbol = tokens[0];
                symbols.add(symbol);
                final double price = Double.parseDouble(tokens[1]);
                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        stats.putNewPrice(symbol, price);
                    }
                });

            }
            pool.shutdown();
            try {
                pool.awaitTermination(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (String symbol : symbols) {
                System.out.println(String.format("%s %.4f %d", symbol,
                        stats.getAveragePrice(symbol),
                        stats.getTickCount(symbol)));
            }
        }
        scanner.close();

    }

    ////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

    public interface StatisticsAggregator {
        // This is an input. Make note of this price.
        public void putNewPrice(String symbol, double price);

        // Get the average price
        public double getAveragePrice(String symbol);

        // Get the total number of prices recorded
        public int getTickCount(String symbol);
    }

    public static class StatisticsAggregatorImpl implements StatisticsAggregator {

        private Map<String, List<Double>> symbolsMap = new ConcurrentHashMap<>();

        @Override
        public void putNewPrice(String symbol, double price) {
            List<Double> prices = symbolsMap.get(symbol);
            if (prices == null) {
                prices = new ArrayList<>();
                symbolsMap.putIfAbsent(symbol, prices);
            }
            synchronized (prices) {
                prices.add(price);
            }
        }

        @Override
        public double getAveragePrice(String symbol) {
            List<Double> prices = symbolsMap.get(symbol);
            if (prices == null)
                return 0.0;
            double average = 0;
            synchronized (prices) {
                int size = prices.size();
                for (double price : prices) {
                    average = average + price;
                }
                average = average / size;
            }
            return average;
            // YOUR CODE HERE
        }

        @Override
        public int getTickCount(String symbol) {
            List<Double> prices = symbolsMap.get(symbol);
            if (prices == null)
                return 0;
            int size = 0;
            synchronized (prices) {
                size = prices.size();
            }
            return size;
        }
    }
}