package com.online.CBuy.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.online.CBuy.pojo.Agency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account") // Đặt tên collection là "accounts"
public class Account {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;


    private String username;


    private String fullName;


    private String password;


    private String identifiNumber;


    private String phoneNumber;


    private String address;


    private Agency province;


    private Agency district;


    private Agency village;


    private ObjectId cardId;


    private String role;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updateDate;
}
