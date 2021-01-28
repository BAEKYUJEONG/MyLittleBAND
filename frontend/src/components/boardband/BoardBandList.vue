<template>
<v-form>
  <v-app id="inspire">
    <h1 style="margin: 10px auto">밴드게시판</h1>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      dark
      disable-sort
      style="text-align: center; margin: 20px"
      @click:row="detail"
    >
    </v-data-table>
    <v-layout>
      <v-flex>
        <v-btn color="blue" @click="write()" style="margin:10px;" >글쓰기</v-btn>
        <v-btn color="blue" @click="back()" style="margin:10px;" >돌아가기</v-btn>
      </v-flex>
    </v-layout>
  </v-app>
  </v-form>
</template>

<script>
import axios from 'axios';
export default {
  created(){//리스트 목록을 items에 저장
    //백엔드와 연동되면 아래 주석 해제
    //this.showList();
  },
  methods: {
    detail(row) {
      //디테일 페이지로 라우터 이동 기능
      this.$router.push("/boardbanddetail/" + this.$route.params.bandnum + "/" + row.num);
    },
    write() {
      //글쓰기 페이지로 라우터 이동 기능
      this.$router.push("/boardbandcreate/" + this.$route.params.bandnum );
    },
    back(){
        //밴드페이지로 라우터 이동 기능 추후 추가 필요
    },
    showList(){//리스트 목록을 items에 저장
      axios
      .get("/board/band",this.$route.params.bandnum)
      .then((response)=>(this.items = response.data))//items에 공지리스트 저장
      .catch((exp) => alert("조회에 실패하였습니다." + exp));
    }
  },
  data() {
    return {
      headers: [
        //header에 들어갈 목록
        { text: "번호", value: "num", align: "center" },
        { text: "제목", value: "title", align: "center" },
        { text: "작성자", value: "writer", align: "center" },
        { text: "작성일", value: "date", align: "center" },
        { text: "조회수", value: "view", align: "center" },
      ],
      //데이터가 저장 될 목록
      items: [
        {
          num: "1",
          title: "첫번째 제목입니다.",
          writer: "john",
          date: "2021.01.01",
          view : "0"
        },
        {
          num: "2",
          title: "두번째 제목입니다.",
          writer: "jane",
          date: "2021.01.03",
          view : "1"
        },
        {
          num: "3",
          title: "세번째 제목입니다.",
          writer: "peter",
          date: "2021.01.05",
          view : "12"
        },
        {
          num: "4",
          title: "네번째 제목입니다.",
          writer: "load",
          date: "2021.01.07",
          view : "11"
        },
        {
          num: "5",
          title: "다섯번째 제목입니다.",
          writer: "michale",
          date: "2021.01.09",
          view : "4"
        },
        {
          num: "6",
          title: "여섯번째 제목입니다.",
          writer: "antonio",
          date: "2021.01.11",
          view : "6"
        },
      ],
    };
  },
};
</script>

<style>
</style>