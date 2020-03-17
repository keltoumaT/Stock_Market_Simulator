import React, { Component } from 'react';
import FormInput from '../form-input/form-input.component';
import CustomButton from '../custom-button/custom-button.component';
import axios from 'axios';
class WalletForm extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      initialCapital: '',
      memo: '',
      memberId: 1
    };
  }
  handleSubmit = async event => {
    event.preventDefault();
    const { name, initialCapital, memo } = this.state;

    this.setState({
      name: name,
      initialCapital: initialCapital,
      memo: memo
    });
    console.log(this.state);
    const URL = 'http://localhost:8585/wallets';
    axios
      .post(URL, {
        name: this.state.name,
        initialCapital: this.state.initialCapital,
        memo: this.state.memo,
        memberId: this.state.memberId
      })
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
  };

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  render() {
    const { name, initialCapital, memo } = this.state;

    return (
      <div>
        <br></br>
        <h1>Wallets</h1>
        <form className="wallet" onSubmit={this.handleSubmit}>
          <FormInput
            type="text"
            name="name"
            value={name}
            onChange={this.handleChange}
            label="Wallet Name"
            minLength="2"
            maxLength="50"
            required
          />
          <FormInput
            type="number"
            name="initialCapital"
            value={initialCapital}
            onChange={this.handleChange}
            label="Initial Capital"
            required
          />
          <FormInput
            type="text"
            name="memo"
            value={memo}
            onChange={this.handleChange}
            label="Memo"
            required
          />

          <CustomButton type="submit">CREATE A WALLET</CustomButton>
        </form>
      </div>
    );
  }
}

export default WalletForm;
