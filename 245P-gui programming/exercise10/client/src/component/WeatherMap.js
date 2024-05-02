// CSS and other resources
import "./weatherMap.css";

import React,{useState,useEffect} from "react";
import { CSSTransition } from "react-transition-group";

const WeacherMap=()=> {
    const [loadingData, setLoadingData] = useState(true);
    const [error, setError] = useState(false);
    const [data, setData] = useState(false);
    
    //set zip,country and apikey
    const [lat, setLat] = useState("0");
    const [lon, setLon] = useState("0");
    const [zip,setZip] = useState("95050");
    const [country,setCountry] = useState("US");
    const APIkey = "dafcb77d1f73f811f0cf306e16f81a47"


    useEffect(() => {
        setLoadingData(true);
        // start the fetching
        fetch(
            //this address can be copied by this network
          `http://api.openweathermap.org/geo/1.0/zip?zip=${zip},${country}&appid=${APIkey}`
        )
          // handling responses
        .then((response)=>response.json())

          // handling normal data state
        .then((actualData) => {
            console.log(actualData);
            setLat(actualData.lat);
            setLon(actualData.lon);
        })
          // handling errors
        .catch((err) => {
            console.log(err.message);
            setLoadingData(false);
            setError(true);
          });
      }, [zip,country]); // no callback statement within the useEffect means that the embedded function will only run once; when the DOM was loaded!

      
      useEffect(() => {
        setLoadingData(true);
        fetch(
          `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${APIkey}`
        )
          // handling responses
          .then((response)=>response.json())
          // handling normal data state
          .then((actualData) => {
            console.log(actualData);
            setLoadingData(false);
            setError(false);
            setData(actualData);
          
          })
          // handling errors
          .catch((err) => {
            console.log(err.message);
            setLoadingData(false);
            setError(true);
          });
      },[lat,lon]);
           
      // Render
        return (
        <>
          <div className="hidden">           
          {loadingData==true?(
            "still loading"
          ):error==true?(
            "Error!!!"
          ):(           
            //if lat==0&&lon==0 , its address is original, not wanted address, just display specific address
            <CSSTransition in={(data.coord.lat!=0||data.coord.lon!=0)} timeout={4000} classNames="my-node">              
               <div className="result">
                        <p>Weather In City</p>
                        <br />Latitude:{data.coord.lat}
                        <br/>Longitude: {data.coord.lon}
                        <br/>Location:{data.name}
                        <br/>Temperature:{Math.round(data.main.temp - 273)} °C
                        <br/>Max-temp:{Math.round(data.main.temp_max - 273)} °C
                        <br/>Min-temp:{Math.round(data.main.temp_min - 273)} °C
                        <br/>Wind-speed:{data.wind.speed} meter/sec
                        <br/>Wind-degree:{data.wind.deg}
                        <br/>Weather-description:{data.weather[0].description}     
                  </div>
                  </CSSTransition>
                )}
            </div>        
        </>
  );
}

export default WeacherMap;