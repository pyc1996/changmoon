package com.ssafy.spark.services;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

@Component
public class DataRead {

    private DataRead instance;

//    gender,age,pay_date,approve_time,sido,gugun,dong,code,pay_amount
    Dataset<Row> data;

    public DataRead getInstance(){
        if(instance==null){
            instance = new DataRead();

            instance.data = SparkSession.builder().appName("dataRefine").master("local[*]").getOrCreate().read().option("header", "true")
//                    .csv("hdfs://j6e104.p.ssafy.io:9000/data/card_data.csv").cache();
                    .csv("hdfs://j6e104.p.ssafy.io:9000/data/card_data.csv").cache();

        }
        return instance;
    }

}
