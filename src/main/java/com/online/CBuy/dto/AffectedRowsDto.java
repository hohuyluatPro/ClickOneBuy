package com.online.CBuy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectedRowsDto {
    private int affectedRows = 0;
    private String message;

    public AffectedRowsDto(int affected){
        this.affectedRows = affected;
        this.message = "";
    }
}
