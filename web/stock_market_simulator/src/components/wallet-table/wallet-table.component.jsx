import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
class WalletTable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      walletArray: [],
      memberId: 1,
      value:[]
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
          value: this.state.value
        });
        console.log(this.state.walletArray);
      })
      .catch(error => {
        console.log(error);
      });
  };


  render() {
    if(this.state.walletArray.length !== 0){
      return (
        <div>
          <h1>WALLET TABLE</h1>
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
