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
    private ChocolateCrudRepository accesoryCrudRepository;

    public List<Chocolate> getAll() {
        return accesoryCrudRepository.findAll();
    }

    public Optional<Chocolate> getAccesory(String reference) {
        return accesoryCrudRepository.findById(reference);
    }
    
    public Chocolate create(Chocolate accesory) {
        return accesoryCrudRepository.save(accesory);
    }

    public void update(Chocolate accesory) {
        accesoryCrudRepository.save(accesory);
    }
    
    public void delete(Chocolate accesory) {
        accesoryCrudRepository.delete(accesory);
    }
}
