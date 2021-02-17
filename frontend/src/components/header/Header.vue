<template>
  <v-app-bar color="#231E1A" height="100" max-height="100">
    <v-app-bar-nav-icon class="hidden-md-and-down mx-auto">
      <v-btn
        v-for="menu in menus"
        :key="menu.text"
        text
        router-link
        :to="menu.path"
        class="white--text mx-3"
      >
        {{ menu.text }}
      </v-btn>

      <router-link to="/">
        <v-img
          max-height="150"
          max-width="350"
          contain
          src="@/assets/image/logo.png"
          class="ma-3"
      /></router-link>

      <Logined mode="wide" v-if="islogin" />
      <NotLogined mode="wide" v-else />
    </v-app-bar-nav-icon>

    <v-row class="hidden-lg-and-up">
      <router-link to="/">
        <v-img
          max-height="250"
          max-width="350"
          src="@/assets/image/logo.png"
          class="my-3"
      /></router-link>

      <v-spacer></v-spacer>

      <v-toolbar-items>
        <v-menu>
          <template v-slot:activator="{ on, attrs }">
            <v-btn dark icon v-bind="attrs" v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item v-for="(menu, i) in menus" :key="i">
              <v-btn
                :key="menu.text"
                text
                router-link
                :to="menu.path"
              >
                {{ menu.text }}
              </v-btn>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <Logined mode="narrow" v-if="islogin" />
          <NotLogined mode="narrow" v-else />
        </v-menu>
      </v-toolbar-items>
    </v-row>
  </v-app-bar>
</template>

<script>
import Logined from "./Logined.vue";
import NotLogined from "./NotLogined.vue";
import { mapGetters } from "vuex"; //vuex사용

const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  data: () => ({
    menus: [
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
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      islogin: "getIsLogined", //islogin 변수에 getIsLogined 리턴값 저장
      manager: "getManager", //manager 변수에 getManager 리턴값 저장
    }),
  },
  methods: {
    menuItems() {
      return this.menu;
    },
  },
  components: {
    Logined,
    NotLogined,
  },
};
</script>

<style scoped>
.v-btn__content {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 17px;
}
</style>
