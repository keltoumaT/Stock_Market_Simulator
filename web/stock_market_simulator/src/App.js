import React from 'react';
import { Switch, Route } from 'react-router-dom';
import HomePage from './pages/homepage/homepage.component';
import SignInAndSignUpPage from './pages/sign-in-and-sign-up/sign-in-and-sign-up.component';
import Header from './components/header/header.component';
import './App.css';


class App extends React.Component{
  constructor(){
    super();
    this.state = {}
  }
  render(){
    return(
      <div>
        <Header/>
        <Switch>
          <Route exact path="/" component={HomePage}/>
          <Route  path="/signup" component={SignInAndSignUpPage}/>
        </Switch>
      </div>
    )
  }
}

export default App;
