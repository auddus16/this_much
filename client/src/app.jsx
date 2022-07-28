import React, { useState, useEffect } from 'react';
import styles from './app.module.css';
import Menu from './components/menu/menu';
import CalendarPage from './Pages/Calendar_page';
import moment from 'moment'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ThisMuchPage from './Pages/ThisMuch_page';
import CategoryPage from './Pages/Category_page';
import MyPage from './Pages/MyPage';
import Signin from './components/signIn/signIn';
import 'bootstrap/dist/css/bootstrap.min.css';
import SignUp from './components/signUp/signUp';
import axios from 'axios'

function App(){
  const [dayState, setDayState] = useState({
    calendarYM : moment(),
    today : moment().format("YYYYMMDD")
  })
  const [dataMoneyTable, setDataMoneyTable] = useState();
  const [loadingMoneyTable, setLoadingMoneyTable] = useState(false);
  const [errorMoneyTable, setErrorMoneyTable] = useState();
  
  const moveMonth = (month) => {
    setDayState({
      calendarYM : dayState.calendarYM.add(month,'M'),
      today : dayState.today
    })
  }

  const fetchMoneyTable = async()=>{
    try{
      setErrorMoneyTable(null);
      setLoadingMoneyTable(true);
      const url = '/thismuch/'+ dayState.calendarYM.format("YYYYMMDD");
      const response = await axios.get(url, { withCredentials: true });
      setDataMoneyTable(response.data);
    } catch(e){
      setErrorMoneyTable(false);
      console.log(e);
    }
    setLoadingMoneyTable(false);
  }

  useEffect(()=>{
    fetchMoneyTable();
  },[dayState]);

    return (
      <BrowserRouter>
        <div className={styles.app}>
          <Menu/>
          <Routes>
            <Route path='/' 
              element={<ThisMuchPage 
              calendarYM={dayState.calendarYM}
              moveMonth={moveMonth}
              dataMoneyTable={dataMoneyTable}
              errorMoneyTable={errorMoneyTable}
              loadingMoneyTable={loadingMoneyTable}/>}/>
            <Route path='/calendar' 
              element={<CalendarPage 
              calendarYM={dayState.calendarYM}
              moveMonth={moveMonth}
              dataMoneyTable={dataMoneyTable}
              errorMoneyTable={errorMoneyTable}
              loadingMoneyTable={loadingMoneyTable}/>}>
              <Route path=':id' element={<CalendarPage 
                calendarYM={dayState.calendarYM}
                moveMonth={moveMonth}/>}
                dataMoneyTable={dataMoneyTable}
                errorMoneyTable={errorMoneyTable}
                loadingMoneyTable={loadingMoneyTable}/>
            </Route>
            <Route path='/this_much' 
              element={<ThisMuchPage 
              calendarYM={dayState.calendarYM}
              moveMonth={moveMonth}
              dataMoneyTable={dataMoneyTable}
              errorMoneyTable={errorMoneyTable}
              loadingMoneyTable={loadingMoneyTable}/>}/>
            <Route path='/category' 
              element={<CategoryPage 
              dataMoneyTable={dataMoneyTable}
              errorMoneyTable={errorMoneyTable}
              loadingMoneyTable={loadingMoneyTable}/>}/>
            <Route path='/mypage' 
              element={<MyPage 
              dataMoneyTable={dataMoneyTable}
              errorMoneyTable={errorMoneyTable}
              loadingMoneyTable={loadingMoneyTable}/>}/>
            <Route path='/signIn' element={<Signin/>}/>
            <Route path='/signUp' element={<SignUp/>}/>
          </Routes>
        </div>
      </BrowserRouter>
    )
}

export default App;
