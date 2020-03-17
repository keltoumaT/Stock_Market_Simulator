import React from 'react';
import { Link } from 'react-router-dom';
import './header.styles.scss';

const Header = () => (
  <div className="header">
    <Link to="/">Home</Link>
    <div className="options">
      <Link className="option" to ="/wallets">Wallets</Link>
      <Link className="option" to="/companies">
        Companies
      </Link>
      <Link className="option" to="/signup">
        Sign Up/ Sign In
      </Link>
    </div>
  </div>
);

export default Header;
