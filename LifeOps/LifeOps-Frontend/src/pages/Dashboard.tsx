import { useEffect, useState } from "react";
import { getGoals } from "../services/goalService";

export default function Dashboard() {
  const [goals, setGoals] = useState([]);

  useEffect(() => {
    fetchGoals();
  }, []);

  const fetchGoals = async () => {
    const res = await getGoals();
    setGoals(res.data || res);
  };

  return (
    <div>
      <h2>My Goals</h2>

      {goals.map((goal: any) => (
        <div key={goal.id}>
          <h3>{goal.title}</h3>
          <p>{goal.description}</p>
        </div>
      ))}
    </div>
  );
}