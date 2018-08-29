package org.launchcode.foodie.models.data;

import org.launchcode.foodie.models.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {


    //@Query(value = "SELECT * FROM `restaurant` WHERE address LIKE '%63017'", nativeQuery = true);
    //public ArrayList<Restaurant> findByAddress(String address);


     public List<Restaurant> findByAddressContaining(String address);
     //public List<Restaurant> findByCuisine(String cuisine);





}
