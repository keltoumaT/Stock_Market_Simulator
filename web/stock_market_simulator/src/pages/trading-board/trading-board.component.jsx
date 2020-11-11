import React from "react";
import DealList from "../../components/deal-list/deal-list.component";
import axios from 'axios';
import CompanyTable from "../../components/company-table/company-table.component";
import Chart from "../../components/chart/chart.component";
import SearchBar from "../../components/search-bar/search-bar.component";
import './trading-board.styles.scss';

class TradingBoard extends React.Component{
    constructor({
        match: {
          params: { walletId }
        }
      }){
        super();
        
            this.state = {
                walletId: walletId,
                data:{},
                currentSymbol: "AAPL",
                range:"5d",
                walletObj : {},
            }
    }

    componentDidMount(){
       this.getWalletInfo();

    }

    componentWillMount(){
       this.getLastDeal();
    }

    getWalletInfo = () =>{
        let token = localStorage.getItem("access_token")
    const config = { headers:{'Authorization': `Bearer ${token}`}};
        let URL_GET = `http://localhost:8585/api/private/wallets/${this.state.walletId}`
        axios
        .get(URL_GET, config) 
        .then(response =>{
            this.setState({
                walletId: this.state.walletId,
                data:response.data,
                currentSymbol: this.state.currentSymbol,
                range:this.state.range,
                walletObj:response.data
            })
            console.log(this.state.data);
            
        })
        .catch(error =>{
            console.log(error);
        })
    }

    getLastDeal = () => {
        let token = localStorage.getItem("access_token")
        const config = { headers:{'Authorization': `Bearer ${token}`}};
        let URL = `http://localhost:8585/api/private/deals/first/${this.state.walletId}`;
        axios
        .get(URL, config)
        .then(response =>{
            this.setState({
                walletId: this.state.walletId,
                data: this.state.data,
                currentSymbol: response.data.symbol,
                range:this.state.range,
                walletObj:this.state.walletObj
            })
            console.log(this.state)
        })
        .catch(error =>{
            console.log(error);
        })
    }

    handleClick = async event =>{
        event.preventDefault();
        let token = localStorage.getItem("access_token")
        const config = { headers:{'Authorization': `Bearer ${token}`}};
        let URL_PUT = `http://localhost:8585/api/private/wallets/${this.state.walletId}`
        axios
        .put(URL_PUT,this.state.data, config)
        .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
    }

    handleChange = event =>{
        const capital = event.target.value;
        this.setState({
            walletId: this.state.walletId,
            data:{
                name:this.state.data.name,
                memberId:1,
                memo:this.state.data.memo,
                capital: capital
            },
            walletObj:this.state.walletObj
        })
    }
  
    changeCompanySymbol = (symbol) =>{
       this.setState({
        walletId: this.state.walletId,
        data:this.state.data,
        currentSymbol: symbol,
        range:this.state.range,
        walletObj:this.state.walletObj
        }) 
    }

    changeRange  = (event) => {
        console.log(event.target.value);
        this.setState({
            walletId: this.state.walletId,
            data:this.state.data,
            currentSymbol: this.state.currentSymbol,
            range: event.target.value,
            walletObj:this.state.walletObj
        })
    }

    getCompanyObj = (obj) =>{
        this.setState({
            walletId: this.state.walletId,
            data:this.state.data,
            currentSymbol: this.state.currentSymbol,
            range: this.state.range,
            walletObj:this.state.walletObj,
            companyObj: obj
        })
    }

    randomNum = () => {
        return Math.floor(Math.random()* 1000)
    }

    handleBuy = event => {
        //if else
        event.preventDefault();
        let quantity = document.getElementById("buy_quantity").value;
        console.log(quantity);
        let token = localStorage.getItem("access_token")
        const config = { headers:{'Authorization': `Bearer ${token}`}};
        axios
          .post('http://localhost:8585/api/private/deals', {
            companyName: document.getElementById("2").innerHTML,
            quantity: quantity,
            unityPrice: document.getElementById("3").innerHTML,
            symbol:document.getElementById("0").innerHTML,
            walletId: this.state.walletId
          }, config)
          .then(response => {
            console.log(response);
            this.randomNum();
            console.log(response.status)
          })
          .catch(error => {
            console.log(error);
          });
      };
    render(){
        if(this.state.currentSymbol !== ""){
            return(
                <div id="trading_board_body">
                    <div id="d">
                        <div className="a">
                    <h1 id="trading_b_header">TRADING BOARD</h1>
                        <p className="wallet_p">Wallet: {this.state.data.name}</p>
                        <p className="wallet_p">Capital: ${this.state.data.capital}</p>
                       <form>
                           <input type="number" name="capital" placeholder="New capital amount" onChange={this.handleChange}/>
                           <button type="submit" onClick={this.handleClick}>ok</button>
                       </form>
                       </div>
                       <div className="a">
                    <SearchBar  data={this.changeCompanySymbol.bind(this)}/>
                    </div>
                    </div>
                    <br/>
                    <DealList walletId={this.state.walletId} capital={this.state.data.capital} walletObj={this.state.walletObj}/>
                    <br/>
                    <hr/>
                    <br/>
                    <CompanyTable getCompanyObj={this.getCompanyObj.bind(this)} key={this.state.currentSymbol} symbol={this.state.currentSymbol}/>
                    <hr/>
                    <table id="x" key={this.state.range} style={{borderRadius: '5px!important'}}>
                        <tbody>
                        <tr>
                            <td>
                                <button className={"button button2"} onClick={this.changeRange} value="5d">5d</button><button className={"button button2"}  onClick={this.changeRange} value="1m">1m</button><button  className={"button button2"}  onClick={this.changeRange} value="3m">3m</button></td>
                            <td id="sell_buy">BUY</td>
                        </tr>
                        <tr>
                            <td>
                                <Chart key={this.state.currentSymbol} symbol={this.state.currentSymbol} range={this.state.range}/>
                            </td>
                            <td>
                                <input type="number" name="quantity" id="buy_quantity" placeholder="QUANTITY"/>
                                <button id="buy_button" onClick={this.handleBuy}>BUY</button>
                            </td>
                        </tr>
                        </tbody>
                        
                    </table>
                </div>
            )
        }
        return(<p>LOADING</p>)
       
    }
}

export default TradingBoard;
