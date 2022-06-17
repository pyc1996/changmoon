<template>
  <!-- 선택 메뉴 -->
  <div style="margin-top: 30px; width: 95%; margin-left: 35px">
    <md-card>
      <md-card-header data-background-color="green">
        <h4 class="title">통계데이터</h4>
      </md-card-header>
      <md-card-content>
        <md-table style="text-align: center">
          <md-table-row>
            <md-table-cell> 성별 </md-table-cell>
            <md-table-cell colspan="5">
              <div style="margin-left: 600px">
                <md-checkbox v-model="male">남</md-checkbox>
                <md-checkbox v-model="female">여</md-checkbox>
              </div>
            </md-table-cell>
          </md-table-row>
          <md-table-row rowspan="2">
            <md-table-cell> 연령별 </md-table-cell>
            <md-table-cell colspan="5">
              <div class="container">
                <div
                  id="my-slider"
                  :se-min="minThreshold"
                  :se-step="step"
                  :se-min-value="min"
                  :se-max-value="max"
                  :se-max="maxThreshold"
                  class="slider"
                >
                  <div class="slider-touch-left">
                    <span></span>
                  </div>
                  <div class="slider-touch-right">
                    <span></span>
                  </div>
                  <div class="slider-line">
                    <span></span>
                  </div>
                </div>
              </div>
            </md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell colspan="4">
              <div style="margin-left: 740px">
                {{ minVal }}대에서 {{ maxVal }}대 까지
              </div>
            </md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell> 지역별 </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedSido"
                  name="sido"
                  id="sido"
                  placeholder="시,도"
                >
                  <md-option v-for="item in sido" :key="item" :value="item">
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedGugun"
                  name="gugun"
                  id="gugun"
                  placeholder="구,군"
                >
                  <md-option v-for="item in gugun" :key="item" :value="item">
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedDong"
                  name="dong"
                  id="dong"
                  placeholder="동,읍,면"
                >
                  <md-option v-for="item in dong" :key="item" :value="item">
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell> 업종별 </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedBigSector"
                  name="bigSector"
                  id="bigSector"
                  placeholder="대분류"
                >
                  <md-option
                    v-for="item in bigSector"
                    :key="item"
                    :value="item"
                  >
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedMediumSector"
                  name="mediumSector"
                  id="mediumSector"
                  placeholder="중분류"
                >
                  <md-option
                    v-for="item in mediumSector"
                    :key="item"
                    :value="item"
                  >
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
            <md-table-cell>
              <md-field>
                <md-select
                  v-model="selectedSmallSector"
                  name="smallSector"
                  id="smallSector"
                  placeholder="소분류"
                >
                  <md-option
                    v-for="item in smallSector"
                    :key="item"
                    :value="item"
                  >
                    {{ item }}
                  </md-option>
                </md-select>
              </md-field>
            </md-table-cell>
          </md-table-row>
        </md-table>
        <div style="margin-top: 20px; margin-bottom: 50px; text-align: center">
          <b-button type="submit" variant="success" @click="search"
            >검색</b-button
          >
        </div>
        <div
          v-if="loadingPageState"
          style="margin-top: 10px; margin-bottom: 50px; text-align: center"
        >
          <div class="loading-message"><b>잠시 기다려 주세요...</b></div>
        </div>

        <!-- 선택 메뉴 끝 -->
        <!-- 차트 시작 -->
        <div id="typography">
          <div class="row">
            <div class="box1">
              <div class="select-box">
                <div class="chartName">
                  <b> 지역 내 업종 순위(거래 횟수)</b>
                </div>
              </div>
              <div class="box-inner">
                <div class="center-box-word">
                  <canvas id="myChart" width="400" height="400"> </canvas>
                </div>
              </div>
            </div>
            <div class="center-box">
              <div class="center-box-word">
                <h2 class="center-box-word2"></h2>
              </div>
            </div>
            <div class="box2">
              <div class="select-box">
                <div class="chartName">
                  <b>지역 내 업종 순위(금액별 비율 %)</b>
                </div>
              </div>
              <div class="box-inner">
                <div class="center-box-word">
                  <canvas id="myChart2" width="400" height="400"> </canvas>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="typography2">
          <div class="row">
            <div class="box1">
              <div class="select-box">
                <div class="chartName"><b>조건 내 시간대별 거래 금액</b></div>
              </div>
              <div class="box-inner">
                <div class="center-box-word">
                  <canvas id="myChart3" width="400" height="400"> </canvas>
                </div>
              </div>
            </div>
            <div class="center-box">
              <div class="center-box-word">
                <h2 class="center-box-word2"></h2>
              </div>
            </div>
            <div class="box2">
              <div class="select-box">
                <div class="chartName"><b> 각 연령대 거래 횟수(성별)</b></div>
              </div>
              <div class="box-inner">
                <div class="center-box-word">
                  <canvas id="myChart4" width="400" height="400"> </canvas>
                </div>
              </div>
            </div>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);
import ZbRangeSlider from "./ZbRangeSlider";
import sigungudata, { sigungu1 } from "@/common/sigungudata";
import axios2 from "../common/http-common-spark";
import sectordata, { sectorCode1 } from "@/common/sectordata";

export default {
  name: "VueChartJS",
  components: {},
  props: {
    minThreshold: {
      type: Number,
      default: 10,
    },
    maxThreshold: {
      type: Number,
      default: 90,
    },
    step: {
      type: Number,
      default: 10,
    },
    min: {
      type: Number,
      required: true,
    },
    max: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      datacollection: null,
      sido: "",
      selectedSido: "",
      gugun: "",
      selectedGugun: "",
      dong: "",
      selectedDong: "",
      smallSector: "",
      smallSectorAll: "",
      selectedSmallSector: "",
      mediumSector: "",
      mediumSectorAll: "",
      selectedMediumSector: "",
      selectedMediumSectorNum: "",
      bigSector: "",
      selectedBigSector: "",
      selectedBigSectorNum: "",
      sectorCode: "",
      smallSectorCodeAll: "",
      selectedSmallSectorCode: "",
      maxVal: 100,
      minVal: 10,
      instance: undefined,
      male: "false",
      female: "false",
      gugunList: "",
      selected: "select1",
      selected2: "select2",
      dropboxData: [],
      myChart1: null,
      myChart2: null,
      myChart3: null,
      myChart4: null,
      chartData1: "",
      chartData2: "",
      chartData3: "",
      chartData4: "",
      loading1: false,
      loading2: false,
      loading3: false,
      loading4: false,
      loadingPageState: false,
      isLogin: "",
    };
  },
  created() {
    this.init();
    this.fillData();
    (this.selected = "select1"),
      (this.selected2 = "select2"),
      (this.dropboxData = [
        { text: "text1", value: "hello1" },
        { text: "text2", value: "hello222" },
      ]);
    this.isJwt();
  },
  watch: {
    selectedSido() {
      this.gugun = sigungu1[this.selectedSido];
    },

    selectedGugun() {
      this.dong = sigungu1[this.selectedSido + "->" + this.selectedGugun];
    },

    selectedBigSector() {
      var num = 0;
      if (this.selectedBigSector === "의생활") num = 0;
      else if (this.selectedBigSector === "식생활") num = 1;
      else if (this.selectedBigSector === "주거생활") num = 2;
      else if (this.selectedBigSector === "유흥") num = 3;
      else if (this.selectedBigSector === "내구재") num = 4;
      else if (this.selectedBigSector === "자동차") num = 5;
      else if (this.selectedBigSector === "유통") num = 6;
      else if (this.selectedBigSector === "여행") num = 7;
      else if (this.selectedBigSector === "레포츠/문화/취미") num = 8;
      else if (this.selectedBigSector === "의료/미용") num = 9;
      else if (this.selectedBigSector === "교육") num = 10;
      else if (this.selectedBigSector === "서비스") num = 11;
      else if (this.selectedBigSector === "기타") num = 12;
      this.selectedBigSectorNum = num;
      this.selectedMediumSector = "중분류";
      this.selectedSmallSector = "소분류";
      this.getMediumSector(num);
    },

    selectedMediumSector() {
      var num = 0;
      if (
        this.selectedMediumSector === "의류" ||
        this.selectedMediumSector === "휴게음식점" ||
        this.selectedMediumSector === "연료판매" ||
        this.selectedMediumSector === "유흥" ||
        this.selectedMediumSector === "가구" ||
        this.selectedMediumSector === "운송기구판매" ||
        this.selectedMediumSector === "대형유통" ||
        this.selectedMediumSector === "숙박" ||
        this.selectedMediumSector === "레저/스포츠" ||
        this.selectedMediumSector === "의료기관/제약" ||
        this.selectedMediumSector === "학교" ||
        this.selectedMediumSector === "보험" ||
        this.selectedMediumSector === "기타 제조/도매"
      )
        num = 0;
      else if (
        this.selectedMediumSector === "직물/침구류" ||
        this.selectedMediumSector === "일반음식점" ||
        this.selectedMediumSector === "건축관련업" ||
        this.selectedMediumSector === "전자제품" ||
        this.selectedMediumSector === "주유" ||
        this.selectedMediumSector === "소매" ||
        this.selectedMediumSector === "운송수단" ||
        this.selectedMediumSector === "문화/취미" ||
        this.selectedMediumSector === "미용" ||
        this.selectedMediumSector === "유아교육기관" ||
        this.selectedMediumSector === "용역서비스" ||
        this.selectedMediumSector === "RF교통기관"
      )
        num = 1;
      else if (
        this.selectedMediumSector === "신변잡화" ||
        this.selectedMediumSector === "음료식품" ||
        this.selectedMediumSector === "주방용품" ||
        this.selectedMediumSector === "자동차 정비/유지" ||
        this.selectedMediumSector === "전자상거래" ||
        this.selectedMediumSector === "관광여행" ||
        this.selectedMediumSector === "학원" ||
        this.selectedMediumSector === "수리서비스" ||
        this.selectedMediumSector === "기타"
      )
        num = 2;
      else if (
        this.selectedMediumSector === "사무기기" ||
        this.selectedMediumSector === "통신판매" ||
        this.selectedMediumSector === "학습자재" ||
        this.selectedMediumSector === "가례서비스"
      )
        num = 3;
      else if (
        this.selectedMediumSector === "악기" ||
        this.selectedMediumSector === "기타 유통" ||
        this.selectedMediumSector === "요금"
      )
        num = 4;
      else if (
        this.selectedMediumSector === "비영리유통" ||
        this.selectedMediumSector === "회비"
      )
        num = 5;

      this.selectedMediumSectorNum = num;
      this.selectedSmallSector = "소분류";
      this.getSmallSector(num);
    },

    selectedSmallSector() {
      let target = 0;
      for (let i = 0; i < this.smallSector.length; i++) {
        if (this.smallSector[i] === this.selectedSmallSector) {
          target = i;
          break;
        }
      }

      this.selectedSmallSectorCode =
        this.smallSectorCodeAll[this.selectedBigSectorNum][
          this.selectedMediumSectorNum
        ][target];
    },

    loading1() {
      this.changeLoadingState();
    },
    loading2() {
      this.changeLoadingState();
    },
    loading3() {
      this.changeLoadingState();
    },
    loading4() {
      this.changeLoadingState();
    },
  },
  mounted() {
    this.instance = new ZbRangeSlider("my-slider");
    this.instance.onChange = (min, max) => this.updateValues(min, max);
  },
  methods: {
    init() {
      this.bigSector = [
        "의생활",
        "식생활",
        "주거생활",
        "유흥",
        "내구재",
        "자동차",
        "유통",
        "여행",
        "레포츠/문화/취미",
        "의료/미용",
        "교육",
        "서비스",
        "기타",
      ];

      this.mediumSectorAll = [
        ["의류", "직물/침구류", "신변잡화"],
        ["휴게음식점", "일반음식점", "음료식품"],
        ["연료판매", "건축관련업"],
        ["유흥"],
        ["가구", "전자제품", "주방용품", "사무기기", "악기"],
        ["운송기구판매", "주유", "자동차 정비/유지"],
        [
          "대형유통",
          "소매",
          "전자상거래",
          "통신판매",
          "기타 유통",
          "비영리유통",
        ],
        ["숙박", "운송수단", "관광여행"],
        ["레저/스포츠", "문화/취미"],
        ["의료기관/제약", "미용"],
        ["학교", "유아교육기관", "학원", "학습자재"],
        ["보험", "용역서비스", "수리서비스", "가례서비스", "요금", "회비"],
        ["기타 제조/도매", "RF교통기관", "기타"],
      ];

      this.smallSectorAll = [
        [
          [
            "맞춤복점",
            "한복점",
            "기성복점",
            "아동 및 유아복점",
            "내의판매점",
            "양품점",
            "기타 의류",
          ],
          ["옷감판매점", "침구,커튼,카펫트점", "수예점", "자석요", "기타 직물"],
          [
            "악세사리점",
            "귀금속점",
            "시계전문점",
            "안경점",
            "가방점",
            "제화점",
            "일반신발점",
            "가발전문점",
            "기타",
          ],
        ],
        [
          [
            "휴게음식점",
            "제과점/아이스크림점",
            "커피/음료전문점",
            "패스트푸드점",
          ],
          [
            "한식",
            "일식/생선회집",
            "중식",
            "양식",
            "부페",
            "일반주점",
            "패밀리레스토랑",
            "일반음식점 기타",
          ],
          [
            "미곡상",
            "정육점",
            "인삼판매점",
            "건강식품점",
            "농．수．축산물점",
            "주류판매점",
            "기타 식품",
          ],
        ],
        [
          ["연탄 및 유류판매점", "가스판매점"],
          [
            "건축자재",
            "기계류 건축설비",
            "철물점",
            "실내장식",
            "지물 및 천막",
            "보안경비시스템",
            "주택,건설",
            "기타 건축자재",
          ],
        ],
        [
          [
            "단란주점",
            "유흥주점",
            "나이트클럽",
            "노래방",
            "안마시술소",
            "유흥기타",
          ],
        ],
        [
          ["일반가구점", "철재가구점"],
          ["가전제품점", "냉난방기구", "조명 전기기구점", "기타 전자제품"],
          ["주방기구점", "주방 및 가정용품점", "정수기판매점", "기타 주방용품"],
          ["사무용기기", "정보통신기기", "컴퓨터기기"],
          ["피아노판매점", "기타악기판매점"],
        ],
        [
          [
            "국산자동차판매점",
            "수입자동차판매점",
            "중고자동차판매점",
            "오토바이판매점",
            "자전거판매점",
            "기타운송기구판매점",
          ],
          ["주유소", "충전소"],
          [
            "차량 정비/부품/인테리어",
            "주차장",
            "세차장",
            "차량견인업",
            "기타 차량서비스",
          ],
        ],
        [
          ["백화점", "대형마트", "농. 수. 축협직판장", "대형쇼핑센터"],
          ["슈퍼마켓", "편의점", "일반잡화판매점", "소비조합", "선물의집"],
          [
            "전자상거래",
            "전자상거래(Passcity)",
            "전자상거래PG",
            "전자상거래오픈마켓",
            "전자상거래상품권",
            "전자상거래PG상품권",
            "전자상거래오픈마켓상품권",
          ],
          ["통신판매"],
          [
            "농어업용품",
            "중고물품 판매점",
            "종교상품점",
            "다단계판매기타",
            "상품권",
            "성인용품점",
            "자동판매기 운영업",
            "기타유통업",
          ],
          ["공무원연금매점", "공공기관직영점", "보훈연금매점"],
        ],
        [
          [
            "특급관광호텔",
            "일반관광호텔",
            "기타관광호텔",
            "펜션/민박",
            "기타숙박업",
          ],
          [
            "항공사",
            "고속.시외버스",
            "철도",
            "여객선",
            "렌트카",
            "택시",
            "기타 운송수단",
          ],
          [
            "관광여행사",
            "관광기념품점",
            "민예·공예·토산품",
            "외국인전용점",
            "면세점",
          ],
        ],
        [
          [
            "스포츠용품점",
            "레저용품점",
            "총포류판매점",
            "골프장",
            "골프연습장",
            "테니스장",
            "볼링장",
            "스키장",
            "수영장",
            "종합스포츠센터",
            "당구장",
            "놀이공원",
            "레포츠클럽",
            "이벤트업",
            "외국인전용카지노",
            "요가",
            "기타 레저업소",
          ],
          [
            "영화관",
            "공연장/전시장",
            "경기장",
            "비디오방/게임방",
            "사진관",
            "서점",
            "화랑",
            "화방",
            "화원",
            "완구점",
            "애완동물",
            "골동품점",
            "표구사",
            "수족관",
            "티켓판매(통신판매)",
            "티켓판매(전자상거래)",
            "음반판매점",
            "비디오 및 도서대여점",
            "문화/취미 기타",
          ],
        ],
        [
          [
            "종합병원",
            "일반·치과·한방병원",
            "일반·치과·한의원",
            "건강진단센터",
            "약국",
            "한약방",
            "동물병원",
            "유사의료업",
            "제약회사/의약품도매업체",
            "산후조리원",
            "의료기기 및 용품",
            "제약/의료 기타",
          ],
          [
            "이용원",
            "미용원",
            "피부미용원",
            "찜질방/목욕탕",
            "화장품점",
            "미용재료",
            "미용 기타",
          ],
        ],
        [
          [
            "초.중.고등학교",
            "대학．대학원",
            "대학．대학원(등록금)",
            "초중고 학교납입금(자동납부)",
          ],
          ["유치원/어린이집/놀이방", "유아전문 교육기관/놀이시설"],
          [
            "기술/사무/가정계학원",
            "예체능계학원",
            "문리계학원",
            "외국어학원",
            "자동차학원",
            "문화센터",
            "독서실",
            "기타학원",
          ],
          ["문방구점", "교육기자재", "학습지", "과학기자재점", "기타 학습자재"],
        ],
        [
          ["생명보험", "손해보험", "기타 보험"],
          [
            "용역서비스업",
            "유학원",
            "인쇄 및 광고",
            "기계 및 장비임대업",
            "보관 및 창고업",
            "화물운송업",
            "시설대여업",
            "부동산 중개업",
            "부동산임대",
            "전문서비스업",
            "소프트웨어업",
            "세탁소",
            "철학관",
            "기타 용역서비스",
          ],
          [
            "가정용품수리",
            "사무용기기수리",
            "컴퓨터 및 통신기기수리",
            "열쇠/도장",
            "기타 수리서비스",
          ],
          ["결혼식장", "결혼서비스업", "장의서비스/제수용품"],
          [
            "케이블ＴＶ",
            "인터넷이용료",
            "공과금",
            "전화요금",
            "전기 수도",
            "관리비",
            "우체국(우편요금)",
            "국세",
            "지방세",
            "도시가스",
          ],
          ["단체회비", "기부금"],
        ],
        [
          ["기계류 제조/도매업", "식품류 제조/도매업", "기타 제조/도매업"],
          ["RF대중교통", "RF유료도로.터널", "RF공항버스", "RF기타운송수단"],
          [
            "구매전용",
            "RF유통기관",
            "그룹사 거래업체",
            "바우처(Voucher)",
            "부동산중개업소 (당사 회원)",
            "부동산임대/관리",
            "바우처(가사간병)",
            "아이행복어린이집",
            "아이행복유치원",
          ],
        ],
      ];

      this.smallSectorCodeAll = [
        [
          ["1001", "1003", "1004", "1007", "1008", "1010", "1099"],
          ["1101", "1102", "1104", "1105", "1199"],
          [
            "1201",
            "1202",
            "1203",
            "1204",
            "1205",
            "1206",
            "1207",
            "1208",
            "1299",
          ],
        ],
        [
          ["2002", "2003", "2004", "2099"],
          ["2104", "2107", "2109", "2110", "2111", "2112", "2113", "2199"],
          ["2401", "2402", "2403", "2404", "2406", "2407", "2499"],
        ],
        [
          ["3302", "3303"],
          ["3401", "3402", "3403", "3404", "3405", "3407", "3408", "3499"],
        ],
        [["2299", "2312", "2317", "4113", "7104", "7299"]],
        [
          ["3001", "3002"],
          ["3101", "3102", "3201", "3199"],
          ["3202", "3203", "3204", "3299"],
          ["6101", "6102", "6103"],
          ["6109", "6110"],
        ],
        [
          ["5501", "5601", "5602", "5603", "5502", "5604"],
          ["5608", "5609"],
          ["5605", "5610", "5611", "5612", "5699"],
        ],
        [
          ["4001", "4107", "4108", "4123"],
          ["4101", "4112", "4110", "4103", "4104"],
          ["4115", "4118", "4120", "4121", "4124", "4125", "4126"],
          ["4106"],
          ["3406", "4111", "4114", "4198", "4200", "6117", "8407", "4199"],
          ["4201", "4202", "4204"],
        ],
        [
          ["5001", "5101", "5102", "5104", "5103"],
          ["5201", "5302", "5303", "5304", "5305", "5306", "5399"],
          ["5301", "5401", "5402", "5403", "5404"],
        ],
        [
          [
            "6001",
            "6002",
            "6003",
            "6004",
            "6005",
            "6006",
            "6007",
            "6008",
            "6009",
            "6010",
            "6011",
            "6012",
            "6013",
            "6015",
            "6016",
            "6021",
            "6099",
          ],
          [
            "6014",
            "6019",
            "6020",
            "6018",
            "6107",
            "6113",
            "6201",
            "6202",
            "6203",
            "6204",
            "6205",
            "6206",
            "6207",
            "6208",
            "6209",
            "6210",
            "6111",
            "6112",
            "6299",
          ],
        ],
        [
          [
            "7001",
            "7002",
            "7003",
            "7004",
            "7005",
            "7006",
            "7007",
            "7008",
            "7009",
            "7010",
            "7107",
            "7099",
          ],
          ["7101", "7102", "7103", "7105", "7106", "7108", "7199"],
        ],
        [
          ["8107", "8108", "8115", "8116"],
          ["8109", "8110"],
          ["8101", "8102", "8106", "8112", "8113", "8114", "8111", "8199"],
          ["6114", "6115", "6116", "6105", "6199"],
        ],
        [
          ["8001", "8002", "8099"],
          [
            "8201",
            "8216",
            "8202",
            "8203",
            "8204",
            "8205",
            "8217",
            "8210",
            "8218",
            "8211",
            "6104",
            "8406",
            "8409",
            "8299",
          ],
          ["8301", "8302", "8303", "8304", "8399"],
          ["8401", "8402", "8404"],
          [
            "8206",
            "8207",
            "8208",
            "8212",
            "8213",
            "8215",
            "8214",
            "8219",
            "8220",
            "8221",
          ],
          ["8408", "8410"],
        ],
        [
          ["9002", "9003", "9001"],
          ["9005", "9013", "9014", "9099"],
          [
            "9004",
            "9006",
            "8209",
            "9007",
            "9008",
            "9009",
            "9010",
            "9011",
            "9012",
          ],
        ],
      ];

      this.sido = sigungu1["시도"];

      this.gugunList = [
        [
          "종로구",
          "중구",
          "용산구",
          "성동구",
          "광진구",
          "동대문구",
          "중랑구",
          "성북구",
          "강북구",
          "도봉구",
          "노원구",
          "은평구",
          "서대문구",
          "마포구",
          "양천구",
          "강서구",
          "구로구",
          "금천구",
          "영등포구",
          "동작구",
          "관악구",
          "서초구",
          "강남구",
          "송파구",
          "강동구",
        ],

        ["아", "이"],
      ];
    },
    isJwt() {
      if (localStorage.getItem("jwt")) {
      } else {
        alert("로그인을 먼저 해주세요");
        this.$router.push("/login");
      }
    },

    getMediumSector(num) {
      this.mediumSector = this.mediumSectorAll[num];
    },

    getSmallSector(num) {
      this.smallSector = this.smallSectorAll[this.selectedBigSectorNum][num];
    },

    fillData() {
      this.datacollection = {
        labels: [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "October",
          "November",
          "December",
        ],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
            ],
          },
          {
            label: "Data Two",
            backgroundColor: "#f13545",
            data: [
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
            ],
          },
        ],
      };
    },
    getRandomInt() {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
    updateValues(min, max) {
      this.$emit("update:min", min);
      this.$emit("update:max", max);
      this.minVal = min;
      this.maxVal = max;
    },
    changeLoadingState() {
      if (
        this.loading1 === false &&
        this.loading2 === false &&
        this.loading3 === false &&
        this.loading4 === false
      ) {
        this.loadingPageState = false;
      }
    },
    search() {
      if (this.selectedSmallSector === "소분류")
        alert("소분류 업종을 선택해주세요.");
      else {
        let dongChecked = false;
        let sidoChecked = false;
        let guGunChecked = false;
        if (this.selectedSido !== "시,도") sidoChecked = true;
        if (this.selectedGugun !== "구,군") guGunChecked = true;
        if (this.selectedDong !== "동,읍,면") dongChecked = true;

        this.loading1 = true;
        this.loading2 = true;
        this.loading3 = true;
        this.loading4 = true;
        this.loadingPageState = true;

        axios2
          .post("api/hdfs/search/count", {
            dong: this.selectedDong,
            dongCheck: dongChecked,
            gender: 3,
            guGun: this.selectedGugun,
            guGunCheck: guGunChecked,
            maxAge: this.maxVal,
            minAge: this.minVal,
            siDo: this.selectedSido,
            siDoCheck: sidoChecked,
            storeCode: this.selectedSmallSectorCode,
          })
          .then((res) => {
            this.chartData1 = res.data;

            this.func1();
            this.loading1 = false;
          })
          .catch((err) => {});

        axios2
          .post("/api/hdfs/search/money", {
            dong: this.selectedDong,
            dongCheck: dongChecked,
            gender: 3,
            guGun: this.selectedGugun,
            guGunCheck: guGunChecked,
            maxAge: this.maxVal,
            minAge: this.minVal,
            siDo: this.selectedSido,
            siDoCheck: sidoChecked,
            storeCode: this.selectedSmallSectorCode,
          })
          .then((res) => {
            this.chartData2 = res.data;

            this.func2();
            this.loading2 = false;
          })
          .catch((err) => {});

        axios2
          .post("api/hdfs/search/timeline", {
            dong: this.selectedDong,
            dongCheck: dongChecked,
            gender: 3,
            guGun: this.selectedGugun,
            guGunCheck: guGunChecked,
            maxAge: this.maxVal,
            minAge: this.minVal,
            siDo: this.selectedSido,
            siDoCheck: sidoChecked,
            storeCode: this.selectedSmallSectorCode,
          })
          .then((res) => {
            this.chartData3 = res.data;

            this.func3();
            this.loading3 = false;
          })
          .catch((err) => {});

        axios2
          .post("api/hdfs/search/gender/age", {
            dong: this.selectedDong,
            dongCheck: dongChecked,
            gender: 3,
            guGun: this.selectedGugun,
            guGunCheck: guGunChecked,
            maxAge: this.maxVal,
            minAge: this.minVal,
            siDo: this.selectedSido,
            siDoCheck: sidoChecked,
            storeCode: this.selectedSmallSectorCode,
          })
          .then((res) => {
            this.chartData4 = res.data;

            this.func4();
            this.loading4 = false;
          })
          .catch((err) => {});
      }
    },

    func1() {
      let storeCodeArr = [];
      let storeCountArr = [];

      this.chartData1.forEach((element) => {
        storeCountArr.push(element.count);
        storeCodeArr.push(sectorCode1[element.storeCode]);
      });
      const ctx = document.getElementById("myChart");
      if (this.myChart1 !== null) this.myChart1.destroy();
      this.myChart1 = new Chart(ctx, {
        type: "bar",
        data: {
          labels: storeCodeArr,
          datasets: [
            {
              label: "지역 내 업종 순위(거래 횟수)",
              data: storeCountArr,
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              // borderWidth: 1
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    },

    func2() {
      let tempStoreCodeArr = [];
      let tempStoreCountArr = [];

      this.chartData2.forEach((element) => {
        tempStoreCountArr.push(element.moneyAmount * 100);
        tempStoreCodeArr.push(sectorCode1[element.storeCode]);
      });
      let storeCodeArr = [];
      let storeCountArr = [];

      for (let a = 0; a < 8; a++) {
        storeCodeArr.push(tempStoreCodeArr[a]);
        storeCountArr.push(tempStoreCountArr[a]);
      }

      const ctx = document.getElementById("myChart2");
      if (this.myChart2 !== null) this.myChart2.destroy();
      this.myChart2 = new Chart(ctx, {
        type: "doughnut",
        data: {
          labels: storeCodeArr,
          datasets: [
            {
              label: "지역 내 업종 순위(금액별 비율 %)",
              data: storeCountArr,
              title: {
                display: true,
                text: "Chart.js Floating Bar Chart",
              },
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              // borderWidth: 1
            },
          ],
        },
      });
    },

    func3() {
      let storeCodeArr = [];
      let storeCountArr = [];
      this.chartData3.forEach((element) => {
        storeCountArr.push(element.sum);
        storeCodeArr.push(element.time);
      });

      const ctx = document.getElementById("myChart3");
      if (this.myChart3 !== null) this.myChart3.destroy();
      this.myChart3 = new Chart(ctx, {
        type: "line",
        data: {
          labels: storeCodeArr,
          datasets: [
            {
              label: "시간대별 거래액",
              data: storeCountArr,
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              // borderWidth: 1
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    },

    func4() {
      let storeCodeArr = [
        "10대",
        "20대",
        "30대",
        "40대",
        "50대",
        "60대",
        "70대 이상",
      ];
      let storeCountArr1 = [];
      let storeCountArr2 = [];

      this.chartData4.forEach((element) => {
        if (element.gender === 1) storeCountArr1.push(element.count);
        else if (element.gender === 2) storeCountArr2.push(element.count);
      });
      const ctx = document.getElementById("myChart4");
      if (this.myChart4 !== null) this.myChart4.destroy();
      this.myChart4 = new Chart(ctx, {
        type: "bar",
        data: {
          labels: storeCodeArr,
          datasets: [
            {
              label: "남성",
              data: storeCountArr1,
              backgroundColor: ["rgba(54, 162, 235, 0.2)"],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              // borderWidth: 1
            },
            {
              label: "여성",
              data: storeCountArr2,
              backgroundColor: ["rgba(255, 99, 132, 0.2)"],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              // borderWidth: 1
            },
          ],
        },
        options: {
          indexAxis: "y",
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.md-table {
  margin-top: 16px;
}
.slider {
  display: block;
  position: relative;
  height: 36px;
  width: 80%;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  -o-user-select: none;
  user-select: none;
}
.slider .slider-touch-left,
.slider .slider-touch-right {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  display: block;
  position: absolute;
  height: 36px;
  width: 36px;
  padding: 6px;
  z-index: 2;
}
.slider .slider-touch-left span,
.slider .slider-touch-right span {
  display: block;
  width: 100%;
  height: 100%;
  background: #f0f0f0;
  border: 1px solid #a4a4a4;
  border-radius: 50%;
}
.slider .slider-line {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  position: absolute;
  width: calc(100% - 36px);
  left: 18px;
  top: 16px;
  height: 4px;
  border-radius: 4px;
  background: #f0f0f0;
  z-index: 0;
  overflow: hidden;
}
.slider .slider-line span {
  display: block;
  height: 100%;
  width: 0%;
  background: orange;
}

.box1 {
  margin-left: 100px;
  float: left;
  background-color: rgba(163, 197, 207, 0.76);
  width: 600px;
  height: 680px;
  margin-bottom: 30px;
  border-radius: 10px;
}

.box2 {
  margin-right: 100px;
  float: right;
  background-color: rgba(163, 197, 207, 0.76);
  width: 600px;
  height: 680px;
  margin-bottom: 30px;
  border-radius: 10px;
}

.center-box {
  width: 140px;
  height: 650px;
  float: left;
}

.center-box-word {
}

.center-box-word2 {
  margin-top: 40px;
  text-align: center;
  padding-top: 170px;
}

.box-inner {
  margin: 10px;
  height: 600px;
  background-color: rgba(231, 241, 241, 0.849);
}

.select-box {
  margin: 10px;
}

.chartName {
  text-align: center;
  font-weight: bold;
}

.loading-message {
  font-weight: bold;
}
</style>
