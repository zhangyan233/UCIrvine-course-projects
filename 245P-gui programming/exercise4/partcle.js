// code and inspiration from:
// https://youtu.be/MfxBfRD0FVU

class Particle {
    constructor(x, y) {
      this.x = x;
      this.y = y;
    }
  
    // update the size of the element we render
    update() {
      this.x += random(-5, 5);
      this.y += random(-5, 5);
    }
  
    // render the element on the screen
    // by default the shape will be a point/circle
    show() {
      stroke(255);
      strokeWeight(25);
      point(this.x, this.y);
    }
  }

  class Box extends Particle {
    constructor(x, y) {
      super(x, y);
      //set color of this item
      this.bright = random(255);
      this.r = 10;
    }
  
    update() {
      super.update();
      this.r += random(-8, 8);
    }
  
    show() {
      //set the border property
      strokeWeight(1);
      stroke(255);

      //set the color
      fill(this.bright);

      // x: It is used to set the x-coordinate of square.
      // y: It is used to set the y-coordinate of square.
      // r: It is used to set the size of side of square.
      square(this.x, this.y, this.r);
    }
  }

  class Ellipse extends Particle{
    constructor(x,y){
        super(x,y);
        this.w=8;
        this.l=10
    }

    update(){
        super.update();
        this.w+=random(-10,10);
        this.l+=random(-10,10);
    }

    show(){
        strokeWeight(1);
        stroke(255);
        fill('yellow');
        // x: This parameter takes the x-coordinate of the ellipse.
        // y: This parameter takes the y-coordinate of the ellipse.
        // w: This parameter takes the width of the ellipse.
        // l: This parameter takes the height of the ellipse.
        ellipse(this.x,this.y,this.w,this.l);
    }
  }
  
  class Triangle extends Particle{
    constructor(x,y){
        super(x,y);
        this.x1=40;
        this.y1=100;
        this.x2=50;
        this.y2=80;
    }

    update(){
        //without inherit
        this.x1+=random(-5,5);
        this.y1+=random(-5,5);
        this.x2+=random(-10,10);
        this.y2+=random(-10,10);
    }

    show(){
        strokeWeight(5);
        stroke('red');
        fill('pink');
        // x: This parameter accept the x-coordinate of first point.
        // y: This parameter accept the y-coordinate of first point.
        // x1: This parameter accept the x-coordinate of second point.
        // y1: This parameter accept the y-coordinate of second point.
        // x2: This parameter accept the x-coordinate of third point.
        // y2: This parameter accept the y-coordinate of third point.
        triangle(this.x,this.y,this.x1,this.y1,this.x2,this.y2);
    }
  }


  let p1, p2,p3,p4;

  function setup() {
    createCanvas(500, 500);
    p1 = new Particle(300, 300);
    p2 = new Box(300, 300);
    p3= new Ellipse(300,300);
    p4=new Triangle(150,150);
  }
  
  function draw() {
    background('skyblue');
    p1.update();
    p1.show();
    p2.update();
    p2.show();
    p3.update();
    p3.show();
    p4.update();
    p4.show();
  }