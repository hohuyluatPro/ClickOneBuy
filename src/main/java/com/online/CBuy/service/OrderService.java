package com.online.CBuy.service;

import com.online.CBuy.document.Order;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Order.SetOrder;
import com.online.CBuy.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public AffectedRowsDto postOrder(SetOrder postOrder){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Order order = new Order();
        if(Objects.nonNull(postOrder.getPaid())){
            order.setPaid(postOrder.getPaid());
        }
        if(Objects.nonNull(postOrder.getNote())){
            order.setNote(postOrder.getNote());
        }
        if(Objects.nonNull(postOrder.getCost())){
            order.setCost(postOrder.getCost());
        }
        if(Objects.nonNull(postOrder.getKMId())){
            order.setKMId(postOrder.getKMId());
        }
        if(Objects.nonNull(postOrder.getAddress())){
            order.setAddress(postOrder.getAddress());
        }
        if(Objects.nonNull(postOrder.getStatus())){
            order.setStatus(postOrder.getStatus());
        }
        if(Objects.nonNull(postOrder.getListProductId())){
            order.setListProductId(postOrder.getListProductId());
        }
        if(Objects.nonNull(postOrder.getTotal())){
            order.setTotal(postOrder.getTotal());
        }
        if(Objects.nonNull(postOrder.getPriceDiscount())){
            order.setPriceDiscount(postOrder.getPriceDiscount());
        }
        if(Objects.nonNull(postOrder.getUserId())){
            order.setUserId(postOrder.getUserId());
        }
        order.setCreatedDate(new Date());
        order.setUpdateDate(new Date());
        try {
            affectedRowsDto.setMessage("success");
            affectedRowsDto.setAffectedRows(1);
        } catch (Exception e){
            affectedRowsDto.setMessage("fail");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putOrder(String id, SetOrder postOrder){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Order order = orderRepository.findOneById(new ObjectId(id));
        if(Objects.nonNull(order)) {
            if (Objects.nonNull(postOrder.getPaid())) {
                order.setPaid(postOrder.getPaid());
            }
            if (Objects.nonNull(postOrder.getNote())) {
                order.setNote(postOrder.getNote());
            }
            if (Objects.nonNull(postOrder.getCost())) {
                order.setCost(postOrder.getCost());
            }
            if (Objects.nonNull(postOrder.getKMId())) {
                order.setKMId(postOrder.getKMId());
            }
            if (Objects.nonNull(postOrder.getAddress())) {
                order.setAddress(postOrder.getAddress());
            }
            if (Objects.nonNull(postOrder.getStatus())) {
                order.setStatus(postOrder.getStatus());
            }
            if (Objects.nonNull(postOrder.getListProductId())) {
                order.setListProductId(postOrder.getListProductId());
            }
            if (Objects.nonNull(postOrder.getTotal())) {
                order.setTotal(postOrder.getTotal());
            }
            if (Objects.nonNull(postOrder.getPriceDiscount())) {
                order.setPriceDiscount(postOrder.getPriceDiscount());
            }
            if (Objects.nonNull(postOrder.getUserId())) {
                order.setUserId(postOrder.getUserId());
            }
            order.setCreatedDate(new Date());
            order.setUpdateDate(new Date());
            try {
                affectedRowsDto.setMessage("success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e) {
                affectedRowsDto.setMessage("fail");
            }
        } else {
            affectedRowsDto.setMessage("Not found");
        }
        return affectedRowsDto;
    }

    public List<Order> getListAllOrder(){
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUserId(String userId){
        return orderRepository.findOneByUserId(new ObjectId(userId));
    }

    public Order getOrder(String id){
        return orderRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteOrder(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            try {
                orderRepository.deleteById(id);
                affectedRowsDto.setMessage("Order deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Order not found");
            return affectedRowsDto;
        }
    }
}
