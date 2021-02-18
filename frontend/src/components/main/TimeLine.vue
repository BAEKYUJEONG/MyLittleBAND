<template>
  <v-container class="my-20">
    <v-card elevation="5" class="pa-7" color="rgba(255, 255, 255, 0.5)">
      <!-- 위쪽 banner -->
      <v-banner single-line class="ma-5"
        ><!--@click:icon="reqShow">-->
        <v-icon size="45">{{ icons.mdiAlarm }}</v-icon>
        <strong class="ml-10">오늘의 공연</strong>

        <!-- 전체 보기 -->
        <template v-slot:actions>
          <v-btn color="secondary" text router-link :to="links[0].path">
            {{ links[0].text }}
          </v-btn>
        </template>
      </v-banner>

      <!-- 오늘 공연 일정을 보여주는 Timeline -->
      <!-- 공연 일정이 없을 때 -->
      <template v-if="getTimeline.length === 0">
        <v-img
          src="@/assets/image/notShowMsg.png"
          width="60%"
          class="mx-auto"
        />
      </template>

      <!-- 공연 일정 존재 시 -->
      <v-timeline v-else class="ma-5">
        <v-timeline-item v-for="data in getTimeline" :key="data.time" large color="black">
          <template v-slot:icon size="64">
            <v-avatar size="62">
              <v-img :src="data.img" />
            </v-avatar>
          </template>
          <template v-slot:opposite>
            <span>{{ data.time }}</span>
          </template>
          <v-card class="elevation-2">
            <v-card-title class="headline">
              {{ data.title }}
            </v-card-title>
            <v-card-text>{{ data.bandId }}</v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </v-card>
  </v-container>
</template>

<script>
import { mdiAlarm } from "@mdi/js";
import { mapGetters, mapActions } from "vuex";

const timelineStore = "timelineStore";

export default {
  data() {
    return {
      icons: {
        mdiAlarm,
      },
      links: [
        {
          text: "전체보기",
          path: "/timetable",
        },
      ],
    };
  },
  created() {
    this.reqTimeline();
    console.log(this.getTimeline);
  },
  computed: {
    ...mapGetters(timelineStore, ["getTimeline"]),
  },
  methods: {
    ...mapActions(timelineStore, ["reqTimeline"]),
  },
};
</script>

<style></style>
