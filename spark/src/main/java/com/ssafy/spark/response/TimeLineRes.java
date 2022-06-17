package com.ssafy.spark.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("TimelineResponse")
public class TimeLineRes {

    @ApiModelProperty(name="시간대")
    int time;

    @ApiModelProperty(name="총합")
    long sum;

}
