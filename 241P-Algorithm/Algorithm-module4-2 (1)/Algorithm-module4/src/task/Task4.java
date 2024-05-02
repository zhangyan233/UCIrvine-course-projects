package task;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Task4 {
    //calculate the population of every island
    public ArrayList<Long> getPopulation(ArrayList<City>cities, ArrayList<City> adjacentList){
        //create a list to displace whether the city is visited
        HashSet<String> isVisited=new HashSet<>();

        //create arraylist to put the population of island
        ArrayList<Long> res=new ArrayList<>();

        for (int i = 0; i < cities.size(); i++) {

            //if the city is not visited, it must one new island, dfs to get the sum of its population
            if(!isVisited.contains(cities.get(i).getName())){
                Long pop = dfs(cities,cities.get(i),isVisited,adjacentList);
                res.add(pop);
            }
        }

        return res;
    }

    //cities: a given city list
    //adjacentList: an array to put all cities
    //index: the index of source city
    // isVisited: an array to display whether city is visited
    private Long dfs(ArrayList<City>cities,City sourceCity,HashSet<String> isVisited,ArrayList<City> adjacentList){
        //set population
        long popu=sourceCity.getPopular();

        //set the city is visited
        isVisited.add(sourceCity.getName());

        //the connection with other city
        ArrayList<String> isConnected = adjacentList.get(adjacentList.indexOf(sourceCity)).getIsConnected();
//
        //find whether other cities in cities connect with the city, if so, continue dfs
        for (String str : isConnected) {
            if(isVisited.contains(str)==false){
                City city=null;
                for (City c : adjacentList) {
                    if(c.getName().equals(str)){
                        city=c;
                    }
                }
                if(cities.contains(city)){
                    popu+=dfs(cities,city,isVisited,adjacentList);
                }
            }
        }
        return popu;
    }

}
