package com.online.CBuy.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agency implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
}
