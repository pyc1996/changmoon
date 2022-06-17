package com.ssafy.spark.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MoneyResponse")
public class RankMoneyRes {
    @ApiModelProperty(name = "업종 코드")
    private String storeCode;

    @ApiModelProperty(name = "순위")
    private int Rank;

    @ApiModelProperty(name = "결제 금액 비율(분모 : 해당 지역 전체 금액)")
    private double moneyAmount;

}