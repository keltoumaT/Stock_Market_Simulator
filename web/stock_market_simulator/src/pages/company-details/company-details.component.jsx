import React, { Component } from 'react';
import CompanyTable from '../../components/company-table/company-table.component';
import DealForm from '../../components/deal-form/deal-form.component';
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
    return(
    <div>
    <CompanyTable symbol={this.state.symbol} />
    <DealForm symbol={this.state.symbol}/>
    </div>
    )
  }
}

export default CompanyDetails;
