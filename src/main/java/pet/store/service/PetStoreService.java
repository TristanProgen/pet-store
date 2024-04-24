package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

	@Autowired
	private PetStoreDao petStoreDao;

	public PetStoreData savePetstore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();

		PetStore petStore = findOrCreatePetstore(petStoreId);
		
		copyPetStoreFields(petStore,petStoreData);
		
		return new PetStoreData(petStoreDao.save(petStore));

	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		
	}

	private PetStore findOrCreatePetstore(Long petStoreId) {

		PetStore petStore;
		if (Objects.isNull(petStoreId)) {
		
			petStore = new PetStore();

		} else {
			petStore = findPetStoreById(petStoreId);
		}

		return petStore;

	}

	private PetStore findPetStoreById(Long petStoreId) {

		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Park with ID = " + petStoreId + " was not found"));
	}

}
