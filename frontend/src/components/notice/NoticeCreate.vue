<template>
  <v-main>
    <v-container>
      <v-layout col-8 offset-2 column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>공지사항 작성</h1>
        </v-flex>

        <!-- icons - modify, delete -->
        <v-flex text-right>
          <v-btn icon color="blue" large @click="onCreate()">
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
import { mapActions } from 'vuex';

const NoticeStore = "NoticeStore";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    ...mapActions(NoticeStore, ["reqCreateNotice"]),
    onCreate(){
      this.reqCreateNotice({ title: this.title, content: this.content })
      .then((response) => {
        if(response.data.result)  alert(response.data.msg);
        else                      alert(response.data.msg);  
      })
      .catch((error) => {
        console.log(error);
      })
    }
  }
};
</script>

<style></style>
