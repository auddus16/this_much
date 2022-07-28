import React, { memo } from 'react';
import './table.module.css';

const Table = memo((props)=>{
  return (
    <table>
      <thead>
        <tr>
        <th key='no'>No</th>
        <th key='date'>날짜</th>
        <th key='asset'>자산</th>
        <th key='classification'>분류</th>
        <th key='usage'>사용 내역</th>
        <th key='amount'>금액</th>
        </tr>
      </thead>
      <tbody>
      {props.data? props.data.map((d, idx)=>(
        <tr>
          <td>{idx+1}</td>
          <td>{d.tranTime}</td>
          <td>{d.accountNo.bankName}</td>
          <td>{d.categoryNo.categoryName}</td>
          <td>{d.content}</td>
          <td>{d.cost}</td>
        </tr>
      )):null}
      </tbody>
    </table>
  );
});
export default Table;