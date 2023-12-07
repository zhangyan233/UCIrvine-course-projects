//since ts cannot justify 'react' and 'react-dom', use command 'npm add @types/react @types/react-dom'

import * as React from 'react';
import * as ReactDOM from 'react-dom';

//create interface and appoint their type
interface props{
    title:string;
    href:string;
    description:string;
    
}


function start(){
    class SideBar extends React.Component<props> {
        render() {
        return (
            React.createElement("li", { },
            React.createElement("h2", {style:{color:"black"} }, this.props.title),
            React.createElement("a",
            { href : this.props.href }, this.props.description
            ),
            )
        );
    }
}
        
        const rootElement =
        React.createElement("nav",{ },
        React.createElement("ul",{},
        React.createElement(
            SideBar,
            {
                href:"https://en.wikipedia.org/wiki/Sichuan_cuisine",
                title:"Sichuan cuisine",
                description:"wikipedia of Sichuan cuisine"
            }
        ),
        React.createElement(
            SideBar,
            {
                href:"https://en.wikipedia.org/wiki/Shandong_cuisine",
                title:"Shandong cuisine",
                description:"wikipedia of Shandong cuisine"
            }
        ),React.createElement(
            SideBar,
            {
                href:"https://en.wikipedia.org/wiki/Cantonese_cuisine",
                title: "Cantonese cuisine",
                description:"wikipedia of Cantonese cuisine cuisine"
            }
        )
        ));

        //create function to display use .ts successfully
        function success(word:string){
            alert(`${word}`+` use typescript to modify the page of cuisine`)
        }
        success('Yan')

        ReactDOM.render(rootElement,
        document.getElementById("mainContainer")
        );
}