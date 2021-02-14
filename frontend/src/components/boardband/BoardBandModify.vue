<template>
  <v-main>
    <v-container>
      <v-layout col-8 offset-2 column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>게시글 수정</h1>
        </v-flex>

        <!-- icons - modify, delete -->
        <v-flex text-right>
          <v-btn
            icon
            color="blue"
            large
            @click="onModify()"
          >
            <v-icon>mdi-check</v-icon>
          </v-btn>

          <v-btn
            icon
            color="primary"
            large
            @click="
              () => {
                this.$router.go(-1);
              }
            "
          >
            <v-icon size="40">mdi-undo-variant</v-icon>
          </v-btn>
        </v-flex>

        <!-- content -->
        <v-flex>
          <v-layout>
            <v-flex col-2 text-right><h2>제목</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-text-field solo style="margin: 20px auto" v-model="title">
            </v-text-field>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex col-2 text-right><h2>내용</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-textarea
              solo
              height="200"
              style="margin: 20px auto"
              v-model="content"
            ></v-textarea>
            </v-flex>
          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

const BandBoardStore = "BandBoardStore";
const MemberStore = "MemberStore";

export default {
  data(){
    return {
      title: "",
      content: ""
    };
  },
  created(){
    this.title = this.getBoard.title;
    this.content = this.getBoard.content;
  },
  computed: {
    ...mapGetters(BandBoardStore, ["getBoard"]),
    ...mapGetters(MemberStore, ["getMemberId"]),
  },
  methods: {
    ...mapActions(BandBoardStore, ["reqModifyBoard"]),
    //공지사항 내용 수정
    onModify() {
      this.reqModifyBoard({ boardno: this.$route.params.boardno, title: this.title, content: this.content,memberId : this.getMemberId })
      .then((response) => {
        if(response.result)   alert(response.msg);
        else                  alert(response.msg);
      })
      .catch((error) => {
        console.log(error);
      })
    },
  },
};
</script>

<style></style>
