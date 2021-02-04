// Components import
import Signup from '../../components/signup/SignUp';
import Mypage from '../../components/mypage/MyPage';
import MemberModify from '../../components/mypage/MemberModify';
import AfterSignUp from '../../components/signup/AfterSignUp';
import AfterEmail from '../../components/signup/AfterEmail';

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
    {   // 이메일 인증 요청 route
        path: '/validate',
        name: 'validate',
        component: AfterSignUp,
        props: false,
    },
    {   // 이메일 인증 완료 route
        path: '/validated',
        name: 'validated',
        component: AfterEmail,
        props: false,
    },
];

export default memberRoute;