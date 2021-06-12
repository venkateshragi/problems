package com.exam.all.scrap;

//import org.apache.commons.lang3.reflect.TypeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class GenericTypeInfo {
    java.util.List< Foo> fooList = new ArrayList<Foo>();

    public List<Foo> getFooList() {
        return fooList;
    }

    public void setFooList(List< Foo> fooList) {
        this.fooList = fooList;
    }

    public static void main(String[] args) throws Exception {
        /*Field field = GenericTypeInfo.class.getDeclaredField("fooList");

        Method method = GenericTypeInfo.class.getMethod("getFooList", null);

        Type returnType = method.getGenericReturnType();

        if(returnType instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
//            System.out.println("Type is this:" + TypeUtils.getArrayComponentType(type));
            for(Type typeArgument : typeArguments){
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass.toString());
            }
        }*/

        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String[] strs = firstLine.split(" ");
        int numberOfStations = Integer.parseInt(strs[0]);
        int numberOfTrains = Integer.parseInt(strs[1]);

        String secondLine = scanner.nextLine();
        strs = secondLine.split(" ");
        int origin = Integer.parseInt(strs[0]);
        int destination = Integer.parseInt(strs[0]);

        int[] trainOrigins = new int[numberOfTrains];
        int[] hops = new int[numberOfTrains];
        for(int i = 0; i < numberOfTrains; i++) {
            String trainOriginAndHop = scanner.nextLine();
            strs = trainOriginAndHop.split(" ");
            trainOrigins[i] = Integer.parseInt(strs[0]);
            hops[i] = Integer.parseInt(strs[1]);
        }
        System.out.println(numberOfStations + " " + numberOfTrains);
        System.out.println(origin + " " + destination);
        for(int i = 0; i < numberOfTrains; i++) {
            System.out.println(trainOrigins[i] + " " + hops[i]);
        }
    }

    static class Foo {}

    static class Bar extends Foo {}
}
