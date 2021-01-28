// Components import
import Timetable from "../../components/timetable/TimeTable";

// ExampleRouter 정의 부분
const timetableRoute = [
    {
        path: '/timetable',
        name: 'timetable',
        component: Timetable,
        props: false,
    },
];

export default timetableRoute;