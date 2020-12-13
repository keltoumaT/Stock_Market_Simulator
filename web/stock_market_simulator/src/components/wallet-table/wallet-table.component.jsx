import React, { Component } from 'react';
import axios from 'axios';
import {FiEdit } from "react-icons/fi";
import { Link } from 'react-router-dom';
import jwt_decode from "jwt-decode";
import {FiRefreshCw} from "react-icons/fi"
class WalletTable extends Component {
  constructor(props) {
    super(props);
    let decoded_token = jwt_decode(localStorage.getItem("access_token"))
    console.log(decoded_token);
    this.state = {
      walletArray: [],
      memberId: decoded_token.userId,
      value:[],
      dealId:""
    };
  }

  componentDidMount() {
    this.getData();
  }


  getData = () => {
    const URL = `http://localhost:8585/api/private/wallets/all/${this.state.memberId}`;
    let token = localStorage.getItem("access_token")
    console.log(token);
    const config = { headers:{'Authorization': `Bearer ${token}`}};
    axios
      .get(URL, config)
      .then(response => {
        this.setState({
          walletArray: response.data,
          memberId : this.state.memberId,
          value: this.state.value,
          dealId:""
        });
        console.log(this.state.walletArray);
      })
      .catch(error => {
        console.log(error);
      });
  };

  getLastDeal = (id) =>{
    let URL = `http://localhost:8585/api/private/deals/first/${id}`;
    axios
    .get(URL)
    .then(response =>{
      this.setState({
        walletArray: this.state.walletArray,
        memberId : this.state.memberId,
        value: this.state.value,
        dealId:response.data.symbol
      });
    })
    .catch(error =>{
        console.log(error);
    })

}
 reloadComponent = () =>{
   this.componentDidMount();
 }
  //Method to get last deal and retrieve company symbol
  //If not found do something
  //Then pass it to the link to create a new page for the trading board

  render() {
    if(this.state.walletArray.length !== 0){
      return (
        <div>
          <br/>
          <h1>Wallet List</h1>
          <FiRefreshCw onClick={()=>this.reloadComponent()}/>
          <br/>
            {this.state.walletArray.map((el, index)=>{
             return <ul key={index}>
                 <Link key={index} to={`trading-board/${el.id}`}>
                      <li>Wallet : {el.name} <FiEdit/></li>
                </Link>
                    </ul>
            })}
        </div>
      );
    }
    return(
      <p>You have no wallet yet.</p>
    )
    }
   
}

export default WalletTable;
