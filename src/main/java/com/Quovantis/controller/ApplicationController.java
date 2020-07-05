package com.Quovantis.controller;

import org.springframework.web.bind.annotation.RestController;


import com.Quovantis.dto.ApplicationDto;
import com.Quovantis.exception.AlreadyExistsException;
import com.Quovantis.exception.NotFoundException;
import com.Quovantis.modal.Application;
import com.Quovantis.modal.Offer;
import com.Quovantis.repository.ApplicationRepository;
import com.Quovantis.repository.OfferRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ApplicationController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;

	
	@PostMapping("/application")
	public ResponseEntity<Object> createApplication(@Valid @RequestBody ApplicationDto applicationDto) throws IllegalAccessException, InvocationTargetException {
		
		Optional<Offer> offer = offerRepository.findById(applicationDto.getOfferId());
		if(!offer.isPresent())
			throw new NotFoundException("Offer does not exist");
		Application  application = applicationRepository.findByEmailAndOffer(applicationDto.getEmail(), offer.get());
		if(application != null)
			throw new AlreadyExistsException("Application already exist for this offer");
		else 
			application = new Application();
	
		BeanUtils.copyProperties(application, applicationDto);
		application.setOffer(offer.get());
		applicationRepository.save(application);
		return ResponseEntity.ok().build();

	}
	
	@GetMapping("/application/{id}")
	public ResponseEntity<Object> getApplication(@PathVariable long id) throws IllegalAccessException, InvocationTargetException {
		Optional<Application> appOptional = applicationRepository.findById(id);
		if(!appOptional.isPresent())
			throw new NotFoundException("Application does not exist");
		ApplicationDto applicationDto = new ApplicationDto();
		BeanUtils.copyProperties(applicationDto, appOptional.get());
		return ResponseEntity.ok(applicationDto);

	}
	
	@GetMapping("/application/offer/{id}")
	public ResponseEntity<Object> getAllicationsByOffer(@PathVariable long id) throws IllegalAccessException, InvocationTargetException {
		Optional<Offer> offer = offerRepository.findById(id);
		if(!offer.isPresent())
			throw new NotFoundException("Offer does not exist");
		List<Application> applications =  applicationRepository.findByOffer(offer.get());
		Collection<ApplicationDto> applicationDtos =  CollectionUtils.collect(applications, new Transformer<Application, ApplicationDto>() {
			
			@Override
			public ApplicationDto transform(Application application) {
				ApplicationDto applicationDto = new ApplicationDto();
				applicationDto.setEmail(application.getEmail());
				applicationDto.setId(application.getId());
				applicationDto.setResumeText(application.getResumeText());
				applicationDto.setApplicationStatus(application.getApplicationStatus());
				applicationDto.setOfferId(application.getOfferId());
				return applicationDto;
			}
		});

		return ResponseEntity.ok(applicationDtos);

	}
}
