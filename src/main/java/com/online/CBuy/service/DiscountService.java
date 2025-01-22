package com.online.CBuy.service;

import com.online.CBuy.document.Discount;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Discount.SetDiscount;
import com.online.CBuy.repository.DiscountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public AffectedRowsDto postDiscount(SetDiscount setDiscount){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        TimeZone timezone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(timezone);
        Optional<Discount> discount = Objects.nonNull(setDiscount.getName()) ? discountRepository.findByName(setDiscount.getName()) : null;
        if(discount.isEmpty()){
            Discount discount1 = new Discount();
            if(Objects.nonNull(setDiscount.getName())){
                discount1.setName(setDiscount.getName());
            }
            if(Objects.nonNull(setDiscount.getDescription())){
                discount1.setDescription(setDiscount.getDescription());
            }
            if(Objects.nonNull(setDiscount.getType())){
                discount1.setType(setDiscount.getType());
            }
            if(Objects.nonNull(setDiscount.getFromDate())){
                discount1.setFromDate(setDiscount.getFromDate());
            }
            if(Objects.nonNull(setDiscount.getToDate())){
                discount1.setToDate(setDiscount.getToDate());
            }
            if(Objects.nonNull(setDiscount.getValue())){
                discount1.setValue(setDiscount.getValue());
            }
            discount1.setCreatedDate(new Date());
            discount1.setUpdateDate(new Date());
            try {
                discountRepository.save(discount1);
                affectedRowsDto.setAffectedRows(1);
                affectedRowsDto.setMessage("success");
            } catch (Exception e){
                affectedRowsDto.setMessage("save false");
            }
        } else {
            affectedRowsDto.setMessage("Already exists");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putDiscount(String id, SetDiscount setDiscount){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        TimeZone timezone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(timezone);
        Discount discount = discountRepository.findOneById(new ObjectId(id));
        if(Objects.nonNull(discount)){
            if(Objects.nonNull(setDiscount.getName())){
                discount.setName(setDiscount.getName());
            }
            if(Objects.nonNull(setDiscount.getDescription())){
                discount.setDescription(setDiscount.getDescription());
            }
            if(Objects.nonNull(setDiscount.getType())){
                discount.setType(setDiscount.getType());
            }
            if(Objects.nonNull(setDiscount.getFromDate())){
                discount.setFromDate(new Date(df.format(setDiscount.getFromDate())));
            }
            if(Objects.nonNull(setDiscount.getToDate())){
                discount.setToDate(new Date(df.format(setDiscount.getToDate())));
            }
            if(Objects.nonNull(setDiscount.getValue())){
                discount.setValue(setDiscount.getValue());
            }
            discount.setUpdateDate(new Date());
            try {
                discountRepository.save(discount);
                affectedRowsDto.setAffectedRows(1);
                affectedRowsDto.setMessage("success");
            } catch (Exception e){
                affectedRowsDto.setMessage("update false");
            }
        } else {
            affectedRowsDto.setMessage("not found");
        }
        return affectedRowsDto;
    }

    public List<Discount> getListAllDiscount(){
        return discountRepository.findAll();
    }

    public Discount getDiscount(String id){
        return discountRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteDiscount(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Discount> existingDiscount = discountRepository.findById(id);
        if (existingDiscount.isPresent()) {
            try {
                discountRepository.deleteById(id);
                affectedRowsDto.setMessage("Discount deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Discount not found");
            return affectedRowsDto;
        }
    }
}
