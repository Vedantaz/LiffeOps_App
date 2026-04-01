import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const protectedRoute = ({children} :any) => {
    const { user } = useAuth();
    if(!user && !localStorage.getItem("token")){
        return < Navigate to="/login" />;
    } return children;


}

export default protectedRoute;

