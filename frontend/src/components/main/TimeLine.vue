<template>
  <v-container class="my-3">
    <!-- 위쪽 banner -->
    <v-banner single-line @click:icon="alert">
      <v-icon size="45">{{ icons.mdiAlarm }}</v-icon>
      <strong class="ml-10">오늘의 공연</strong>
      
      <!-- 전체 보기 -->
      <template v-slot:actions>
        <v-btn color="primary" text router-link :to="links[0].path">
          {{
          links[0].text
        }}
        </v-btn>
      </template>
    </v-banner>

    <!-- 오늘 공연 일정을 보여주는 Timeline -->
    <v-timeline class="my-5">
      <v-timeline-item v-for="data in sampleData" :key="data.no" large>
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
          <v-card-text
            >{{ data.intro }}</v-card-text
          >
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </v-container>
</template>

<script>
import { mdiAlarm } from '@mdi/js';

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
      sampleData: [
        {
          no: 1,
          time: "12:00",
          band: "SURL",
          intro: "브리티시 록, 블루스 기반의 다양한 음악을 연주"
        },
        {
          no: 2,
          time: "14:00",
          band: "세이수미",
          intro: "부산 남포동의 한 찻집에서 '심심한데 밴드나 해보자'는 말 한마디에 꾸려진 밴드"
        },
        {
          no: 3,
          time: "17:00",
          band: "DASUTT",
          intro: "멤버가 다섯이었다가 네명이 되었다는 밴드, 빈디지하고 펑키한 사운드"
        },
        {
          no: 4,
          time: "20:00",
          band: "wetter",
          intro: "트렌디하면서도 전통적인 록 성향을 가진 밴드"
        },
        {
          no: 5,
          time: "22:00",
          band: "맥거핀",
          intro: "록 기반이지만 자신들의 색깔을 확고히 한 밴드"
        },
      ],
    };
  },
  methods: {},
};
</script>

<style></style>
