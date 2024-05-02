"use strict";
//since ts cannot justify 'react' and 'react-dom', use command 'npm add @types/react @types/react-dom'
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.start = void 0;
const React = __importStar(require("react"));
const ReactDOM = __importStar(require("react-dom"));
function start() {
    //assign type to attribute in react component
    class SideBar extends React.Component {
        render() {
            return (React.createElement("li", {}, React.createElement("h2", { style: { color: "black" } }, this.props.title), React.createElement("a", { href: this.props.href }, this.props.description)));
        }
    }
    const rootElement = React.createElement("nav", {}, React.createElement("ul", {}, React.createElement(SideBar, {
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
    ;
    class Person {
        constructor(inFirstName) {
            this.firstName = inFirstName;
        }
        success() {
            alert(`${this.firstName} use typescript and webpack to modify webpage`);
        }
    }
    const p = new Person("Yan");
    p.success();
    ReactDOM.render(rootElement, document.getElementById("mainContainer"));
}
exports.start = start;
//# sourceMappingURL=mymodule.js.map