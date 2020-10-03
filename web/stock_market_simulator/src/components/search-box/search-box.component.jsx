import React from 'react';
import { Component } from 'react';
import './search-box.styles.scss';
import axios from 'axios';
import { Link } from 'react-router-dom';
class SearchBox extends Component {
  constructor(props) {
    super(props);

    this.state = {
      companies: [],
      remainingCompanies: []
    };
  }
  componentDidMount() {
    this.getCompanies();
  }

  getCompanies = () => {
    let URL = 'https://api.iextrading.com/1.0/ref-data/symbols';
    axios
      .get(URL)
      .then(response => {
        this.setState({
          companies: response.data,
          remainingCompanies: []
        });
        console.log(typeof response.data);
        console.log(this.state.companies);
      })
      .catch(error => {
        console.log(error);
      });
  };

  onChange = event => {
    console.log(event.target.value);
    let searchQuery = event.target.value.toLowerCase();
    let displayedCompanies = this.state.companies.filter(company => {
    let searchValue = company.name.toLowerCase();
      if(event.target.value !== ""){
      return searchValue.indexOf(searchQuery) !== -1;

      }
    });

    this.setState({
      companies: this.state.companies,
      remainingCompanies: displayedCompanies
    });
    console.log(this.state.remainingCompanies);
  };
  render() {
    return (
      <div className="holder">
        <input
          type="text"
          name="searchBox"
          id=""
          placeholder="Company Name"
          onChange={this.onChange}
          className="search_input"
        />
        
        <ul>
          {this.state.remainingCompanies.slice(0, 5).map((value, index) => {
            return (
              <Link key={index} to={`company/${value.symbol}`}>
                <li key={index}>
                  {value.name} <span>Symbol: {value.symbol}</span>
                </li>
              </Link>
            );
          })}
        </ul>
        <div></div>
      </div>
    );
  }
}

export default SearchBox;
