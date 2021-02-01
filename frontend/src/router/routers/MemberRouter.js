// Components import
import Signup from '../../components/signup/SignUp';
import Mypage from '../../components/mypage/MyPage';

// MemberRouter
const memberRoute = [
    {   // 회원가입 route
        path: '/signup',
        name: 'signup',
        component: Signup,
        props: false,
    },
    {   // 내 정보 route
        path: '/member/:memberno',
        name: 'mypage',
        component: Mypage,
        props: false,
    },
];

export default memberRoute;