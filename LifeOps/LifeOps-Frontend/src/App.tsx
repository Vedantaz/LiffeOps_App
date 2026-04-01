import './App.css'
import Login from ".././src/pages/Login";
import Dashboard from './pages/Dashboard';
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import ProtectedRoute from './components/ProtectedRoute';


function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login/>}/>
          <Route path="/dashboard" element={
            <ProtectedRoute>
            <Dashboard/>
            </ProtectedRoute>
            }/>

        </Routes>
      </BrowserRouter>

  );
}

export default App;
