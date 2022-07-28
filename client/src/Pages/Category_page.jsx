import React, { memo, useState, useEffect } from 'react';
import CategoryAddForm from '../components/CategoryAddForm/CategoryAddForm';
import CategoryListForm from '../components/CategoryListForm/CategoryListForm';
import Header from '../components/Header/header';
import Loader from '../components/Loader'
import axios from 'axios';
import styles from './Category_page.module.css';
const CategoryPage = memo((props) => {
  const [category, setCategory] = useState("비분류");
  const [categorys, setCategorys] = useState([]);
  const [data, setData] = useState();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const fetchCategory = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url1 = '/api/category/getAll';
      const response = await axios.get(url1, {withCredentials:true});
      setData(response.data);
      setCategorys(response.data.map(n=>n.categoryName));
    } catch(e){
      setError(e);
      console.log(e);
    }
    setLoading(false);
  }
  
  const categorySetting = content => {
    setCategory(content.c);
  };
  const CategoryAdd = newCategory =>{
    const c = [...categorys, newCategory];
    setCategorys(c);
  };
  const CategoryDelete = delCategory => {
    const c = categorys.filter(item => item !== delCategory.c);
    setCategorys(c);
    setCategory("비분류");
  };
  useEffect(()=>{
      fetchCategory();
  }, [category]);
  return (
    <div className={styles.container}>
      <Header title = '카테고리 설정'/>
      {loading?<Loader type="spin" message='loding...' />:
        error?<div className={styles.container}>에러</div>:
        data?
        <div className={styles.categoryContainer}> 
            <CategoryListForm category={category} categorys={categorys}/>
            <CategoryAddForm categorySetting={categorySetting} 
            categorys={categorys} onAdd={CategoryAdd} onDel={CategoryDelete}/>
        </div>: null}
    </div>
  );
});

export default CategoryPage;