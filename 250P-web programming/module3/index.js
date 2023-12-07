function start(){
    //create the part of sidebar
    class SideBar extends React.Component {
        constructor(props) {
            super(props);
            console.log("Cuisine List created");

            //get some attributes 
            this.title=this.props.title
            this.href=this.props.href
            this.description=this.props.description
        }
            
        render() {
        return (
            React.createElement("li", { },
            //the title of every part
            React.createElement("h2", {style:{color:"black"} }, this.title),
            // the outer link of every part
            React.createElement("a",{ href : this.href ,style:{color:"blue",textDecoration:"none"}}, this.description),
            )
        );
    }
}
        //create react components 
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

        //display rootElement inside a browser DOM node, whose name in index.html is mainContainer
        ReactDOM.render(rootElement,
        document.getElementById("mainContainer")
        );
}