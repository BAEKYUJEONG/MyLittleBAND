import axios from "axios";

const ExampleStore = {
    namespaced: true,
    state: {
        /*
            Store에 필요한 변수들을 넣으면 됨
            
            items: [],
            item: {},
        */
        
    },
    getters: {
        /*
            필요한 getter를 넣으면 됨

            getItems(state) { return state.items; },
            getItem(state) { return state.item; },
        */
        
    },
    mutations: {
        /*
            store state에 셋팅
        
            setItems(state, payload) {
                state.items = payload;
            },
        */
    },
    actions: {
        /*
            비동기 통신으로 Backend에 요청 후
            값을 받아와서 처리(mutation으로 셋팅, or 값을 리턴)

            reqGeneralList(context) {
            return axios
            .get(`${SERVER_URL}board/general`)
            .then((response) => {
                context.commit("setItems", response.data);
            })

            // insert
            reqInsertList(context, item) {
                return axios
                    .post(`${SERVER_URL}board`, item)
                    .then((response) => {
                        return response.data;
                    })
            },
        */
    }
}

export default ExampleStore;