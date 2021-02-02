<template>
  <v-container class="my-3">
    <!-- 위쪽 banner -->
    <v-banner single-line
      ><!--@click:icon="reqShow">-->
      <v-icon size="45">{{ icons.mdiAlarm }}</v-icon>
      <strong class="ml-10">오늘의 공연</strong>

      <!-- 전체 보기 -->
      <template v-slot:actions>
        <v-btn color="primary" text router-link :to="links[0].path">
          {{ links[0].text }}
        </v-btn>
      </template>
    </v-banner>

    <!-- 오늘 공연 일정을 보여주는 Timeline -->
    <!-- 공연 일정이 없을 때 -->
    <templete v-if="0 === 0">
      <v-row>
        <v-spacer />
        <v-col class="py-10" cols="3"
          ><v-alert align="center">
            예약 된 공연이 존재하지 않습니다
          </v-alert></v-col
        >
        <v-spacer />
      </v-row>
    </templete>

    <!-- 공연 일정 존재 시 -->
    <template v-else>
      <v-timeline class="my-5">
        <v-timeline-item v-for="data in sampleData" :key="data.time" large>
          <template v-slot:icon size="64">
            <v-avatar size="62">
              <img src="http://i.pravatar.cc/128" />
            </v-avatar>
          </template>
          <template v-slot:opposite>
            <span>{{ data.time }}</span>
          </template>
          <v-card class="elevation-2">
            <v-card-title class="headline">
              {{ data.band }}
            </v-card-title>
            <v-card-text>{{ data.intro }}</v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </template>
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
  computed: {
    ...mapGetters(timelineStore, ["getTimeline"]),
  },
  methods: {
    ...mapActions(timelineStore, ["reqTimeline"]),
  },
};
</script>

<style></style>
