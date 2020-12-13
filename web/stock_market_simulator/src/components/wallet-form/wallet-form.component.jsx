import React, { Component } from 'react';
import FormInput from '../form-input/form-input.component';
import CustomButton from '../custom-button/custom-button.component';
import axios from 'axios';
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
class WalletForm extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      capital: '',
      memo: '',
      memberId: 1,
      // decode token to get current user id
      open:false,
      severity:""
    };
  }
  handleSubmit = async event => {
    event.preventDefault();
    const { name, capital, memo } = this.state;

    this.setState({
      name: name,
      capital: capital,
      memo: memo
    });
    console.log(this.state);
    let token = localStorage.getItem("access_token")
    const config = { headers:{'Authorization': `Bearer ${token}`}};
    const URL = 'http://localhost:8585/api/private/wallets';
    axios
      .post(URL, {
        name: this.state.name,
        capital: this.state.capital,
        memo: this.state.memo,
        memberId: this.state.memberId
      }, config)
      .then(response => {
        console.log(response);
        this.setState({
            open:true,
            severity:"success"
        })
      })
      .catch(error => {
        console.log(error);
        this.setState({
          open:true,
          severity:"error"
        })
      });
  };

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleClose = (event, reason) =>{
    if(reason === "clickaway"){
      return;
    }
    this.setState({
      open:false
    })
  }

  render() {
    const { name, capital, memo } = this.state;

    return (
      <div>
        <br></br>
        <h1>Create a wallet</h1>
        <Snackbar open={this.state.open} autoHideDuration={6000} onClose={this.handleClose}>
        <Alert onClose={this.handleClose} severity={this.state.severity}>



          {
            this.state.severity === "success" &&  "Success ! You can now refresh the wallet list ! "
          }
          {
            this.state.severity === "error" && "Oops we've encountered a problem. Please try again later !"
          }
      
        </Alert>
        </Snackbar>
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
            name="capital"
            value={capital}
            onChange={this.handleChange}
            label="Capital"
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
