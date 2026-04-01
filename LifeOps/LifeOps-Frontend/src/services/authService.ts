import API from "../api/axios";

export const login  = async (data:any)=>{
    const res = await API.post("/auth/login", data);
    return res.data;

}

export const register = async (data:any) =>{
    const res = await API.post("/auth/register", data);
    return res.data;

}