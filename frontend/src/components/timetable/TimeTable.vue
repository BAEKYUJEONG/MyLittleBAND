<template>
  <v-main>
    <!-- Title 부분 -->
    <v-row class="ma-5">
      <v-col>
        <v-toolbar flat>
          <v-toolbar-title v-if="$refs.timetable" class="mx-auto">
            {{ focus.getFullYear() }}년 {{ focus.getMonth() + 1 }}월
            {{ thisweek.weekNo }}주차 공연
          </v-toolbar-title>
        </v-toolbar>
      </v-col>
    </v-row>

    <!-- TimeTable 부분 -->
    <v-row class="ma-10">
      <v-col v-if="getShows.length === 0">
        <p>공연이 존재하지 않습니다</p>
      </v-col>
      <v-col v-else>
        <v-sheet>
          <v-calendar
            v-model="focus"
            ref="timetable"
            :events="getShows"
            color="primary"
            type="week"
            @click:event="showEvent"
          ></v-calendar>

          <!-- 선택시 나오는 상세일정 -->
          <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <v-card
              color="grey lighten-4"
              min-width="600px"
              max-width="700px"
              flat
            >
              <v-toolbar :color="getShow.color" dark>
                <v-toolbar-title v-html="getShow.title"></v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn
                  text
                  router-link
                  :to="'/band/introduce/'+getShow.bandId"
                >
                  <v-toolbar-title
                    v-html="'<small>' + getShow.bandname + '</small>'"
                  ></v-toolbar-title>
                </v-btn>
                <v-btn icon @click="selectedOpen = false">
                  <v-icon>mdi-check</v-icon>
                </v-btn>
              </v-toolbar>

              <v-layout row class="my-3 mx-1">
                <v-spacer />
                <v-flex col style="color: black"
                  ><v-card-text>시작</v-card-text></v-flex
                >
                <v-flex col>
                  <v-chip small>
                    {{ getShow.start }}
                  </v-chip>
                </v-flex>
                <v-flex col style="color: black"
                  ><v-card-text>종료</v-card-text></v-flex
                >
                <v-flex col>
                  <v-chip small>
                    {{ getShow.end }}
                  </v-chip>
                </v-flex>
              </v-layout>

              <v-divider class="mx-4" />
              <v-layout
                ><v-flex>
                  <v-card-text>{{ getShow.content }}</v-card-text>
                </v-flex>
              </v-layout>

              <v-divider class="mx-4" />
              <v-layout
                ><v-flex>
                  <v-card-text><v-img :src="getShow.img" alt="이미지 없음"/></v-card-text>
                </v-flex>
              </v-layout>
            </v-card>
          </v-menu>
        </v-sheet>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

const TimetableStore = "TimetableStore";

export default {
  data: () => ({
    focus: "",
    firstday: "",
    msg: "",
    lastday: "",
    thisweek: {},
    selectedElement: null,
    selectedOpen: false,
  }),
  mounted() {
    this.setToday();
    this.thisweek = this.weekNumberByMonth(this.focus);
    this.reqShows({
      start: this.$moment(this.focus)
        .add(-this.focus.getDay(), "d")
        .format("YYYY-MM-DD"),
      end: this.$moment(this.focus)
        .add(6 - this.focus.getDay(), "d")
        .format("YYYY-MM-DD"),
    })
      .then((response) => {
        if (response)
          this.msg = response.msg;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    ...mapGetters(TimetableStore, ["getShows", "getShow"]),
  },
  methods: {
    ...mapActions(TimetableStore, ["reqShows", "reqShow"]),
    // 오늘의 날짜 설정
    setToday() {
      this.focus = new Date();
    },
    // 주차 구하는 함수
    weekNumberByMonth(dateFormat) {
      const inputDate = new Date(dateFormat);

      // 인풋의 년, 월
      let year = inputDate.getFullYear();
      let month = inputDate.getMonth() + 1;

      // 목요일 기준 주차 구하기
      const weekNumberByThurFnc = (paramDate) => {
        const year = paramDate.getFullYear();
        const month = paramDate.getMonth();
        const date = paramDate.getDate();

        // 인풋한 달의 첫 날과 마지막 날의 요일
        const firstDate = new Date(year, month, 1);
        const lastDate = new Date(year, month + 1, 0);

        const firstDayOfWeek =
          firstDate.getDay() === 0 ? 7 : firstDate.getDay();
        const lastDayOfweek = lastDate.getDay();

        // 인풋한 달의 마지막 일
        const lastDay = lastDate.getDate();

        // 첫 날의 요일이 금, 토, 일요일 이라면 true
        const firstWeekCheck =
          firstDayOfWeek === 5 || firstDayOfWeek === 6 || firstDayOfWeek === 7;
        // 마지막 날의 요일이 월, 화, 수라면 true
        const lastWeekCheck =
          lastDayOfweek === 1 || lastDayOfweek === 2 || lastDayOfweek === 3;

        // 해당 달이 총 몇주까지 있는지
        const lastWeekNo = Math.ceil((firstDayOfWeek - 1 + lastDay) / 7);

        // 날짜 기준으로 몇주차 인지
        let weekNo = Math.ceil((firstDayOfWeek + date) / 7);

        // 인풋한 날짜가 첫 주에 있고 첫 날이 월, 화, 수로 시작한다면 'prev'(전달 마지막 주)
        if (weekNo === 1 && firstWeekCheck) weekNo = "prev";
        // 인풋한 날짜가 마지막 주에 있고 마지막 날이 월, 화, 수로 끝난다면 'next'(다음달 첫 주)
        else if (weekNo === lastWeekNo && lastWeekCheck) weekNo = "next";
        // 인풋한 날짜의 첫 주는 아니지만 첫날이 월, 화 수로 시작하면 -1;
        else if (firstWeekCheck) weekNo = weekNo - 1;

        return weekNo;
      };

      // 목요일 기준의 주차
      let weekNo = weekNumberByThurFnc(inputDate);

      // 이전달의 마지막 주차일 떄
      if (weekNo === "prev") {
        // 이전 달의 마지막날
        const afterDate = new Date(year, month - 1, 0);
        year = month === 1 ? year - 1 : year;
        month = month === 1 ? 12 : month - 1;
        weekNo = weekNumberByThurFnc(afterDate);
      }
      // 다음달의 첫 주차일 때
      if (weekNo === "next") {
        year = month === 12 ? year + 1 : year;
        month = month === 12 ? 1 : month + 1;
        weekNo = 1;
      }

      return { year, month, weekNo };
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.reqShow({
          showId: event.showId,
          color: event.color,
        })
          .then((response) => {
            if (response === undefined) {
              this.selectedElement = nativeEvent.target;
              setTimeout(() => {
                this.selectedOpen = true;
              }, 10);
            }
          })
          .catch((error) => {
            console.error(error);
          });
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
  },
};
</script>

<style>
.v-calendar .v-event-timed-container {
  margin-right: 0;
}
</style>
