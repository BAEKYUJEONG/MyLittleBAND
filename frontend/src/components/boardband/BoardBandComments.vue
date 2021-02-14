<template>
  <v-container>
    <v-row>
      <v-col cols="2">
        <v-banner>
          <strong>댓글</strong>
        </v-banner>
      </v-col>
    </v-row>
    <!-- 댓글 작성 -->
    <v-row class="mt-2"
      ><v-col cols="auto"
        ><v-avatar>
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          /> </v-avatar
      ></v-col>
      <v-col>
        <v-text-field
          :counter="50"
          label="내용"
          name="usercomment"
          required
          v-model="usercomment"
          maxlength="50"
        ></v-text-field>
      </v-col>
      <v-col cols="auto">
        <v-btn block outlined color="blue" @click="onWrite()"> 등록 </v-btn>
      </v-col>
    </v-row>

    <!-- 작성된 댓글 목록-->
    <v-row class="mt-2">
      <v-list shaped>
        <v-list-item v-for="(comment, i) in getComments" :key="i">
          <v-list-item-icon>
            <v-list-item-avatar>
              <v-img :src="comment.avatar"></v-img>
            </v-list-item-avatar>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="comment.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

const BandBoardStore = "BandBoardStore";
const MemberStore = "MemberStore";

export default {
  data(){
    return {
      title: "",
      content: "",
      usercomment: "",
    };
  },
  created(){
    this.title = this.getBoard.title;
    this.content = this.getBoard.content;
  },
  computed: {
    ...mapGetters(BandBoardStore, ["getBoard","getComments"]),
    ...mapGetters(MemberStore, ["getMemberId"]),
  },
  methods: {
    ...mapActions(BandBoardStore, ["reqComments","reqCreateComment","reqModifyComment"]),
    //댓글 작성
    onWrite() {
      this.reqCreateComment({ boardno: this.$route.params.boardno, content: this.usercomment, writer : this.getMemberId })
      .then((response) => {
        if(response.result)   alert(response.msg);
        else                  alert(response.msg);
      })
      .catch((error) => {
        console.log(error);
      })
    },

    //댓글 수정
    onModify(no){
      this.reqModifyComment({ commentno: no, content: this.usercomment})
      .then((response) => {
        if(response.result)   alert(response.msg);
        else                  alert(response.msg);
      })
      .catch((error) => {
        console.log(error);
      })
    },

    //댓글 삭제
    onDelete(no){
      this.reqDeleteComment(no)
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
