package com.ssafy.spark.services;

import com.ssafy.spark.request.InfoReq;
import com.ssafy.spark.response.GenderAgeRes;
import com.ssafy.spark.response.RankCountRes;
import com.ssafy.spark.response.RankMoneyRes;
import com.ssafy.spark.response.TimeLineRes;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

//    private String path = "C:\\Users\\SSAFY\\Desktop\\SSAFY\\프로젝트2\\DummyData\\TestData.csv";

    private String path = "hdfs://j6e104.p.ssafy.io:9000/data/card_data.csv";
    SparkSession sparkSession;


    public DataServiceImpl() {
        this.sparkSession = SparkSession.builder().appName("dataRefine").master("local[*]").getOrCreate();
    }

    @Override
    public List<RankMoneyRes> findMoneyRank(InfoReq infoReq) {
        Dataset<Row> dataset = sparkSession.read().option("header", "true")
                .csv(path).cache();

        Dataset<Row> df = getRowDataset(infoReq, dataset);

        double totalMoney = Double.parseDouble(df.agg(functions.sum("pay_amount")).collectAsList().get(0).get(0).toString());
        List<Row> countList = df.groupBy("code").agg(functions.sum("pay_amount").as("amount")).sort(new Column("amount").desc()).collectAsList();

        List<RankMoneyRes> moneyResList = new ArrayList<>();
        int end = Math.min(countList.size(), 15);
        for (int i = 0; i < end; i++) {
            Row row = countList.get(i);
            RankMoneyRes rankMoneyRes = new RankMoneyRes();
            rankMoneyRes.setStoreCode(row.get(0).toString());
            rankMoneyRes.setRank(i + 1);
            rankMoneyRes.setMoneyAmount(Double.parseDouble(row.get(1).toString()) / totalMoney);
            moneyResList.add(rankMoneyRes);
        }

        return moneyResList;
    }

    @Override
    public List<RankCountRes> findCountRank(InfoReq infoReq) {
        Dataset<Row> dataset = sparkSession.read().option("header", "true")
                .csv(path).cache();
        Dataset<Row> df = getRowDataset(infoReq, dataset);

        double totalCount = df.count();
        List<Row> countList = df.groupBy("code").count().sort(new Column("count").desc()).collectAsList();

        List<RankCountRes> rankCountResList = new ArrayList<>();
        int end = Math.min(countList.size(), 15);
        for (int i = 0; i < end; i++) {
            Row row = countList.get(i);
            RankCountRes rankCountRes = new RankCountRes();
            rankCountRes.setStoreCode(row.get(0).toString());
            rankCountRes.setRank(i + 1);
            rankCountRes.setCount(row.get(1).toString());
            rankCountRes.setCountAmount(Double.parseDouble(row.get(1).toString()) / totalCount);
            rankCountResList.add(rankCountRes);
        }

        return rankCountResList;
    }


    @Override
    public List<GenderAgeRes> findByGenderAndAge(InfoReq infoReq) {
        Dataset<Row> dataset = sparkSession.read().option("header", "true")
                .csv(path).cache();
        Dataset<Row> df = getRowDataset(infoReq, dataset);

        df = df.filter(df.col("code").equalTo(infoReq.getStoreCode()));

        List<GenderAgeRes> result = new ArrayList<>();

        Dataset<Row> male = df.filter(df.col("gender").contains(1));
        Dataset<Row> female = df.filter(df.col("gender").contains(2));

        male.groupBy("age").agg(functions.sum("pay_amount")).show();

        List<Row> maleList = male.groupBy("age").count().collectAsList();
        List<Row> femaleList = female.groupBy("age").count().collectAsList();

        for (Row row : maleList) {
            GenderAgeRes man = new GenderAgeRes();
            man.setGender(1);
            man.setAge(row.getString(0));
            man.setCount(row.getLong(1));
            result.add(man);
        }

        for (Row row : femaleList) {
            GenderAgeRes woman = new GenderAgeRes();
            woman.setGender(2);
            woman.setAge(row.getString(0));
            woman.setCount(row.getLong(1));
            result.add(woman);
        }

        return result;
    }

    @Override
    public List<TimeLineRes> findByTimeLine(InfoReq infoReq) {
        Dataset<Row> dataset = sparkSession.read().option("header", "true")
                .csv(path).cache();
        Dataset<Row> df = getRowDataset(infoReq, dataset);
        df = df.filter(df.col("code").equalTo(infoReq.getStoreCode()));

        List<Row> tmp = df.groupBy("approve_time").agg(functions.sum("pay_amount")).orderBy(df.col("approve_time")).collectAsList();
        List<TimeLineRes> result = new ArrayList<>();
        for (int i = 0; i < tmp.size(); i++) {
            int time = Integer.parseInt(tmp.get(i).getString(0));
            long cnt = (long) tmp.get(i).getDouble(1);

            TimeLineRes timeLineRes = new TimeLineRes();

            timeLineRes.setTime(time);
            timeLineRes.setSum(cnt);

            result.add(timeLineRes);
        }

        return result;
    }


    private Dataset<Row> getRowDataset(InfoReq infoReq, Dataset<Row> df) {

        if (infoReq.isSiDoCheck()) {
            df = df.filter(df.col("sido").contains(infoReq.getSiDo()));
        }
        if (infoReq.isGuGunCheck()) {
            df = df.filter(df.col("gugun").contains(infoReq.getGuGun()));
        }
        if (infoReq.isDongCheck()) {
            df = df.filter(df.col("dong").contains(infoReq.getDong()));
        }
        return df;
    }
}
