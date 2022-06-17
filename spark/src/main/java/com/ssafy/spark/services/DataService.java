package com.ssafy.spark.services;

import com.ssafy.spark.request.InfoReq;
import com.ssafy.spark.response.GenderAgeRes;
import com.ssafy.spark.response.RankCountRes;
import com.ssafy.spark.response.RankMoneyRes;
import com.ssafy.spark.response.TimeLineRes;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DataService {

//    해당 지역 내 소분류 순위(금액 총합)
      public List<RankMoneyRes> findMoneyRank(InfoReq infoReq);
//    해당 지역 내 소분류순위 (횟수별)
      public List<RankCountRes> findCountRank(InfoReq infoReq);



//    지역 내 선택한 데이터와 전체의 비율
// => 고른 지역내 전체 데이터 숫자(금액으로?) / 내가 선택한 조건에 해당하는 데이터 숫자(금액?), 업종별인지? -> 보류
//      public double findByCondition(InfoReq infoReq);


//    각 연령별 남녀 비율 (성별, 나이, 건수) -> response
      public List<GenderAgeRes> findByGenderAndAge(InfoReq infoReq);

//    시간대별 매출액
      public List<TimeLineRes> findByTimeLine(InfoReq infoReq);
}

/*
* gender,age,pay_date,approve_time,sido,gugun,dong,code,pay_amount
1.남성,4.40대,190921,13,강원,강릉시,강동면,2004,26000
1.남성,4.40대,190926,06,강원,강릉시,강동면,2104,9900
1.남성,4.40대,190926,07,강원,강릉시,강동면,2104,25200
1.남성,4.40대,190926,11,강원,강릉시,강동면,2104,53600
1.남성,4.40대,190930,06,강원,강릉시,강동면,2104,16500
1.남성,4.40대,190929,17,강원,강릉시,강동면,2104,90000
1.남성,4.40대,190930,07,강원,강릉시,강동면,2104,19800
1.남성,4.40대,190930,11,강원,강릉시,강동면,2104,67750
1.남성,4.40대,190925,07,강원,강릉시,강동면,2104,16500
1.남성,4.40대,190924,06,강원,강릉시,강동면,2104,16500
1.남성,4.40대,190927,07,강원,강릉시,강동면,2104,23100
1.남성,4.40대,190924,07,강원,강릉시,강동면,2104,13200
1.남성,4.40대,190927,06,강원,강릉시,강동면,2104,23100
1.남성,5.50대,190905,11,강원,강릉시,강동면,2104,160500
1.남성,4.40대,190911,07,강원,강릉시,강동면,2104,31200
1.남성,4.40대,190906,06,강원,강릉시,강동면,2104,9900
1.남성,4.40대,190902,11,강원,강릉시,강동면,2104,90500
1.남성,2.20대,190929,11,강원,강릉시,강동면,2104,27800
1.남성,4.40대,190920,06,강원,강릉시,강동면,2104,9900
1.남성,4.40대,190917,07,강원,강릉시,강동면,2104,37800
1.남성,4.40대,190910,07,강원,강릉시,강동면,2104,29700
1.남성,4.40대,190920,07,강원,강릉시,강동면,2104,13200
1.남성,2.20대,190930,07,강원,강릉시,강동면,2104,9900
1.남성,4.40대,190906,12,강원,강릉시,강동면,2104,60600
1.남성,4.40대,190918,06,강원,강릉시,강동면,2104,9900

* */