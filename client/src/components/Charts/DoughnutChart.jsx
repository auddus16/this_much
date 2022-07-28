import React, { memo } from 'react';
import 'chart.js/auto';
import { Chart } from "react-chartjs-2"

const DoughnutChart = memo((props) => {
  const category = [];
  const value = [];
  console.log(props.categoryList);
  const categorys = Object.keys(props.categoryList);
  const categoryArray = categorys.map(d =>{
    return {category: d, value: Number(props.categoryList[d]) || 0}
  });
  const sortValue = categoryArray.sort((a, b)=>b.value-a.value);
  if(sortValue[0].value){
    sortValue.forEach(a =>{
      category.push(a.category);
      value.push(a.value);
    });
  }else{
    category.push('비어있음');
    value.push(100);
  }
  const data = {
    labels:category,
    datasets:[{
      data: value,
      backgroundColor:[
        '#558CCF',
        "#48C179",
        "#C6F38B",
        "#F8DC7A",
        "purple",
        "orange"
      ]
    }],
  };
  const options = {
    maintainAspectRatio :false,
    plugins: {
      legend: {
        display: false,
      }
    }
  }
  return (
    <div style = {{width: "auto", margin:"0 auto", height:"100%"}}>
      <Chart type='doughnut' data={data} options={options}/>
    </div>
  );
});

export default DoughnutChart;