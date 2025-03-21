package com.online.CBuy.service;


import com.online.CBuy.document.Account;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.GetAccountDto;
import com.online.CBuy.pojo.Account.PostAccount;
import com.online.CBuy.pojo.Account.PutAccount;
import com.online.CBuy.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AffectedRowsDto postAccount(PostAccount postAccount){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Date date = new Date();
        Optional<Account> account = accountRepository.findByUsername(postAccount.getUsername());
        // Kiểm tra nếu username đã tồn tại
        if (account.isPresent()) {
            logger.info("Username already exists");
            affectedRowsDto.setMessage("Username already exists");
            return affectedRowsDto;
        }
        Account account1 = new Account();
        if(Objects.nonNull(postAccount.getUsername())){
            account1.setUsername(postAccount.getUsername());
        }
        if(Objects.nonNull(postAccount.getPassword())){
            account1.setPassword(passwordEncoder.encode(postAccount.getPassword()));
        }
        if(Objects.nonNull(postAccount.getFullName())){
            account1.setFullName(postAccount.getFullName());
        }
        if(Objects.nonNull(postAccount.getIdentifiNumber())){
            account1.setIdentifiNumber(postAccount.getIdentifiNumber());
        }
        if(Objects.nonNull(postAccount.getPhoneNumber())){
            account1.setPhoneNumber(postAccount.getPhoneNumber());
        }
        if(Objects.nonNull(postAccount.getEmail())){
            account1.setEmail(postAccount.getEmail());
        }
        if(Objects.nonNull(postAccount.getAddress())){
            account1.setAddress(postAccount.getAddress());
        }
        if(Objects.nonNull(postAccount.getRole())){
            account1.setRole(postAccount.getRole());
        }
        if(Objects.nonNull(postAccount.getProvince())){
            account1.setProvince(postAccount.getProvince());
        }
        if(Objects.nonNull(postAccount.getVillage())){
            account1.setVillage(postAccount.getVillage());
        }
        if(Objects.nonNull(postAccount.getDistrict())){
            account1.setDistrict(postAccount.getDistrict());
        }
        account1.setCreatedDate(date);
        account1.setUpdateDate(date);
        try {
            accountRepository.save(account1);
            affectedRowsDto.setAffectedRows(1);
        } catch (Exception e){
            logger.info("Error: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return  affectedRowsDto;
    }


    public AffectedRowsDto putAccount(String id, PutAccount putAccount){
//        TimeZone timezone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        df.setTimeZone(timezone);
        Date date = new Date();
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            Account account1 = existingAccount.get();
            if (Objects.nonNull(putAccount.getUsername())) {
                account1.setUsername(putAccount.getUsername());
            }
            if (Objects.nonNull(putAccount.getPassword())) {
                account1.setPassword(passwordEncoder.encode(putAccount.getPassword()));
            }
            if(Objects.nonNull(putAccount.getFullName())){
                account1.setFullName(putAccount.getFullName());
            }
            if (Objects.nonNull(putAccount.getIdentifiNumber())) {
                account1.setIdentifiNumber(putAccount.getIdentifiNumber());
            }
            if (Objects.nonNull(putAccount.getPhoneNumber())) {
                account1.setPhoneNumber(putAccount.getPhoneNumber());
            }
            if(Objects.nonNull(putAccount.getEmail())){
                account1.setEmail(putAccount.getEmail());
            }
            if (Objects.nonNull(putAccount.getAddress())) {
                account1.setAddress(putAccount.getAddress());
            }
            if (Objects.nonNull(putAccount.getRole())) {
                account1.setRole(putAccount.getRole());
            }
            if (Objects.nonNull(putAccount.getProvince())) {
                account1.setProvince(putAccount.getProvince());
            }
            if (Objects.nonNull(putAccount.getVillage())) {
                account1.setVillage(putAccount.getVillage());
            }
            if (Objects.nonNull(putAccount.getDistrict())) {
                account1.setDistrict(putAccount.getDistrict());
            }
            account1.setUpdateDate(date);
            try {
                accountRepository.save(account1);
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e) {
                logger.info("Error: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return  affectedRowsDto;
    }

    public List<GetAccountDto> getListAccount(){
        List<Account> result = mongoTemplate.findAll(Account.class);
//        return result;
        return result.stream().map(doc -> new GetAccountDto(doc)).collect(Collectors.toList());
    }

    public GetAccountDto getAccount(String id){
        GetAccountDto account = accountRepository.findAccountOneById(new ObjectId(id));
        if (Objects.nonNull(account)) {
            return account;
        } else {
            return null;
        }
    }

    public AffectedRowsDto deleteAccount(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            try {
                accountRepository.deleteById(id);
                affectedRowsDto.setMessage("Account deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Account not found");
            return affectedRowsDto;
        }
    }
}
