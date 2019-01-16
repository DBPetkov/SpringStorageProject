package com.storageproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storageproject.model.Storage;
import com.storageproject.repository.StorageRepository;

@Service("storageService")
public class StorageService {
	
	private StorageRepository storageRepository;
    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }
    
    public Storage saveItem(Storage storage) {
    	return storageRepository.save(storage);
    }
    
    public List<Storage> getAllItems(){
    	return (List<Storage>) storageRepository.findAll();
    }
    public Storage findByCode(int productCode) {
    	return storageRepository.findByProductCode(productCode);
    }
    
    public Storage save(Storage storage) {
		return storageRepository.save(storage);
	}
	
	public void delete(Long id) {
		storageRepository.deleteById(id);
	}
	public Optional<Storage> get(Long id) {
		return storageRepository.findById(id);
	}
	public List<Storage> search(String name,String category) {
		return storageRepository.productName(name,category);
	}

    
}
