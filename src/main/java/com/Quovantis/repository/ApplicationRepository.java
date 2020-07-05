package com.Quovantis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quovantis.modal.Application;
import com.Quovantis.modal.Offer;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long>{
	
	List<Application> findByOffer(Offer offer);
	
	Application findByEmailAndOffer(String email, Offer offer);
	

}
