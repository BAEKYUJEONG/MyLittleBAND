<template>
  <v-main>
    <v-container class="my-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
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
            label="원하는 밴드명을 입력하세요."
            append-outer-icon="mdi-magnify"
            clearable
            @keypress.enter="onSearch()"
            @click:append-outer="onSearch()"
          ></v-text-field>
        </v-col>
      </v-row>
      <!-- 밴드리스트 -->
      <v-row justify="center">
        <v-col cols="6">
          <v-tooltip right v-for="band in bandlist" :key="band.bandId">
            <template v-slot:activator="{ on, attrs }">
              <v-card class="mb-15 rounded-tl-xl rounded-br-xl" v-bind="attrs" v-on="on" color="rgba(255, 255, 255, 0.9)">
                <v-row class="px-10" align="center">
                  <v-col cols="3">
                    <!-- 밴드이미지가 없을 때 -->
                    <v-img
                      v-if="band.img == '' || band.img == null"
                      src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                      height="100"
                      width="100"
                      style="border-radius: 50%"
                    >
                    </v-img>
                    <!-- 밴드이미지가 있을 때 -->
                    <v-img
                      v-else
                      :src="band.img"
                      height="100"
                      width="100"
                      style="border-radius: 50%"
                    ></v-img>
                  </v-col>
                  <v-col cols="auto" >
                    <v-row>
                      <v-col>
                        <v-btn x-large text @click="BandDetail(band.bandId)"
                          ><h2>{{ band.name }}</h2></v-btn
                        ></v-col
                      >
                    </v-row>
                    <v-row>
                      <v-col
                        ><h3 style="color: black">
                          팔로워 수 : {{ band.follownum }}
                        </h3></v-col
                      >
                    </v-row>
                  </v-col>
                </v-row>
                <v-card-actions>
                  <v-btn color="orange lighten-2" text> 밴드소개 </v-btn>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="band.show = !band.show">
                    <v-icon>{{
                      band.show ? "mdi-chevron-up" : "mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>
                <v-expand-transition>
                  <div v-show="band.show">
                    <v-divider></v-divider>
                    <v-card-text> {{ band.intro }} </v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
            </template>
            <span>
              <div>밴드명 클릭 시</div>
              <div>해당밴드로 이동합니다</div>
            </span>
          </v-tooltip>
        </v-col>
      </v-row>
      <!-- 검색초기화, 돌아가기 버튼 -->
      <v-row justify="center">
        <v-col cols="auto">
          <v-btn
            class="mx-6"
            color="primary"
            @click="getSuggestBand()"
            >검색초기화</v-btn
          >
          <v-btn class="mx-6" @click="ToBandList()"
            >돌아가기</v-btn
          >
        </v-col>
      </v-row>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
import axios from "../../axios/axios-common";
import { mapGetters } from "vuex";
const MemberStore = "MemberStore";


export default {
  created() {
    this.getSuggestBand(); //추천밴드리스트 가져오기
  },
  computed: {
    ...mapGetters(MemberStore,["getMemberId"]),
  },
  methods: {
    getSuggestBand() {
      //추천밴드목록 만들기
      //관리자계정의 팔로우 목록을 가져와 추천 목록으로 만들면 편할듯
      //그중에서 이미 팔로우 중인 밴드는 제외하기
      axios
        .get("/follow/"+this.getMemberId)
        .then((response) => {
          if (response.data.status) {
            this.bandlist = response.data.object;
          }
        })
        .catch((exp) => alert(exp + "추천밴드목록 조회 실패"));
    },
    onSearch() {
      //만약 빈칸이면 초기 밴드정보 호출
      if (this.msg == "") {
        this.getSuggestBand();
        return;
      }
      axios
        .get("/band/find/" + this.msg)
        .then((response) => {
          if (response.data.status) {
            this.bandlist = response.data.object;
          } else {
            alert("해당하는 검색 결과가 없습니다.");
          }
        })
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));

        this.msg= "";
    },
    BandDetail(val) {
      //밴드상세페이지로 이동
      this.$router.push("/band/introduce/" + val);
    },
    ToBandList() {
      //밴드리스트 페이지로 이동
      this.$router.push("/band/list/" + this.$route.params.memberno);
    },
  },

  data() {
    return {
      msg: "",
      bandlist: [],
    };
  },
};
</script>

<style>
</style>