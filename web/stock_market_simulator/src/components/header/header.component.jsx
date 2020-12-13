import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import jwt_decode from "jwt-decode";
import {FiLogOut} from 'react-icons/fi';
import './header.styles.scss';


class Header extends Component{
  constructor(){
    super();
    this.state={
      isLoggedIn:false
    }
   
  }

  
  componentDidMount(){
    if(localStorage.getItem("access_token")== null){
      this.setState({
        isLoggedIn:false
      })
    }
    else{
      this.setState({
        isLoggedIn :true
      })
      console.log(this.state.decodedToken);
      let decoded_token = jwt_decode(localStorage.getItem("access_token"))
     localStorage.setItem("username", decoded_token.user_name);
    }
  }

  logOut = () =>{
    localStorage.removeItem("access_token");
    localStorage.removeItem("username");
    window.location.replace("/");
    
  }


  render(){
    return(
      <div className="header">
         {this.state.isLoggedIn ? <p id="user_welcome">Welcome <span>{localStorage.getItem("username")}</span></p>  :null}
        <div className="options">
        <Link className="option" to="/">Home</Link>
        {this.state.isLoggedIn ? <Link className="option" to ="/wallets">Wallets</Link>  :null}
        {!this.state.isLoggedIn ? <Link className="option" to="/signup">
            Sign Up/ Sign In
          </Link> : <Link  onClick={()=>this.logOut()} className="option" to="">Log out <FiLogOut/></Link>}
          
        </div>
      </div>
    )
  }
}

export default Header;
