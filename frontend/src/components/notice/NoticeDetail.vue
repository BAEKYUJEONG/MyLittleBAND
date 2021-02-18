<template>
  <v-main>
    <v-container class="mb-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
      <v-layout col-8 offset-2 column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>공지사항 상세보기</h1>
        </v-flex>

        <!-- icons - modify, delete -->
        <v-flex text-right v-if="manager=='1'">
          <v-btn
          class="mx-7"
            icon
            color="blue"
            large
            router-link
            :to="'/notice/modify/' + no"
          >
            <v-icon size="40">mdi-pencil</v-icon>
          </v-btn>

          <v-btn icon color="red" large @click="dialog=true">
            <v-icon size="50">mdi-minus</v-icon>
          </v-btn>
        </v-flex>

        <!-- 게시글 삭제 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 공지사항삭제 </v-card-title>
            <v-card-text>정말 삭제하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="onRemove()">
                예
              </v-btn>
              <v-btn color="green darken-1" text @click="dialog = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <!-- content -->
        <v-flex>
          <v-layout>
            <v-flex col-2 text-right><h2>제목</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-text-field  solo v-model="getNotice.noticeTitle" readonly>
              </v-text-field>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex col-2 text-right><h2>내용</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-textarea auto-grow style="margin: 20px auto" solo readonly :value="getNotice.noticeContent" />
            </v-flex>
          </v-layout>
        </v-flex>

        <!-- icon - return -->
        <v-flex text-right>
          <v-btn
            icon
            color="primary"
            large
            router-link :to="{name: 'noticelist'}"
          >
            <v-icon size="50">mdi-undo-variant</v-icon>
          </v-btn>
        </v-flex>
      </v-layout>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

const NoticeStore = "NoticeStore";
const MemberStore = "MemberStore";
export default {
  data() {
    return {
      no: this.$route.params.noticeno,
      dialog : false,
    };
  },
  computed: {
    ...mapGetters(NoticeStore, ["getNotice"]),
    ...mapGetters(MemberStore, { manager: "getManager"}),
  },
  created(){
    this.reqNotice(this.no);
  },
  methods: {
    ...mapActions(NoticeStore, ["reqNotice", "reqDeleteNotice"]),
    // 글 삭제
    onRemove(){
      this.reqDeleteNotice(this.no)
      .then((response) =>{
        if(response.result){ 
          alert(response.msg);
          this.$router.push("/notice");
        }
        else
        alert(response.msg);
        this.dialog = false;
      })
    }
  },
};
</script>

<style></style>
