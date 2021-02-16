import axios from "../../axios/axios-common";

const ExampleStore = {
  namespaced: true,
    state: {
      listnum : 0,
    notices: [  // 백엔드에서 페이지 번호당 10개씩 묶어 줄 예정
      { no: 10, title: "나작밴 운영정책 변경", date: "2021-02-08" },
      { no: 9, title: "개인정보 처리방침 개정안내", date: "2021-02-06" },
      { no: 8, title: "나작밴 계정 약관", date: "2021-02-01" },
      { no: 7, title: "나작밴 서비스 점검", date: "2021-01-28" },
      { no: 6, title: "나작밴 랭킹 서비스 오픈", date: "2021-01-19" },
      { no: 5, title: "나작밴 서비스 점검", date: "2021-01-10" },
      { no: 4, title: "나작밴 휴면 전환 계정 처리 방침", date: "2021-01-05" },
      { no: 3, title: "나작밴 2021년 새해인사", date: "2021-01-01" },
      { no: 2, title: "크리스마스 이벤트 안내", date: "2020-12-23" },
      { no: 1, title: "나작밴 서비스 오픈", date: "2020-12-12" },
    ],
    notice: {
        title: "테스트 글입니다",
        content: '안녕하세요. 카카오(이하 ‘회사’)입니다.\
        \
        서비스를 이용해 주시는 이용자 여러분께 감사드리며, 새로운 개인정보 처리방침 적용에 관한 안내 말씀 드립니다\
        \
        \
        회사는 이용자 여러분의 개인정보를 무엇보다 소중하게 처리하고 있으며, 어떤 사안보다도 우선하여 안전하게 관리하고 있습니다.\
        \
        \
        새롭게 변경될 개인정보 처리방침 내용을 확인하시고 서비스 이용에 참고하시기 바랍니다.\
        \
        1. 변경 사항\
          (1) 개인정보 제3자 제공 내용 추가\
               - \'카카오톡 채널 상품구독형\' 서비스의 개인정보 제3자 제공 목적 및 항목이 추가됩니다.\
        \
        2. 변경 시기\
         변경된 개인정보 처리방침은 2021년 02월 05일(금)자로 효력이 발생됩니다.\
        \
        3. 문의 및 동의 철회\
        \
        새로운 개인정보 처리방침 내용에 동의하지 않으시는 경우, 각 서비스 내에서 “탈퇴”를 신청하여 회원 탈퇴를 하실 수 있습니다.\
        개인정보 처리방침 내용에 대한 문의사항이 있으신 경우,\
        \
        개인정보보호 담당부서(카카오: 고객센터 ) 로 문의주시면 친절하게 안내해 드리겠습니다.\
        회사는 앞으로도 이용자의 개인정보를 보다 안전하게 보호할 것을 약속드리며, 신뢰받는 서비스로 보답하겠습니다.\
        감사합니다.'
    }
  },
  getters: {
    getNotices(state) { return state.notices; },
      getNotice(state) { return state.notice; },
      getListnum(state) { return state.listnum; },
  },
  mutations: {
    setNotices(state, payload) { state.notices = payload; },
    setNotice(state, payload) { state.notice = payload; },
    setListnum(state, payload) { state.listnum = payload; },
  },
  actions: {
    // 공지사항 리스트 받아오기
    reqNotices(context,no){
        return axios
        .get("/notice/listpage/"+no)
        .then((response) => {
            if(response.data.status){
                context.commit("setNotices", response.data.object);
                return { result: true, msg: "글 목록 받아오기 성공" };
            } else {
                return { result: false, msg: "글 목록을 불러오는데 실패했습니다" };
            }
        })
        .catch((error) => {
            console.log(error);
        })
      },
      // 공지사항 리스트개수 받아오기
    reqListnum(context){
        return axios
        .get("/notice/list")
        .then((response) => {
            if (response.data.status) {
                context.commit("setListnum", response.data.object);
                return { result: true, msg: "글 목록 받아오기 성공" };
            } else {
                return { result: false, msg: "글 목록을 불러오는데 실패했습니다" };
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 공자사항 글 받아오기
    reqNotice(context, no){
        return axios
        .get("/notice/"+no)
        .then((response) => {
            if(response.data.status){
                context.commit("setNotice", response.data.object);
            } else {
                return { result: false, msg: "글을 불러오는데 실패했습니다" };
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 공지사항 글 생성하기
      reqCreateNotice(context, info) {
          console.log(info);
        return axios
        .post("/notice", {
            noticeTitle: info.title,
            noticeContent: info.content
        })
        .then((response) => {
            if(response.data.status)    return { result: true, msg: "글 작성이 완료되었습니다" };
            else                        return { result: false, msg: "글 작성이 실패했습니다" };
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 공지사항 글 수정하기
    reqModifyNotice(context, info) {
        return axios
        .put("/notice/"+info.no, {
            noticeTitle: info.title,
            noticeContent: info.content,
        })
        .then((response) => {
            if(response.data.status)    return { result: true, msg: "글 수정이 완료되었습니다" };
            else                        return { result: false, msg: "글 수정이 실패했습니다" };
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 공지사항 글 삭제하기
    reqDeleteNotice(context, no) {
        return axios
        .delete("/notice/"+no)
        .then((response) => {
            if(response.data.status)    return { result: true, msg: "글 삭제가 완료되었습니다" };
            else                        return { result: false, msg: "글 삭제가 실패했습니다" };
        })
        .catch((error) => {
            console.log(error);
        })
    }
  },
};

export default ExampleStore;
