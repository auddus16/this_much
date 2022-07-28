import React, { memo } from 'react';
import styles from './moneyTable.module.css';
import Loader from '../Loader'

const MoneyTable = memo((props) => {  
    if(props.loadingMoneyTable) return <Loader type="spin" message='loding...' />;
    if(props.errorMoneyTable) return <div className={styles.container}>에러</div>
    if(!props.dataMoneyTable) return null;
    return (
        <div className={styles.moneyTable}>
                <p className={styles.expense}>지출 : {props.dataMoneyTable.total_spending?props.dataMoneyTable.total_spending:0}원</p>
                <p className={styles.purchase}>수입 : {props.dataMoneyTable.total_income?props.dataMoneyTable.total_income:0}원</p>
        </div>
    );
});

export default MoneyTable;