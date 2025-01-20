package com.online.CBuy.document;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cart")
public class Cart {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private ObjectId productId;

    private Double quantity;

    private Double price;

    private Double total;

    private ObjectId userId;

    private int status;
}
