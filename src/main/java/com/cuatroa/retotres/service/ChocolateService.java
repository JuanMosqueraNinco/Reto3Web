package com.cuatroa.retotres.service;

import com.cuatroa.retotres.model.Chocolate;
import com.cuatroa.retotres.repository.ChocolateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author desaextremo
 */
@Service
public class ChocolateService {

    @Autowired
    private ChocolateRepository chocolateRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Chocolate> getAll() {
        return chocolateRepository.getAll();
    }

    public Optional<Chocolate> getAccesory(String reference) {
        return chocolateRepository.getAccesory(reference);
    }

    public Chocolate create(Chocolate accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return chocolateRepository.create(accesory);
        }
    }

    public Chocolate update(Chocolate accesory) {

        if (accesory.getReference() != null) {
            Optional<Chocolate> accesoryDb = chocolateRepository.getAccesory(accesory.getReference());
            if (!accesoryDb.isEmpty()) {
                // if (accesory.getBrand() != null) {
                // accesoryDb.get().setBrand(accesory.getBrand());
                // }

                if (accesory.getCategory() != null) {
                    accesoryDb.get().setCategory(accesory.getCategory());
                }

                // if (accesory.getObjetivo() != null) {
                // accesoryDb.get().setObjetivo(accesory.getObjetivo());
                // }

                if (accesory.getDescription() != null) {
                    accesoryDb.get().setDescription(accesory.getDescription());
                }
                if (accesory.getPrice() != 0.0) {
                    accesoryDb.get().setPrice(accesory.getPrice());
                }
                if (accesory.getQuantity() != 0) {
                    accesoryDb.get().setQuantity(accesory.getQuantity());
                }
                if (accesory.getPhotography() != null) {
                    accesoryDb.get().setPhotography(accesory.getPhotography());
                }
                accesoryDb.get().setAvailability(accesory.isAvailability());
                chocolateRepository.update(accesoryDb.get());
                return accesoryDb.get();
            } else {
                return accesory;
            }
        } else {
            return accesory;
        }
    }

    public boolean delete(String reference) {
        Boolean aBoolean = getAccesory(reference).map(accesory -> {
            chocolateRepository.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<Chocolate> getByPrice(double price) {
        return chocolateRepository.getByPrice(price);
    }

    public List<Chocolate> getByDescriptionContains(String description) {
        return chocolateRepository.getByDescriptionContains(description);
    }

}
