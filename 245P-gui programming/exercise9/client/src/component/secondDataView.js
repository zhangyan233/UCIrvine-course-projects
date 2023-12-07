// Components

import Main from "./Main.js";

// Data

import { MAIN_DATA } from "./data.js";

function SecondDataView() {
  return (
    <>
      <Main content={MAIN_DATA[1].content}  title={MAIN_DATA[1].title} image={MAIN_DATA[1].image} />
    </>
  );
}

export default SecondDataView;