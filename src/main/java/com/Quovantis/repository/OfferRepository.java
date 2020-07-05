package com.Quovantis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quovantis.modal.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	Offer findByJobTitle(String jobTitle);
	
}
