import React from 'react';

import './search-box.styles.scss';
import Axios from 'axios';

class SearchBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      companies: [],
      searchField: ''
    };
  }

  componentDidMount() {
    Axios.get('https://api.iextrading.com/1.0/ref-data/symbols').then(res => {
      console.log(res.data);
      this.setState({
        companies: res.data,
        searchField: this.state.searchField
      });
      console.log(this.state.companies);
    });
  }

  onSearchChange = event => {
    console.log('changed');
    this.setState({ searchField: event.target.value });
  };
  render() {
    const { companies, searchField } = this.state;
    const filteredCompanies = companies.filter(company =>
      company.name.toLowerCase().includes(searchField.toLowerCase())
    );
    return (
      <input
        className="search-box"
        type="search"
        placeholder="search company"
        onChange={this.onSearchChange}
      />
    );
  }
}

export default SearchBox;
