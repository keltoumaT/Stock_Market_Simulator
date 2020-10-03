import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
class WalletTable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      walletArray: [],
      memberId: 1,
      value:[],
      dealId:""
    };
  }

  componentDidMount() {
    this.getData();
  }


  getData = () => {
    const URL = `http://localhost:8585/wallets/all/${this.state.memberId}`;
    axios
      .get(URL)
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
    let URL = `http://localhost:8585/deals/first/${id}`;
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
 
  //Method to get last deal and retrieve company symbol
  //If not found do something
  //Then pass it to the link to create a new page for the trading board

  render() {
    if(this.state.walletArray.length !== 0){
      return (
        <div>
          <br/>
          <h1>Wallet List</h1>
          <br/>
            {this.state.walletArray.map((el, index)=>{
             return <ul key={index}>
                 <Link key={index} to={`trading-board/${el.id}`}>
                      <li>Wallet : {el.name}</li>
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
