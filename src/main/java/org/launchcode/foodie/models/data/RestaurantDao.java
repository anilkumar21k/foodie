package org.launchcode.foodie.models.data;

import org.launchcode.foodie.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {

    public List<Restaurant>findByCuisine(String cuisine);

}
