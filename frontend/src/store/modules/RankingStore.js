import axios from "axios";

const RankingStore = {
  namespaced: true,
  state: {
    /*
      ranking: [
        {
          rankname: "",
          items: [
            ????
          ],
        }
      ]
    */
    //ranking: [],
    
    // sample data
    ranking: [
      {
        rankname: "일간 랭킹",
        items: [
          { no: 1, title: "Thank you", band: "로빈", view: "1987" },
          { no: 2, title: "앵콜요청금지", band: "브로콜리 너마저", view: "1253" },
          { no: 3, title: "행운을 빌어요", band: "페퍼톤스", view: "937" },
          { no: 4, title: "챠우챠우", band: "델리스파이스", view: "787" },
          { no: 5, title: "리코타 치즈 샐러드", band: "소란", view: "517" },
        ],
      },
      {
        rankname: "주간 랭킹",
        items: [
          { no: 6, title: "0308", band: "보수동쿨러", view: "20487" },
          { no: 7, title: "피너츠송", band: "상상밴드", view: "19879" },
          { no: 8, title: "꿈에선 놀아줘", band: "루싸이트 토끼", view: "16942" },
          { no: 9, title: "연애상담인듯", band: "헤르쯔 아날로그", view: "14624" },
          { no: 10, title: "점심시간", band: "다섯(Dasutt)", view: "13452" },
        ],
      },
      {
        rankname: "월간 랭킹",
        items: [
          { no: 11, title: "위잉위잉", band: "혁오", view: "194387" },
          { no: 12, title: "파도", band: "새소년", view: "185824" },
          { no: 13, title: "봄의 발라드", band: "세렝게티", view: "175942" },
          { no: 14, title: "사랑하긴 했었나요 스쳐가는 인연이었나요 짧지않은 우리 함께했던 시간들이 자꾸 내 마음을 가둬두네", band: "잔나비", view: "164942" },
          { no: 15, title: "12:00", band: "칵스", view: "144252" },
        ],
      },
    ],
  },
  getters: {
    getRanking(state) {
      return state.ranking;
    }, // 전체 랭킹 얻기
  },
  mutations: {
    // 랭킹 store 셋팅
    setRanking(state, payload) {
      state.ranking = payload;
    },
  },
  actions: {
    // 랭킹 정보 요청
    reqRanking(context) {
      return axios
        .get("/rank")
        .then((response) => {
          context.commit("setRanking", response.data);
        })
        .catch((error) => {
          // TODO: 에러 상황 정리해서 만들기
          console.log(error);
        });
    },
  },
};

export default RankingStore;
