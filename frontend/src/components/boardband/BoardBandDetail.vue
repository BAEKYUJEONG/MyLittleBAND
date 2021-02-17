<template>
  <v-main>
    <v-container>
      <v-layout col-8 offset-2 column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>{{ getBoard.title }}</h1>
        </v-flex>

        <!-- icons - modify, delete -->
        <v-flex text-right v-if="getMemberId == getBoard.memberId">
          <v-btn
            icon
            color="blue"
            large
            router-link
            :to="'/band/board/modify/' + no"
          >
            <v-icon>mdi-pencil</v-icon>
          </v-btn>

          <v-btn icon color="red" large @click="dialog = true">
            <v-icon>mdi-minus</v-icon>
          </v-btn>
        </v-flex>

        <!-- 게시글 삭제 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 게시글삭제 </v-card-title>
            <v-card-text>정말 삭제하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="onRemove(no)">
                예
              </v-btn>
              <v-btn color="green darken-1" text @click="dialog = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <!-- content -->
        <v-flex >
          <v-layout>
            <v-flex col-2 text-right><h2>작성자</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-4>
              <v-sheet>
                {{ writer }}
              </v-sheet>
            </v-flex>
            <v-flex col-2>
              <h2>조회수 :</h2>
            </v-flex>
            <v-flex col-1>
              <v-sheet>{{ getBoard.view }}</v-sheet>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex col-2 text-right><h2>내용</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-textarea outlined auto-grow readonly :value="getBoard.content" />
            </v-flex>
          </v-layout>
        </v-flex>
        <BoardBandComments></BoardBandComments>
        <!-- icon - return -->
        <v-flex text-right>
          <v-btn
            icon
            color="primary"
            large
            @click="moveList()"
          >
            <v-icon size="40">mdi-undo-variant</v-icon>
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axiosCommon from '../../axios/axios-common';
import BoardBandComments from "../boardband/BoardBandComments";
const BandBoardStore = "BandBoardStore";
const MemberStore = "MemberStore";
export default {
  data() {
    return {
      no: this.$route.params.boardno,
      dialog: false,
      writer : '',
    };
  },
  created(){
    this.reqBoard(this.no);
    this.getWriter();
  },
  computed: {
    ...mapGetters(BandBoardStore, ["getBoard"]),
    ...mapGetters(MemberStore, ["getMemberInfo","getMemberId"]),
  },
  methods: {
    ...mapActions(BandBoardStore, ["reqBoard", "reqDeleteBoard"]),
    // 글 삭제
    onRemove(no) {
      this.reqDeleteBoard(no).then((response) => {
        if (response.result) {
        alert(response.msg);
        this.$router.push("/band/board/"+this.getBoard.bandId);
        }
      });
      this.dialog = false;
    },
    moveList(){
      this.$router.push("/band/board/"+this.getBoard.bandId);
    },
    //글쓴이 이름 가져오기
    getWriter(){
      axiosCommon.get("/member/"+this.getBoard.memberId)
      .then((res) => this.writer = res.data.object.name );
    },
  },
  components:{
      BoardBandComments
    }
};
</script>

<style></style>
