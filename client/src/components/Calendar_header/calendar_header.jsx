import React, { memo } from 'react';
import MoneyTable from '../moneyTable/moneyTable';
import styles from './calendar_header.module.css'

const CalendarHeader = memo((props) => {
    return(
        <div className={styles.container}>
            <section className={styles.left}>
                <nav className={styles.date_nav}>
                    <button onClick={()=>{props.moveMonth(-1)}}>◀</button>
                    <p className={styles.day}>{props.calendarYM}</p>
                    <button onClick={()=>{props.moveMonth(1)}}>▶</button>
                </nav>
                {props.calendarPage?
                <nav className={styles.nav}>
                    <ul className={styles.nav_list}>
                        <li onClick={()=>{props.calendarButtonSetting('calendar')}} 
                            className={`${styles.nav_item} ${props.calendarContent == 'calendar'?styles.active:null}`}>달력
                            <div className={`${styles.nav_item_underbar} ${props.calendarContent == 'calendar'?styles.active_bar:null}`}></div>
                        </li>
                        <li onClick={()=>{props.calendarButtonSetting('purchase')}} 
                            className={`${styles.nav_item} ${props.calendarContent == 'purchase'?styles.active:null}`}>지출
                            <div className={`${styles.nav_item_underbar} ${props.calendarContent == 'purchase'?styles.active_bar:null}`}></div>
                        </li>
                        <li onClick={()=>{props.calendarButtonSetting('expense')}}
                            className={`${styles.nav_item} ${props.calendarContent == 'expense'?styles.active:null}`}>수입
                            <div className={`${styles.nav_item_underbar} ${props.calendarContent == 'expense'?styles.active_bar:null}`}></div>
                        </li>
                    </ul>
                </nav>:null
                }
            </section>
            <section className={styles.right}>
                <MoneyTable
                dataMoneyTable={props.dataMoneyTable}
                errorMoneyTable={props.errorMoneyTable}
                loadingMoneyTable={props.loadingMoneyTable}/>
            </section>
        </div>
    );
});

export default CalendarHeader;