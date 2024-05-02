import './App.css';

// External Libraries
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Footer from './component/Footer';
import SideBar from './component/SideBar';
import Header from './component/Header';
import LandingView from './component/LandingView';
import SecondDataView from './component/secondDataView';
import FirstHeader from './component/firstDataHeader';
import SecondHeader from './component/secondDataHeader';
import WeatherMap from './component/WeatherMap';


function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <SideBar />
        <Routes>
          <Route index element={<FirstHeader  />} />
          <Route path="firstDev" element={<LandingView />}></Route>
          <Route path="secondDev" element={<SecondDataView />}></Route>
          <Route path="contact" element={<SecondHeader />}></Route>
          <Route path="WeatherMap" element={<WeatherMap />}></Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
