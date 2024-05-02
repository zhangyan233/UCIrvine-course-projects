import Main from "./Main";
import { MAIN_DATA } from "./data.js";

function LandingView() {
    return (
        <>
         <Main title={MAIN_DATA[0].title} content={MAIN_DATA[0].content} image={MAIN_DATA[0].image} />
        </>
    );
}

export default LandingView;