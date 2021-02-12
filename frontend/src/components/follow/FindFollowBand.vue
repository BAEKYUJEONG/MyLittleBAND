<template>
  <v-main>
    <v-container class="mb-10">
      <!-- 배너 -->
      <v-row justify="center">
        <v-col cols="6">
          <v-banner class="my-10">
            <strong>추천 팔로우 밴드</strong>
          </v-banner>
        </v-col>
      </v-row>
      <!-- 검색창 -->
      <v-row justify="center">
        <v-col cols="4">
          <v-text-field
            v-model="msg"
            label="밴드명"
            placeholder="원하는 밴드명을 입력하세요."
            append-outer-icon="mdi-magnify"
            clearable
            @click:append-outer="onSearch()"
          ></v-text-field>
        </v-col>
      </v-row>
      <!-- 밴드리스트 -->
      <v-row justify="center">
        <v-col cols="6">
          <v-tooltip
            right
            v-for="band in bandlist"
            :key="band.bandId"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-card class="mb-15" v-bind="attrs" v-on="on">
                <v-row class="px-10" align="center">
                  <v-col cols="3">
                    <!-- 밴드이미지가 없을 때 -->
                    <v-img
                      v-if="band.img == ''"
                      src="../../assets/image/pepe.jpg"
                      max-height="100"
                      max-width="100"
                      style="border-radius: 50%"
                    >
                    </v-img>
                    <!-- 밴드이미지가 있을 때 -->
                    <v-img
                      v-else
                      :src="band.img"
                      max-height="100"
                      max-width="100"
                      style="border-radius: 50%"
                    ></v-img>
                  </v-col>
                  <v-col cols="9" align-self="center">
                    <v-row>
                      <v-col>
                        <v-btn x-large text @click="BandDetail(band.bandId)"
                          ><strong> {{ band.name }} </strong></v-btn
                        ></v-col
                      >
                    </v-row>
                    <v-row>
                      <v-col>
                        <v-btn>{{band.follow}}</v-btn>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
                <v-card-actions>
                  <v-btn color="orange lighten-2" text> 밴드소개 </v-btn>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="show = !show">
                    <v-icon>{{
                      show ? "mdi-chevron-up" : "mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>
                <v-expand-transition>
                  <div v-show="show">
                    <v-divider></v-divider>
                    <v-card-text> {{band.intro}} </v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
            </template>
            <span >
                <div>밴드명 클릭 시</div>
                <div>해당밴드로 이동합니다</div>
                </span>
          </v-tooltip>
        </v-col>
      </v-row>
      <!-- 검색초기화, 돌아가기 버튼 -->
      <v-row justify="center">
        <v-col cols="4">
          <v-btn class="mx-6" color="primary" @click="getSuggetBandList()"
            >검색초기화</v-btn
          >
          <v-btn class="mx-6" color="primary" @click="ToBandList()"
            >돌아가기</v-btn
          >
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import axios from "../../axios/axios-common"
export default {

  async created() {
    await this.getFollowBand(); //팔로우중인 밴드
    await this.getbandlist();   //내가 소속된 밴드
     this.getSuggestBand();//추천밴드리스트 가져오기
    //this.getFollownum();
  },
  watch:{//route이동시에도 created함수를 호출할 수 있도록 함
    '$route' : 'created'
  },
  methods: {
    getSuggestBand() {
        //추천밴드목록 만들기
        //관리자계정의 팔로우 목록을 가져와 추천 목록으로 만들면 편할듯
        //그중에서 이미 팔로우 중인 밴드는 제외하기
        axios
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

        for(let j=0; j< this.Bandin.length; j++){
          //소속밴드목록만큼 반복
          if(this.tmplist[i].bandId == this.Bandin[j].bandId){
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
        this.bandlist.push(this.tmplist[i]);
      }
    },
    getFollowBand() {
      //팔로우 중인 밴드리스트 가져오기
      axios
        .get("/followlist/" + this.$route.params.memberno)
        .then((response) => {
          if (response.data.data == "success")
            this.followBand = response.data.object;
        })
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
    getbandlist() {
      //소속된 밴드리스트 조회
      axios
        .get("/band-list/" + this.$route.params.memberno)
        .then((response) => (this.Bandin = response.data.object))
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
    getFollownum(){
        //밴드별 팔로우 수 조회
        this.bandlist.follow = "1";
    },
    onSearch() {
        //밴드명으로 검색

      //만약 빈칸이면 초기 밴드정보 호출
      if (this.msg == "") {
        this.getSuggetBandList();
        return;
      }
       axios
       .post("/band/find",{msg : this.msg})
       .then((response)=>{
           if(response.data.status){
               this.bandlist = response.data.object;
           }else{
               alert("해당하는 검색 결과가 없습니다.");
           }
       })
       .catch((exp)=>alert(exp+"조회에 실패하였습니다."));
    },
    BandDetail(val) {
      //밴드상세페이지로 이동
      this.$router.push("/band/detail/" + val);
    },
    ToBandList() {
        //밴드리스트 페이지로 이동
      this.$router.push("/band/list/" + this.$route.params.memberno);
    },
  },

  data() {
    return {
      msg: "",
      show: false,
      followBand:[],
      Bandin:[],
      tmplist:[],
      bandlist: [
        {
          bandId: 1,
          name: "잘한다밴드",
          img: "",
          intro : "저희는 홍대에 터를두고 생활하는 밴드입니다."
        },
        {
          bandId: 2,
          name: "더잘한다밴드",
          img: require("../../assets/image/pepe.jpg"),
          intro : "궁금하면 들어와서 영상부터 만나봐"
        },
      ],
    };
  },
};
</script>

<style>
</style>