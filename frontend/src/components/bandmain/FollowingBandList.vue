<template>
  <v-container class="mb-10">
    <v-row>
      <v-col cols="2">
        <v-banner class="my-10">
          <strong>팔로우 중인 밴드</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row justify="center" v-if="followBand.length == 0">
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
      <v-col v-for="band in followBand" :key="band.bandId" cols="2">
        <v-tooltip bottom nudge-top="5">
          <template v-slot:activator="{ on, attrs }">
            <v-card style="border-radius: 50%" v-bind="attrs" v-on="on" @click="bandinfo(band.bandId)">
              <!-- 밴드이미지가 없을 때 -->
              <v-img
                v-if="band.img == ''"
                src="../../assets/image/pepe.jpg"
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
import axiosCommon from "../../axios/axios-common";
export default {
  created() {
    //팔로우 중인 밴드 정보 가져오기
    this.getFollowBand();
  },
  data() {
    return {
      followBand: [
        {
          bandid: 3,
          name: "내이름은코난,밴드죠",
          img: "",
        },
        {
          bandid: 4,
          name: "나보다네가더잘한다밴드",
          img: require("../../assets/image/pepe.jpg"),
        },
      ],
    };
  },
  methods: {
    bandinfo(val) {
      //밴드소개페이지로 이동
      this.$router.push("/band/introduce/" + val);
    },
    getFollowBand() {
      //팔로우 중인 밴드리스트 가져오기
      axiosCommon
        .get("/followlist/" + this.$route.params.memberno)
        .then((response) => {
          if (response.data.data == "success")
            this.followBand = response.data.object;
        })
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
  },
};
</script>

<style>
</style>