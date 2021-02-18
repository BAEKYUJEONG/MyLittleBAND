<template>
  <v-container class="mb-10">
    <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
    <v-row>
      <v-col cols="2">
        <v-banner class="mb-10">
          <strong>추천 팔로우 밴드</strong>
        </v-banner>
      </v-col>
      <v-col cols="8"></v-col>
      <v-col cols="2" class="ma-auto">
        <v-btn @click="BandSuggestPage()" icon>
          <v-icon color="info" x-large>mdi-plus-box-multiple</v-icon>
        </v-btn>
      </v-col>
    </v-row>

    <v-row>
      <v-col v-for="band in suggestBand" :key="band.bandId" cols="2">
        <v-tooltip bottom nudge-top="5">
          <template v-slot:activator="{ on, attrs }">
            <v-card
              style="border-radius: 50%"
              v-bind="attrs"
              v-on="on"
              height="200"
                width="200"
              @click="bandinfo(band.bandId)"
            >
              <!-- 밴드이미지가 없을 때 -->
              <v-img
                v-if="band.img == '' || band.img == null"
                src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200"
                width="200"
              >
                <v-card-title class="justify-center" v-text="band.name"></v-card-title>
              </v-img>
              <!-- 밴드이미지가 있을 때 -->
              <v-img
                v-else
                :src="band.img"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200"
                width="200"
              >
                <v-card-title class="justify-center" v-text="band.name"></v-card-title>
              </v-img>
            </v-card>
          </template>
          <span>클릭 시 해당밴드로 이동합니다</span>
        </v-tooltip>
      </v-col>
    </v-row>
    </v-card>
  </v-container>
</template>

<script>
import axiosCommon from "../../axios/axios-common";
import { mapGetters } from "vuex";
const FollowStore = "FollowStore";
const BandStore = "BandStore";
const MemberStore = "MemberStore";

export default {
  async created() {
    //추천밴드리스트 가져오기
    this.getSuggestBand(); //추천밴드리스트 가져오기
  },
  computed: {
    ...mapGetters(BandStore, [ "getBandList" ]),
    ...mapGetters(MemberStore,["getMemberId"]),
    ...mapGetters(FollowStore, [ "getFollowList" ]),
  },

  methods: {
    BandSuggestPage() {
      //밴드 추천 페이지로 이동
      this.$router.push("/findfollowband/" + this.$route.params.memberno);
    },
    bandinfo(val) {
      //밴드소개페이지로 이동
      this.$router.push("/band/introduce/" + val);
    },
    getSuggestBand() {
      //추천밴드목록 만들기
      //관리자계정의 팔로우 목록을 가져와 추천 목록으로 만들면 편할듯
      //그중에서 이미 팔로우 중인 밴드는 제외하기
      axiosCommon
        .get("/follow/"+this.getMemberId)
        .then((response) => {
          if (response.data.status) {
            this.suggestBand = response.data.object;
          }
        })
        .catch((exp) => alert(exp + "추천밴드목록 조회 실패"));
    },
    
  },
  data() {
    return {
      tmplist: [],
      suggestBand: [],
    };
  },
};
</script>

<style>
</style>