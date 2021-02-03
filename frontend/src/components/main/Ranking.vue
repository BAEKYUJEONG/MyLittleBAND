<template>
  <v-container>
    <!-- 랭킹이 존재하지 않음 -->
    <v-row v-if="getRanking.length === 0">
      <v-spacer />
      <v-col class="py-10" cols="3"
        ><v-alert align="center">
          랭킹 목록이 존재하지 않습니다.
        </v-alert></v-col
      >
      <v-spacer />
    </v-row>

    <!-- 랭킹 종목 -->
    <v-col v-else v-for="r in getRanking" :key="r.title">
      <!-- 랭킹 제목(배너) -->
      <v-banner class="my-10">
        <strong>{{ r.rankname }}</strong>
      </v-banner>

      <!-- 랭킹 목록 -->
      <v-sheet class="mx-auto" elevation="8">
        <v-slide-group class="pa-4" show-arrows>
          <v-slide-item
            v-for="n in r.items"
            :key="n.no"
            v-slot="{ active }"
          >
            <v-card
              :color="active ? 'grey lighten-1' : ''"
              class="d-flex ma-4 flex-end flex-column"
              height="300"
              width="500"
              @click="[active, getItem(n.no)]"
            >
              <v-card-text class="text-end">
                <v-icon size="25" class="mr-1">{{ icons.mdiEye }}</v-icon>
                {{ n.view }}
              </v-card-text>
              <v-card-text class="text-end mt-auto">
                <p>{{ n.title }}</p>
                <p>{{ n.band }}</p>
              </v-card-text>
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
    </v-col>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import { mdiEye } from '@mdi/js';

const RankingStore = "RankingStore";

export default {
  data() {
    return {
      icons: {
        mdiEye,
      },
    };
  },
  computed: {
    ...mapGetters(RankingStore, ["getRanking"]),
  },
  methods: {
    ...mapActions(RankingStore, ["reqRanking"]),
    // click item
    getItem(no) {
      alert(no);
    }
  },
};
</script>

<style></style>
