import Main from "./Main";
import { MAIN_DATA } from "./data.js";

function Third() {
    return (
        <>
         <Main title={MAIN_DATA[2].title} content={MAIN_DATA[2].content} image={MAIN_DATA[2].image} />
        </>
    );
}

export default Third;