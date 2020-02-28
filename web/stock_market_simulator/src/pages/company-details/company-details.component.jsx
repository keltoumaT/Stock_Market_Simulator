import React, { Component } from 'react';
import CompanyTable from '../../components/company-table/company-table.component';
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
    return <CompanyTable symbol={this.state.symbol} />;
  }
}

export default CompanyDetails;
