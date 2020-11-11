import React from "react";
import axios from 'axios';
import {FiX } from "react-icons/fi";
import './modal.styles.scss';

class Modal extends React.Component {
    
    constructor(props){
        super(props);
        this.state={
            dealHasBeenDeleted : "",
            dealHasBeenUpdated:"",
        }

        console.log("props "  + this.props)
    }

    
    handleClick = (quantityToSell) =>{
        console.log(quantityToSell);
        let remainingQuantity = quantityToSell;
        if(this.props.currentQuantity !== quantityToSell){
            remainingQuantity = this.props.currentQuantity - quantityToSell;
        }
        console.log( {
            "companyName": this.props.companyName,
            "quantity": remainingQuantity,
            "unityPrice": this.props.currentUnityPrices[this.props.symbol].quote.latestPrice,
            "symbol": this.props.symbol,
            "id":this.props.dealId,
            "walletId":this.props.walletObj.id
        })
        let token = localStorage.getItem("access_token")
        const config = { headers:{'Authorization': `Bearer ${token}`}};
        axios.put(`http://localhost:8585/api/private/deals/${this.props.dealId}`, 
        {
            "companyName": this.props.companyName,
            "quantity": remainingQuantity,
            "unityPrice": this.props.currentUnityPrices[this.props.symbol].quote.latestPrice,
            "symbol": this.props.symbol,
            "id":this.props.dealId,
            "walletId":this.props.walletObj.id
        }, config)
        .then(response =>(
            console.log(response)
        ))
        .catch(error => (
            console.log(error)
        ))
    }
    
    render () {
        const { isOpen, onClose } = this.props;

        return (
        <div className={isOpen ? 'modal modal--is-open' : 'modal'}>
            <p id="prompt">What quanity of stock from the <span style={{color:"#FF4081", display:"inline"}}>{this.props.companyName}</span> company would you like to sell ? </p>
            <input type="text" name="quantity"   placeholder="QUANTITY" id="quantity"/>
            <button className="deal_sell_button" onClick={()=> this.handleClick(document.getElementById("quantity").value)}>Sell</button>
            <button className="deal_sell_button" onClick={onClose}>Close</button>
            {this.state.dealHasBeenDeleted == true && <p>This deal has been deleted</p>}
            {this.state.dealHasBeenUpdated == true && <p>This deal has been updated</p>}
        </div>
        );
    
}
}

export default Modal;