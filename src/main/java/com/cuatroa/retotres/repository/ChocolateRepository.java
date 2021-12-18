package com.cuatroa.retotres.repository;

import com.cuatroa.retotres.model.Chocolate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cuatroa.retotres.repository.crud.ChocolateCrudRepository;

/**
 *
 * @author desaextremo
 */
@Repository
public class ChocolateRepository {
    @Autowired
    private ChocolateCrudRepository chocolateCrudRepository;

    public List<Chocolate> getAll() {
        return chocolateCrudRepository.findAll();
    }

    public Optional<Chocolate> getAccesory(String reference) {
        return chocolateCrudRepository.findById(reference);
    }
    
    public Chocolate create(Chocolate accesory) {
        return chocolateCrudRepository.save(accesory);
    }

    public void update(Chocolate accesory) {
        chocolateCrudRepository.save(accesory);
    }
    
    public void delete(Chocolate accesory) {
        chocolateCrudRepository.delete(accesory);
    }
    public List<Chocolate> getByPrice(double price){
        return chocolateCrudRepository.findByPrice(price);
    }

    public List<Chocolate> getByDescriptionContains(String description){
        return chocolateCrudRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
