package com.ssafy.spark.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RankResponse")
public class RankCountRes {
    @ApiModelProperty(name="업종 코드")
    String storeCode;

    @ApiModelProperty(name="순위")
    int Rank;

    @ApiModelProperty(name="결제 수량")
    String count;

    @ApiModelProperty(name = "결제 수량 비율(분모 : 해당 지역 전체 수량)")
    private double countAmount;
}