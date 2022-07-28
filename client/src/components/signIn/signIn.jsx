import axios from 'axios';
import React, { useState, memo } from "react";
import { Link, useNavigate } from 'react-router-dom';
import styles from './signIn.module.css';

const Login = memo(() => {
  const navigate = useNavigate();
  const sessionStorage = window.sessionStorage;
  const [id, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const onChangeUserId = (e) => {
    setUserId(e.target.value);
  };
  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };
  const loginClickHandler = () => {
    sessionStorage.setItem("loginId", id);
    const url = '/mw/login'
    const header = {"Content-type":"application/json"}
    const data= {
      passwd: password,
      userId: id,
    }
    axios.post(url, data, header,{ withCredentials: true })
    .then(function (response) {
      if(response.status == 200){
        localStorage.setItem("users", JSON.stringify(response.data));
        navigate("/");
          return response.config.data;
      } else {
        console.log('error');
      }
      }).catch(function (error) {
      console.log(error);
      });
    }; 

    return (
      <div className={styles.container}>
        <section id="login" className={styles.login__container}>
          <h1 className={styles.login_logo}>이만큼</h1>
          <div id="login__id" className={styles.login__idpw}>
            <label className={styles.subTitle} for='id'>아이디</label>
            <input type="text" name="id" id="id" maxLength="20" value={id}
            placeholder="ID" className={styles.input} onChange={onChangeUserId}/>
          </div>
          <div id="login__pw" className={styles.login__idpw}>
            <label className={styles.subTitle} for='password'>비밀번호</label>
            <input type="Password" name="password" id="password" maxLength="20" value={password}
            placeholder="비밀번호" className={styles.input} onChange={onChangePassword}/>
          </div>
          
          <div className={styles.option}>
          <div className={styles.login__remember}>
            <input type="checkbox" name="" id=""/> 
            Remember Me?
          </div>
          <Link to="/signUp"><a href="#" className={styles.membership}>회원가입하기</a></Link>
          </div>
          <div onClick={loginClickHandler} className={styles.login__submit}>
            <button>로그인하기</button>
          </div>   
        </section>
      </div>
    )
});

export default Login;