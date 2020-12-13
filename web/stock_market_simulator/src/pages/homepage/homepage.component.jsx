import React from 'react';
import './homepage.styles.scss';
import FirstImage from "../../assets/example-28.svg";
import SecondImage from "../../assets/example-29.svg";

const HomePage = () => (
  <div className="homepage">
    <div className="main_divs">
      <div style={{width:"70%"}}>
        <h1>Practice investing without risk</h1>
        <h2>Invest in stock market values ​​without taking any risk.</h2>
        <br/>
        <p>This application allows you to simulate stock market investments by creating a virtual stock portfolio. </p>
        <p>Benefit from precise and continuous monitoring of the value and content of the portfolio.</p>
      </div>
      <div style={{width:"100%"}}>
      <img style={{width:"100%"}} src={FirstImage} alt=""/>

      </div>
    </div>
    <div className="main_divs">
    
      <div style={{width:"100%"}}>
      <img style={{width:"75%"}} src={SecondImage} alt=""/>
      </div>
      <div style={{width:"80%"}}>
        <h1>Create several portfolios to test different investement strategies</h1>
        <h2>A clear and simple trading board to focus on the essential</h2>
        <br/>
        <p>This make it possible to ensure that the investment strategy initially envisaged achieves the expected results. </p>
      </div>
    </div>
  </div>
);

export default HomePage;
