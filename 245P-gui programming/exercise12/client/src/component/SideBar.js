import "./SideBar.css";
import { Link } from "react-router-dom";

function SideBar() {
    return (
        <>
          <div className="sidenav">
          <Link to="./">Sichuan cuisine</Link>
          <Link to="Second">Shandong cuisine</Link>
          <Link to="Third">Cantonese cuisine</Link>
          </div>
        </>
      );
}


export default SideBar;