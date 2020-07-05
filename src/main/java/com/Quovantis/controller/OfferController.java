package com.Quovantis.controller;


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
import org.springframework.web.bind.annotation.RestController;

import com.Quovantis.dto.OfferDto;
import com.Quovantis.exception.AlreadyExistsException;
import com.Quovantis.exception.NotFoundException;
import com.Quovantis.modal.Offer;
import com.Quovantis.repository.OfferRepository;

@RestController
public class OfferController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@PostMapping("/offer")
	public ResponseEntity<Object> createOffer(@Valid @RequestBody  OfferDto offerDto) throws IllegalAccessException, InvocationTargetException {
		Offer offer = offerRepository.findByJobTitle(offerDto.getJobTitle());
		if(offer != null) 
			throw new AlreadyExistsException("Offer already exist for this job title");
		else 
			offer = new Offer();
		BeanUtils.copyProperties(offer, offerDto);
		offerRepository.save(offer);
		return ResponseEntity.ok().build();

	}
	
	@GetMapping("/offer/{id}")
	public ResponseEntity<Object> getOffer(@PathVariable long id) throws IllegalAccessException, InvocationTargetException {
		Optional<Offer> offer = offerRepository.findById(id);
		if(!offer.isPresent())
			throw new NotFoundException("Offer does not exist");
		OfferDto offerDto = new OfferDto();
		BeanUtils.copyProperties(offerDto, offer.get());
		return ResponseEntity.ok(offerDto);

	}
	
	@GetMapping("/offer")
	public ResponseEntity<Object> getOffers() throws IllegalAccessException, InvocationTargetException {
		List<Offer> offers = (List<Offer>) offerRepository.findAll();
		Collection<OfferDto> offerDtos =  CollectionUtils.collect(offers, new Transformer<Offer, OfferDto>() {
			
			@Override
			public OfferDto transform(Offer offer) {
				OfferDto offerDto = new OfferDto();
				offerDto.setJobTitle(offer.getJobTitle());
				offerDto.setStartDate(offer.getStartDate());
				return offerDto;
			}
		});

		return ResponseEntity.ok(offerDtos);

	}
}
