import React, { useState, memo } from 'react';
import styles from './calendar.module.css'

const CalendarDate = memo(() =>{
  const [days] = useState(['일', '월', '화', '수', '목', '금', '토']);

  return (
    <div className={styles.days}>
      {days.map((day) => (
        <div className={styles.day} key={day}>
          {day}
        </div>
      ))}
    </div>
  );
});

export default CalendarDate;