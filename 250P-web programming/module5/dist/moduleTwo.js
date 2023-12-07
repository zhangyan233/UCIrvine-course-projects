"use strict";
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
exports.moduleTwoStart = void 0;
const React = __importStar(require("react"));
const ReactDOM = __importStar(require("react-dom"));
function moduleTwoStart() {
    class Content extends React.Component {
        render() {
            return (React.createElement("article", {}, React.createElement("h1", { style: { color: "red", fontWeight: "bolder" } }, this.props.title), React.createElement("p", { style: { color: "black", textAlign: "left" } }, this.props.description)));
        }
    }
    const rootElement = React.createElement("wrapper", {}, React.createElement(Content, {
        title: "Sichuan cuisine",
        description: "Sichuan cuisine or Sichuanese cuisine, alternatively romanized as Szechwan cuisine or Szechuan cuisine, is a style of Chinese cuisine originating from Sichuan province and the neighboring Chongqing municipality. Chongqing was formerly a part of Sichuan until 1997. There are many local variations within Sichuan and Chongqing.It has bold flavours, particularly the pungency and spiciness resulting from liberal use of garlic and chili peppers, as well as the unique flavour of Sichuan pepper. Some examples are Kung Pao chicken and Yuxiang shredded pork. Four sub-styles of Sichuan cuisine include Chongqing, Chengdu, Zigong and Buddhist vegetarian style."
    }), React.createElement(Content, {
        title: "Shandong cuisine",
        description: "Shandong cuisine, more commonly known in Chinese as Lu cuisine, is one of the Eight Culinary Traditions of Chinese cuisine and one of the Four Great Traditions. It is derived from the native cooking style of Shandong Province, a northern coastal province of China.Shandong cuisine is famous for its wide selection of material and use of different cooking methods. The raw materials are mainly domestic animals and birds, seafood and vegetables. The masterly cooking techniques include bao, liu, pa, kao, zhu, and coating sugar onto fruits with honey as the adhesive."
    }), React.createElement(Content, {
        title: "Cantonese cuisine",
        description: "Cantonese or Guangdong cuisine, also known as Yue cuisine is the cuisine of Guangdong province of China, particularly the provincial capital Guangzhou, and the surrounding regions in the Pearl River Delta including Hong Kong and Macau. Strictly speaking, Cantonese cuisine is the cuisine of Guangzhou or of Cantonese speakers, but it often includes the cooking styles of all the speakers of Yue Chinese languages in Guangdong.The Teochew cuisine and Hakka cuisine of Guangdong are considered their own styles. However, scholars may categorize Guangdong cuisine into three major groups based on the region's dialect: Cantonese, Hakka and Chaozhou cuisines. Neighboring Guangxi's cuisine is also considered separate despite eastern Guangxi being considered culturally Cantonese due to the presence of ethnic Zhuang influences in the rest of the province."
    }));
    class Person {
        constructor(InFirstName) {
            this.firstName = InFirstName;
        }
        get() {
            alert(`${this.firstName} use the second module`);
        }
    }
    const p = new Person('Yan');
    p.get();
    ReactDOM.render(rootElement, document.getElementById("secondContainer"));
}
exports.moduleTwoStart = moduleTwoStart;
//# sourceMappingURL=moduleTwo.js.map