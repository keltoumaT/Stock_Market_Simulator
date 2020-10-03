import React from "react";
import axios from 'axios';
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
        if(!this.props.currentQuantity === quantityToSell){
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
        axios.put(`http://localhost:8585/deals/${this.props.dealId}`, 
        {
            "companyName": this.props.companyName,
            "quantity": remainingQuantity,
            "unityPrice": this.props.currentUnityPrices[this.props.symbol].quote.latestPrice,
            "symbol": this.props.symbol,
            "id":this.props.dealId,
            "walletId":this.props.walletObj.id
        })
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
            <p>Please specify the quantity of stock you want to sell for the <span style={{color:"#FF4081", display:"inline"}}>{this.props.companyName}</span> company</p>
            <input type="text" name="quantity" placeholder="quantity" id="quantity"/>
            <button onClick={()=> this.handleClick(document.getElementById("quantity").value)}>SELL</button>
            <button onClick={onClose}>close</button>
            {this.state.dealHasBeenDeleted == true && <p>This deal has been deleted</p>}
            {this.state.dealHasBeenUpdated == true && <p>This deal has been updated</p>}
        </div>
        );
    
}
}

export default Modal;