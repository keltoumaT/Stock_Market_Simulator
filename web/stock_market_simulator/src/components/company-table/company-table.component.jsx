import React, { Component } from 'react';
import axios from 'axios';
import './company-table.styles.scss';
class CompanyTable extends Component {
  constructor({ symbol }) {
    super();
    this.state = {
      companyData: {},
      symbol: symbol
    };
  }

  componentDidMount() {
    this.getCompanyData();
  }
  getCompanyData = () => {
    let URL = `http://localhost:8585/companies/${this.state.symbol}`;
    axios
      .get(URL)
      .then(response => {
        console.log(response.data);
        this.setState({
          companyData: response.data,
          symbol: this.state.symbol
        });
      })
      .catch(error => {
        console.log(error);
      });
  };

  render() {
    const arr = [];
    return (
      <div>
        <h1>Company Info : {this.state.symbol}</h1>
        <div>
          <table>
            <thead>
              <tr>
                <th scope="col">Symbol</th>
                <th scope="col">Calculation Price</th>
                <th scope="col">Company Name</th>
                <th scope="col">Latest Price</th>
                <th scope="col">High</th>
                <th scope="col">Low</th>
                <th scope="col">Latest Time</th>
                <th scope="col">Close</th>
                <th scope="col">Open</th>
              </tr>
            </thead>
            <tbody>
              <tr className="content">
                {/* Loop here for td  */}
                {Object.entries(
                  this.state.companyData
                ).forEach(([key, value]) => arr.push(value))}
                {arr.map((value, index) => {
                  return (
                    <td scope="row" key={index}>
                      {value}
                    </td>
                  );
                })}
              </tr>
            </tbody>
          </table>
        </div>

      </div>
      // Put DEALFORM component here and pass it props i.e => data
    );
  }
}

export default CompanyTable;
