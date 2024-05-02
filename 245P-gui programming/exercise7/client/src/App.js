import logo from './logo.svg';
import './App.css';

import Footer from './component/Footer';
import SideBar from './component/SideBar';
import Header from './component/Header';
import Main from './component/Main';

function App() {
  return (
    <div className="App">
      <Footer/>
      <SideBar/>
      <Header />     
      <Main/>
    </div>
  );
}

export default App;
