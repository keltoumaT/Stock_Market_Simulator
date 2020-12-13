import React, { Component } from 'react';
import FormInput from '../form-input/form-input.component';
import CustomButton from '../custom-button/custom-button.component';
import axios from 'axios';
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import './sign-up.styles.scss';

class SignUp extends Component {
  constructor() {
    super();
    this.state = {
      firstName: '',
      lastName: '',
      username:'',
      email: '',
      password: '',
      open:false,
      severity:"",
    };
  }

  handleSubmit = async event => {
    event.preventDefault();
    const { firstName, lastName, username, email, password } = this.state;

    this.setState({
      firstName: firstName,
      lastName: lastName,
      username: username,
      email: email,
      password: password,
      open:this.state.open,
      severity:this.state.severity
    });
    console.log(this.state);
    const URL = 'http://localhost:8585/api/public/members';
    axios
      .post(URL, {
        lastName: this.state.lastName,
        firstName: this.state.firstName,
        username: this.state.username,
        email: this.state.email,
        password: this.state.password
      })
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


  handleClose = (event, reason) =>{
      if(reason === 'clickaway'){
        return;
      }
      this.setState({
        open:false
      })
  }
  

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };
  render() {
    const { firstName, lastName, username, email, password } = this.state;
    return (
      <div className="sign-up">
        <Snackbar open={this.state.open} autoHideDuration={6000} onClose={this.handleClose}>
        <Alert onClose={this.handleClose} severity={this.state.severity}>



          {
            this.state.severity === "success" &&  "Your account has been created. You can now Sign In ! "
          }
          {
            this.state.severity === "error" && "Oops we've encountered a problem. Please try again later !"
          }
      
        </Alert>
        </Snackbar>
        <h2 className="title">I do not have an account</h2>
        <span>Sign up</span>
        <form className="sign-up-form" onSubmit={this.handleSubmit}>
          <FormInput
            type="text"
            name="firstName"
            value={firstName}
            onChange={this.handleChange}
            label="First Name"
            minLength="2"
            maxLength="50"
            required
          />
          <FormInput
            type="text"
            name="lastName"
            value={lastName}
            onChange={this.handleChange}
            label="Last Name"
            minLength="2"
            maxLength="50"
            required
          />
           <FormInput
            type="text"
            name="username"
            value={username}
            onChange={this.handleChange}
            label="Username"
            minLength="2"
            maxLength="50"
            required
          />
          <FormInput
            type="email"
            name="email"
            value={email}
            onChange={this.handleChange}
            label="Email"
            maxLength="200"
            required
          />
          <FormInput
            type="password"
            name="password"
            value={password}
            onChange={this.handleChange}
            label="Password"
            pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^?&*_=+-]).{8,50}$"
            required
          />
          <CustomButton type="submit">SIGN UP</CustomButton>
        </form>
      </div>
    );
  }
}

export default SignUp;
