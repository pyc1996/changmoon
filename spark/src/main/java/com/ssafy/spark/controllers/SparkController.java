package com.ssafy.spark.controllers;

import com.ssafy.spark.request.InfoReq;
import com.ssafy.spark.response.GenderAgeRes;
import com.ssafy.spark.response.RankCountRes;
import com.ssafy.spark.response.RankMoneyRes;
import com.ssafy.spark.response.TimeLineRes;
import com.ssafy.spark.services.DataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//SwaggerUI : http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = "*")
@RestController
@Api(tags = "HDFS API")
@RequestMapping(value = "/api/hdfs")
public class SparkController {

  @Autowired
  private DataService dataService;

  @PostMapping("/search/money")  //spark 예제 실험 controller
  public ResponseEntity<List<RankMoneyRes>> searchMoney(@RequestBody InfoReq infoReq) throws Exception{
    List<RankMoneyRes> rankMoneyResList = dataService.findMoneyRank(infoReq);
    return new ResponseEntity<List<RankMoneyRes>>(rankMoneyResList, HttpStatus.CREATED);
  }

  @PostMapping("/search/count")  //spark 예제 실험 controller
  public ResponseEntity<List<RankCountRes>> searchCount(@RequestBody InfoReq infoReq) throws Exception{
    List<RankCountRes> rankCountResList = dataService.findCountRank(infoReq);
    return new ResponseEntity<List<RankCountRes>>(rankCountResList, HttpStatus.CREATED);
  }

  @PostMapping("/search/gender/age")  //spark 예제 실험 controller
  public ResponseEntity<List<GenderAgeRes>> searchGenderAge(@RequestBody InfoReq infoReq) throws Exception{
    List<GenderAgeRes> genderAgeResList = dataService.findByGenderAndAge(infoReq);
    return new ResponseEntity<List<GenderAgeRes>>(genderAgeResList, HttpStatus.CREATED);
  }

  @PostMapping("/search/timeline")  //spark 예제 실험 controller
  public ResponseEntity<List<TimeLineRes>> searchTimeline(@RequestBody InfoReq infoReq) throws Exception{
    List<TimeLineRes> timeLineResList = dataService.findByTimeLine(infoReq);
    return new ResponseEntity<List<TimeLineRes>>(timeLineResList, HttpStatus.CREATED);
  }

}
