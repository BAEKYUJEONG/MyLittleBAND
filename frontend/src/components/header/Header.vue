<!-- header 기본페이지 -->

<!-- Login상태 or Logout상태 -->

<template>
  <v-app-bar app color="#231E1A" flat extended>
    <v-row class="my-auto">
      <v-spacer></v-spacer>
      
      <!-- 공지사항, 영상게시판 -->
      <v-col class="my-auto ml-auto"
        ><v-btn
          v-for="link in links"
          :key="link"
          text
          router-link
          :to="link.path"
          class="white--text mx-6"
        >
          {{ link.text }}
        </v-btn></v-col
      >

      <!-- Logo -->
      <v-col
        ><v-avatar tile width="350px" height="75px">
          <v-img src="@/assets/image/logo.png" /> </v-avatar
      ></v-col>

      <!-- Login, NotLogin -->
      <v-col class="my-auto ml-auto">
        <Logined v-if="islogin" />
        <NotLogined v-else />
      </v-col>      

      <v-spacer></v-spacer>
    </v-row>
  </v-app-bar>
</template>

<script>
import Logined from "./Logined.vue";
import NotLogined from "./NotLogined.vue";
import { mapGetters } from 'vuex' //vuex사용
const MemberStore = 'MemberStore' //MemberStore 모듈 사용

export default {
  data: () => ({
    links: [
      {
        text: "공지사항",
        path: "/notice",
      },
      {
        text: "영상게시판",
        path: "/video",
      },
    ],
  }),
  computed:{
    ...mapGetters(MemberStore,{//MemberStore 모듈 내 getters 사용
      islogin : 'getIsLogined',//islogin 변수에 getIsLogined 리턴값 저장
      manager : 'getManager'    //manager 변수에 getManager 리턴값 저장
    })
  },
  components: {
    Logined,
    NotLogined,
  },
};
</script>

<style>
.v-btn__content {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 17px;
}
</style>
