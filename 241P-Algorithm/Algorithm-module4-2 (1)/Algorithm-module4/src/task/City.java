package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//the information of city
public class City {
    private String name;
    private Integer popular;
    private ArrayList<String> isConnected;//cities'name connected with this city

    //constructor
    public City(String name, Integer popular) {
        this.name = name;
        this.popular = popular;
    }

    public City(String name, Integer popular, ArrayList<String> isConnected) {
        this.name = name;
        this.popular = popular;
        this.isConnected = isConnected;
    }

    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public ArrayList<String> getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(ArrayList<String> isConnected) {
        this.isConnected = isConnected;
    }
}
