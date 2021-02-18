<template>
  <v-main>
    <v-container class="mb-10" >
    <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
      <v-row>
          <v-col cols="3"></v-col>
          <v-col cols="2">
            <v-banner class="mb-10">
              <strong>영상 업로드</strong>
            </v-banner>
          </v-col>
        </v-row>

      <v-row justify="center">
        <v-col cols="4"></v-col>
        <v-col cols="2"
          ><h2 style="margin: 20px auto" class="black--text">제목</h2></v-col
        >
        <v-col cols="4">
          <v-text-field solo 
          label="영상제목"
          style="margin: 20px auto" v-model="board.title">
          </v-text-field>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="4"></v-col>
        <v-col cols="2"
          ><h2 style="margin: 20px auto"  class="black--text">내용</h2></v-col
        >
        <v-col cols="4">
          <v-textarea
            solo
            label="영상 설명"
            full-width
            height="200"
            style="margin: 20px auto"
            v-model="board.content"
          ></v-textarea>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row >
      <v-row justify="center">
        <v-col cols="4"></v-col>
        <v-col cols="2"
          ><h2 style="margin: 20px auto" class="black--text">썸네일</h2></v-col
        >
        <v-col cols="4">
          <v-file-input
            accept="image/*"
            v-model="imgfiles"
            show-size
            solo
            label="이미지 파일"
          ></v-file-input>
          <p>File Name : {{ imgfiles.name }}</p>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="4"></v-col>
        <v-col cols="2"
          ><h2 style="margin: 20px auto" class="black--text">영상</h2></v-col
        >
        <v-col cols="4">
          <v-file-input
            accept="video/mp4"
            v-model="videofiles"
            show-size
            solo
            label="비디오 파일(mp4)"
          ></v-file-input>
          <p>File Name : {{ videofiles.name }}</p>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
      <v-row justify="center">
        <v-col cols="4"></v-col>
        <v-col cols="2"
          ><h2 style="margin: 20px auto" class="black--text">밴드</h2></v-col
        >
        <v-col class="d-flex" cols="4" >
          <v-select
            v-model="select"
            :items="bandlist"
            item-text="name"
            item-value="bandId"
            label="밴드명"
            solo
            dense
          ></v-select>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-card>
    </v-container>

      <v-row justify="center">
        <v-col cols="auto">
          <v-btn style="font-size:large" large color="info" class="ma-7" @click="create()"
            >작성완료</v-btn
          >
          <v-btn style="font-size:large" large class="ma-7" @click="list()"
            >돌아가기</v-btn
          >
        </v-col>
      </v-row>
  </v-main>
</template>
<script>
import axios from '@/axios/axios-common.js';

import { mapGetters } from 'vuex';

const MemberStore = 'MemberStore'; //로그인 체크 용.

// 필요한 거
// 썸네일
// 영상 소스
// 영상 제목
// 밴드.. 자신이 속한 밴드 리스트를 가져와야 함.
// 영상 설명

export default {
  created() {
    this.getbandlist();
  },
  computed: {
    ...mapGetters(MemberStore, {
      memberid: 'getMemberId',
    }),
  },
  data() {
    return {
      select: '',
      bandlist: [],
      imgfiles: [],
      videofiles: [],
      board: {
        title: '',
        content: '',
      },
    };
  },
  methods: {
    list() {
      this.$router.push('/video');
    },
    create() {
      //공백이 존재하면 경고
      //console.log(this.videofiles.size);
      //console.log(this.videofiles.type); //타입을 알 수 있음.

      if (
        this.board.title == '' ||
        this.board.content == '' ||
        this.videofiles.name == '' ||
        this.imgfiles.name == '' ||
        this.select == ''
      ) {
        alert('빈칸이 존재합니다.');
        return;
      }

      if (this.videofiles.size > 1e8 + 1e7) {
        //byte 단위임.
        alert('100MB 이하 영상만 가능합니다.');
        return;
      }

      // 서버에 파일 업로드.

      let fd = new FormData();
      let fd2 = new FormData();
      fd.append('file', this.imgfiles);
      fd2.append('file', this.videofiles);

      let fileImg, fileVideo;
      axios
        .post('/upload/thumbnail/' + this.select, fd, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
        .then((rec) => {
          console.log('suc');
          console.log(rec);
          fileImg = rec.data;
          axios
            .post('/upload/video/' + this.select, fd2, {
              headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then((rec2) => {
              fileVideo = rec2.data;
              axios
                .post('/videoboard/' + this.select, {
                  bandId: this.select,
                  boardContent: this.board.content,
                  boardSubject: this.board.title,
                  boardThumbnail: fileImg,
                  boardVideoUrl: fileVideo,
                })
                .then((response) => {
                  console.log(response);
                  alert('업로드 하였습니다!');
                  this.$router.push('/video');
                })
                .catch(function() {
                  console.log('실패');
                });
            })
            .catch((e1) => console.log(e1));
        })
        .catch((e2) => console.log(e2));

      //DB에 등록하는 과정.
    },
    getbandlist() {
      axios
        .get('/band-list/' + this.memberid)
        .then((response) => (this.bandlist = response.data.object))
        .catch((exp) => alert(exp + '조회에 실패하였습니다.'));
    },
  },
};
</script>
