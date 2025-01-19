package com.online.CBuy.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.online.CBuy.document.Account;
import com.online.CBuy.pojo.Agency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountDto {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @JsonProperty("username")
    private String username;

    private String identifiNumber;

    private String phoneNumber;

    private String address;

    private Agency province;

    private Agency district;

    private Agency village;

    private ObjectId cardId;

    @JsonProperty("role")
    private String role;

    public GetAccountDto(Account account){
        this.username = account.getUsername();
        this.identifiNumber = account.getIdentifiNumber();
        this.phoneNumber = account.getPhoneNumber();
        this.address = account.getAddress();
        this.province = account.getProvince();
        this.district = account.getDistrict();
        this.village = account.getVillage();
        this.cardId = account.getCardId();
        this.role = account.getRole();
    }
}
