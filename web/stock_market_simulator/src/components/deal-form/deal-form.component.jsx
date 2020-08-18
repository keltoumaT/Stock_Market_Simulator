import React, { Component } from 'react';
import FormInput from '../form-input/form-input.component';
import CustomButton from '../custom-button/custom-button.component';
import axios from 'axios';
class DealForm extends Component {
  constructor({ symbol }) {
    super();
    this.state = {
      companyData: {},
      symbol: symbol,
      quantity:0
    };
  }

  componentDidMount() {
    this.getCompanyData();
  }
  getCompanyData = () => {
    let URL = `http://localhost:8585/companies/${this.state.symbol}`;
    axios
      .get(URL)
      .then(response => {
        console.log(response.data);
        this.setState({
          companyData: response.data,
          symbol: this.state.symbol
        });
      })
      .catch(error => {
        console.log(error);
      });
  };

  handleChange = event => {
      this.setState({
        companyData: this.state.companyData,
        symbol: this.state.symbol,
        quantity: event.target.value
      });
      console.log(this.state.quantity);
    
  };
  handleClick = event => {
    //if else
    event.preventDefault();
    console.log(event);
    axios
      .post('http://localhost:8585/deals', {
        companyName: this.state.companyData.companyName,
        quantity: this.state.quantity,
        unityPrice: this.state.companyData.latestPrice,
        symbol:this.state.symbol,
        walletId: 1
      })
      .then(response => {
        console.log(response);
        console.log(response.status)
      })
      .catch(error => {
        console.log(error);
      });
  };

  userHasEnoughLiquidity(){
    //Make call to get wallet info
    //Compare liquidity from wallet with quantity times unityPrice return true or false
  }
  render() {
    return (
      <div>
        <br></br>
        <FormInput
          onChange={this.handleChange}
          placeholder="Quantity"
          required
        />
        <CustomButton onClick={this.handleClick}>Buy</CustomButton>
      </div>
    );
  }
}

export default DealForm;
