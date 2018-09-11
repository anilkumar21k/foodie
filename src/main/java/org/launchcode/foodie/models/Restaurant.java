package org.launchcode.foodie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.launchcode.foodie.models.data.RestaurantDao;
import java.util.List;


@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @Size(min=3, max=200)
    private String address;

    private String menu;

    private String reserve;

    @ManyToOne
    private Cuisine cuisine;
   /* @ManyToMany(mappedBy="restaurants")
    private List<Cuisine> cuisines; */

    public Restaurant(String name, String address, String menu, String reserve) {
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.reserve = reserve;
    }



    public Restaurant() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine qisine) {
        this.cuisine = qisine;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    //public Restaurant getByCuisine(String cuisine) {
    //   return restaurant.stream().filter(restaurant -> restaurant.getCuisine().equals(cuisine)).findFirst().get();
    //}
}