package com.online.CBuy.pojo.Product;

import com.online.CBuy.document.Option;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetProduct implements Serializable {
    private ObjectId sellerId;

    private String name;

    private String description;

    private List<ObjectId> listOption;

    private Double quantity;

    private Double price;

    private List<ObjectId> listKM;

    private List<Object> fileImage;
}
