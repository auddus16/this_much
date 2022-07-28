import React, { memo, useState,useEffect } from 'react';
import CalendarHeader from '../components/Calendar_header/calendar_header';
import Calendar from '../components/Calendar/calendar';
import Income from './Income';
import Spending from './Spending';
import styles from '../components/Calendar/calendar.module.css'
import Loader from '../components/Loader'
import axios from 'axios'
const CalendarPage = memo((props) => {
  const [calendarContent, setCalendarContent] = useState('calendar');
  const calendarButtonSetting = content => {
    setCalendarContent(content);
  }
  const [YM, setYM] = useState(props.calendarYM.format('YYYYMMDD'));
  const [dataTran, setDataTran] = useState();
  const [dataIncome, setDataIncome] = useState();
  const [dataSpending, setDataSpending] = useState();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const calendarComponent = {
    calendar: <Calendar calendarYM={props.calendarYM} tran={dataTran}/>,
    expense: <Income calendarYM={props.calendarYM} data={dataIncome}/>,
    purchase: <Spending calendarYM={props.calendarYM} data={dataSpending}/>
  };
  const fetchTran = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url = '/thismuch/tran/'+YM;
      const response = await axios.get(url, {withCredentials:true});
      setDataTran(response.data);
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }
  const fetchIncome = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url = '/thismuch/income/'+YM;
      const response = await axios.get(url, {withCredentials:true});
      setDataIncome(response.data);
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }
  const fetchSpending = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url = '/thismuch/spending/'+YM;
      const response = await axios.get(url, {withCredentials:true});
      setDataSpending(response.data);
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }
  useEffect(()=>{
    fetchTran();
    fetchIncome();
    fetchSpending();
  },[YM])
  
  if(YM !== props.calendarYM.format('YYYYMMDD')){
    setYM(props.calendarYM.format('YYYYMMDD'));
  }
  return(
    <div className={styles.pageContainer}>
      <CalendarHeader 
        calendarYM={props.calendarYM.format("YYYY년 MM월")}
        moveMonth={props.moveMonth}
        calendarButtonSetting={calendarButtonSetting}
        calendarComponent={calendarComponent}
        calendarPage={true}
        calendarContent={calendarContent}
        dataMoneyTable={props.dataMoneyTable}
        errorMoneyTable={props.errorMoneyTable}
        loadingMoneyTable={props.loadingMoneyTable}/>
      {loading? <Loader type="spin" message='loding...' />:
        error?<div className={styles.container}>에러</div>:
        dataTran? calendarContent && <div className={styles.contentContainer}>{calendarComponent[calendarContent]}</div>:null}
    </div>
  );
});

export default CalendarPage;