import React from 'react';
import { memo } from 'react';
import styles from './fixedExpenditure.module.css';
const FixedExpenditure = memo((props) => {
    const fixKeys = Object.keys(props.fix);

    return (
        <div className={styles.container}>
            <h3 className={styles.title}>고정지출</h3>
            <div className={styles.expenseContainer}>
            {fixKeys.map(key =>(
                <div className={styles.expenseItem}>
                <p>{key}</p>
                <p>{props.fix[key]}</p>
                </div>
            ))}
            </div>
        </div>
    );
});

export default FixedExpenditure;