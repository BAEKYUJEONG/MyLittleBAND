import axios from "axios";

// axios 공통 설정
export default axios.create({
    baseURL: "http://i4a408.p.ssafy.io:8080/",
    headers: {
        "Content-type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "*"
    },
    //withCredentials: true,
});