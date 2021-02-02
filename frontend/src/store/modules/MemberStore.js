//import axios from "axios";

const MemberStore = {
  namespaced: true, // 모듈 개발사용 가능
  state: {
    isLogin: false, // 로그인 여부
    memberId: null, // 로그인된 아이디
    manager: false, // 관리자 여부

  },

  getters: {
    getIsLogined(state) {//로그인상태 받아옴
      return state.isLogin;
    },
    getMemberId(state) {//멤버아이디 받아옴
      return state.memberId;
    },
    getManager(state) {//멤버 관리자여부 받아옴
      return state.manager;
    }

  },
  mutations: {
    setIsLogined(state, isLogin) {//로그인상태 설정
      state.isLogin = isLogin;
    },

    setMemberId(state, memberid) {//멤버아이디 설정
      state.memberId = memberid;
    },

    setManager(state, manager) {//관리자상태 설정
      state.manager = manager;
    },

    logout(state) {//로그아웃 시 변수 초기화
      state.isLogin = false;
      state.memberId = null;
      state.manager = false;
    }
  },


  actions: {

    Login(context, member) {
      console.log("로그인시작");
      /* 백엔드 연동 후 return까지 내용 삭제 후 하단 주석 해제*/
      
      localStorage.setItem("access-token", "token");

      context.commit("setIsLogined", true);
      context.commit("setMemberId", "ssafy01");
      context.commit("setManager", false);

      return console.log(member.password + " " + member.email);
      
      //백엔드 연동 후 아래 주석 해제
      /* 
      return axios
        .post("/login", {//유저정보를 보냄
          member: member
        })
        .then((response) => {//성공 시 변수 재설정
          if (response.data.message === "success") {
            console.log("성공");

            //토큰생성 및 저장
            let token = response.data["access-token"];
            localStorage.setItem("access-token", token);//로컬스토리지에 토큰저장

            context.commit("setIsLogined", true); //로그인 상태 true로 변환
            context.commit("setMemberId", response.data.id);//memberid 저장
            context.commit("setManager", response.data.manager);//관리자 여부저장
          } else {
            console.log("실패");
          }
        });*/
    },

    Logout(context) {//로그아웃
      context.commit("logout");
      localStorage.removeItem("access-token");
    }

  }

}

export default MemberStore;