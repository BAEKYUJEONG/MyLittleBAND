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
      <v-col v-for="band in suggestBand" :key="band.bandId" cols="2">
        <v-tooltip bottom nudge-top="5">
          <template v-slot:activator="{ on, attrs }">
            <v-card
              style="border-radius: 50%"
              v-bind="attrs"
              v-on="on"
              @click="bandinfo(band.bandId)"
            >
              <!-- 밴드이미지가 없을 때 -->
              <v-img
                v-if="band.img == ''"
                src="../../assets/image/pepe.jpg"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                max-height="200"
                max-width="200"
              >
                <v-card-title v-text="band.name"></v-card-title>
              </v-img>
              <!-- 밴드이미지가 있을 때 -->
              <v-img
                v-else
                :src="band.img"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                max-height="200"
                max-width="200"
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
  async created() {
    //추천밴드리스트 가져오기
    await this.getFollowBand(); //팔로우중인 밴드
    await this.getbandlist();   //내가 소속된 밴드
    this.getSuggestBand();//추천밴드리스트 가져오기
  },
  watch:{//route이동시에도 created함수를 호출할 수 있도록 함
    '$route' : 'created'
  },
  methods: {
    BandSuggestPage() {
      //밴드 추천 페이지로 이동
      this.$router.push("/findfollowband/"+this.$route.params.memberno);
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
        .get("/followlist/1")
        .then((response)=>{
          if(response.data.status){
            this.tmplist = response.data.object;
            this.makeSuggestBand();
          }
        })
        .catch((exp) => alert(exp + "추천밴드목록 조회 실패"));
    },
    makeSuggestBand(){
      here : for(let i = 0; i < this.tmplist.length; i++){
        //추천밴드목록까지 반복

        for(let j=0; j< this.bandlist.length; j++){
          //소속밴드목록만큼 반복
          if(this.tmplist[i].bandId == this.bandlist[j].bandId){
            continue here;
          }
        }

        for(let j=0; j< this.followBand.length; j++){
          //팔로우중인 밴드목록만큼 반복
          if(this.tmplist[i].bandId == this.followBand[j].bandId){
            continue here;
          }
        }
        //해당하는게 없으면 추천밴드에 입력
        this.suggestBand.push(this.tmplist[i]);
      }
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
        tmplist:[],
      suggestBand: [
        {
          bandId: 4,
          name: "추천밴드명 뭐로하지",
          img: "",
        },
        {
          bandId: 5,
          name: "쉽지가않아요",
          img: "",
        },
        {
          bandId: 6,
          name: "그래도추천인데",
          img: "",
        },
        {
          bandId: 7,
          name: "밴드5개는있어야",
          img: "",
        },
        {
          bandId: 8,
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