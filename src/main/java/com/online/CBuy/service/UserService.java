package com.online.CBuy.service;

import com.online.CBuy.document.User;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.PostUserDto;
import com.online.CBuy.dto.PutUserDto;
import com.online.CBuy.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public AffectedRowsDto createUser(PostUserDto postUserDto){
        if(Objects.isNull(postUserDto)){
            return new AffectedRowsDto(0);
        }
        User user = new User();
        user.setEmail(postUserDto.getEmail());
        user.setName(postUserDto.getName());
        user.setIdentifiNumber(postUserDto.getIdentifiNumber());
        user.setPhoneNumber(postUserDto.getPhoneNumber());
        user.setAddress(postUserDto.getAddress());
        user.setVillage(postUserDto.getVillage());
        user.setDistrict(postUserDto.getDistrict());
        user.setProvince(postUserDto.getProvince());
        user.setCardId(postUserDto.getCardId());

        userRepository.save(user);
//        try {
//            mongoTemplate.save(postUserDto, "users");
//        } catch (Exception e) {
//            System.out.println(e);
//            return new AffectedRowsDto(0);
//        }
        return new AffectedRowsDto(1);
    }


    @Transactional
    public AffectedRowsDto updateUser(ObjectId id, PutUserDto putUser) {
        try {
            User user = userRepository.findById(id.toString()).get();
            if (Objects.nonNull(user)) {
                if(Objects.nonNull(putUser.getName())){
                    user.setName(putUser.getName());
                }
                if(Objects.nonNull(putUser.getEmail())){
                    user.setEmail(putUser.getEmail());
                }
                if(Objects.nonNull(putUser.getIdentifiNumber())){
                    user.setIdentifiNumber(putUser.getIdentifiNumber());
                }
                if(Objects.nonNull(putUser.getAddress())){
                    user.setAddress(putUser.getAddress());
                }
                if(Objects.nonNull(putUser.getPhoneNumber())){
                    user.setPhoneNumber(putUser.getPhoneNumber());
                }
                if(Objects.nonNull(putUser.getCardId())){
                    user.setCardId(putUser.getCardId());
                }
                if(Objects.nonNull(putUser.getDistrict())){
                    user.setDistrict(putUser.getDistrict());
                }
                if(Objects.nonNull(putUser.getProvince())){
                    user.setProvince(putUser.getProvince());
                }
                if(Objects.nonNull(putUser.getVillage())){
                    user.setVillage(putUser.getVillage());
                }
                userRepository.save(user);
            }
        } catch (Exception e){
            System.out.print(e);
            return new AffectedRowsDto(0);
        }
        return new AffectedRowsDto(1);
    }

    @Transactional
    public AffectedRowsDto delete(ObjectId id) {
        try {
            int affected = userRepository.deleteUsersById(id);
            return new AffectedRowsDto(affected);
        } catch (Exception e) {
            System.out.println("Delete payment failed! " + e.getMessage());
        }
        return new AffectedRowsDto(0);
    }
}
