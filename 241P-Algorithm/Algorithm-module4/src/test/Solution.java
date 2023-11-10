package test;

import task.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {

        //test task2
        Task2 task2=new Task2();
        ArrayList<City> adjacentList=new ArrayList<>();
        File cityPop=new File("src/city_population_no_dup.txt");
        File roadNet=new File("src/road_network.txt");

        task2.readCity_population(adjacentList,cityPop,roadNet);
//        for (City city : adjacentList) {
//            System.out.println(city.getName()+":"+city.getIsConnected());
//        }

        //test task3
        Task3 task3=new Task3();
        Integer islandNumber = task3.getIslandNumber(adjacentList, adjacentList);
        System.out.println(islandNumber);

       //test task4
        Task4 task4=new Task4();
        ArrayList<Long> population = task4.getPopulation(adjacentList, adjacentList);
        for (Long popu: population) {
            System.out.println(popu);
        }
//
//        // task5
        Task5 task5=new Task5();
        City one=null;
        City two=null;
        for (City city : adjacentList) {
            if(city.getName().equals("Maplewood")){
                one=city;
            }
            if(city.getName().equals("Los Angeles")){
                two=city;
            }
        }
        Integer integer = task5.shortestPath(one, two, adjacentList);
        System.out.println(integer);
    }

}
