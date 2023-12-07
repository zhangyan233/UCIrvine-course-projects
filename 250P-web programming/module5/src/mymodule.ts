//since ts cannot justify 'react' and 'react-dom', use command 'npm add @types/react @types/react-dom'

import * as React from "react";
import * as ReactDOM from 'react-dom';

export interface props{
    title:string;
    href:string;
    description:string;
    
}

export function start():void{
    //assign type to attribute in react component
    
    class SideBar extends React.Component<props> {
        render() {
        return (
            React.createElement("li", { },
            React.createElement("h2", {style:{color:"black"} }, this.props.title),
            React.createElement("a",{ href : this.props.href }, this.props.description),
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

        //create function to display use ts successfully
        
        interface IPerson{
            firstName:string;
            success():void;
        };

        class Person implements IPerson{
            firstName: string;
            constructor(inFirstName:string){
                this.firstName=inFirstName;
            }
            success(){
                alert(`${this.firstName} use typescript and webpack to modify webpage`);
            }
        }

       const p=new Person("Yan");
       p.success();

        ReactDOM.render(rootElement,
        document.getElementById("mainContainer")
        );
}

