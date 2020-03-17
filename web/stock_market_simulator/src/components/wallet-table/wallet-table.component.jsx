import React, { Component } from "react";
import axios from 'axios';

class WalletTable extends Component{

    constructor(props){
        super(props);
        this.state = {
            walletArray : [],
        }
    }

    componentDidMount(){
        this.getData();
    }

getData = () =>{
    const URL = "http://localhost:8585/wallets"
    axios.get(URL)
    .then(response =>{
      this.setState({
        walletArray: response.data,
      });
      console.log(response);
      console.log(this.state.walletArray)
    })
    .catch(error => {
      console.log(error);
    })
  
}
render(){
    return (
        <div>
            <h1>WALLET TABLE</h1>
            
        {/* Loop through this.state.walletAray to create a table */}

        </div>
    )
}
   
}

export default WalletTable;