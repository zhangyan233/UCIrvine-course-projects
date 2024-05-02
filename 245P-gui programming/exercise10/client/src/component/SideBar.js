import "./SideBar.css";
import { Link } from "react-router-dom";

function SideBar() {
    return (
        <>
          <div className="sidenav">
          <a href="#about">Information</a>
          <a href="#services">Experience</a>
          <Link to="firstDev">first</Link>
          <Link to="secondDev">second</Link>
          <Link to="weatherMap">WeatherMap</Link>
          <a href="#clients">Education</a>
          <a href="#contact">Contact</a>
          </div>
        </>
      );
}


export default SideBar;