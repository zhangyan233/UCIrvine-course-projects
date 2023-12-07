// Components

import Main from "./Main.js";

// Data

import {MAIN_DATA } from "./data.js";

function FirstDataHeader() {
  return (
    <>
      <Main content={MAIN_DATA [2].content}  title={MAIN_DATA [2].title} image={MAIN_DATA [2].image} />
    </>
  );
}

export default FirstDataHeader;