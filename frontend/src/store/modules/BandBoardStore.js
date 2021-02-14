import axios from "../../axios/axios-common";

const BandBoardStore = {
    namespaced: true,
    state: {
        boards: [  // 백엔드에서 페이지 번호당 10개씩 묶어 줄 예정
            { no: 10, title: "다음 온라인 공연 일정 안내입니다", date: "2021-02-08" },
            { no: 9, title: "기다리고있습니다", date: "2021-02-06" },
            { no: 8, title: "유튜브를 보고 들어온 팬이에요~", date: "2021-02-01" },
            { no: 7, title: "SNS는 안하시나요??", date: "2021-01-28" },
            { no: 6, title: "유투브 개설했습니다!", date: "2021-01-19" },
            { no: 5, title: "다음 공연은 언제쯤", date: "2021-01-10" },
            { no: 4, title: "작은밴드 팬을 위한 선물", date: "2021-01-05" },
            { no: 3, title: "직은밴드 2021년 새해인사", date: "2021-01-01" },
            { no: 2, title: "크리스마스 공연 안내", date: "2020-12-23" },
            { no: 1, title: "첫번째 글!", date: "2020-12-12" },
        ],
        board: {
            title: "테스트 글입니다",
            content: '게시물 더미데이터를 짜는게 너무어려워 제발 도와줘',
            memberId: "1",
            writer : "길동 홍",
            views: "11"
        },
        comments: [],
    },
    getters: {
        getBoards(state) { return state.boards; },
        getBoard(state) { return state.board; },
        getComments(state) { return state.comments; },
    },
    mutations: {
        setBoards(state, payload) { state.boards = payload; },
        setBoard(state, payload) { state.board = payload; },
        setComments(state, payload) { state.comments = payload; },
    },
    actions: {
        // 밴드게시판 리스트 받아오기
        reqBoards(context, info) {
            return axios
                .get("/band/board?bandId=" + info.bandId + "&page=" + info.page)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setBoards", response.data.object);
                        return { result: true, msg: "글 목록 받아오기 성공" };
                    } else {
                        return { result: false, msg: "글 목록을 불러오는데 실패했습니다" };
                    }
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        // 밴드게시판 글 받아오기
        reqBoard(context, boardno) {
            return axios
                .get("/band/board?boardno=" + boardno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setBoard", response.data);
                    } else {
                        return { result: false, msg: "글을 불러오는데 실패했습니다" };
                    }
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        // 밴드게시판 글 생성하기
        reqCreateBoard(context, info) {
            return axios
                .post("/band/board/" + info.bandId, {
                    title: info.title,
                    content: info.content,
                    writer: info.memberId,
                })
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "글 작성이 완료되었습니다" };
                    else return { result: false, msg: "글 작성이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        // 밴드게시판 글 수정하기
        reqModifyBoard(context, info) {
            return axios
                .put("/band/board/" + info.boardno, {
                    title: info.title,
                    content: info.content,
                    memberId : info.memberId,
                })
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "글 수정이 완료되었습니다" };
                    else return { result: false, msg: "글 수정이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        // 밴드게시판 글 삭제하기
        reqDeleteBoard(context, boardno) {
            return axios
                .delete("/band/board/" + boardno)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "글 삭제가 완료되었습니다" };
                    else return { result: false, msg: "글 삭제가 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        //댓글 리스트 받아오기.
        reqComments(context, boardno) {
            return axios
                .get('/board/band/comment' + boardno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit('setComments', response.data);
                        return { result: true, msg: '댓글 목록 받아오기 성공' };
                    } else {
                        return {
                            result: false,
                            msg: '댓글 목록을 불러오는데 실패했습니다',
                        };
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },

        //댓글 작성하기
        reqCreateComment(context, info) {
            return axios
                .post("/band/board/comment/" + info.boardno, {
                    content: info.content,
                    writer: info.memberId,
                })
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "댓글 작성이 완료되었습니다" };
                    else return { result: false, msg: "글 작성이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        //댓글 수정하기
        reqModifyComment(context, info) {
            return axios
                .put("/band/board/comment/" + info.commentno, {
                    content: info.content,
                })
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "댓글 수정이 완료되었습니다" };
                    else return { result: false, msg: "글 수정이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        
        //댓글 삭제하기
        reqDeleteComment(context, commentno) {
            return axios
                .delete("/band/board/comment/" + commentno)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "글 삭제가 완료되었습니다" };
                    else return { result: false, msg: "글 삭제가 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },
    },
};

export default BandBoardStore;
