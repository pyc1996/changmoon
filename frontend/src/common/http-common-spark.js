import axios from "axios";

export default axios.create({
    //http://localhost:8123/
    baseURL: "https://j6e104.p.ssafy.io:58080/",
    header: {
        "Access-Control-Allow-Origin" : "*",
        "Access-Control-Allow-Methods" : "GET,POST,PUT,DELETE,OPTIONS",
        "Access-Control-Allow-Headers": "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"
    }
})