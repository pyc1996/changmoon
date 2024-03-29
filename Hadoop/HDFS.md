<aside>
💡 가장 효율적인 데이터 처리 패턴은 한 번 쓰고 여러 번 읽는 것

</aside>

# 특징(다른 분산 파일 시스템과의 차이)

- 높은 내결함성
- 저가 하드웨어에 배포
- 어플리케이션 데이터에 대한 높은 처리량 엑세스

# 가정

- 하드웨어 오류****Hardware Failure****
    - HDFS는 엄청나게 많은 구성요소가 있고, 이에 따른 실패 확률이 존재한다.
    - 이러한 실패를 막는 것이 아니라 결함을 감지하고 신속하게 자동복구하는 것이 HDFS의 핵심 아키텍처 목표이다.
    
- 스트리밍(방식의) 데이터 액세스****Streaming Data Access****
    - HDFS는 일괄처리(배치)작업을 위해 설계되었다.
    - 따라서 지연시간(대기시간)을 줄이는 것 보다 높은 처리량이 우선된다.
    - 이를 위해 데이터 셋에 대해 스트리밍 방식으로 접근한다.
    
- 대규모 데이터세트****Large Data Sets****
    - HDFS에서는 대용량 파일을 저장한다.
    - 이를 위해 높은 데이터 전송 대역폭
    - + 하나의 클러스터에서 수백개의 노드로 확장하는 기능 제공
    - 단일 인스턴스에서 수천만 개의 파일 지원

- 단순 일관성 모델****Simple Coherency Model****
    - HDFS에서는 정책상 한번 저장한 데이터는 변경할 필요가 없다.
    - 이에 따라 일관성 문제가 단순화되고, 높은 처리량이 가능해진다.
    
- ****"컴퓨팅을 옮기는 것이 데이터를 옮기는 것보다 저렴합니다"****
    - HDFS는 애플리케이션이 데이터가 있는 위치에 더 가깝게 이동할 수 있는 인터페이스를 제공
    - 응용 프로그램에서 요청한 계산은 
    응용 프로그램이 작동하는 데이터 근처에서 실행되는 경우 훨씬 더 효율적
    - 네트워크 혼잡을 최소화하고 시스템의 전체 처리량을 증가
    
- ****이기종 하드웨어 및 소프트웨어 플랫폼 간의 이식성****
    - HDFS는 한 플랫폼에서 다른 플랫폼으로 쉽게 이식할 수 있도록 설계
    

# 네임노드와 데이터노드

HDFS는 아래와 같은 일을 한다.

- 파일 시스템 네임스페이스를 노출
- 사용자 데이터를 파일에 저장
    - 내부적으로 파일은 하나 이상의 블록으로 분할
    블록은 DataNode 세트에 저장

![Untitled](https://hadoop.apache.org/docs/r1.2.1/images/hdfsarchitecture.gif)

HDFS는 마스터(네임노드*1)-슬레이브(데이터노드*n) 구조이다.

HDFS 클러스터는 네임노드로 구성된다.

- 파일 시스템 [네임스페이스](https://losskatsu.github.io/os-kernel/linux-namespace/#%EB%A6%AC%EB%88%85%EC%8A%A4---%EB%84%A4%EC%9E%84%EC%8A%A4%ED%8E%98%EC%9D%B4%EC%8A%A4namespace%EC%9D%98-%EA%B0%9C%EB%85%90) 관리
- 클라이언트의 파일 액세스 규제
    
    ```bash
    NameNode는 클라이언트가 하둡이 어떻게 실행될지 처리에 대한 
    요청을 직접 접수하게 된다.
    
    1. 클라이언트가 HDFS에 대해 파일 접근을 요청을 하고
    2. NameNode가 메타데이터를 바탕으로 대상 블록을 보유한 
       DataNode 리스트를 클라이언트에게 전달한다
    3. 이 리스트를 가지고 클라이언트는 해당 DataNode에 직접 통신을 하게 된다.
    ```
    
- 네임스페이스 작업
    - 파일 및 디렉토리 열기, 닫기, 이름 바꾸기
- DataNode와 주기적으로 상황을 주고 받으면서(**HeartBeat**)
    - DataNode 다운 감시
    - DataNode의 용량이 다 차면 다른 DataNode로 블록을 이동 가능하도록 만들어 준다.(DataNode에 대한 블록 매핑을 결정)
- 모든 HDFS 메타데이터에 대한 중재자이자 저장소
    - 하둡에서 처리되는 파일 속성 정보나 파일 시스템 정보를
    디스크가 아닌 메모리에서 직접 관리 → 빠른응답
    

# 메타데이터 MetaData

- edits
    - 파일 처리 시 로컬 파일 시스템에 생성되는 편집로그로써
    메모리 상에서 관리되고 있는 파일 시스템 이미지(fsimage)에 적용
- fsimage : 메모리 상에 관리되어 있는 메타데이터 내의 파일 시스템 이미지
    - 체크 포인트라고 불리는 타이밍에 NameNode의 로컬 파일 시스템에 생성
    
    ```bash
    - 파일명
    - 디렉터리
    - 데이터 블록 크기
    - 소유자 : 소속 그룹
    - 파일 속성
    ```
    
- DataNode와 블록 대응 정보.
    - 블록 ID와 해당 블록을 보유한 DataNode 정보
    - DataNode가 하트비트를 3초 간격으로 전송 할때,
    자신이 관리하는 블록 정보를 통지(block report)
    - 이를 바탕으,로 전체 블록 정보를 구축 및 복제수가 충분한지 판단.

# SecondaryNode

NameNode 고장을 대비한 복제품

## 구성

1. HA 클러스터 구성을 사용하지 않는 클러스터에서 사용.
2. 주기적으로  NameNode에서 fsimage와 edits를 받아서
이들을 합치고, 
HDFS에 대한 갱신 내용을 반영한 새로은 fsimage를 생성.
3. 생성한 fsimage를 NameNode로 전송
4. 체크 포인트를 실시하여
fsimage에 적용 완료한 edits를 삭제함으로써 디스크 공간 절약
5. NameNode 재시작시
fsimage에 대한 edits 적용 크기를 줄일 수 있어서
재시작 시 시간 단축
6. NameNode 장애가 발생한 경우,
메타 데이터를 보존 함으로써 완전 데이터 손실을 방지.

# 고가용성 High Availability

secondary namenode의 경우 장애 복구시 새 네임노드를 구동하기 위해서 어떤 요청도 처리하지 못한 채 30분 이상이 걸리는 경우도 있다.

이를 해결하기 위해 2버전 잉후포터는 HA를 지원한다.

# 하둡 파일시스템

# 참고

공식문서를 기준으로 블로그와 도서 참고해 이해 및 보완함

[HDFS Architecture Guide](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html)

[HDFS란? (Hadoop File System)](https://nathanh.tistory.com/91)

[하둡 완벽 가이드](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=103031150)
