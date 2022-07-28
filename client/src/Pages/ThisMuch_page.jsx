import React, { useState, useEffect, memo } from 'react';
import styles from './ThisMuch_page.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from 'react-bootstrap';
import CalendarHeader from '../components/Calendar_header/calendar_header';
import DoughnutChart from '../components/Charts/DoughnutChart';
import LineChart from '../components/Charts/LineChart';
import FixedExpenditure from '../components/fixedExpenditure/fixedExpenditure';
import Loader from '../components/Loader'
import axios from 'axios'

const ThisMuchPage = memo((props) => {
  const [YM, setYM] = useState(props.calendarYM.format('YYYYMMDD'));
  const [data, setData] = useState();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const fetchData = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url = '/thismuch/stats/'+YM;
      const response = await axios.get(url, {withCredentials:true});
      setData(response.data);
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }
  useEffect(()=>{
    fetchData();
  },[YM])
  
  if(YM !== props.calendarYM.format('YYYYMMDD')){
    setYM(props.calendarYM.format('YYYYMMDD'));
  }
  
  return (
    <div className={styles.container}>
      <CalendarHeader 
        calendarYM={props.calendarYM.format("YYYY년 MM월")}
        moveMonth={props.moveMonth}
        calendarPage={false}
        dataMoneyTable={props.dataMoneyTable}
        errorMoneyTable={props.errorMoneyTable}
        loadingMoneyTable={props.loadingMoneyTable}/>
      {loading?<Loader type="spin" message='loding...' />:
      error?<div className={styles.container}>에러</div>:
      data?
        <Container>
        <Row classNmae={styles.row}>
          <Col className="item" md="6">
            <div className={styles.itemContainer}>
              <FixedExpenditure 
                fix={data.fixed_spending_list}/>
            </div>
          </Col>
          <Col className="item" md="6">
            <div className={styles.itemContainer}>   
              <DoughnutChart 
                categoryList={data.category_list}/>
            </div>
          </Col>
        </Row>
        <Row>
          <Col className="item" md="12">
            <div className={styles.itemContainer}>  
              <LineChart 
                category='thisMuch' 
                monthList={data.month_list}/>
            </div>
          </Col>
        </Row>
      </Container>
      :null
      }
      
    </div>
  );
});

export default ThisMuchPage;