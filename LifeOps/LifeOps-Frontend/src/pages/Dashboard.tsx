import { useEffect, useState } from "react";
import { getGoals } from "../services/goalService";
import api from "../services/api";

export default function Dashboard() {
  const [goals, setGoals] = useState([]);
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetchGoals();
    fetchTasks();

  }, []);

  const fetchGoals = async () => {
    try{
      const res = await getGoals();
      setGoals(res.data);

    }catch(err){
      console.log(err);
    }
  };

  const fetchTasks = async ()=>{
    try{
      const res = await api.get("/tasks");
      setTasks(res.data.content);

    }catch(err){
      console.log(err);
      
    }
  }

  return (
    <div style={{padding:"20px"}}>
      <h2>Dashboard</h2>

      {/* Tasks */}
      <h3>Tasks</h3>
      {tasks.map((task:any)=>{
        <div key={task.id} >
          <p> {task.title} - {task.status}</p>
        </div>
      })}

      <h3>Goals</h3>  


      {goals.map((goal: any) => (
        <div key={goal.id}>
          <h3>{goal.title} - {goal.currentValue}/{goal.targetValue} </h3>
          <p>{goal.description}</p>
        </div>
      ))}
    </div>
  );
}