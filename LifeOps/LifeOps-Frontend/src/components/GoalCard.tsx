const GoalCard = ({goal}:any)=>{


    const progress = (goal.currentValue / goal.targetValue) *100 || 0;

    return (

        <div style={{border:"1px solid grey", padding:"10px", margin:"10px" }}>

            <h4>{goal.title}</h4>
            <p>{goal.description}</p>
            <p>Progress: {progress.toFixed(1)}%</p>
        </div>
    )
 

}

export default GoalCard;
