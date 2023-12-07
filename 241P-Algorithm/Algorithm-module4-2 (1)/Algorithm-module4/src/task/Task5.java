package task;

import java.util.*;

public class Task5 {

    public Integer shortestPath(City one, City two, ArrayList<City> adjacentList) {
        //create a set to place cities have visited
        Set<String> isVisited = new HashSet<>();

        //create a hashMap in order to search city by its name
        HashMap<String, City> mp = new HashMap<>();
        for (City city : adjacentList) {
            mp.put(city.getName(), city);
        }

        //create queue to put potential cities in shortPath
        Queue<City> q = new LinkedList<>();
        q.add(one);
        int layer = 0;

        while (!q.isEmpty()) {
            //the number of cities who have the same path
            int size = q.size();

            //current path of this city
            layer++;

            //calculate these cities
            for (int i = 0; i < size; i++) {
                City city = q.poll();

                //make the city as visited
                isVisited.add(city.getName());

                //find the connection of cur, and put these cities in q
                ArrayList<String> isConnected = adjacentList.get(adjacentList.indexOf(city)).getIsConnected();

                for (String str : isConnected) {
                    //judeg whether the city is the two
                    if (two.getName().equals(str)) {
                        return layer;
                    }

                    //if the connected city is not visited, put it to the q
                    if (isVisited.contains(str) == false) {
                        q.add(mp.get(str));
                    }

                }
            }
        }
        //two cities are not connected
        return -1;
    }
}
