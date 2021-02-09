<template>
  <v-main>
    <v-row class="ma-auto">
      <v-col cols="3"></v-col>
      <!-- 날짜 선택 달력 -->
      <v-col cols="4">
        <v-banner class="ma-10">
          <strong>날짜선택하기</strong>
        </v-banner>
        <v-date-picker
          mode="date"
          v-model="date"
          :min="new Date().toISOString().substr(0, 10)"
          :model-config="modelConfig"
          locale="ko-KR"
          @change="onChange()"
        />
      </v-col>

      <!-- 공연시간신청 테이블 -->
      <v-col cols="2">
        <v-banner class="my-10">
          <strong>{{ date }}</strong>
        </v-banner>
        <v-responsive class="overflow-y-auto" max-height="300">
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-center">시간</th>
                  <th class="text-center">신청</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="time in timetable" :key="time.hour">
                  <td>{{ time.hour }}</td>
                  <td>
                    <v-btn
                      v-if="time.pos"
                      color="primary"
                      @click="reserve(time.hour)"
                      >신청</v-btn
                    >
                    <v-btn v-else color="primary" disabled>신청</v-btn>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-responsive>
      </v-col>

      <v-col cols="3"></v-col>
    </v-row>

    <!-- 신청중인 공연 목록 -->
    <v-row justify="center" class="mb-10">
      <v-col cols="6">
        <v-banner class="ma-10">
          <strong>신청중인 공연 목록</strong>
        </v-banner>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-center">날짜</th>
                <th class="text-center">신청취소</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="time in bandreserve" :key="time.no">
                <td>{{ time.date + " " + time.hour + ":00" }}</td>
                <td>
                  <!-- 공연취소 모달창 -->
                  <v-dialog v-model="dialog" persistent max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="error"
                        v-bind="attrs"
                        v-on="on"
                        class="white--text mx-4"
                        @click="cancleno = time.no"
                      >
                        공연취소
                      </v-btn>
                    </template>
                    <v-card style="opacity: 1">
                      <v-card-title class="headline"> 공연취소 </v-card-title>
                      <v-card-text>정말 취소하시겠습니까?</v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="cancle()">
                          예
                        </v-btn>
                        <v-btn
                          color="green darken-1"
                          text
                          @click="dialog = false"
                        >
                          아니오
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>

    <v-row justify="center" class="ma-10">
      <v-btn color="primary" @click="moveDetail()">돌아가기</v-btn>
    </v-row>
  </v-main>
</template>

<script>
import axiosCommon from "../../axios/axios-common";
import { mapActions, mapGetters } from "vuex"; //vuex사용
const timelineStore = "timelineStore";

export default {
  created() {
    //오늘의 신청목록 가져오기
    //this.reqTimeline(this.date);
    //this.temptimetable =  this.getTimeline;
    //밴드의 공연신청목록 가져오기
    //this.getBandReserve();
  },
  computed: {
    ...mapGetters(timelineStore, ["getTimeline"]),
  },
  methods: {
    ...mapActions(timelineStore, ["reqTimeline"]), //공연신청하기

    getBandReserve() {
      //밴드의 공연신청목록 가져오기
      axiosCommon
        .get("/show/" + this.$route.params.bandno)
        .then((response) => {
          if (response.data.data == "success") {
            this.bandreserve = response.data.object; //bandreserve 목록에 저장
          }
        })
        .catch((exp) => {
          console.log(exp + "조회에 실패하였습니다.");
        });
    },
    checkReserve() {
      //timetable초기화
      for (let i = 0; i < this.timetable.length; i++) {
        this.timetable[i].pos = true;
      }
      //공연이 가능한지 판별
      for (let i = 0; i < this.temptimetable.length; i++) {
        let val = this.temptimetable[i].time; //DB에서 가져온 시간표에서 시간추출
        for (let j = 0; j < this.timetable.length; j++) {
          if (val == this.timetable[j].time)
            //현재 타임테이블의 시간과 동일하면 false로 바꿔줌
            this.timetable[j] = false;
          break;
        }
      }
    },
    onChange() {
      //date-picker날짜가 바뀔 때
      //바뀐날짜의 신청목록 가져오기
      //this.reqTimeline(this.date);
      //this.temptimetable =  this.getTimeline;
      this.checkReserve(); //공연이 가능한지
    },
    reserve(val) {
      //공연신청하기
      let hour = val.substr(0, 2); //시간에서 앞에 몇시인지만 추출

      axiosCommon
        .post("/show", {
          date: this.date,
          bandNo: this.$route.params.bandno,
          time: hour,
        })
        .then((response) => {
          if (response.data.data == "success") {
            alert("신청 성공!");
            //페이지 새로고침
          } else {
            alert("이미 선택된 시간입니다");
          }
        })
        .catch((exp) => {
          console.log(exp + "신청에 실패하였습니다.");
        });
    },
    cancle() {
      //공연취소하기
      axiosCommon
        .delete("/show/" + this.cancleno)
        .then((response) => {
          if (response.data.data == "success") {
            alert("취소 성공!");
            //신청중인 공연 목록 다시 가져오기
            this.getBandReserve();
          }
        })
        .catch((exp) => {
          console.log(exp + "신청에 실패하였습니다.");
        });
      this.dialog = false; //창닫아주기
    },
    moveDetail() {
      //밴드상세 페이지로 이동
      this.$router.push("/band/detail/" + this.$route.params.bandno);
    },
  },
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      dialog: false, //공연취소 dialog
      cancleno: "", //공연취소할 번호
      modelConfig: {
        type: "string",
        mask: "YYYY-MM-DD",
      },
      bandreserve: [
        {
          no: 1,
          date: "2021-03-01",
          hour: "10",
        },
        {
          no: 2,
          date: "2021-03-01",
          hour: "11",
        },
        {
          no: 4,
          date: "2021-04-06",
          hour: "14",
        },
      ],
      temptimetable: [], //공연이 있는지 없는지 판별
      timetable: [
        {
          hour: "10:00",
          pos: true,
        },
        {
          hour: "11:00",
          pos: true,
        },
        {
          hour: "12:00",
          pos: false,
        },
        {
          hour: "13:00",
          pos: true,
        },
        {
          hour: "14:00",
          pos: false,
        },
        {
          hour: "15:00",
          pos: false,
        },
        {
          hour: "16:00",
          pos: true,
        },
        {
          hour: "17:00",
          pos: true,
        },
        {
          hour: "18:00",
          pos: true,
        },
        {
          hour: "19:00",
          pos: true,
        },
        {
          hour: "20:00",
          pos: true,
        },
        {
          hour: "21:00",
          pos: true,
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>