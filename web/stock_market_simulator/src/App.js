import React from 'react';
import {
  Switch,
  Route
} from 'react-router-dom';
import HomePage from './pages/homepage/homepage.component';
import SignInAndSignUpPage from './pages/sign-in-and-sign-up/sign-in-and-sign-up.component';
import Companies from './pages/companies/companies.component';
import Header from './components/header/header.component';
import './App.css';
import CompanyDetails from './pages/company-details/company-details.component';
import WalletPage from './pages/wallet/wallet.component';


class App extends React.Component {
  constructor() {
    super();
    this.state = {}
  }
  render() {
    return ( <div>
      <Header/>
      <Switch>
      <Route exact path = "/"
      component = {
        HomePage
      }
      /> 
      <Route path = "/signup"
      component = {
        SignInAndSignUpPage
      }
      /> 
      <Route path = "/companies"
      component = {
        Companies
      }
      /> 
      <Route path = "/company/:symbol"
      component = {
        CompanyDetails
      }
      /> 
      <Route path = "/wallets"
      component={WalletPage}
      />
      </Switch> 
      </div>
    )
  }
}

export default App;