import './App.css';

import {BrowserRouter, Routes, Route } from "react-router-dom";
import LoginForm from './components/LoginForm';

function App() {
  // we are also writing a handleSubmit function that will be the output of our form and
  // that could be the interface to a connected backend service
  const handleSubmit = (data) => {
    const json = JSON.stringify(data, null, 4); //transfer to JSON 
    console.clear(); // clearing previous console logs
    console.log(json); // showing what we actually submitted thru the form
  };

  return (
    <div className="App">
      <BrowserRouter>
      <LoginForm onSubmit={handleSubmit}/>
      </BrowserRouter>
    </div>
  );
}

export default App;
