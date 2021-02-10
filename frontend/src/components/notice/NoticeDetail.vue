<template>
  <v-main>
    <v-container>
      <v-layout col-8 offset-2 column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>공지사항 상세보기</h1>
        </v-flex>

        <!-- icons - modify, delete -->
        <v-flex text-right>
          <v-btn
            icon
            color="blue"
            large
            router-link
            :to="'/notice/modify/' + no"
          >
            <v-icon>mdi-pencil</v-icon>
          </v-btn>

          <v-btn icon color="red" large @click="onRemove(no)">
            <v-icon>mdi-minus</v-icon>
          </v-btn>
        </v-flex>

        <!-- content -->
        <v-flex>
          <v-layout>
            <v-flex col-2 text-right><h2>제목</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-sheet>
                {{ getNotice.title }}
              </v-sheet>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex col-2 text-right><h2>내용</h2></v-flex>
            <v-flex col-1><v-spacer /></v-flex>
            <v-flex col-7>
              <v-textarea auto-grow disabled :value="getNotice.content" />
            </v-flex>
          </v-layout>
        </v-flex>

        <!-- icon - return -->
        <v-flex text-right>
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
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

const NoticeStore = "NoticeStore";

export default {
  data() {
    return {
      no: this.$route.params.noticeno,
    };
  },
  computed: {
    ...mapGetters(NoticeStore, ["getNotice"]),
  },
  methods: {
    ...mapActions(NoticeStore, ["reqNotice", "reqDeleteNotice"]),
    // 글 삭제
    onRemove(no){
      this.reqDeleteNotice(no)
      .then((response) =>{
        if(response.result) alert(response.msg);

        alert(response.msg);
      })
    }
  },
};
</script>

<style></style>
