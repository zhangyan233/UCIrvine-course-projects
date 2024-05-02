import "./Header.css";
import { Link } from "react-router-dom";

function Header() {
    return (
        <>
          <div className="header">
            <a href="#default" className="logo">
              Lydia's Home
            </a>
            <div className="headerRight">
              <Link to="/">Home</Link>
              <Link to="contact">contact</Link>
              <a href="#about">About</a>
            </div>
          </div>
        </>
      );
}

export default Header;