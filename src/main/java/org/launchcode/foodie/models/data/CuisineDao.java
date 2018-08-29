package org.launchcode.foodie.models.data;

import org.launchcode.foodie.models.Cuisine;
import org.launchcode.foodie.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CuisineDao extends CrudRepository<Cuisine, Integer> {
    //public List<Restaurant> findByCuisine(int  cuisineId);
}
