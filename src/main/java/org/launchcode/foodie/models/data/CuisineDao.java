package org.launchcode.foodie.models.data;

import org.launchcode.foodie.models.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CuisineDao extends CrudRepository<Cuisine, Integer> {
}
