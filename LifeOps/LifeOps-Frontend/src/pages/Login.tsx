import { useState } from "react";
import { login } from "../services/authService";
import { useAuth } from "../context/AuthContext";

export default function Login() {
  const [form, setForm] = useState({ username: "", password: "" });
  const auth = useAuth();
  if(!auth) { throw new Error("AuthContext not found. Wrap with AuthProvider.")}
  const { loginUser } = auth;



  const handleLogin = async () => {
    const res = await login(form);
    loginUser(res.accessToken);
  };

  return (  
    <div>
      <h2>Login</h2>
      <input placeholder="Username"
        onChange={(e) => setForm({ ...form, username: e.target.value })}
      />
      <input type="password"
        placeholder="Password"
        onChange={(e) => setForm({ ...form, password: e.target.value })}
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}