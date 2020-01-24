import React from 'react';
import FormInput from '../form-input/form-input.component';
import CustomButton from '../custom-button/custom-button.component';
import axios from 'axios';
import './sign-up.styles.scss';

class SignUp extends React.Component {
  constructor() {
    super();
    this.state = {
      firstName: '',
      lastName: '',
      email: '',
      password: ''
    };
  }

  handleSubmit = async event => {
    event.preventDefault();
    const { firstName, lastName, email, password } = this.state;

    this.setState({
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password
    });
    console.log(this.state);
    const URL = 'http://localhost:8585/member';
    axios
      .post(URL, {
        lastName: this.state.lastName,
        firstName: this.state.firstName,
        email: this.state.email,
        password: this.state.password
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
    const { firstName, lastName, email, password } = this.state;
    return (
      <div className="sign-up">
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
            type="email"
            name="email"
            value={email}
            onChange={this.handleChange}
            label="Email"
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
