<template>
  <v-container class="my-20">
    <v-card elevation="5" class="my-10" id="bg">
      <!-- 랭킹이 존재하지 않음 -->
      <v-row v-if="getRanking.length <= 1">
        <v-spacer />
        <v-col class="py-10" cols="3"
          ><v-alert align="center">
            랭킹 목록이 존재하지 않습니다.
          </v-alert></v-col
        >
        <v-spacer />
      </v-row>

      <!-- 랭킹 종목 -->
      <v-col v-else v-for="(r, idx) in getRanking[0][0]" :key="r.title">
        <!-- 랭킹 제목(배너) -->
        <v-sheet class="my-10 ml-15 display-1" id="batitle">
          <v-icon
            :color="icons[Number(idx.substr(1)) - 1][1]"
            size="40"
            class="mr-3"
            >{{ icons[Number(idx.substr(1)) - 1][0] }}</v-icon
          >{{ r }}
        </v-sheet>

        <!-- 랭킹 목록 -->
        <v-sheet class="mx-10" elevation="8" color="rgba(240, 240, 240, 1)">
          <v-slide-group
            class="pa-4"
            next-icon="mdi-chevron-right"
            prev-icon="mdi-chevron-left"
            show-arrows
          >
            <v-slide-item
              v-for="n in getRanking[idx.substr(1)]"
              :key="n.boardId"
              v-slot="{ active }"
            >
              <v-tooltip bottom nudge-top="5">
                <template v-slot:activator="{ on, attrs }">
                  <v-card
                    v-bind="attrs"
                    v-on="on"
                    :color="active ? 'grey lighten-1' : ''"
                    class="d-flex ma-4 flex-end flex-column"
                    height="320"
                    width="470"
                    @click="[active, onVideoCard(n.boardId)]"
                  >
                    <v-img height="200" :src="n.boardThumbnail"> </v-img>

                    <v-row>
                      <v-col
                        ><v-card-title v-if="n.boardView != undefined"
                          ><v-icon color="grey darken-3" class="mr-2"
                            >mdi-eye</v-icon
                          >{{ n.boardView }}</v-card-title
                        ><v-card-title v-else-if="n.boardLike != undefined"
                          ><v-icon color="grey darken-3" class="mr-2"
                            >mdi-heart</v-icon
                          >{{ n.boardLike }}</v-card-title
                        >
                      </v-col>
                      <v-col>
                        <div class="text-end mb-1 mr-2">
                          <v-card-text
                            class="headline font-weight-black text-no-wrap text-truncate"
                            style="width: 20rem"
                            >{{ n.boardSubject }}</v-card-text
                          >
                          <v-card-text
                            style="font-size:17px;font-weight:bold;"
                            class=" text-truncate"
                            >{{ n.name }}</v-card-text
                          >
                        </div>
                      </v-col>
                    </v-row>
                  </v-card>
                </template>
                <span>{{ n.boardSubject }}</span>
              </v-tooltip>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
    </v-card>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

const RankingStore = 'RankingStore';
const VideoStore = 'VideoStore';
const MemberStore = 'MemberStore';

export default {
  data() {
    return {
      icons: [
        ['mdi-eye', 'blue-grey darken-1'],
        ['mdi-fire', 'red'],
      ],
    };
  },
  created() {
    this.reqRanking();
  },
  computed: {
    ...mapGetters(RankingStore, ['getRanking']),
    ...mapGetters(MemberStore, ['getIsLogined']),
  },
  methods: {
    ...mapActions(RankingStore, ['reqRanking']),
    ...mapActions(VideoStore, ['reqVideo']),
    // 비디오 카드 클릭
    onVideoCard(videono) {
      if (this.getIsLogined) {
        this.reqVideo(videono)
          .then((response) => {
            if (!response) this.$router.push('/video/' + videono);
            else alert(response.msg);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert('로그인후 이용해주세요');
      }
    },
  },
};
</script>

<style scoped>
#bg {
  background-color: rgba(255, 255, 255, 0.5);
}

#batitle {
  background-color: rgba(255, 255, 255, 0);
}
</style>
