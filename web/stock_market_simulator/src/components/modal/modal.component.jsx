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

    }

    
    handleClick = () =>{
        console.log("ok")
        let amountToCapital = this.props.capital;
        console.log(amountToCapital);
        let gainsOrLosses=this.props.gainsOrLosses;
        console.log(gainsOrLosses);
        let newQuantity = document.getElementById("quantity").value;
        console.log(newQuantity);
        console.log("total cost = ", this.props.totalCost);

        console.log("wallet obj= ", this.props.walletObj);
        console.log("currentQuantity = ", this.props.currentQuantity);
        console.log("new quantity ", newQuantity);
        if(newQuantity < this.props.currentQuantity && newQuantity >= 1){
            // UPDATE DEAL
            let remainingQuantity = this.props.currentQuantity - newQuantity;
            console.log("remaining quantity = ", remainingQuantity)
            amountToCapital = Number(amountToCapital) + Number(newQuantity * this.props.unityPrice) + ((Number(gainsOrLosses)/this.props.currentQuantity)* remainingQuantity);
            console.log("symbol  = ", this.props.symbol)
            axios
            .put(`http://localhost:8585/deals/${this.props.dealId}`,
            {
                "symbol":this.props.symbol,
                "companyName": this.props.companyName,
                "quantity": remainingQuantity,
                "unityPrice":this.props.unityPrice,
                "walletId":this.props.walletObj.id
            })
            .then(response =>{
                console.log(response);
                axios
                .put(`http://localhost:8585/wallets/${this.props.walletObj.id}`, 
                {
                    "memo": this.props.walletObj.memo,
                    "name":this.props.walletObj.name,
                    "capital":amountToCapital,
                    "memberId":this.props.walletObj.memberId
                })
                .then(response =>{
                    console.log(response);
                    this.setState({
                        dealHasBeenDeleted:false,
                        dealHasBeenUpdated: true
                    })
                })
                .catch(error=>{
                    console.log(error);
                })
            })
            .catch(error =>{
                console.log(error);
            })


        }
        else if(newQuantity == this.props.currentQuantity){
            // delete deal
            console.log("about to be deleted")
            let URL_DELETE = `http://localhost:8585/deals/${this.props.dealId}`;
            axios
            .delete(URL_DELETE)
            .then(response =>{
                console.log(response);
                amountToCapital = Number(amountToCapital) + Number(this.props.totalCost) + Number(gainsOrLosses);
                console.log({
                    "memo": this.props.walletObj.memo,
                    "name":this.props.walletObj.name,
                    "capital":amountToCapital,
                    "memberId":this.props.walletObj.memberId
                })
                axios
                .put(`http://localhost:8585/wallets/${this.props.walletObj.id}`, 
                {
                    "memo": this.props.walletObj.memo,
                    "name":this.props.walletObj.name,
                    "capital":amountToCapital,
                    "memberId":this.props.walletObj.memberId
                })
                .then(response =>{
                    console.log(response);
                    this.setState({
                        dealHasBeenDeleted:true,
                        dealHasBeenUpdated: false
                    })
                })
                .catch(error=>{
                    console.log(error);
                })
            })
            .catch(error =>{
                console.log(error);
            })
        }
        else{
            // You don't have the newQuantity on this deal
        }
    }
    
    render () {
        const { isOpen, onClose } = this.props;

        return (
        <div className={isOpen ? 'modal modal--is-open' : 'modal'}>
            <p>Please specify the quantity of stock you want to sell for the <span style={{color:"#FF4081", display:"inline"}}>{this.props.companyName}</span> company</p>
            <input type="text" name="quantity" placeholder="quantity" id="quantity"/>
            <button onClick={this.handleClick}>SELL</button>
            <button onClick={onClose}>close</button>
            {this.state.dealHasBeenDeleted == true && <p>This deal has been deleted</p>}
            {this.state.dealHasBeenUpdated == true && <p>This deal has been updated</p>}
        </div>
        );
    
}
}

export default Modal;