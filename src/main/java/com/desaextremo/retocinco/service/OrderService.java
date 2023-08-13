package com.desaextremo.retocinco.service;

import com.desaextremo.retocinco.entity.Order;
import com.desaextremo.retocinco.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.findById(id);
    }

    public Order create(Order order) {
        if (order.getId() == null) {
            return order;
        } else {
            return orderRepository.save(order);
        }
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepository.findById(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.save(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Order orderDelete;
        Optional<Order> optional = orderRepository.findById(id);

        if (optional.isPresent()){
            orderDelete = optional.get();
            orderRepository.delete(orderDelete);
            return true;
        }else{
            return false;
        }
    }

    //Reto 3
    public List<Order> findOrderByZoneV1(String zona){
        return orderRepository.findByZona(zona);
    }
    public List<Order> findOrderByZoneV2(String zona){
        return orderRepository.findAllBySalesMan_Zone(zona);
    }


    //Reto 4
    public List<Order> findAllBySalesMan_Id(int idSalesMan){
        return orderRepository.findAllBySalesMan_Id(idSalesMan);
    }

    public List<Order> encontrarOrdenesIdVen(int idSalesMan){
        return orderRepository.encontrarOrdenesIdVen(idSalesMan);
    }

    //reto 4 consulta: BASE_URL/api/order/state/Pendiente/6

    //opcion 1
    public List<Order> findAllByStatusAndSalesMan_Id(String estado,int idSalesMan){
        return orderRepository.findAllByStatusAndSalesMan_Id(estado,idSalesMan);
    }

    //opcion 2

    public List<Order> encontrarOrdenesXEstadnIdVen(String estado,int idSalesMan){
        return orderRepository.encontrarOrdenesXEstadnIdVen(estado,idSalesMan);
    }

    //Reto 4 BASE_URL/api/order/date/2021-11-15/6
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }
}
