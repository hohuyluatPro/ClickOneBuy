package com.online.CBuy.pojo.Rate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetRate implements Serializable {

    private ObjectId userId;

    private ObjectId productId;

    private Double score;

    private List<Object> fileImage;

    private String comment;
}
