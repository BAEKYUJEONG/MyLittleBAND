import axios from "../../axios/axios-common";

const ExampleStore = {
  namespaced: true,
  state: {
    events: [
      {
        no: 1,
        title: "아이고난",
        start: "2021-02-06",
        end: "2021-02-15",
      },
      {
        no: 2,
        title: "뭉탱이로 있다가 유링게숭",
        start: "2021-02-05",
        end: "2021-02-21",
      },
    ],
    selectedEvent: {
      no: 2,
      title: "뭉탱이로 있다가 유링게숭",
      content: "뭉탱이로 있다가 유링게숭 아니그냥\n\
      뭉탱이로 있다가 유링게숭 아니그냥\n\
      뭉탱이로 있다가 유링게숭 아니그냥",
      start: "2021-02-05",
      end: "2021-02-21",
    },
  },
  getters: {
    getEvents(state) {
      let events = [];
      state.events.forEach((val) => {
        events.push({
          no: val.no,
          name: val.title,
          start: new Date(val.start+"T00:00:00"),
          end: new Date(val.end+"T23:59:59"),
          color: "#" + Math.round(Math.random() * 0xffffff).toString(16),
          timed: false,
        });
      });
      return events;
    },
    getSelectedEvent(state) {
      return {
        no: state.selectedEvent.no,
        name: state.selectedEvent.title,
        content: state.selectedEvent.content,
        start: state.selectedEvent.start,
        end: state.selectedEvent.end,
        color: state.selectedEvent.color,
      };
    },
  },
  mutations: {
    setEvents(state, payload) {
      state.events = payload;
    },
    setSelectedEvent(state, payload) {
      state.selectedEvent = payload;
    },
  },
  actions: {
    // 일정 받아오기
    reqEvents(context, bandno) {
      return axios
        .get("/calender/" + bandno)
        .then((response) => {
          context.commit("setEvents", response.data.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 세부 일정 받아오기
    reqEvent(context, info) {
      return axios
        .get("/calender/" + info.bandno + "/" + info.no)
        .then((response) => {
          if (response.data.status)
            context.commit("setEvent", response.data.data);
          else return { result: false, msg: "세부 일정을 받아오지 못했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 등록하기
    reqCreateEvent(context, bandno) {
      return axios
        .post("/calender/" + bandno, {})
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 등록이 완료되었습니다" };
          else return { result: false, msg: "일정 등록이 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 수정하기
    reqModifyEvent(context, info) {
      return axios
        .put("/calender/" + info.bandno + "/" + info.no, {})
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 수정이 완료되었습니다" };
          else return { result: false, msg: "일정 수정이 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 삭제하기
    reqDeleteEvent(context, info) {
      return axios
        .delete("/calender/" + info.bandno + "/" + info.no)
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 삭제가 완료되었습니다" };
          else return { result: false, msg: "일정 삭제가 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default ExampleStore;
