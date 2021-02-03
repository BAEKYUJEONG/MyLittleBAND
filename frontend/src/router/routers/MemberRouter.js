// Components import
import Signup from '../../components/signup/SignUp';
import Mypage from '../../components/mypage/MyPage';
import MemberModify from '../../components/mypage/MemberModify'
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
    {   // 내 정보 수정 route
        path: '/member/modify/:memberno',
        name: 'membermodify',
        component: MemberModify,
        props: false,
    },
];

export default memberRoute;