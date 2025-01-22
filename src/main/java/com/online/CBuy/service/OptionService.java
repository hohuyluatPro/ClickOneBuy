package com.online.CBuy.service;

import com.online.CBuy.document.Option;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Option.PostOption;
import com.online.CBuy.pojo.Option.PutOption;
import com.online.CBuy.repository.OptionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public AffectedRowsDto postOption(PostOption postOption){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Date date = new Date();
        if(Objects.nonNull(postOption) && Objects.nonNull(postOption.getNameOption())){
            Optional<Option> option = optionRepository.findByName(postOption.getNameOption());
            if(option.isEmpty()){
                Option option1 = new Option(postOption.getNameOption(), postOption.getValue());
                try {
                    option1.setCreatedDate(date);
                    option1.setUpdateDate(date);
                    optionRepository.save(option1);
                    affectedRowsDto.setAffectedRows(1);
                    affectedRowsDto.setMessage("success");
                } catch (Exception e){
                    affectedRowsDto.setMessage("save false");
                }
            } else {
                affectedRowsDto.setMessage("Already exists");
            }
        } else {
            affectedRowsDto.setMessage("null postOption");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putOption(String id, PutOption putOption){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Date date = new Date();
        Option option = optionRepository.findOneById(new ObjectId(id));
        if(Objects.nonNull(option)){
            if(Objects.nonNull(putOption.getNameOption())){
                option.setName(putOption.getNameOption());
            }
            if(Objects.nonNull(putOption.getValue())){
                option.setValue(putOption.getValue());
            }
            try {
                option.setUpdateDate(date);
                optionRepository.save(option);
                affectedRowsDto.setAffectedRows(1);
                affectedRowsDto.setMessage("success");
            } catch (Exception e){
                affectedRowsDto.setMessage("save false");
            }
        } else {
            affectedRowsDto.setMessage("not found");
        }
        return  affectedRowsDto;
    }

    public List<Option> getListAllOption(){
        return optionRepository.findAll();
    }

    public Option getOption(String id){
        return optionRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteOption(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Option> existingOption = optionRepository.findById(id);
        if (existingOption.isPresent()) {
            try {
                optionRepository.deleteById(id);
                affectedRowsDto.setMessage("Option deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Option not found");
            return affectedRowsDto;
        }
    }
}
