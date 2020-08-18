import React from "react";
import './loading.styles.scss'

class Loading extends React.Component {
    render(){
      return( 
         <div className="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
      );
    }
  }


export default Loading;