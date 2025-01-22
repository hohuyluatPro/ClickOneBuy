package com.online.CBuy.service;

import com.online.CBuy.document.Cart;
import com.online.CBuy.document.Rate;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Rate.SetRate;
import com.online.CBuy.repository.RateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    public AffectedRowsDto postRate(SetRate postRate){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Rate rate = Objects.nonNull(postRate.getUserId()) ? rateRepository.findByProductIdAndUserId(postRate.getProductId(), postRate.getUserId()) : null;
        if(Objects.isNull(rate)){
            Rate rate1 = new Rate();
            if(Objects.nonNull(postRate.getUserId())){
                rate1.setUserId(postRate.getUserId());
            }
            if(Objects.nonNull(postRate.getProductId())){
                rate1.setProductId(postRate.getProductId());
            }
            if(Objects.nonNull(postRate.getComment())){
                rate1.setComment(postRate.getComment());
            } else {
                rate1.setComment("sản phẩm uy tín, chất lượng");
            }
            if(Objects.nonNull(postRate.getScore())){
                rate1.setScore(postRate.getScore());
            } else {
                rate1.setScore(5.0);
            }
            if(Objects.nonNull(postRate.getFileImage())){
                rate1.setFileImage(postRate.getFileImage());
            }
            rate1.setCreatedDate(new Date());
            rate1.setUpdateDate(new Date());
            try {
                rateRepository.save(rate1);
                affectedRowsDto.setMessage("save success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("save fail");
            }
        } else {
            affectedRowsDto.setMessage("Already exists");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putRateByProductIdAndUserId(SetRate putRate){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Rate rate = Objects.nonNull(putRate.getUserId()) ? rateRepository.findByProductIdAndUserId(putRate.getProductId(), putRate.getUserId()) : null;
        if(Objects.nonNull(rate)){
            if(Objects.nonNull(putRate.getUserId())){
                rate.setUserId(putRate.getUserId());
            }
            if(Objects.nonNull(putRate.getProductId())){
                rate.setProductId(putRate.getProductId());
            }
            if(Objects.nonNull(putRate.getComment())){
                rate.setComment(putRate.getComment());
            } else {
                rate.setComment("sản phẩm uy tín, chất lượng");
            }
            if(Objects.nonNull(putRate.getScore())){
                rate.setScore(putRate.getScore());
            } else {
                rate.setScore(5.0);
            }
            if(Objects.nonNull(putRate.getFileImage())){
                rate.setFileImage(putRate.getFileImage());
            }
            rate.setCreatedDate(new Date());
            rate.setUpdateDate(new Date());
            try {
                rateRepository.save(rate);
                affectedRowsDto.setMessage("update success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("update fail");
            }
        } else {
            affectedRowsDto.setMessage("Not found");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putRate(String id, SetRate putRate){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Rate rate = Objects.nonNull(id) ? rateRepository.findOneById(new ObjectId(id)) : null;
        if(Objects.nonNull(rate)){
            if(Objects.nonNull(putRate.getUserId())){
                rate.setUserId(putRate.getUserId());
            }
            if(Objects.nonNull(putRate.getProductId())){
                rate.setProductId(putRate.getProductId());
            }
            if(Objects.nonNull(putRate.getComment())){
                rate.setComment(putRate.getComment());
            } else {
                rate.setComment("sản phẩm uy tín, chất lượng");
            }
            if(Objects.nonNull(putRate.getScore())){
                rate.setScore(putRate.getScore());
            } else {
                rate.setScore(5.0);
            }
            if(Objects.nonNull(putRate.getFileImage())){
                rate.setFileImage(putRate.getFileImage());
            }
            rate.setCreatedDate(new Date());
            rate.setUpdateDate(new Date());
            try {
                rateRepository.save(rate);
                affectedRowsDto.setMessage("update success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("update fail");
            }
        } else {
            affectedRowsDto.setMessage("Not found");
        }
        return affectedRowsDto;
    }

    public List<Rate> getListAllRate(){
        return rateRepository.findAll();
    }

    public List<Rate> getRateByUserId(String userId){
        return rateRepository.findOneByUserId(new ObjectId(userId));
    }

    public List<Rate> getRateByProductId(String productId){
        return rateRepository.findByProductId(new ObjectId(productId));
    }

    public Rate getRate(String id){
        return rateRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteRate(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Rate> existingRate = rateRepository.findById(id);
        if (existingRate.isPresent()) {
            try {
                rateRepository.deleteById(id);
                affectedRowsDto.setMessage("Rate deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Rate not found");
            return affectedRowsDto;
        }
    }
}
