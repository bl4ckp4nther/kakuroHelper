package com.blackpanther.kakuroHelper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakuroDto {
    private Integer target;
    private Integer digits;
    private Integer noOfResults;
    private List<String> combinations;
}
