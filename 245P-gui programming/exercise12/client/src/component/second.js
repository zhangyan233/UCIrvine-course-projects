import Main from "./Main";
import { MAIN_DATA } from "./data.js";

function Second() {
    return (
        <>
         <Main title={MAIN_DATA[1].title} content={MAIN_DATA[1].content} image={MAIN_DATA[1].image} />
        </>
    );
}

export default Second;