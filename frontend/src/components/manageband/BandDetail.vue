<!-- 밴드소속 멤버들(크루원)에게 보여 줄 페이지 -->
<template>
  <v-main>
    <v-row class="px-10">
      <v-col cols="12">
        <v-banner class="my-10">
          <strong>밴드소개</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row class="ma-auto pa-10" justify="center" align="center">
      <!-- 밴드 프로필 이미지-->
      <v-col cols="3" class="ma-auto" >
        <v-spacer></v-spacer>
        <v-card height="300" width="300" id="rounded-card" class="ma-auto">
          <v-layout column align-center justify-center>
            <v-img
              v-if="band.img == '' || band.img == null"
              class="ma-auto mp-4"
              height="300"
              width="300"
              style="border-radius: 50%"
              src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
            ></v-img>
            <v-img
              v-else
              class="ma-auto mp-4"
              height="300"
              width="300"
              style="border-radius: 50%"
              :src="band.img"
            >
            </v-img>
          </v-layout>
        </v-card>
      </v-col>

      <!-- 밴드 소개  -->
      <v-col cols="4" class="ma-auto">
        <v-text-field
          v-model="band.name"
          label="밴드명"
          outlined
          readonly
        ></v-text-field>

        <v-textarea
          v-model="band.intro"
          label="소개"
          outlined
          readonly
        ></v-textarea>
      </v-col>
      <!-- 밴드 멤버 -->
      <v-col cols="4" class="ma-auto">
        <v-card>
          <v-card-title left
            >멤버구성
            <v-spacer></v-spacer>
            <v-btn class="kakao-link" @click="memberInvite()" x-small v-if="isChief == 1">
              멤버초대
            </v-btn>
          </v-card-title>
          <v-text-field
            v-for="member in members"
            :key="member.no"
            v-model="member.name"
            :label="member.codeSession"
            readonly
            class="ma-auto pa-2"
          >
          </v-text-field>
        </v-card>
      </v-col>
    </v-row>
    <v-divider></v-divider>

    <!-- 달력 -->
    <BandCalender />

    <v-divider></v-divider>

    <!-- 공연영상 -->
    <v-row class="ma-auto px-10">
      <v-col>
        <!-- 제목(배너) -->
        <v-banner class="pa-10">
          <strong>공연영상</strong>
        </v-banner>

        </v-col>
    </v-row>

    <v-row class="ma-auto px-10" justify="center">
      <!-- 랭킹 영상이 없을 때 -->
      <v-col cols="4" v-if="videolist.length == 0">
        <v-alert align="center" type="warning">
          밴드영상 게시물이 존재하지 않습니다.
        </v-alert>
      </v-col>

      <v-col v-else>
        <!-- 목록 -->
        <v-sheet class="mx-auto" elevation="8">
          <v-slide-group class="pa-4" show-arrows>
            <v-slide-item
              v-for="video in videolist"
              :key="video.boardId"
              v-slot="{ active }"
            >
              <v-card
                :color="active ? 'grey lighten-1' : ''"
                class="d-flex ma-4 flex-end flex-column"
                height="300"
                width="500"
                @click="[active, videoDetail(video.boardId)]"
              >
                <v-card-text class="text-end mt-auto">
                  <p>{{ video.boardSubject }}</p>
                </v-card-text>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
    </v-row>

    <v-divider></v-divider>
    <!-- 활성화버튼 -->
    <v-row class="ma-auto pa-10" justify="center">
      <!-- 밴드장일때 -->
      <v-col cols="auto" v-if="isChief == 1" >
        <v-btn
          v-for="c in chief"
          :key="c.text"
          @click.native="onClick(c.text)"
          color="primary"
          class="mx-6"
        >
          {{ c.text }}
        </v-btn>
      </v-col>

      <!-- 밴드원일때 -->
      <v-col cols="auto" v-else>
        <v-btn color="primary" class="mx-6" @click="upload()">영상업로드</v-btn>

        <v-btn
          color="primary"
          class="mx-6"
          router-link
          :to="'/band/board/' + this.$route.params.bandno"
          >밴드게시판</v-btn
        >

        <!-- 밴드탈퇴 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="error"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-6"
            >
              밴드탈퇴
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드탈퇴 </v-card-title>
            <v-card-text>정말 탈퇴하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="remove()"> 예 </v-btn>
              <v-btn color="green darken-1" text @click="dialog = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
import { mapGetters } from 'vuex'; //vuex사용
const MemberStore = 'MemberStore'; //MemberStore 모듈 사용
import axios from '../../axios/axios-common';
import BandCalender from '../manageband/BandCalender';

export default {
  data: () => {
    return {
      dialog: false,
      //로그인 한 아이디의 정보
      isChief: '0',
      crewId: '',
      chief: [
        //밴드장일 때 나오는 버튼들
        {
          text: '밴드정보수정',
        },
        {
          text: '영상업로드',
        },
        {
          text: '밴드게시판',
        },
        {
          text: '공연신청',
        },
        {
          text: '돌아가기',
        },
      ],
      //캘린더 관련 데이터
      dateOpen: false,
      start: '',
      type: 'month',
      typeOptions: [
        { text: 'Day', value: 'day' },
        { text: 'Week', value: 'week' },
        { text: 'Month', value: 'month' },
      ],

      band: {}, //밴드정보
      members: [], //소속멤버정보
      videolist: [],
    };
  },
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberId: 'getMemberId', //memberId 변수에 getMemberId 리턴값 저장
    }),
  },
  created() {
    //백엔드와 연동 후 아래 주석 해제

    this.getBandinfo(); //밴드정보가져오기
    this.getMemberinfo(); //밴드소속 멤버정보 가져오기
    this.getVideolist(); //밴드의 비디오리스트 가져오기
  },
  methods: {
    getBandinfo() {
      //밴드 정보 불러옴
      axios
        .get('/band/' + this.$route.params.bandno)
        .then((response) => {
          this.band = response.data.object;
          //console.log(response.data.object.name)
        })
        .catch((exp) => alert(exp + '밴드정보 조회에 실패하였습니다.'));
    },
    getMemberinfo() {
      //밴드 소속 멤버 정보를 불러옴
      axios
        .get('/band/member/' + this.$route.params.bandno)
        .then((response) => {
          this.members = response.data.object;
          this.getMember(); //로그인 멤버 정보 가져오기
        })
        .catch((exp) => alert(exp + '소속 멤버 조회에 실패하였습니다.'));
    },
    getVideolist() {
      //밴드의 비디오 리스트를 조회합니다.
      axios
        .get('/videoboard/videolist/' + this.$route.params.bandno)
        .then((response) => (this.videolist = response.data.object))
        .catch((exp) => alert(exp + '비디오리스트 조회에 실패하였습니다.'));
    },
    getMember() {
      //밴드장 여부 확인을 위해 불러옴
      for (let i = 0; i < this.members.length; i++) {
        if (this.members[i].memberId == this.memberId) {
          if (this.members[i].isChief == '1') this.isChief = '1';
          this.crewId = this.members[i].crewId;
          break;
        }
      }
    },
    memberInvite() {
      // 밴드 멤버 초대
      // 영현이의 도움이 필요

      // bandid 필요함. memberid도 필요함.
      let url =
        'https://i4a408.p.ssafy.io/bandinvite?bandno=' + this.$route.params.bandno;
      let descr = this.band.name + ' 밴드에 가입하세요.';
      console.log(url);
      window.Kakao.Link.createDefaultButton({
        container: '.kakao-link',
        objectType: 'feed',
        content: {
          title: '나작밴!',
          description: descr,
          imageUrl:
            'https://cdn.crowdpic.net/list-thumb/thumb_l_238B4B58BB065F4F5E114CDFAD1E70AC.jpg',
          link: {
            webUrl: url,
            mobileWebUrl: url,
          },
        },
        social: {
          likeCount: 300,
          commentCount: 400,
          sharedCount: 500,
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: url,
              webUrl: url,
            },
          },
          {
            title: '앱으로 이동',
            link: {
              mobileWebUrl: url,
              webUrl: url,
            },
          },
        ],
      });
    },
    videoDetail(value) {
      //비디오게시판 비디오 상세로 라우터링크
      this.$router.push('/video/' + value);
    },
    onClick(value) {
      console.log(value);
      if (value == '밴드정보수정') {
        this.$router.push('/band/modify/' + this.$route.params.bandno);
      } else if (value == '영상업로드') {
        this.upload();
      } else if (value == '밴드게시판') {
        this.$router.push('/band/board/' + this.$route.params.bandno);
      } else if (value == '공연신청') {
        this.$router.push('/band/reserve/' + this.$route.params.bandno);
      } else if (value == '돌아가기') {
        this.$router.push('/band/list/' + this.memberId);
      }
    },
    remove() {
      //밴드탈퇴
      axios
        .delete('/band/member/' + this.crewId)
        .then((response) => {
          if (response.data.data == 'success') {
            alert('탈퇴에 성공하였습니다.');
            this.dialog = false;
            this.$router.push('/band/list/' + this.memberId);
          }
        })
        .catch((exp) => alert(exp + '탈퇴에 실패하였습니다.'));
    },
    upload(){
      this.$router.push({name : 'videocreate'});
    }
  },
  components: {
    BandCalender,
  },
};
</script>

<style>
#rounded-card {
  border-radius: 50%;
  min-height: 200px;
  min-width: 200px;
}
</style>
