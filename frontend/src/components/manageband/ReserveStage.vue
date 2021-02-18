<template>
  <v-main>
    <v-container class="mb-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
        <v-row justify="center">
          <v-col cols="3"></v-col>
          <!-- 날짜 선택 달력 -->
          <v-col cols="auto">
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

          <v-col cols="1"></v-col>

          <!-- 공연시간신청 테이블 -->
          <v-col cols="auto">
            <v-banner class="my-10">
              <strong>{{ date }}</strong>
            </v-banner>
            <v-responsive class="overflow-y-auto" max-height="378" width="300">
              <v-simple-table>
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-center">시간</th>
                      <th class="text-center">신청</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="time in timetable" :key="time.time">
                      <td>{{ time.time }}</td>
                      <td>
                        <v-btn
                          v-if="time.pos"
                          color="primary"
                          class="white--text mx-4"
                          @click="OpenReserve(time)"
                          :disabled="
                            date == new Date().toISOString().substr(0, 10) &&
                            time.time < new Date().toTimeString().substr(0, 8)
                          "
                        >
                          신청
                        </v-btn>
                        <v-btn
                          v-else
                          class="white--text mx-4"
                          color="primary"
                          disabled
                          >신청</v-btn
                        >
                      </td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-responsive>
          </v-col>

          <v-col cols="3"></v-col>
        </v-row>

        <!-- 공연신청 모달창 -->
        <v-dialog v-model="Dialog.reserve" persistent max-width="600">
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드공연신청 </v-card-title>
            <v-card-subtitle>
              {{ date + "  " + TmpShow.time }}
            </v-card-subtitle>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="공연명"
                      v-model="title"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-textarea
                      label="공연설명"
                      v-model="showContent"
                      required
                    ></v-textarea>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="reserve()">
                공연신청
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="Dialog.reserve = false"
              >
                닫기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <!-- 신청중인 공연 목록 -->
        <v-row justify="center" class="mb-10">
          <v-col cols="6">
            <v-banner class="ma-10">
              <strong>신청중인 공연 목록</strong>
            </v-banner>
            <v-layout column>
              <v-flex text-center>
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-center">날짜</th>
                        <th class="text-center">공연명</th>
                        <th class="text-center">신청취소</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="show in bandreserve" :key="show.showId">
                        <td>{{ show.date + " " + show.time }}</td>
                        <td>
                          <v-btn class="mx-4" text @click="OpenModify(show)">
                            {{ show.title }}
                          </v-btn>
                        </td>
                        <td>
                          <v-btn
                            color="error"
                            class="white--text mx-4"
                            @click="OpenCancle(show)"
                            :disabled="
                              show.date <
                                new Date().toISOString().substr(0, 10) ||
                              (show.date ==
                                new Date().toISOString().substr(0, 10) &&
                                show.time <
                                  new Date().toTimeString().substr(0, 8))
                            "
                          >
                            공연취소
                          </v-btn>
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-flex>
            </v-layout>
          </v-col>
        </v-row>
      </v-card>
    </v-container>

    <!-- 공연수정 모달창 -->
    <v-dialog v-model="Dialog.modify" persistent max-width="600">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 공연정보수정 </v-card-title>
        <v-card-subtitle>
          {{ TmpShow.date + '  ' + TmpShow.time }}
        </v-card-subtitle>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="공연명"
                  v-model="TmpShow.title"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                  label="공연설명"
                  v-model="TmpShow.showContent"
                  required
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            :disabled="
              TmpShow.date < new Date().toISOString().substr(0, 10) ||
              (TmpShow.date == new Date().toISOString().substr(0, 10) &&
                TmpShow.time < new Date().toTimeString().substr(0, 8))
            "
            text
            @click="modify()"
          >
            수정하기
          </v-btn>
          <v-btn color="green darken-1" text @click="Dialog.modify = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 공연취소 모달창 -->
    <v-dialog v-model="Dialog.cancle" persistent max-width="290">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 공연취소 </v-card-title>
        <v-card-text>정말 취소하시겠습니까?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="cancle()"> 예 </v-btn>
          <v-btn color="green darken-1" text @click="Dialog.cancle = false">
            아니오
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-row justify="center" class="ma-10">
      <v-btn
        color="primary"
        class="mx-6"
        @click="moveBroadcast()"
        :disabled="isBroadcast"
        >공연하기</v-btn
      >
      <v-btn color="primary" class="mx-6" @click="moveDetail()">돌아가기</v-btn>
    </v-row>
  </v-main>
</template>

<script>
import axiosCommon from '../../axios/axios-common';

export default {
  created() {
    //오늘의 신청목록 가져오기
    this.getDayReserve(this.date);
    //밴드의 공연신청목록 가져오기
    this.getBandReserve();
  },
  methods: {
    getBandReserve() {
      //밴드의 공연신청목록 가져오기
      axiosCommon
        .get('/show/' + this.$route.params.bandno)
        .then((response) => {
          if (response.data.status) {
            this.bandreserve = response.data.object; //bandreserve 목록에 저장
          }
        })
        .catch((exp) => {
          console.log(exp + '조회에 실패하였습니다.');
        });
    },
    getDayReserve() {
      //특정 날짜의 공연목록 가져오기
      //console.log(this.date);
      axiosCommon.get('/show/list/' + this.date).then((response) => {
        if (response.data.status) {
          this.temptimetable = response.data.object;
          this.checkReserve();
          this.checkBroadcast();
        }
      });
    },
    checkReserve() {
      //timetable초기화
      for (let i = 0; i < this.timetable.length; i++) {
        this.timetable[i].pos = true;
      }
      //공연이 가능한지 판별
      for (let i = 0; i < this.temptimetable.length; i++) {
        let val = this.temptimetable[i].time.substr(0, 5); //DB에서 가져온 시간표에서 시간추출
        for (let j = 0; j < this.timetable.length; j++) {
          if (val == this.timetable[j].time) {
            //현재 타임테이블의 시간과 동일하면 false로 바꿔줌
            this.timetable[j].pos = false;
            break;
          }
        }
      }
    },
    onChange() {
      //date-picker날짜가 바뀔 때
      //바뀐날짜의 신청목록 가져오기
      this.getDayReserve();
    },
    OpenReserve(time) {
      this.TmpShow = time;
      this.Dialog.reserve = true;
      this.title = '';
      this.showContent = '';
    },
    reserve() {
      //공연신청하기
      axiosCommon
        .post('/show', {
          date: this.date,
          bandId: this.$route.params.bandno,
          time: this.TmpShow.time,
          title: this.title,
          showContent: this.showContent,
        })
        .then((response) => {
          if (response.data.data == 'success') {
            alert('신청 성공!');
            this.getBandReserve(); //목록 갱신
            this.getDayReserve();
            this.title = '';
            this.showContent = '';
          } else {
            alert('이미 선택된 시간입니다');
          }
        })
        .catch((exp) => {
          console.log(exp + '신청에 실패하였습니다.');
        });
      this.Dialog.reserve = false; //창닫아주기
    },

    OpenModify(show) {
      this.TmpShow = show;
      this.Dialog.modify = true;
    },
    modify() {
      let frm = new FormData();
      frm.append("title", this.TmpShow.title);
      frm.append("showContent", this.TmpShow.showContent);

      axiosCommon
        .put('/show/' + this.TmpShow.showId, frm)
        .then((response) => {
          if (response.data.status) {
            alert('수정 성공!');
            this.getBandReserve(); //목록 갱신
            this.getDayReserve();
          }
        })
        .catch((exp) => {
          console.log(exp + '수정에 실패하였습니다.');
        });
      this.Dialog.modify = false;
    },
    OpenCancle(show) {
      this.TmpShow = show;
      this.Dialog.cancle = true;
    },
    cancle() {
      //공연취소하기
      axiosCommon
        .delete('/show/' + this.TmpShow.showId)
        .then((response) => {
          if (response.data.data == 'success') {
            alert('취소 성공!');
            //신청중인 공연 목록 다시 가져오기
            this.getBandReserve(); //목록 갱신
            this.getDayReserve();
          }
        })
        .catch((exp) => {
          console.log(exp + '신청에 실패하였습니다.');
        });
      this.Dialog.cancle = false; //창닫아주기
    },

    moveDetail() {
      //밴드상세 페이지로 이동
      this.$router.push('/band/detail/' + this.$route.params.bandno);
    },
    checkBroadcast() {
      //공연할 수 있는지 확인하기.
      const nowTime = new Date().toTimeString().substr(0, 5); //HH-MM
      const nowHour = nowTime.substr(0, 2);
      for (let i = 0; i < this.temptimetable.length; i++) {
        console.log(this.temptimetable.length);
        let _bandId = this.temptimetable[i].bandId;
        let val = this.temptimetable[i].time.substr(0, 5); //DB에서 가져온 시간표에서 시간추출
        let valHour = val.substr(0, 2);
        if (_bandId == this.$route.params.bandno && nowHour == valHour) {
          this.isBroadcast = false;
        }
      }
    },
    moveBroadcast() {
      //공연 페이지로 이동.
      this.$router.push('/bandbroadcast');
    },
  },
  data() {
    return {
      isBroadcast: true,
      date: new Date().toISOString().substr(0, 10), //오늘날짜
      TmpShow: {},
      title: '', //신청공연제목
      showContent: '', //공연내용
      Dialog: {
        reserve: false, //공연신청 dialog
        modify: false, //공연수정 dialog
        cancle: false, //공연취소 dialog
      },
      modelConfig: {
        //달령게서 날짜 지정할때 정하는 타입
        type: 'string',
        mask: 'YYYY-MM-DD',
      },
      bandreserve: [
        {
          showId: 1,
          date: '2021-03-01',
          time: '10:00',
          title: '1공연',
          showContent: '공연내용',
        },
        {
          showId: 2,
          date: '2021-03-01',
          time: '11:00',
          title: '2공연',
          showContent: '공연내용',
        },
        {
          showId: 4,
          date: '2021-04-06',
          time: '14:00',
          title: '4공연',
          showContent: '공연내용',
        },
      ],
      temptimetable: [], //공연이 있는지 없는지 판별
      timetable: [
        {
          time: '10:00',
          pos: true,
        },
        {
          time: '11:00',
          pos: true,
        },
        {
          time: '12:00',
          pos: false,
        },
        {
          time: '13:00',
          pos: true,
        },
        {
          time: '14:00',
          pos: false,
        },
        {
          time: '15:00',
          pos: false,
        },
        {
          time: '16:00',
          pos: true,
        },
        {
          time: '17:00',
          pos: true,
        },
        {
          time: '18:00',
          pos: true,
        },
        {
          time: '19:00',
          pos: true,
        },
        {
          time: '20:00',
          pos: true,
        },
        {
          time: '21:00',
          pos: true,
        },
      ],
    };
  },
};
</script>

<style scoped></style>
