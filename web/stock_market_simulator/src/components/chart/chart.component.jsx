
import React from 'react';
import ReactApexChart from 'react-apexcharts'
import axios from "axios";
import Loading from '../loading/loading.component';
class CandleStickChart extends React.Component {
    constructor(props) {
      super(props);

      this.state = {
      
        series: [{
          data: []
        }],
        options: {
          chart: {
            type: 'candlestick',
            height: 350
          },
          title: {
            text: 'Chart',
            align: 'left'
          },
          xaxis: {
            type: 'datetime'
          },
          yaxis: {
            tooltip: {
              enabled: true
            }
          },
          plotOptions: {
            candlestick: {
              colors: {
                upward: '#39d3ec',
                downward: '#9CAEF8'
              },
              wick: {
                useFillColor: true
              }
            }
          }
      
        },
        
      
      };
      console.log(this.props.symbol)
    }
  componentDidMount(){
    let token = localStorage.getItem("access_token")
    const config = { headers:{'Authorization': `Bearer ${token}`}};
    let range = "5d";
    console.log(this.props.range);
    if(this.props.symbol !== undefined){
      axios.get(`http://localhost:8585/api/private/companies/${this.props.symbol}/${this.props.range}`, config)
      .then(response => {
  
          Object.entries(response.data).forEach((entry)=>{
          let tmpArray=[];
          let tmpDate;
          let dataObj;
              tmpDate = entry[1].date;
              tmpArray.push(entry[1].open);
              tmpArray.push(entry[1].high);
              tmpArray.push(entry[1].low);
              tmpArray.push(entry[1].close);
              if(range =="1d"){
                  let tmpTime = entry[1].label;
                  if(!tmpTime.includes(":")){
                     tmpTime = tmpTime.slice(0, -3) +":00 "+tmpTime.slice(-3);
                     tmpDate = entry[1].date + " " + tmpTime;
                     tmpDate = new Date(tmpDate.split('-').join('/')).getTime();
                  }
                  else{
                    tmpDate = tmpDate + " " + tmpTime;
                    tmpDate = new Date(tmpDate.split('-').join('/')).getTime();
                  }
              }
             dataObj = {
                x : tmpDate,
                y : tmpArray
            }
            this.setState({
                series: [{
                    data: [...this.state.series[0].data, 
                    dataObj]
                  }],
                  options: {
                    chart: {
                      type: 'candlestick',
                      height: 350
                    },
                    title: {
                      text: `Company Symbol : ${this.props.symbol}`,
                      align: 'left'
                    },
                    xaxis: {
                      type: 'datetime'
                    },
                    yaxis: {
                      tooltip: {
                        enabled: true
                      },
                    },
                    plotOptions: {
                      candlestick: {
                        colors: {
                          upward: '#39d3ec',
                          downward: '#9CAEF8'
                        },
                        wick: {
                          useFillColor: true
                        }
                      }
                    }
                    
                  }
            })
          })
      })
      .catch(error =>{
          console.log(error);
      })
      console.log(this.state.series[0].data)
    }
    
  }

  componentWillUnmount() {
    // fix Warning: Can't perform a React state update on an unmounted component
    this.setState = (state,callback)=>{
        return;
    };
}

    render() {
      //Take status instead of array length ? 
      if(this.state.series[0].data.length === 0){
        return(
          <Loading/>
        )
      }else{
        return (
        

          <div id="chart">
            <ReactApexChart options={this.state.options} series={this.state.series} type="candlestick" height={350} />
          </div>
        )
        
      }
      
    }
  }
export default CandleStickChart;