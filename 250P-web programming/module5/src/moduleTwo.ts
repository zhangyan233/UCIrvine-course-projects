import * as React from 'react';
import * as ReactDOM from 'react-dom';

export interface props{
    title:string;
    description:string;
    
}

export function moduleTwoStart():void{
    
    class Content extends React.Component<props> {
        render() {
        return (
            React.createElement("article",{ },
            React.createElement("h1", {style:{color:"red",fontWeight:"bolder"} }, this.props.title),
            React.createElement("p", {style:{color:"black", textAlign:"left"}},this.props.description)
        ));
    }
}
        
        const rootElement =
        React.createElement("wrapper",{ },
        React.createElement(
            Content,
            {
                title:"Sichuan cuisine",
                description:"Sichuan cuisine or Sichuanese cuisine, alternatively romanized as Szechwan cuisine or Szechuan cuisine, is a style of Chinese cuisine originating from Sichuan province and the neighboring Chongqing municipality. Chongqing was formerly a part of Sichuan until 1997. There are many local variations within Sichuan and Chongqing.It has bold flavours, particularly the pungency and spiciness resulting from liberal use of garlic and chili peppers, as well as the unique flavour of Sichuan pepper. Some examples are Kung Pao chicken and Yuxiang shredded pork. Four sub-styles of Sichuan cuisine include Chongqing, Chengdu, Zigong and Buddhist vegetarian style."
            }
        ),
        React.createElement(
            Content,
            {
                title:"Shandong cuisine",
                description:"Shandong cuisine, more commonly known in Chinese as Lu cuisine, is one of the Eight Culinary Traditions of Chinese cuisine and one of the Four Great Traditions. It is derived from the native cooking style of Shandong Province, a northern coastal province of China.Shandong cuisine is famous for its wide selection of material and use of different cooking methods. The raw materials are mainly domestic animals and birds, seafood and vegetables. The masterly cooking techniques include bao, liu, pa, kao, zhu, and coating sugar onto fruits with honey as the adhesive."
            }
        ),React.createElement(
            Content,
            {
                title: "Cantonese cuisine",
                description:"Cantonese or Guangdong cuisine, also known as Yue cuisine is the cuisine of Guangdong province of China, particularly the provincial capital Guangzhou, and the surrounding regions in the Pearl River Delta including Hong Kong and Macau. Strictly speaking, Cantonese cuisine is the cuisine of Guangzhou or of Cantonese speakers, but it often includes the cooking styles of all the speakers of Yue Chinese languages in Guangdong.The Teochew cuisine and Hakka cuisine of Guangdong are considered their own styles. However, scholars may categorize Guangdong cuisine into three major groups based on the region's dialect: Cantonese, Hakka and Chaozhou cuisines. Neighboring Guangxi's cuisine is also considered separate despite eastern Guangxi being considered culturally Cantonese due to the presence of ethnic Zhuang influences in the rest of the province."
            }
        )
        );

        //create function to display use ts successfully
        interface IPerson{
            firstName: string;
            get():void;
        }

        class Person implements IPerson{
            firstName: string;
            constructor(InFirstName:string){
                this.firstName=InFirstName;               
            }
            get(){
               alert(`${this.firstName} use the second module`); 
            }
        }

        const p=new Person('Yan');
        p.get();
        
        ReactDOM.render(rootElement,
        document.getElementById("secondContainer")
        );
}