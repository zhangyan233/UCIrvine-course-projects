"use strict";
//since ts cannot justify 'react' and 'react-dom', use command 'npm add @types/react @types/react-dom'
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
var React = require("react");
var ReactDOM = require("react-dom");
function start() {
    var SideBar = /** @class */ (function (_super) {
        __extends(SideBar, _super);
        function SideBar() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        SideBar.prototype.render = function () {
            return (React.createElement("li", {}, React.createElement("h2", { style: { color: "black" } }, this.props.title), React.createElement("a", { href: this.props.href }, this.props.description)));
        };
        return SideBar;
    }(React.Component));
    var rootElement = React.createElement("nav", {}, React.createElement("ul", {}, React.createElement(SideBar, {
        href: "https://en.wikipedia.org/wiki/Sichuan_cuisine",
        title: "Sichuan cuisine",
        description: "wikipedia of Sichuan cuisine"
    }), React.createElement(SideBar, {
        href: "https://en.wikipedia.org/wiki/Shandong_cuisine",
        title: "Shandong cuisine",
        description: "wikipedia of Shandong cuisine"
    }), React.createElement(SideBar, {
        href: "https://en.wikipedia.org/wiki/Cantonese_cuisine",
        title: "Cantonese cuisine",
        description: "wikipedia of Cantonese cuisine cuisine"
    })));
    //create function to display use .ts successfully
    function success(word) {
        alert("".concat(word) + " use typescript to modify the page of cuisine");
    }
    success('Yan');
    ReactDOM.render(rootElement, document.getElementById("mainContainer"));
}
