<template>
  <v-main>
    <v-container>
      <h1 style="margin: 10px auto">영상 업로드</h1>
      <v-row>
        <v-col
          ><h2 style="margin: 20px auto" class="black--text">제목</h2></v-col
        >
        <v-col>
          <v-text-field solo style="margin: 20px auto" v-model="board.title">
          </v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          ><h2 style="margin: 20px auto" class="black--text">내용</h2></v-col
        >
        <v-col>
          <v-textarea
            solo
            full-width
            height="200"
            style="margin: 20px auto"
            v-model="board.content"
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          ><h2 style="margin: 20px auto" class="black--text">썸네일</h2></v-col
        >
        <v-col>
          <v-file-input
            v-model="imgfiles"
            show-size
            label="File input"
          ></v-file-input>
          <p>File Name : {{ imgfiles.name }}</p>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          ><h2 style="margin: 20px auto" class="black--text">영상</h2></v-col
        >
        <v-col>
          <v-file-input
            v-model="videofiles"
            show-size
            label="File input"
          ></v-file-input>
          <p>File Name : {{ videofiles.name }}</p>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          ><h2 style="margin: 20px auto" class="black--text">밴드</h2></v-col
        >
        <v-col class="d-flex" cols="12" sm="6">
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
      </v-row>

      <v-row>
        <v-col>
          <v-btn color="blue" style="margin: 10px" @click="create()"
            >작성완료</v-btn
          >
          <v-btn color="blue" style="margin: 10px" @click="list()"
            >돌아가기</v-btn
          >
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>
<script>
import axios from '@/axios/axios-common.js';

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
    async upload() {
      // 여기서 파일 업로드에 대한 요청 백으로 보냄.
    },
    list() {
      this.$router.push('/video');
    },
    async create() {
      //공백이 존재하면 경고
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

      let fd = new FormData();
      fd.append('imgfiles', this.imgfiles);
      fd.append('videofiles', this.videofiles);

      //이 부분은 백 보고 수정해야 할듯.
      await axios
        .post('/video', fd, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          console.log(response);
          alert('업로드 하였습니다!');
          this.$router.push('/video');
        })
        .catch(function() {
          console.log('실패');
        });
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
