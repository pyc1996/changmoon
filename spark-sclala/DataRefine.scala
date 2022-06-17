import org.apache.hadoop.shaded.org.eclipse.jetty.websocket.common.frames.DataFrame
import org.apache.spark.sql
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, regexp_replace}

import scala.collection.mutable.ArrayBuffer

object DataRefine {


  def main(args: Array[String]): Unit = {


    val spark = SparkSession.builder().appName("dataRefine").master("local[*]").getOrCreate()


    // 성별,연령,매출년월일,승인시간대1,가맹점_시도명,가맹점_시군구명,가맹점_읍면동명,가맹점업종코드,결제금액
    val csv_list = Array("09", "10", "11", "12")
    val remove_row = Array("회원_시도명", "회원_시군구명", "개인기업구분", "결제건수", "회원수")
    val df_list = ArrayBuffer[sql.DataFrame]()
    for (a <- csv_list) {
      var df = spark.read.option("header", "true").csv("C:\\SSAFY\\2nd\\special_pjt\\card-data\\card-data_2019" + a + ".csv").cache()
      df = df.filter(df("개인기업구분") =!= "기업")
      df = df.filter(df("성별") =!= "0.기타")

      for (b <- remove_row) {
        df = df.drop(b)
      }
      df.persist()

      df_list.append(df)
    }

    var df_all = df_list(0)


    for (i <- 1 to 3) {
      println(i)
      df_all = df_all.union(df_list(i))
    }

    val df_all_renamed = df_all.withColumnRenamed("성별", "gender")
      .withColumnRenamed("연령", "age")
      .withColumnRenamed("매출년월일", "pay_date")
      .withColumnRenamed("승인시간대1", "approve_time")
      .withColumnRenamed("가맹점_시도명","sido")
      .withColumnRenamed("가맹점_시군구명","gugun")
      .withColumnRenamed("가맹점_읍면동명","dong")
      .withColumnRenamed("가맹점업종코드","code")
      .withColumnRenamed("결제금액","pay_amount")



    df_all_renamed.coalesce(1).write.option("header","true").csv("C:\\SSAFY\\2nd\\special_pjt\\card-data\\merged_Data.csv")



  }

}
