
# 설치

### 다운로드-압출풀기-폴더이름 변경

```bash
# ubuntu계정으로 진행하기 때문에 /home/ubuntu 위치에서 시작함
wget [https://dlcdn.apache.org/hbase/2.4.11/hbase-2.4.11-bin.tar.gz](https://dlcdn.apache.org/hbase/2.4.11/hbase-2.4.11-bin.tar.gz)
tar zxvf [hbase-2.4.11-bin.tar.gz](https://dlcdn.apache.org/hbase/2.4.11/hbase-2.4.11-bin.tar.gz)
mv [hbase-2.4.11-bin](https://dlcdn.apache.org/hbase/2.4.11/hbase-2.4.11-bin.tar.gz) hbase
```

### 환경변수 등록(.bashrc파일)

```bash
# 아래 두 줄을 추가한다
# HBASE_HOME에는 위에서 
export HBASE_HOME=/home/ubuntu/hbase
export PATH=$PATH:$HBASE_HOME/bin
```

### HBase 설정($HBASE_HOME/conf/)

**hbase-env.sh**

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
# export HBASE_MANAGES_ZK=true
```

- 설명
    
    HBASE_MANAGES_ZK
    
    - HBase는 자체적으로 zookeeper를 내장하고 있다.
    - HBASE_MANAGES_ZK는 내장 zookeeper 사용여부를 결정한다.
    - 기본값은 true이며, 내장 주키퍼 사용시 외부 주키퍼는 중단시켜야한다.(충돌발생함)
    

**hbase-site.xml**

```xml
<configuration>
  <property>
    <name>hbase.rootdir</name>
    <value>hdfs://hadoop.ip:9000/hbase</value>
  </property>
  <property>
    <name>hbase.cluster.distributed</name>
    <value>false</value>
  </property>
  <property>
    <name>hbase.zookeeper.quorum</name>
    <value>localhost</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.clientPort</name>
    <value>2181</value>
  </property>
</configuration>
```

- 설명
    
    hbase.rootdir
    
    - hbase의 기본 디렉토리 설정
    
    hbase.cluster.distributed
    
    - hbase의 분산 여부
    - 여기서는 standalone모드라서 false
    
    hbase.zookeeper.quorum
    
    - 쿼럼 세팅 설정
    
    hbase.zookeeper.property.clientPort
    
    - 클라이언트 포트

# hbase table column name

진한색은 colum family

| 회원_시도명 | member_sido |
| --- | --- |
| 성별 | gender |
| 연령 | age |
| 날짜 | date |
| 매출년월일 | pay_date |
| 승인시간대 | approve_time |
| 가맹점 정보 | shop |
| 가맹점_시도명 | shop_sido |
| 가맹점_시군구명 | shop_gugun |
| 가맹점_읍면동면 | shop_dong |
| 가맹점업종코드 | category |
| 결제 정보 | payments |
| 결제금액 | pay_amount |

# Hbase 다루기

- 참고
    
    [](https://cdmana.com/2022/03/202203230149337041.html)
    

### 테이블 생성(hbase shell)

```bash
# 테이블 생성
# card테이블에 컬렘 패밀리 member,date,shop,payments
create "card", "member", "date","shop","payments"
# 컬럼 패밀리 여러개 생성이 안된다면 하나씩 추가한다
create "card", "member"
alter "card", "date"
alter "card", "shop"
alter "card", "payments"
```

### 데이터 import(hbase shell x)

```bash
# 사전에 정제한(필요없는 컬럼 삭제, 인코딩 등) csv데이터를 import
# HBASE_ROW_KEY의 경우 가장 앞의 컬럼이 row키가 된다.
## 여기서는 gender가 키가 됐다.
hbase org.apache.hadoop.hbase.mapreduce.ImportTsv \
-Dimporttsv.separator=',' \
-Dimporttsv.columns=HBASE_ROW_KEY,member:gender,\
member:age,date:pay_date,date:approve_time,\
shop:sido,shop:gugun,shop:dong,shop:code,\
payments:pay_amount \
card /data/card_data.csv
```

- mysql에서 hbase로 import하는 경우
    
    ```bash
    # sqoop을 이용함.
    # row키로 삼을 데이터가 필요해서 mysql의 id를 이용하기 위해 사용했었다.
    # 최종적으로는 사용하지 않음
    sqoop import \
    --connect jdbc:mysql://localhost:3306/bigdata \
    --driver com.mysql.jdbc.Driver\
    --username bigdata\
    --table card \
    --hbase-table card\
    --column-family member:member_sido, member:member_gugun, member:enterprise,member:gender,member:age,date:pay_date,date:approve_time,shop:shop_sido,shop:shop_gugun,shop:shop_dong,shop:category,payments:pay_amount,payments:pay_number,payments:member_number \
    --hbase-row-key id \
    -m 1\
    --password=ssafy
    ```
    
    [https://earthconquest.tistory.com/241](https://earthconquest.tistory.com/241)
    
    ---
    

### 데이터 확인(hbase shell)

```bash
scan 'card'
```

# Mysql 데이터 적재

```jsx
데이터 적재

LOAD DATA INFILE '/var/lib/mysql-files/encoding/card-data_201911.csv'
INTO TABLE card
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@'회원_시도명',@'회원_시군구명',@'개인기업구분', @'성별',@'연령',@'매출년월일',@'승인시간대1',@'가맹점_시도명',@'가맹점_시군구명',@'가맹점_읍면동명',@'가맹점업종코드',@'결제금액',@'결제건수',@'회원수')
SET member_sido=@'회원_시도명',
    member_gugun=@'회원_시군구명' ,
    enterprise=@'개인기업구분',
    gender=@'성별' ,
    age=@'연령' ,
    pay_date=@'매출년월일',
    approve_time=@'승인시간대1' ,
    shop_sido=@'가맹점_시도명' ,
    shop_gugun=@'가맹점_시군구명' ,
    shop_dong=@'가맹점_읍면동명'  ,
    category=@'가맹점업종코드' ,
    pay_amount=@'결제금액' ,
    pay_number=@'결제건수' ,
    member_number=@'회원수';
```
