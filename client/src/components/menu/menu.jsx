import React, { useState, useEffect, memo } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import styles from './menu.module.css';

const Menu = memo(() => {
  const navigate = useNavigate();
  const location = useLocation();
  const [menuActive, setMenuActive] = useState('');
  const sessionStorage = window.sessionStorage;

  const handleLogOut = ()=>{
    sessionStorage.removeItem("loginId");
  }
  const handleActiveChange = (e)=>{
    setMenuActive(e);
  };

  useEffect(()=>{
    if (location.pathname == '/signUp'){
      navigate('/signUp');
    }
    else if(!JSON.stringify(sessionStorage.loginId)){
      navigate('/signIn');
    }
  },[menuActive])
  return(
    <div className={styles.container}>
      <div className={styles.logo} href="#">
        <img src="" alt="" />
        <h2>이만큼</h2>
      </div>
      <ul className={styles.list}>
        <Link className={styles.menu_a} to="/this_much" 
          onClick={()=>handleActiveChange('thisMuch')}>
          <li className={`${styles.list_item} ${menuActive == 'thisMuch'?styles.active:null}`}>
          이만큼</li></Link>
        <Link className={styles.menu_a} to="/calendar" 
          onClick={()=>handleActiveChange('calendar')}>
          <li className={`${styles.list_item} ${menuActive == 'calendar'?styles.active:null}`}>
            달력</li></Link>
        <Link className={styles.menu_a} to="/category" 
          onClick={()=>handleActiveChange('category')}>
          <li className={`${styles.list_item} ${menuActive == 'category'?styles.active:null}`}>
            카테고리 설정</li></Link>
        <Link className={styles.menu_a} to="/mypage" 
          onClick={()=>handleActiveChange('myPage')}>
          <li className={`${styles.list_item} ${menuActive == 'myPage'?styles.active:null}`}>
            마이페이지</li></Link>
      </ul>
      {JSON.stringify(sessionStorage.loginId)?
        <Link onClick={handleLogOut} to='/signIn'>
        <a className={`${styles.login_btn} ${styles.menu_a}`}>로그아웃</a></Link>
        :<Link to='/signIn'><a className={`${styles.login_btn} ${styles.menu_a}`}>로그인</a></Link>}
    </div>
  )
});

export default Menu;