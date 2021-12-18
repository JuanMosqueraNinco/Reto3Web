package com.cuatroa.retotres.repository.crud;

import java.util.List;

import com.cuatroa.retotres.model.Chocolate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author desaextremo
 */
public interface ChocolateCrudRepository extends MongoRepository<Chocolate, String> {
    public List<Chocolate> findByPrice(double price);
    public List<Chocolate> findByDescriptionContainingIgnoreCase(String description);
}
