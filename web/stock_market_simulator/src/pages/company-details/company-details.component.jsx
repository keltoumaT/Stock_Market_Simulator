import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

class CompanyDetails extends Component {
  constructor({
    match: {
      params: { symbol }
    }
  }) {
    super();
    this.state = {
      symbol: symbol
    };
  }

  render() {
    return (
      <div>
        <h1>Company Details</h1>
        <h2>{this.state.symbol}</h2>
      </div>
    );
  }
}

export default CompanyDetails;
