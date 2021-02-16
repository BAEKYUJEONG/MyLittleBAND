<template>
  <v-main align="center">
    <v-row justify="center" aligh="center" class="ma-10">
      <v-col cols="4">
        <v-alert
          color="pink darken-1"
          outlined
          text
          border="left"
          v-if="!loginflag"
        >
          <div>로그인 후 초대 링크를 다시 클릭 해 주세요!</div>
        </v-alert>

        <v-alert type="success" outlined text border="left" v-else>
          <v-col class="ml-14" cols="4">
            <v-select
              outlined
              label="세션정보"
              :items="sessions"
              v-model="codeSession"
            ></v-select>
          </v-col>
          <div class="title">버튼을 누르면 밴드 가입이 완료됩니다.</div>
          <div class="mt-10">
            <v-btn rounded @click.once="onInviteBand()" color="warning">가입</v-btn>
          </div>
        </v-alert>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
import axios from '@/axios/axios-common.js';

import { mapGetters } from 'vuex';

const MemberStore = 'MemberStore';

export default {
  data() {
    return {
      codeSession: '',
      sessions: ['보컬', '키보드', '드럼', '일렉기타', '베이스'],
      email: '',
      bandNo: '',
      loginflag: false,
      bandlist: [],
    };
  },
  created() {
    this.bandNo = this.$route.query.bandno;
    this.loginflag = this.getIsLogined;
    this.bandlist = this.onBandList();
  },
  computed: {
    ...mapGetters(MemberStore, ['getIsLogined', 'getMemberId']),
  },
  methods: {
    onInviteBand() {
      if (this.codeSession == '') alert('세션을 선택해 주세요!');
      else {
        //console.log(this.bandlist);
        const size = this.bandlist.length;
        for (var i = 0; i < size; i++) {
          if (this.bandlist[i].memberId == this.getMemberId) {
            alert('이미 가입되어있는 밴드입니다.');
            this.$router.push("/band/list/"+this.getMemberId);
            return;
          }
        }
        axios
          .post('/band/invite', {
            memberId: this.getMemberId,
            bandId: this.bandNo,
            codeSession: this.codeSession,
          })
          .then((response) => {
            if (response.data.status){ 
              alert("밴드에 가입되었습니다!");
              this.$router.push("/band/list/"+this.getMemberId);
            }
          })
          .catch((exp) => alert(exp + '멤버정보 조회에 실패하였습니다.'));
      }
    },
    onBandList() {
      axios
        .get('/band/member/' + this.bandNo)
        .then((response) => {
          console.log(response);
          this.bandlist = response.data.object;
        })
        .catch((err) => alert(err));
    },
  },
};
</script>

<style></style>
