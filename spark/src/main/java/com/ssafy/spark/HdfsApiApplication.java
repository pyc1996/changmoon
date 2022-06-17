package com.ssafy.spark;

import com.ssafy.spark.services.DataRead;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class HdfsApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(HdfsApiApplication.class, args);
	}

}
