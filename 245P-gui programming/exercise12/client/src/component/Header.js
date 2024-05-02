import "./Header.css";
import { Link } from "react-router-dom";

function Header() {
    return (
        <>
          <div className="header">
            <a href="#default" className="logo">
            Major Chinese cuisines
            </a>
          </div>
        </>
      );
}

export default Header;