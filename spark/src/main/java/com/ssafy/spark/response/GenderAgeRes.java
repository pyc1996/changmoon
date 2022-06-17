package com.ssafy.spark.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GenderAgeRes")
public class GenderAgeRes {

    @ApiModelProperty(name="성별")
    int gender;

    @ApiModelProperty(name="나이")
    String age;

    @ApiModelProperty(name="수량")
    long count;

    @Override
    public String toString() {
        return "GenderAgeRes{" +
                "gender=" + gender +
                ", age='" + age + '\'' +
                ", count=" + count +
                '}';
    }
}
