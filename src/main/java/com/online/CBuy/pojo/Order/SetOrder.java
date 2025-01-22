package com.online.CBuy.pojo.Order;

import com.online.CBuy.document.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetOrder implements Serializable {
    private ObjectId userId;

    private String address;

    private int status;

    private List<ObjectId> listProductId;

    private Double total;

    private Double cost;

    private Double paid;

    private String note;

    private List<ObjectId> KMId;

    private Double priceDiscount;
}
