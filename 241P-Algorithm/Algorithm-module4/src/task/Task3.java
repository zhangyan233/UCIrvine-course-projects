package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Task3 {
    //calculate how many islands in this graph, connected cities belong to one island
    //cities: a given city list
    //adjacentList: an array to put all cities
    public Integer getIslandNumber(ArrayList<City> cities, ArrayList<City> adjacentList){
        //create a set to displace whether the city is visited
        HashSet<String> isVisited=new HashSet<>();
        int res=0;

        for (int i = 0; i < cities.size(); i++) {
            //if the city is never visited, it seems a new island, use dfs to search for other cities which are connected with it
            if(!isVisited.contains(cities.get(i).getName())){
                res+=1;
                dfs(cities,cities.get(i),isVisited,adjacentList);
            }
        }

        return res;
    }

    //cities: a given city list
    //adjacentList: an array to put all cities
    //index: the index of source city
    // isVisited: an array to display whether city is visited
    private void dfs(ArrayList<City>cities,City sourceCity,HashSet<String> isVisited,ArrayList<City> adjacentList){
        //set the city is visited
        isVisited.add(sourceCity.getName());

        //the connection with other city
        ArrayList<String> isConnected = adjacentList.get(adjacentList.indexOf(sourceCity)).getIsConnected();

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
                    dfs(cities,city,isVisited,adjacentList);
                }
            }
        }

    }
}
