<template>
  <v-container class="my-20">
    <v-row justify="center">
      <v-col cols="12">
        <v-card elevation="5" class="pa-3" color="rgba(255, 255, 255, 0.5)">
          <!-- 위쪽 banner -->
          <v-banner single-line class="ma-5">
            <!--@click:icon="reqShow">-->
            <v-icon size="45">{{ icons.mdiAlarm }}</v-icon>

            <strong class="ml-10" style="font-size: 20px">오늘의 공연</strong>

            <!-- 전체 보기 -->
            <template v-slot:actions>
              <v-btn
                color="info"
                style="font-size: 20px; font-weight: bold"
                text
                router-link
                :to="links[0].path"
              >
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

          <v-timeline dense v-else class="my-2">
            <v-responsive class="overflow-y-auto" max-height="600">
              <v-timeline-item
                v-for="data in getTimeline"
                :key="data.time"
                right
                large
                color="black"
              >
                <template v-slot:icon size="64">
                  <v-avatar size="62">
                    <v-img
                      v-if="data.img == '' || data.img == null"
                      src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                    />
                    <v-img v-else :src="data.img" />
                  </v-avatar>
                </template>
                <template>
                  <span
                    ><strong>{{ data.time.substr(0, 5) }}</strong></span
                  >
                </template>
                <v-card
                  max-width="24rem"
                  class="elevation-2"
                >
                  <v-card-title
                    class="font-weight-black text-truncate"
                    style="width: 22rem"
                  >
                    {{ data.title }}
                  </v-card-title>
                  <v-card-actions>
                  <v-card-text
                    style="font-size: 17px; font-weight: bold"
                    class="text-truncate"
                    >{{ data.name }}
                    
                    </v-card-text
                  >
                    <v-btn icon @click="data.shows = !data.shows">
                    <v-icon>
                      {{
                      data.shows ? "mdi-chevron-down" : "mdi-chevron-up"
                    }}</v-icon></v-btn>
                  </v-card-actions>
                  <v-expand-transition>
                  <div v-show="!data.shows">
                    <v-divider></v-divider>
                    <v-card-text> {{ data.showContent }} </v-card-text>
                  </div>
                </v-expand-transition>
                </v-card>
              </v-timeline-item>
            </v-responsive>
          </v-timeline>
        </v-card>
      </v-col>
    </v-row>
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

<style scoped>
</style>
