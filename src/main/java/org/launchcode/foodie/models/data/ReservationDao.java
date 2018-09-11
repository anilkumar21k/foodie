package org.launchcode.foodie.models.data;

import org.launchcode.foodie.models.Reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReservationDao extends CrudRepository<Reservation, Integer> {}
