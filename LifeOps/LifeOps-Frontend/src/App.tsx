import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import { BrowserRouter, Route, Routes } from 'react-router-dom'

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/dashboard" element={<Dashboard/>}/>

        </Routes>
      </BrowserRouter>

  );
}

export default App;
