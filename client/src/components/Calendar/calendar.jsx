// import React from 'react';
import { memo } from 'react';
import CalendarDate from './calendar_date';
import CalendarMonth from './calendar_month';
import styles from './calendar.module.css'

const Calendar = memo((props) => {
  return(
    <div className={styles.calendarContainer}>
    	<CalendarDate/>
    	<CalendarMonth calendarYM={props.calendarYM}
      tran={props.tran}/>
    </div>
  );
});

export default Calendar;