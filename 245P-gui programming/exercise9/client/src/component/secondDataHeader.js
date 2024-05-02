// Components

import Main from "./Main.js";

// Data

import { MAIN_DATA } from "./data.js";

function SecondDataHeader() {
  return (
    <>
      <Main content={ MAIN_DATA[3].content}  title={ MAIN_DATA[3].title} image={MAIN_DATA[3].image} />
    </>
  );
}

export default SecondDataHeader;