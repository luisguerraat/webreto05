package com.desaextremo.retocinco.repository;

import com.desaextremo.retocinco.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Integer> {
    @Query("{'salesMan.zone': ?0}")
    public List<Order> findByZona(String zona);

    public List<Order> findAllBySalesMan_Zone(String zona);

    //reto 4 consulta: BASE_URL/api/order/salesman/6

    //opcion 1
    public List<Order> findAllBySalesMan_Id(int idSalesMan);

    //opcion 2
    @Query("{'salesMan.id': ?0}")
    public List<Order> encontrarOrdenesIdVen(int idSalesMan);

    //reto 4 consulta: BASE_URL/api/order/state/Pendiente/6

    //opcion 1
    public List<Order> findAllByStatusAndSalesMan_Id(String estado,int idSalesMan);

    //opcion 2
    @Query("{'status':?0 ,'salesMan.id': ?1}")
    public List<Order> encontrarOrdenesXEstadnIdVen(String estado,int idSalesMan);
}
