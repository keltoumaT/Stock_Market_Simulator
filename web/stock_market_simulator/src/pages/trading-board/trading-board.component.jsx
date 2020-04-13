import React from "react";


class TradingBoard extends React.Component{
    constructor({
        match: {
          params: { walletId }
        }
      }){
        super();
            this.state = {
                walletId: walletId
            }
        
    }


    render(){
        return(
            <div>
                <h1>TRADING BOARD</h1>
            </div>
        )
    }
}

export default TradingBoard;