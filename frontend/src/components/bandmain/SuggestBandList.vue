<template>
  <v-container class="mb-10">
    <v-row>
      <v-col cols="2">
        <v-banner class="my-10">
          <strong>추천 팔로우 밴드</strong>
        </v-banner>
      </v-col>
      <v-col cols="8"></v-col>
      <v-col cols="2" class="ma-auto">
        <v-btn @click="BandSuggestPage()" icon>
            <v-icon x-large>mdi-plus-box-multiple</v-icon> </v-btn>
      </v-col>
    </v-row>

    <v-row>
      <v-col v-for="band in suggestBand" :key="band.bandid" cols="2">
        <v-tooltip bottom nudge-top="5">
          <template v-slot:activator="{ on, attrs }">
            <v-card
              style="border-radius: 50%"
              v-bind="attrs"
              v-on="on"
              @click="bandinfo(band.bandid)"
            >
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
import axiosCommon from '../../axios/axios-common';
export default {
  created() {
    //추천밴드리스트 가져오기
    //this.getFollowBand(); //팔로우중인 밴드
    //this.getbandlist();   //내가 소속된 밴드
    //this.getSuggestBand();//추천밴드리스트 가져오기
  },
  methods: {
    BandSuggestPage() {
      //밴드 추천 페이지로 이동
      //this.$router.push("/findfollow");
    },
    bandinfo(val) {
      //밴드소개페이지로 이동
      this.$router.push("/band/introduce/" + val);
    },
    getSuggestBand() {
        //추천밴드목록 만들기
        

        //전체밴드목록 중에서 내가 소속된 밴드 제외, 내가 팔로우 중인 밴드 제외 후 랜덤으로 추출

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
    getbandlist() {
      //소속된 밴드리스트 조회
      axiosCommon
        .get("/band-list/" + this.$route.params.memberno)
        .then((response) => (this.bandlist = response.data.object))
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
  },
  data() {
    return {
        bandlist:[],
        followBand:[],//추천밴드를 만드는데 사용됨
      suggestBand: [
        {
          bandid: 4,
          name: "추천밴드명 뭐로하지",
          img: "",
        },
        {
          bandid: 5,
          name: "쉽지가않아요",
          img: "",
        },
        {
          bandid: 6,
          name: "그래도추천인데",
          img: "",
        },
        {
          bandid: 7,
          name: "밴드5개는있어야",
          img: "",
        },
        {
          bandid: 8,
          name: "구실이있어보여",
          img: "",
        },
      ],
    };
  },
};
</script>

<style>
</style>