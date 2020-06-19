import React, { Component } from 'react';
import axios from 'axios';

class DealList extends Component{
    
    constructor(){
        super();
        this.state={
            deal:[]
        }
    }


    componentDidMount(){
        this.getData();
    }

    getData = () =>{
        const URL = `http://localhost:8585/deals/all/1`;
        axios.get(URL)
        .then(response =>{
                this.setState({
                    deal:response.data
                })
                console.log(this.state.deal);
        })
        .catch(error =>{
            console.log(error);
        })
    };


    render(){
        return(
            <div> 
            <table className="table table-dark">
  <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Company</th>
      <th scope="col">Quantity</th>
      <th scope="col">Unity Price</th>
      <th scope="col">Total Cost</th>
    </tr>
  </thead>
    <tbody>
            {this.state.deal.map((el, index)=>(
                
                <tr key={index}>
                    <td>{el.date}</td>
                    <td>{el.companyName}</td>
                    <td>{el.quantity}</td>
                    <td>{el.unityPrice}</td>
                    <td>${el.quantity * el.unityPrice}</td>
                </tr>
                
            ))}
    </tbody>
</table>
</div>
        )
    }
}

export default DealList;