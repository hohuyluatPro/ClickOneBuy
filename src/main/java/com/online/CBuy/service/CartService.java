package com.online.CBuy.service;

import com.online.CBuy.document.Cart;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Cart.SetCart;
import com.online.CBuy.repository.CartRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public AffectedRowsDto postCart(SetCart postCart){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Cart cart = Objects.nonNull(postCart.getUserId()) ? cartRepository.findOneByUserId(postCart.getUserId()) : null;
        if(Objects.isNull(cart)){
            Cart cart1 = new Cart();
            if(Objects.nonNull(postCart.getUserId())){
                cart1.setUserId(postCart.getUserId());
            }
            if(Objects.nonNull(postCart.getProductId())){
                cart1.setProductId(postCart.getProductId());
            }
            if(Objects.nonNull(postCart.getTotal())){
                cart1.setTotal(postCart.getTotal());
            }
            if(Objects.nonNull(postCart.getQuantity())){
                cart1.setQuantity(postCart.getQuantity());
            }
            if(Objects.nonNull(postCart.getStatus())){
                cart1.setStatus(postCart.getStatus());
            }
            if(Objects.nonNull(postCart.getPrice())){
                cart1.setPrice(postCart.getPrice());
            }
            cart1.setCreatedDate(new Date());
            cart1.setUpdateDate(new Date());
            try {
                cartRepository.save(cart1);
                affectedRowsDto.setMessage("success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("save false");
            }
        } else {
            affectedRowsDto.setMessage("Already exists");
        }
        return  affectedRowsDto;
    }

    public AffectedRowsDto putCart(String id, SetCart putCart){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Cart cart = Objects.nonNull(id) ? cartRepository.findById(id).get() : null;
        if(Objects.nonNull(cart)){
            if(Objects.nonNull(putCart.getUserId())){
                cart.setUserId(putCart.getUserId());
            }
            if(Objects.nonNull(putCart.getProductId())){
                cart.setProductId(putCart.getProductId());
            }
            if(Objects.nonNull(putCart.getTotal())){
                cart.setTotal(putCart.getTotal());
            }
            if(Objects.nonNull(putCart.getQuantity())){
                cart.setQuantity(putCart.getQuantity());
            }
            if(Objects.nonNull(putCart.getStatus())){
                cart.setStatus(putCart.getStatus());
            }
            if(Objects.nonNull(putCart.getPrice())){
                cart.setPrice(putCart.getPrice());
            }
            cart.setUpdateDate(new Date());
            try {
                cartRepository.save(cart);
                affectedRowsDto.setMessage("success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("update false");
            }
        } else {
            affectedRowsDto.setMessage("Not found");
        }
        return  affectedRowsDto;
    }

    public AffectedRowsDto putCartByUserId(SetCart putCart){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Cart cart = Objects.nonNull(putCart.getUserId()) ? cartRepository.findOneByUserId(putCart.getUserId()) : null;
        if(Objects.nonNull(cart)){
            if(Objects.nonNull(putCart.getUserId())){
                cart.setUserId(putCart.getUserId());
            }
            if(Objects.nonNull(putCart.getProductId())){
                cart.setProductId(putCart.getProductId());
            }
            if(Objects.nonNull(putCart.getTotal())){
                cart.setTotal(putCart.getTotal());
            }
            if(Objects.nonNull(putCart.getQuantity())){
                cart.setQuantity(putCart.getQuantity());
            }
            if(Objects.nonNull(putCart.getStatus())){
                cart.setStatus(putCart.getStatus());
            }
            if(Objects.nonNull(putCart.getPrice())){
                cart.setPrice(putCart.getPrice());
            }
            cart.setUpdateDate(new Date());
            try {
                cartRepository.save(cart);
                affectedRowsDto.setMessage("success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("update false");
            }
        } else {
            affectedRowsDto.setMessage("Not found");
        }
        return  affectedRowsDto;
    }

    public List<Cart> getListAllCart(){
        return cartRepository.findAll();
    }

    public Cart getCartByUserId(String userId){
        return cartRepository.findOneByUserId(new ObjectId(userId));
    }

    public Cart getCart(String id){
        return cartRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteCart(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Cart> existingCart = cartRepository.findById(id);
        if (existingCart.isPresent()) {
            try {
                cartRepository.deleteById(id);
                affectedRowsDto.setMessage("Cart deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Cart not found");
            return affectedRowsDto;
        }
    }
}
