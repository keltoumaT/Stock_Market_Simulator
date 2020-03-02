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
    event.preventDefault();
    console.log(event);
    axios
      .post('http://localhost:8585/deals', {
        companyName: this.state.companyData.companyName,
        quantity: this.state.quantity,
        date: new Date().toISOString().slice(0, 10),
        unityPrice: this.state.companyData.latestPrice,
        walletId: 1
      })
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
  };
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
