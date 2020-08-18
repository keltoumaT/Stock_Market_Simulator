import React, { Component } from 'react';
import Modal from "../modal/modal.component";
import "./deal-list.styles.scss"
import axios from 'axios';
import Pagination from '../pagination/pagination.component';
import Loading from '../loading/loading.component';

class DealList extends Component{
    
    constructor(props){
        super(props);
        this.state={
            walletId:this.props.walletId,
            deal:[],
            latestPrices:[],
            isModalOpen:false,
            numberOfPages:"",
            page: 0,
            currentCompanyName:"",
            dealId:"",
            quantity:"",
            totalCost:""
        }
        this.openModal = this.openModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
    }

   getPages = (pageNum) =>{
    const URL = `http://localhost:8585/deals/all/page/${this.state.walletId}?p=${pageNum}&s=2&by=walletId`;
    axios.get(URL)
    .then(response =>{
        this.setState({
            walletId : this.state.walletId,
            deal: response.data.content,
            latestPrices : [],
            isModalOpen :this.state.isModalOpen,
            numberOfPages : response.data.totalPages,
            currentCompanyName:this.state.currentCompanyName,
            dealId:this.state.dealId,
            quantity:this.state.quantity,
            totalCost:this.state.totalCost
        })
        this.getLatestPrices();
    })
    .catch(error =>{
        console.log(error);
    })
   
   }

    componentWillMount(){
        //this.getData();
        this.getPages(this.state.page);
    }

    getCurrentSelectedCompany = (companyName, symbol, quantity, dealId,totalCost, gainsOrLosses, unityPrice) =>{
        console.log(companyName, quantity, dealId);
        this.setState({
            walletId : this.state.walletId,
            deal: this.state.deal,
            latestPrices : this.state.latestPrices,
            isModalOpen :this.state.isModalOpen,
            numberOfPages : this.state.numberOfPages,
            currentCompanyName : companyName,
            dealId: dealId,
            quantity:quantity, 
            gainsOrLosses : gainsOrLosses,
            totalCost: totalCost, 
            unityPrice : unityPrice,
            symbol : symbol

        })
    }

    getNextPage = pageNum =>{
        console.log("page num = "  + pageNum);
        this.getPages(pageNum);
    }

    getData = () =>{
        const URL = `http://localhost:8585/deals/all/${this.props.walletId}`;
        axios.get(URL)
        .then(response =>{
                this.setState({
                    walletId:this.walletId,
                    deal:response.data,
                    latestPrices:[],
                    isModalOpen: this.state.isModalOpen, 
                    numberOfPages : this.state.numberOfPages,
                    currentCompanyName: this.state.currentCompanyName,
                    dealId:this.state.dealId,
                    quantity:this.state.quantity, 
                    totalCost: this.state.totalCost
                })
            this.getLatestPrices();
                console.log(this.state.deal);
        })
        .catch(error =>{
            console.log(error);
        })
       
       
        }


        getLatestPrices = () =>{
            this.state.deal.map((el, index)=>{
                axios.get(`http://localhost:8585/companies/${el.symbol}`)
                .then(response =>{
                    console.log(response.data.latestPrice)
                    this.setState({
                        walletId: this.state.walletId,
                        deal : this.state.deal,
                        latestPrices : [...this.state.latestPrices, response.data.latestPrice],
                        isModalOpen : this.state.isModalOpen,
                        numberOfPages: this.state.numberOfPages,
                        currentCompanyName:this.state.currentCompanyName,
                        dealId: this.state.dealId,
                        quantity:this.state.quantity,
                        totalCost:this.state.totalCost
                    });
                console.log("latestPrices ", this.state.latestPrices);

                });
            }
            )}
    
     cutNumber = (number, digitsAfterDot) => {
        let str = `${number}`;
        str = str.slice(0, str.indexOf('.') + digitsAfterDot + 1);
        return str
    }

    handleClick = event =>{
            console.log(event.target.value)
            let dealId  = event.target.value;
            let URL_DELETE = `http://localhost:8585/deals/${dealId}`
            axios
            .delete(URL_DELETE)
            .then(response =>{
                this.getData();
                console.log(response);
            })
            .catch(error =>{
                console.log(error);
            })
    }

    getPercentVariation = (unityPrice, index) =>{
       let variation;
        variation =  (this.state.latestPrices[index] - unityPrice)/ unityPrice * 100;
        return variation;
    }

    openModal() {
        this.setState({ isModalOpen: true})
      }
      
      closeModal () {
        this.setState({ isModalOpen: false })
      }
    render(){
        if(this.state.deal.length !== 0){
            return(
        
                <div> 
                <table>
      <thead>
        <tr>
          <th className="first_th" scope="col">Date</th>
          <th scope="col">Symbol</th>
          <th scope="col">Company</th>
          <th scope="col">Quantity</th>
          <th scope="col">Unity Price</th>
          <th scope="col">Total Cost</th>
          <th scope="col">Current Price</th>
          <th>Variation %</th>
          <th>Gains or Losses</th>
          <th className="last_th" scope="col"></th>
        </tr>
      </thead>
        <tbody>
                {this.state.deal.map((el, index)=>(
                    <tr key={index}>
                        <td>{el.date.replace(/T/g, " ").split('.')[0]}</td>
                        <td>{el.symbol}</td>
                        <td>{el.companyName}</td>
                        <td>{el.quantity}</td>
                        <td>$ {el.unityPrice}</td>
                        <td>$ {this.cutNumber(el.quantity * el.unityPrice, 4)}</td>
                        <td>$ {this.state.latestPrices[index]}</td>
                        {/* Extract this in another method and store in an array then display using the index to avoid lagging */}
                        <td style={{color: this.cutNumber(this.getPercentVariation(el.unityPrice, index),2) > 0 ? "green" : "red"} }>
                            {this.cutNumber(this.getPercentVariation(el.unityPrice, index),2)}%
                        </td>
                        <td style={{color: this.cutNumber((el.unityPrice * this.getPercentVariation(el.unityPrice, index)/100)* el.quantity,4) == "NaN" ? "white" : "black"}}>$ {this.cutNumber((el.unityPrice * this.getPercentVariation(el.unityPrice, index)/100)* el.quantity,4)}</td>
                        <td><button type="submit" name={el.companyName} value={el.id} onClick={()=>{this.getCurrentSelectedCompany(el.companyName, el.symbol, el.quantity, el.id,this.cutNumber(el.quantity * el.unityPrice, 4), this.cutNumber((el.unityPrice * this.getPercentVariation(el.unityPrice, index)/100)* el.quantity,4), el.unityPrice); this.openModal();}}>Sell</button></td>
                    </tr>
                   
                ))}
        </tbody>
    </table>
    <div id="pagination_holder">
        <Pagination numberOfPages={this.state.numberOfPages} nextPage={this.getNextPage.bind(this)}/>
    </div>
    <Modal isOpen={this.state.isModalOpen} onClose={this.closeModal}  walletObj={this.props.walletObj} totalCost={this.state.totalCost} symbol={this.state.symbol} gainsOrLosses={this.state.gainsOrLosses} unityPrice={this.state.unityPrice} companyName={this.state.currentCompanyName} dealId={this.state.dealId} currentQuantity={this.state.quantity} capital={this.props.capital}/>
    </div>
            )
        }
        return ( <p>You don't have any deal yet</p>);

    }

}
export default DealList;