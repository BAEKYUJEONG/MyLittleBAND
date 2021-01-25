import axios from "axios";

// axios 공통 설정
export default axios.create({
    baseURL: "http://localhost",
    headers: {
        "Content-type": "application/json",
    }
});