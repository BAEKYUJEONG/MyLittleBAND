import Vue from 'vue';
import VueRouter from 'vue-router';

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
]

const router = new VueRouter({
    mode:'history',
    base : process.env.BASE_URL, // /
    routes,
});

export default router;