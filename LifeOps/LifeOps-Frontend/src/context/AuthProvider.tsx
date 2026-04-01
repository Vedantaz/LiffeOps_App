import { useState } from "react";
import { AuthContext } from "./AuthContext";

export const AuthProvider = ({children} :any)=>{
    const [user, setUser] = useState(null);

    const loginUser = (data:any)=>{
        setUser(data);
        localStorage.setItem("token", data.accessToken);

    };

    const logoutUser =() =>{
        setUser(null);
        localStorage.removeItem("token");

    };

    return (
        <AuthContext.Provider value={{user, loginUser, logoutUser}}>
            {children}
        </AuthContext.Provider>
    );
}