<template>
  <v-container>
      <v-card class="pa-10" color="rgba(200, 200, 200, 0.5)">

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
          <v-img v-if="getMemberInfo.img == '' || getMemberInfo.img == null" src="https://i4a408.p.ssafy.io/profile/LogoMini.png" />
        <v-img v-else :src="getMemberInfo.img" />
         </v-avatar
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
        <v-btn block color="info" @click="onWrite()"> 등록 </v-btn>
      </v-col>
    </v-row>

    <!-- 작성된 댓글 목록-->
    <v-row class="mt-2">
      <v-list shaped color="rgba(255, 255, 255, 0)">
        <v-list-item v-for="comment in getComments" :key="comment.commentId">
          <v-list-item-icon>
            <v-list-item-avatar>
              <v-img v-if="comment.img == '' || comment.img == null" src="https://i4a408.p.ssafy.io/profile/LogoMini.png" />
              <v-img :src="comment.img"></v-img>
            </v-list-item-avatar>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title  v-text="comment.content"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-action v-if="comment.memberId == getMemberId">
            <v-btn icon color="blue" @click="openModify(comment)"><v-icon>mdi-pencil</v-icon></v-btn>
          </v-list-item-action>
          <v-list-item-action v-if="comment.memberId == getMemberId">
            <v-btn icon color="red" @click="openDelete(comment.commentId)"><v-icon>mdi-minus</v-icon></v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-row>
    
    <!--댓글수정 모달창 -->
    <v-dialog v-model="Dialog.modify" persistent max-width="700">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 댓글수정 </v-card-title>
        <v-card-text> <v-text-field
                  v-model="tmpComment.content"
                  required
                ></v-text-field></v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="onModify()"> 수정 </v-btn>
          <v-btn color="green darken-1" text @click="Dialog.modify = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 댓글삭제 모달창 -->
    <v-dialog v-model="Dialog.delete" persistent max-width="290">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 댓글삭제 </v-card-title>
        <v-card-text>정말 삭제하시겠습니까?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="onDelete()"> 예 </v-btn>
          <v-btn color="green darken-1" text @click="Dialog.delete = false">
            아니오
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
      </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const BandBoardStore = "BandBoardStore";
const MemberStore = "MemberStore";

export default {
  data() {
    return {
      title: "",
      content: "",
      usercomment: "",
      Dialog:{
        modify : false,
        delete : false,
      },
      tmpComment: {},
      tmpNo : '',
    };
  },
  created() {
    this.title = this.getBoard.title;
    this.content = this.getBoard.content;
    this.reqMemberInfo(this.getMemberId);
    this.reqComments(this.$route.params.boardno);
  },
  computed: {
    ...mapGetters(BandBoardStore, ["getBoard", "getComments"]),
    ...mapGetters(MemberStore, ["getMemberId", "getMemberInfo"]),
  },
  methods: {
    ...mapActions(BandBoardStore, [
      "reqComments",
      "reqCreateComment",
      "reqModifyComment",
      "reqDeleteComment"
    ]),
    ...mapActions(MemberStore,["reqMemberInfo"]),
    //댓글 작성
    onWrite() {
      this.reqCreateComment({
        boardno: this.$route.params.boardno,
        content: this.usercomment,
        memberId: this.getMemberId,
      })
        .then((response) => {
          if (response.result) {
            alert(response.msg);
            this.usercomment = "";
            this.reqComments(this.$route.params.boardno);
          } else alert(response.msg);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    openModify(val){
      this.Dialog.modify = true;
      this.tmpComment = val;
    },
    //댓글 수정
    onModify() {
      this.reqModifyComment({ commentno: this.tmpComment.commentId, content: this.tmpComment.content })
        .then((response) => {
          if (response.result) {
            alert(response.msg);
            this.usercomment = "";
            this.reqComments(this.$route.params.boardno);
          } else alert(response.msg);
        })
        .catch((error) => {
          console.log(error);
        });
        this.Dialog.modify = false;
    },
    openDelete(val){
      this.Dialog.delete = true;
      this.tmpNo = val;
    },
    //댓글 삭제
    onDelete() {
      this.reqDeleteComment(this.tmpNo)
        .then((response) => {
          if (response.result) {
            alert(response.msg);
            this.usercomment = "";
            this.reqComments(this.$route.params.boardno);
          } else alert(response.msg);
        })
        .catch((error) => {
          console.log(error);
        });

        this.Dialog.delete = false;
    },
  },
};
</script>

<style></style>
