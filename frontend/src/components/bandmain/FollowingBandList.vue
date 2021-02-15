<template>
  <v-container class="mb-10">
    <v-row>
      <v-col cols="2">
        <v-banner class="my-10">
          <strong>팔로우 중인 밴드</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row justify="center" v-if="followBand == null">
      <v-col cols="6">
        <v-spacer />
        <v-alert
          align="center"
          border="bottom"
          colored-border
          type="warning"
          elevation="2"
        >
          <div class="title">팔로잉 중인 밴드가 없습니다</div>
          <div>새로운 밴드 팔로잉를 시작 해 보세요!</div>
        </v-alert>
        <v-spacer />
      </v-col>
    </v-row>
  
    <v-row v-else >
      <v-col v-for="band in getFollowList" :key="band.bandId" cols="2">
        <v-tooltip bottom nudge-top="5">
          <template v-slot:activator="{ on, attrs }">
            <v-card style="border-radius: 50%" v-bind="attrs" v-on="on" @click="bandinfo(band.bandId)">
              <!-- 밴드이미지가 없을 때 -->
              <v-img
                v-if="band.img == '' || band.img == null"
                src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                max-height="200px"
              >
                <v-card-title v-text="band.name"></v-card-title>
              </v-img>
              <!-- 밴드이미지가 있을 때 -->
              <v-img
                v-else
                :src="band.img"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                max-height="200px"
              >
                <v-card-title v-text="band.name"></v-card-title>
              </v-img>
            </v-card>
          </template>
          <span>클릭 시 해당밴드로 이동합니다</span>
        </v-tooltip>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters,mapActions } from "vuex";
const FollowStore = "FollowStore";

export default {
  data() {
    return {
      followBand : []
    };
  },
  computed: {
    ...mapGetters(FollowStore, [ "getFollowList"]),
  },
  created() {
    //팔로우 중인 밴드 정보 가져오기
    this.getFollowBand();
  },
  methods: {
    ...mapActions(FollowStore,["reqFollowList"]),

    bandinfo(val) {
      //밴드소개페이지로 이동
      this.$router.push("/band/introduce/" + val);
    },
    getFollowBand() {
      //팔로우 중인 밴드리스트 가져오기
      this.reqFollowList(this.$route.params.memberno);
    },
  },
};
</script>

<style>
</style>