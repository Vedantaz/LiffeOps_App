import API from "../api/axios";

export const getGoals = async()=>{
    const res = await API.get("/goals");
    return res.data;

}
export const createGoal = async(data:any) => {
    const res = await API.post("/goals", data);
    return res.data;

}

export const updateProgress = async(id:number, value:number) =>{
    const res = await API.patch(`/goals/${id}/progress?value=${value}`);
    return res.data;
}
