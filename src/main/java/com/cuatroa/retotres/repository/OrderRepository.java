package com.cuatroa.retotres.repository;

import com.cuatroa.retotres.model.Order;
import com.cuatroa.retotres.repository.crud.OrderCrudRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author desarrolloextremo
 */
@Repository
public class OrderRepository {


    @Autowired
    private OrderCrudRepository orderCrudRepository;
    // atributo de relacion
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return (List<Order>) orderCrudRepository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudRepository.findById(id);
    }

    public Order create(Order order) {
        return orderCrudRepository.save(order);
    }

    public void update(Order order) {
        orderCrudRepository.save(order);
    }

    public void delete(Order order) {
        orderCrudRepository.delete(order);
    }
    
    public Optional<Order> lastUserId(){
        return orderCrudRepository.findTopByOrderByIdDesc();
    }

    public List<Order> findByZone(final String country){
        return orderCrudRepository.findByZone( country);
    }
    //Métodos del reto 4
//Reto 4: Ordenes de un asesor
public List<Order> ordersSalesManByID(Integer id) {
    Query query = new Query();
         
     Criteria criterio = Criteria.where("salesMan.id").is(id);
     query.addCriteria(criterio);
         
     List<Order> orders = mongoTemplate.find(query, Order.class);
         
     return orders;
         
  }
  
  //Reto 4: Ordenes de un asesor x Estado
     public List<Order> ordersSalesManByState(String state, Integer id) {
         Query query = new Query();
         Criteria criterio = Criteria.where("salesMan.id").is(id)
                             .and("status").is(state);
         
         query.addCriteria(criterio);
         
         List<Order> orders = mongoTemplate.find(query,Order.class);
         
         return orders;
     }
 	 
 	//Reto 4: Ordenes de un asesor x Fecha
 	    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
 	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 	        Query query = new Query();
 	        
 	        Criteria dateCriteria = Criteria.where("registerDay")
                        .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
 	                        .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
 	                        .and("salesMan.id").is(id);
        
             query.addCriteria(dateCriteria);    
 	        List<Order> orders = mongoTemplate.find(query,Order.class);
 	        
 	        return orders;       
 	    }

         
    public List<Order> getBySalesManIdAndStatus(Integer id, String status){
        return orderCrudRepository.findBySalesManIdAndStatus(id, status);
    }

    public List<Order> getByRegisterDayAndSalesManId(String registerDay, Integer id){
        try {
            return orderCrudRepository.findByRegisterDayAndSalesManId(new SimpleDateFormat("yyyy-MM-dd").parse(registerDay), id);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}
