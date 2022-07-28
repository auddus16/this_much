import React, { memo } from 'react';
import 'chart.js/auto';
import { Chart } from "react-chartjs-2"

const LineChart = memo((props) => {
  let labels=["red","blue","yellow","green","purple","orange"];
  let values=[1,2,3,4,5,6];

  if(props.category=='thisMuch'){
    labels = Object.keys(props.monthList);
    values = labels.map(m =>Number(props.monthList[m]) || 0);
  }
  
  const data = {
    labels: labels,
    datasets:[{
      data: values,
      backgroundColor:[
        '#558CCF',
        "#48C179",
        "#C6F38B",
        "#F8DC7A",
        "purple",
        "orange"
      ]
    }]
  };
  const options = {
    maintainAspectRatio :false,
    scales: {
      yAxes: [
        {
          ticks: {
            beginAtZero: true,
          },
        },
      ],
    },
    plugins: {
      legend: {
        display: false,
      }
    }
  };
  return (
    <div style = {{width: "auto", margin:"0 auto", height:"100%"}}>
      <Chart type='line' data={data} options={options}/>
    </div>
  );
});

export default LineChart;