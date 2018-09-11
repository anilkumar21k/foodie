package org.launchcode.foodie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Reservation {

        @Id
        @GeneratedValue
        private int id;

        @NotNull
        @Size(min=3, max=50)
        private String customer_name;

        @NotNull
        @Size(min=9, max=200)
        private String phone;

        private String time;

        private String date;

        private int numberOfGuests;

    public Reservation(String customer_name, String phone, String time, String date, int numberOfGuests) {
        this.customer_name = customer_name;
        this.phone = phone;
        this.time = time;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public Reservation() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
