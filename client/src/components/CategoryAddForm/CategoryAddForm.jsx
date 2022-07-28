import React, { memo } from 'react';
import styles from './CategoryAddForm.module.css';
const CategoryAddForm = memo((props) => {
  const formRef = React.createRef();
  const inputRef = React.createRef();
  const onSubmit = event => {
    event.preventDefault();
    const name = inputRef.current.value;
    name && props.onAdd(name);
    formRef.current.reset();
  };
  return (
    <div className={styles.container}>
      <h3 className={styles.title}>카테고리 추가</h3>
      <form ref={formRef} onSubmit={onSubmit}>
      <input className={styles.categoryInput} ref={inputRef} type="text" />
      <button className={styles.categoryInputBtn}>등록</button></form>
      <div className={styles.categoryContainer}>
        {props.categorys.map(c=>(
        <div className={styles.categoryBtn} >
            <div onClick={()=>{props.categorySetting({c})}}>{c}</div>
            {c=='비분류'?null:<div onClick={()=>{props.onDel({c})}} className={styles.delete}>&times;</div>}
        </div>
        ))}
      </div>
    </div>
  );
});

export default CategoryAddForm;