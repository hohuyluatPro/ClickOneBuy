package com.online.CBuy.document;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private ObjectId userId;

    private String address;

    private int status;

    private List<Cart> listOrder;

    private Double total;

    private Double paid;

    private String note;

    private ObjectId KMId;

    private Double priceDiscount;

}
