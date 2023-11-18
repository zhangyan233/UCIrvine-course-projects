import './App.css';

// External Libraries
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Footer from './component/Footer';
import SideBar from './component/SideBar';
import Header from './component/Header';
import First from './component/first';
import Second from './component/second';
import Third from './component/third';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <SideBar />
        <Routes>
          <Route index element={<First />} />
          <Route path="Second" element={<Second />}></Route>
          <Route path="Third" element={<Third />}></Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
