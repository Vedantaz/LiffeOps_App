const TaskCard = ({task}:any) => {
    return (
        <div style={{border:"1px solid grey", padding:"10px", margin:"10px" }}>

            <h4>{task.title}</h4>
            <p>{task.description}</p>
            <p>Status: {task.status}</p>
        </div>
    )
}
export default TaskCard;
