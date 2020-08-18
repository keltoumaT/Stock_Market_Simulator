import React from "react";
import "./pagination.styles.scss";

class Pagination extends React.Component{
    constructor(props){
        super(props);
        
    }

    
    render(){
       
    return( 
        [...Array(this.props.numberOfPages)].map((e, i) => 
            <div key={i} id="pagination_body">
                <div className="toggle">
                    <input id={`toggle-${i}`} type="radio" name="toggle" value={`${i}`} onClick={()=>{this.props.nextPage(i)}}/>
                    <label className={`label-${i}`} htmlFor={`toggle-${i}`}>{i+1}</label>
                </div>
            </div>)
  
        )}
    
}

export default Pagination;