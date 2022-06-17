package com.ssafy.spark.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InfoRequest")
public class InfoReq {
    @ApiModelProperty(name="성별_남:1/여:2/둘다:3")    //중복가능
    int gender;

    @ApiModelProperty(name="연령 최소")
    int minAge;

    @ApiModelProperty(name="연령 최대")
    int maxAge;

    @ApiModelProperty(name="시/도 유무")
    boolean siDoCheck;

    @ApiModelProperty(name="시/도")
    String siDo;

    @ApiModelProperty(name="구/군 유무")
    boolean guGunCheck;

    @ApiModelProperty(name="구/군")
    String guGun;

    @ApiModelProperty(name="동 유무")
    boolean dongCheck;

    @ApiModelProperty(name="동/읍/면")
    String dong;

    @ApiModelProperty(name="업종 코드")
    String storeCode;

}
