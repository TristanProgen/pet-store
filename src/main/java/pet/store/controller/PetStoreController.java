package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;


@RestController
@Slf4j
@RequestMapping("/pet_store_app")
//http://localhost:8080/pet_store_app/  
public class PetStoreController {
	

	
	@Autowired
	private PetStoreService petStoreService;
	
	
	
	@PostMapping("/pet_store")
	// Create Pet Store REST endpoint 
	//http://localhost:8080/pet_store_app/pet_store	
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creatign new Pet Store {} ", petStoreData);
		
		return petStoreService.savePetstore(petStoreData);
		
	}
	
	
	@PutMapping("/pet_store/{petStoreId}")
	//Update Pet Store REST endpont 
	//http://localhost:8080/pet_store_app/pet_store/{petStoreId}	
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreData petStoreData) {
		
		petStoreData.setPetStoreId(petStoreId);
		
		log.info("Updating Pet Store with ID = {} ", petStoreId);
		
		return petStoreService.savePetstore(petStoreData);

	}


}
