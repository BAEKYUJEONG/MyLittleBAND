<!-- header 기본페이지 -->

<!-- Login상태 or Logout상태 -->

<template>
  <v-app-bar app color="gray" flat>
    <v-container class="py-0 fill-height">
      <v-avatar class="mr-10" color="grey darken-1" size="32"></v-avatar>

      <v-btn v-for="link in links" :key="link" text>
        {{ link }}
      </v-btn>

      <v-spacer></v-spacer>

      <Logined v-if="false" />
      <NotLogined v-else />
    </v-container>
  </v-app-bar>
</template>

<script>
import Logined from './Logined.vue';
import NotLogined from './NotLogined.vue';

export default {
  data: () => ({
    links: ["공지사항", "영상게시판"],
  }),
  components :{
    Logined,
    NotLogined,
  },
};
</script>

<style></style>
