import { useState } from "react";
import { useAuth } from "../context/AuthContext";

// export default function Login() {

//   const { loginUser} = useAuth();
//   const [username, setUsername] = useState("");
//   const [password, setPassword] = useState("");


//   const [form, setForm] = useState({ username: "", password: "" });
//   const auth = useAuth();
//   if(!auth) { throw new Error("AuthContext not found. Wrap with AuthProvider.")}




//   const handleLogin = async (e:any) => {
//     // e.preventDefault();
//     // try{
//     //     await loginUser(username, password);
//     //     alert("Login successful!");


//     // }catch(err){
//     //   alert("Login failed!");
//     // }
//     const res = await login(form);
//     loginUser(res.accessToken);
//   };

//   return (  
//     <div>
//       <h2>Login</h2>
//       <input placeholder="Username"
//         onChange={(e) => setForm({ ...form, username: e.target.value })}
//       />
//       <input type="password"
//         placeholder="Password"
//         onChange={(e) => setForm({ ...form, password: e.target.value })}
//       />
//       <button onClick={handleLogin}>Login</button>
//     </div>
//   );
// }


const Login = () => {
  const {loginUser} = useAuth();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async (e:any) => {

      e.preventDefault();
      try{
        await loginUser(username, password);
        alert("Login successful!");

      }catch(err){
      alert("Login failed");
    }
      
    }
    
    
return (
    <form onSubmit={handleLogin}>
      <input
        placeholder="username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        placeholder="password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button type="submit">Login</button>
    </form>
  );

  }

  export default Login;
