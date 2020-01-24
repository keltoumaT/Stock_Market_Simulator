import React from 'react';
import SignUp from '../../components/sign-up/sign-up.component'
import SignIn from '../../components/sign-in/sign-in.component';
import './sign-in-and-sign-up.styles.scss';

const SignInAndSignUpPage = () => (
  <div className="sign-in-and-sign-up">
    <SignUp />
    <SignIn />
  </div>
);

export default SignInAndSignUpPage;
