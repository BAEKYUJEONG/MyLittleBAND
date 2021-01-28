import Vue from 'vue';
import VueRouter from 'vue-router';
import Videocreate from '../components/boardvideo/VideoCreate.vue';
import Videolist from '../components/boardvideo/VideoList.vue'
import Videodetail from '../components/boardvideo/VideoDetail.vue'

Vue.use(VueRouter);

const routes = [
    //path: 
    //name: 
    //component:
    //한 쌍으로 넣어주기.
    /*
    {
        path : '/',
        name : 'main',
        component : Main
    },
    */
    // concat을 통해서 routes 추가할 계획.
    // 배열을 추가한다고 생각하면 됨.

    // 테스트 용으로 라우터에 추가해 둠.
    {
        path : '/test',
        name : 'Videocreate',
        component : Videocreate
    },
    {
        path : '/test2',
        name : 'Videolist',
        component : Videolist
    },
    {
        path : '/test3',
        name : 'Videodetail',
        component : Videodetail
    },
]

const router = new VueRouter({
    mode:'history',
    base : process.env.BASE_URL, // /
    routes,
});

export default router;