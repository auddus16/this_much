import React, { memo } from 'react';
import moment from 'moment'
import styles from './calendar.module.css'

const CalendarMonth = memo((props)=> {
  const currYear = props.calendarYM.year();
  const currMonth = props.calendarYM.month();

  const lastDateOfThisMonth = moment([currYear, 0, 31])
    .month(currMonth)
    .date(); 
  const lastDateOfPreviousMonth = moment([currYear, 0, 31])
    .month(currMonth-1)
    .date();
  const lastDayOfThisMonth = moment([currYear, currMonth, lastDateOfThisMonth]).day();
  const lastDayOfPreviousMonth = moment([
    currYear,
    currMonth-1,
    lastDateOfPreviousMonth,
  ]).day();
  
  const previousDates = [];
  const thisDates = [...Array(lastDateOfThisMonth + 1).keys()].slice(1);
  const nextDates = [];

  if (lastDayOfPreviousMonth !== 6) {
    for (let i = 0; i < lastDayOfPreviousMonth + 1; i++) { 
      previousDates.unshift(lastDateOfPreviousMonth - i);
    }
  }
  
  for (let i = 1; i < 7 - lastDayOfThisMonth; i++) {
    nextDates.push(i);
  }
  const dates = previousDates.concat(thisDates, nextDates);
  const weeks = [];
  
  for (let i = 0; i <= 35; i += 7) {
    weeks.push([...dates].slice(i, i + 7));
  }

  return weeks.map((week, index) => (
    <div className={styles.week}
      key={week}
    >
      {
        week.map((date, idx) => (
          <div className={styles.dates}>
            {(index==0&&date>7)||(index==weeks.length-1&&date<lastDateOfThisMonth-7)?
            <div className={`${styles.GrayColor}`} key={date}>
            {date}
            </div>
            :idx == 6?<div className={`${styles.SaturdayColor}`} key={date}>
            {date}
            </div>
            :idx == 0? 
            <div className={`${styles.SundayColor}`} key={date}>
            {date}
            </div>
            :<div className={`${styles.BasicColor}`} key={date}>
            {date}
            </div>}
            <div className={styles.moneyTable}>
            {props.tran? props.tran.map((d) =>(
              <>
                {d[0].slice(8,10) == date?
                (d[1] == 1?
                  <div className= {styles.expense}>- {d[2]}</div>
                  :<div className= {styles.purchase}>+ {d[2]}</div>
                  )
                : null
                }
              </>
            )):null}
            </div>
          </div>
        ))
      }
    </div>
  ));
})

export default CalendarMonth;