import React from "react";

import FormInput from "../form-input/form-input.component";
import CustomButton from "../custom-button/custom-button.component";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import "./sign-in.styles.scss";
import Axios from "axios";

class SignIn extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      open: false,
      severity: "",
    };
  }

  handleSubmit = (event) => {
    event.preventDefault();
    this.setState({ username: "", password: "" });
    console.log(this.state);
    this.login();
  };

  login = () => {
    const queryString = require("query-string");
    let config = {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    };
    let body = {
      grant_type: "password",
      username: this.state.username,
      password: this.state.password,
      client_id: "my-client-app",
    };
    Axios.post(
      "http://localhost:8585/oauth/token",
      queryString.stringify(body),
      config
    )
      .then((response) => {
        let refresh_token = response.data.refresh_token;
        let body = {
          grant_type: "refresh_token",
          refresh_token: refresh_token,
          client_id: "my-client-app",
        };
        Axios.post(
          "http://localhost:8585/oauth/token",
          queryString.stringify(body),
          config
        )
          .then((response) => {
            console.log(response);
            //Store access token in localStorage
            let access_token = response.data.access_token;
            localStorage.setItem("access_token", access_token);
            window.location.replace("/wallets");
          })
          .catch((error) => {
            this.setState({
              open: true,
              severity: "error",
            });
          });
      })
      .catch((error) => {
        this.setState({
          open: true,
          severity: "error",
        });
      });
  };

  handleChange = (event) => {
    const { value, name } = event.target;
    this.setState({
      [name]: value,
    });
  };

  handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    this.setState({
      open: false,
    });
  };

  render() {
    return (
      <div className="sign-in">
        <h2>I already have an account</h2>
        <span>Sign in with your username and password</span>
        <Snackbar
          open={this.state.open}
          autoHideDuration={6000}
          onClose={this.handleClose}
        >
          <Alert onClose={this.handleClose} severity={this.state.severity}>
            {this.state.severity === "success" && "Your are now sign in ! "}
            {this.state.severity === "error" &&
              "Oops we've encountered a problem. Please try again later !"}
          </Alert>
        </Snackbar>

        <form onSubmit={this.handleSubmit}>
          <FormInput
            name="username"
            type="text"
            value={this.state.username}
            handleChange={this.handleChange}
            label="Username"
            required
          />
          <FormInput
            name="password"
            type="password"
            value={this.state.password}
            handleChange={this.handleChange}
            label="Password"
            required
          />
          <div className="buttons">
            <CustomButton type="submit">Sign In</CustomButton>
          </div>
        </form>
      </div>
    );
  }
}

export default SignIn;
