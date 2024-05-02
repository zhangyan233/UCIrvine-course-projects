let slideContainer = document.querySelector(".slide__container");
let images = document.querySelectorAll(".slide__container img");
let prevButton = document.querySelector("#prevButton");
let nextButton = document.querySelector("#nextButton");
let imgSize = images[0].clientWidth;//the width(including padding) of image

//width of moving a picture
let counter = 1;
slideContainer.style.transform = "translateX(" + -imgSize * counter + "px)";//display the number 1 picture

nextButton.addEventListener("click", () => {
  if (counter >= images.length - 1) return; // do slideContainer.addEventListener 
  counter = counter + 1; //continue to move
  slideContainer.style.transition = "all 3s ease 1s"; //change moving time to 5s,delay 1s 
  slideContainer.style.transform = "translateX(" + -imgSize * counter + "px)";  //move picture 
});

prevButton.addEventListener("click", () => {
  if (counter <= 0) return; // do slideContainer.addEventListener 
  counter = counter - 1; //continue to move
  slideContainer.style.transition = "all 1s ease"; // change function to line
  slideContainer.style.transform = "translateX(" + -imgSize * counter + "px)"; // move picture
});

slideContainer.addEventListener("transitionend", () => {
  // if the current image we are at is the first clone, move to last one and update counter
  if (images[counter].id === "firstClone") {
    slideContainer.style.transition = "all 0s ease-in-out";//move picture from firstClone to normal slide
    counter = images.length - counter;
  }
  
  if (images[counter].id === "lastClone") {
    slideContainer.style.transition = "all 0s ease-in-out";//move fast make people cannot realize moving
    counter = images.length - 2;
  }

  // display picture
  slideContainer.style.transform = "translateX(" + -imgSize * counter + "px)";
});