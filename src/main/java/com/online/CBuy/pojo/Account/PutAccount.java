package com.online.CBuy.pojo.Account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.online.CBuy.pojo.Agency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutAccount implements Serializable {

    private ObjectId id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("identifiNumber")
    private String identifiNumber;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("province")
    private Agency province;

    @JsonProperty("district")
    private Agency district;

    @JsonProperty("village")
    private Agency village;

    @JsonProperty("cardId")
    private ObjectId cardId;

    @JsonProperty("role")
    private String role;
}
