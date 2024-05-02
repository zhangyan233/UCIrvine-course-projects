const http=require('http')
const fs=require('fs')
const path=require('path')

//create server
const server=http.createServer()

server.on('request',(req,res)=>{
    //1.get url
    const url=req.url

    //get path
    const xpath=path.join(__dirname,url)
   
    //read file
    fs.readFile(xpath,(err,dataStr)=>{
        //1.read err
        if(err) return res.end(`404 Not found`);
        //2.read
        res.end(dataStr)
    })
})
server.listen(80,()=>{
    console.log(`server running at 127.0.0.1:80`)
})