//part one
const books = [
    {title: 1984, author: "George Orwell"},
    {title: "In Search of Lost Time", author: "Marcel Proust"},
    {title: "Don Quixote", author: "Miguel de Cervantes"},
    {title: "Moby Dick", author: "Herman Melville"}
   ]

const result=books.filter(checkAuthor);
const resultTwo=books.filter(checkTwoAuthors);

function checkAuthor(book){
    return book.author!="George Orwell";
}

function checkTwoAuthors(book){
    return book.author!="George Orwell" && book.author!="Herman Melville";
}

console.log(result);
console.log(resultTwo);


//part two
function functionone(){
    document.getElementById("content").style.color="blue"
}

function functiontwo(){
    document.getElementById("content").innerHTML="bye-bye World";
}