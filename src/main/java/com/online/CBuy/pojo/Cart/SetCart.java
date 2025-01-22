package com.online.CBuy.pojo.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetCart implements Serializable {

    private ObjectId productId;

    private Double quantity;

    private Double price;

    private Double total;

    private ObjectId userId;

    private int status;
}
