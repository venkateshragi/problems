package com.exam.all.wissen;

import java.util.Scanner;

/**
 * Given a stick of any form (here line, sine wave, exp equation, power etc) find the balance point on it.
 * The approach is to use binary search. First check the middle point, the according to the weight of the stick on left
 * and right, move the left end or right end to find middle point.
 * see balancePoint method for implementation.
 */
public class BalanceLog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            Log c = null;
            switch (tokens[0]) {
                case "LINE":
                    c = new Line(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]),
                            Double.parseDouble(tokens[3]));
                    break;
                case "EXP":
                    c = new Exp(Double.parseDouble(tokens[1]));
                    break;
                case "POWER":
                    c = new Power(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                    break;
                case "SINE":
                    c = new Sine(Double.parseDouble(tokens[1]));
                    break;
            }

            if (c == null) {
                break;
            }
            BalanceLog t = new BalanceLog();
            double h = t.balancePoint(c);
            System.out.println(Math.round(h * 1000d));
        }

        scanner.close();
    }

    public double balancePoint(Log log) {
        double length = log.length();
        double point = length / 2;
        double leftEnd = 0;
        double rightEnd = length;
        while (true) {
            double weightTillPoint = log.weightUpto(point);
            double weightFromPointToEnd = log.weightUpto(length) - weightTillPoint;
            if (weightTillPoint == weightFromPointToEnd)
                return point;
            if (weightTillPoint > weightFromPointToEnd) {
                rightEnd = point;
                point = (leftEnd + point) / 2;
            } else {
                leftEnd = point;
                point = (point + rightEnd) / 2;
            }
        }
    }

    // DO NOT MODIFY CODE BELOW THIS LINE
    interface Log {
        double weightUpto(double x); // returns the weightUpto of the part of the log from the left end to a point at distance x from it.

        double length(); // returns the total length of the log
    }

    static class Line implements Log {
        private double m;
        private double c;
        private double l;

        public Line(double l, double m, double c) {
            this.m = m;
            this.c = c;
            this.l = l;
        }

        @Override
        public double weightUpto(double x) {
            return m * x + c;
        }

        @Override
        public double length() {
            return l;
        }
    }

    static class Exp implements Log {
        private double l;

        public Exp(double l) {
            this.l = l;
        }

        @Override
        public double weightUpto(double x) {
            if (x < 0) {
                return 0;
            } else if (x > l) {
                return Math.exp(l);
            }
            return Math.exp(x);
        }

        @Override
        public double length() {
            return l;
        }
    }

    static class Power implements Log {
        private double l;
        private double power;

        public Power(double l, double power) {
            this.l = l;
            this.power = power;
        }

        @Override
        public double length() {
            return l;
        }

        @Override
        public double weightUpto(double x) {
            if (x < 0) {
                return 0;
            } else if (x > l) {
                return Math.pow(l, power);
            } else {
                return Math.pow(x, power);
            }
        }
    }

    static class Sine implements Log {
        private double l;

        public Sine(double l) {
            this.l = l;
        }

        @Override
        public double length() {
            return l;
        }

        @Override
        public double weightUpto(double x) {
            if (x < 0) {
                return 0;
            } else if (x > l) {
                return l + Math.sin(l);
            } else {
                return x + Math.sin(x);
            }
        }
    }
}
