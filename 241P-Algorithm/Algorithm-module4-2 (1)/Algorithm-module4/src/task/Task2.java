package task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//use class city to model graph
public class Task2 {
    //adjacentList: put all cities in this array
    //cityAndPopFile: a file related to city_population
    public void readCity_population(ArrayList<City> adjacentList,File cityAndPopFile,File netCity) throws IOException {
        //read city_population file get the name and population of a city

        //use Fileread to read file and user bufferedread to improve the efficiency
        BufferedReader br = null;

        try {
            br=new BufferedReader(new FileReader(cityAndPopFile));

            //read the row of content as string
            String read=br.readLine();
            while(read!=null){

                //1. find the index of ':'
                int index=read.indexOf(':');

                //2. 0-index-2 substring is the name of city
                String cityName=read.substring(0,index-1);

                //3. index+2- substring is the population of city
                String cityPopulation=read.substring(index+2);

                //transfer cityPopulation to int
                int cityPop=Integer.parseInt(cityPopulation);

                //get a newClass of city,and put it into arr
                City newCity=new City(cityName,cityPop,new ArrayList<String>());
                adjacentList.add(newCity);

                //continue to read file
                read=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //close bufferedReader
        br.close();

        //read road_network file to know the connection of any two cities, in order to receive the graph
        //use Fileread to read file and user bufferedread to improve the efficiency
        br = null;

        try {
            br=new BufferedReader(new FileReader(netCity));

            //read the row of content as string
            String read=br.readLine();
            while(read!=null) {
                //1. find the index of ':'
                int index = read.indexOf(':');

                //2.two cities are connected, the first one string 0-index-2; the second one string index+2-
                String firstCity=read.substring(0,index-1);
                String secondCity=read.substring(index+2);

                //3.add it to the isConnected list to each other
                City first=null;
                City second=null;

                //find two city object
                for (City city : adjacentList) {
                    if(city.getName().equals(firstCity)){
                        first=city;
                    }
                    if(city.getName().equals(secondCity)){
                        second=city;
                    }
                }

                //get their list of connected cities
                int firstIndex=adjacentList.indexOf(first);
                int secondIndex=adjacentList.indexOf(second);

                ArrayList<String> firstConnected=adjacentList.get((firstIndex)).getIsConnected();
                ArrayList<String> secondConnected=adjacentList.get((secondIndex)).getIsConnected();

                //add the new one
                firstConnected.add(secondCity);
                secondConnected.add(firstCity);

                adjacentList.get(adjacentList.indexOf(first)).setIsConnected(firstConnected);
                adjacentList.get(adjacentList.indexOf(second)).setIsConnected(secondConnected);

                //continue to read
                read=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        br.close();

    }

}
