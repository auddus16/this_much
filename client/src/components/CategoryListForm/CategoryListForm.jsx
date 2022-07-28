import React, { useEffect, useState, memo } from 'react';
import styles from './CategoryListForm.module.css';
import axios from 'axios';
import Loader from '../Loader'
const CategoryListForm = memo((props) => {
  const [categoryList, setCategoryList] = useState([]);
  const [category, setCategory] = useState(props.category);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const categorys = props.categorys;

  const fetchCategoryList = async()=>{
    try{
      setError(null);
      setLoading(true);
      if(categorys.includes(category)){
        const url = '/thismuch/cate/'+ category;
        const response = await axios.get(url, {withCredentials:true});
        const set = new Set(response.data.map(n=>n.content));
        setCategoryList([...set]);
      }
      else{setCategoryList([])}
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }

  if(category !== props.category){
    setCategory(props.category);
  }

  useEffect(()=>{
    fetchCategoryList();
  }, [category]);

  return (
    <div className={styles.container}>
      {loading?<Loader type="spin" message='loding...' />:
      error?<div className={styles.container}>에러</div>:
      categoryList?
      <><h3 className={styles.title}>{props.category} 카테고리</h3>
      <div className={styles.listContainer}>
      {categoryList.map(c=>(
      <div className={styles.list}>
        <p className={styles.content}>{c}</p>
        <div className={styles.opt}>
          <select id='category' className={styles.select}
          defaultValue={category} key={category}
          // onChange={e =>setForm({ ...form, year: e.target.value })}
          >
            {props.categorys.map(ctg => (
              <option value={ctg} key={ctg}>
                  {ctg}
              </option>
            ))}
          </select>
        </div>
      </div>
      ))}
      </div></>:null}
    </div>
  );
});

export default CategoryListForm;