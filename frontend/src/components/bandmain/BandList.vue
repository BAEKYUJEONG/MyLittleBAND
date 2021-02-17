<!-- 밴드 목록 보여 줄 페이지 -->
<template>
  <v-container class="mb-10">
    
    <v-row>
      <v-col cols="2">
        <v-banner class="my-10">
          <strong>소속밴드</strong>
        </v-banner>
      </v-col>
       <v-col cols="8"></v-col>
      <v-col cols="2" class="ma-auto">
        <v-btn @click="CreateBand()"> 밴드개설 </v-btn>
      </v-col>
    </v-row>
    
    <v-row justify="center">
      <!-- 가입된 밴드가 없을 때 -->
      <v-col cols="6" v-if="getBandList.length == 0">
        <v-spacer />
        <v-alert
          align="center"
          border="bottom"
          colored-border
          type="warning"
          elevation="2"
        >
          <div class="title">가입된 밴드가 없습니다</div>
          <div>밴드추가를 눌러 밴드를 시작해 보세요!</div>
        </v-alert>
        <v-spacer />
      </v-col>

      <!-- 가입한 밴드가 존재할 때 -->
      <v-col cols="6" v-else>
        <v-tooltip bottom nudge-top="5"  v-for="band in getBandList" :key="band.bandId">
          <template v-slot:activator="{ on, attrs }">
            <v-card class="mb-15"  v-bind="attrs" v-on="on">
              <v-row class="px-10">
                <v-col cols="3">
                  <!-- 밴드이미지가 없을 때 -->
                  <v-img v-if="band.img == '' || band.img == null"
                    src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                    height="100"
                    width="100"
                    style="border-radius: 50%">
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
                <v-col cols="6" align-self="center">
                  <v-btn x-large text @click="BandDetail(band.bandId)"
                    ><strong> {{ band.name }} </strong></v-btn
                  >
                </v-col>
                <v-col cols="3" align-self="center">
                  <!-- 밴드장이면 버튼 생성 -->
                  <v-btn v-if="band.isChief == 1" class="ma-2" color="indigo accent-1">
                    <v-icon color="#FFD600" x-large>mdi-crown</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </v-card>
          </template>
          <span>밴드명 클릭 시 해당밴드로 이동합니다</span>
        </v-tooltip>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters,mapActions } from "vuex";
const BandStore = "BandStore";

export default {
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters(BandStore, ["getBandList"]),
  },
  created() {
    //소속밴드정보가져오기
    this.reqBandList(this.$route.params.memberno);
  },
  methods: {
    ...mapActions(BandStore,["reqBandList"]),

    CreateBand() {
      //밴드생성페이지로 이동
      this.$router.push("/bandcreate");
    },
    BandDetail(val) {
      //밴드상세페이지로 이동
      this.$router.push("/band/detail/" + val);
    },

  },
  
};
</script>

<style>
</style>