import axios from "../../axios/axios-common";
import moment from "moment";

const TimetableStore = {
  namespaced: true,
  state: {
    shows: [],
    show: {},
  },
  getters: {
    getShows(state) {
      return state.shows;
    },
    getShow(state) {
      return state.show;
    },
  },
  mutations: {
    setShows(state, payload) {
      let target = [];
      payload.map((value) => {
        let date = moment(value.date + "T" + value.time);
        target.push({
          showId: value.showId,
          name: value.title,
          start: date.format("YYYY-MM-DDTHH:mm:ss"),
          end: date.add(1, "hours").format("YYYY-MM-DDTHH:mm:ss"),
          bandId: value.bandId,
          color: "#" + Math.round(Math.random() * 0xffffff).toString(16),
        });
      });

      state.shows = target;
    },
    setShow(state, payload) {
      state.show = {
        showId: payload.object.showId,
        title: payload.object.title,
        content: payload.object.showContent,
        start: moment(payload.object.date + "T" + payload.object.time).format(
          "YYYY-MM-DD hh:mm:ss"
        ),
        end: moment(payload.object.date + "T" + payload.object.time)
          .add(1, "h")
          .format("YYYY-MM-DD hh:mm:ss"),
        img: payload.object.band.img,
        bandId: payload.object.band.bandId,
        bandname: payload.object.band.name,
        color: payload.color,
      };
    },
  },
  actions: {
    // 지정된 기간의 show 불러오기
    reqShows(context, info) {
      return axios
        .get("/show/term/" + info.start + "/" + info.end)
        .then((response) => {
          if (response.data.status){
            context.commit("setShows", response.data.object);
          } else {
            return {
              result: false,
              msg: "타임테이블 정보를 불러오지 못했습니다.",
            };
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // show 클릭시 상세정보 보기
    reqShow(context, info) {
      return axios
        .get("/show/detail/" + info.showId)
        .then((response) => {
          if (response.data.status) {
            context.commit("setShow", {
              object: response.data.object,
              color: info.color,
            });
          } else
            return { result: false, msg: "공연 정보를 불러오지 못했습니다." };
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default TimetableStore;
