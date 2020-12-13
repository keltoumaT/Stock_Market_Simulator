import React from 'react';
import { Component } from 'react';
import './search-bar.styles.scss';
import axios from 'axios';
class SearchBar extends Component {
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
    document.getElementById("companyList").style.display="block";
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

  clearInputField(){
      document.getElementById("searchBar").value = "";
      document.getElementById("companyList").style.display="none";
  }
  componentWillUnmount() {
    // fix Warning: Can't perform a React state update on an unmounted component
    this.setState = (state,callback)=>{
        return;
    };
}
  render() {
    return (
      <div className="holder">
        <input
          type="text"
          name="SearchBar"
          id="searchBar"
          placeholder="SEARCH FOR A COMPANY"
          onChange={this.onChange}
          className="search_input"
        />
        
        <ul id="companyList">
          {this.state.remainingCompanies.slice(0, 3).map((value, index) => {
            return (
                <li onClick={()=>{this.props.data(value.symbol); this.clearInputField()}} key={index}>
                  {value.name} <span>Symbol: {value.symbol}</span>
                </li>
            
            );
          })}
        </ul>
        <div></div>
      </div>
    );
  }
}

export default SearchBar;
