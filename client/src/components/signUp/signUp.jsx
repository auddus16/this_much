import React, { useState, memo } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios'
import Popup from './popup';
import styles from './signUp.module.css';

const SignUp = memo(() => {
  const navigate = useNavigate();
  const [popup, setPopup] = useState({open: false, title: "", message: "", callback: false});

  const [id, setUserId] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setConfirmPassword] = useState("");
  const [name, setUserName] = useState("");
  const [tel, setTel] = useState("");

  const [idError, setUserIdError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [passwordConfirmError, setConfirmPasswordError] = useState(false);
  const [nameError, setUserNameError] = useState(false);
  const [telError, setTelError] = useState(false);
  const [tokenClick, setTokenClick] = useState(false);

  const onChangeUserId = (e) => {
      const userIdRegex = /^[A-Za-z0-9+]{5,}$/;
      if ((!e.target.value || (userIdRegex.test(e.target.value)))) setUserIdError(false);
      else setUserIdError(true);
      setUserId(e.target.value);
  };

  const onChangeEmail = (e) => {
      const emailRegex = /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
      if (!e.target.value || emailRegex.test(e.target.value)) setEmailError(false);
      else setEmailError(true);
      setEmail(e.target.value);
  };
  
  const onChangePassword = (e) => {
      const passwordRegex =/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
      if ((!e.target.value || (passwordRegex.test(e.target.value)))) setPasswordError(false);
      else setPasswordError(true);

      if (!passwordConfirm || e.target.value === passwordConfirm) setConfirmPasswordError(false);
      else setConfirmPasswordError(true);
      setPassword(e.target.value);
  };
  const onChangeConfirmPassword = (e) => {
      if (password === e.target.value) setConfirmPasswordError(false);
      else setConfirmPasswordError(true);
      setConfirmPassword(e.target.value);
  };
  const onChangeUserName = (e) => {
      setUserNameError(false);
      setUserName(e.target.value)
  };

  const onChangeTel = (e)=>{
    const telRegex =  /^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$/;
    // const telRegex = /^\\d{2,3}[-]\\d{3,4}[-]\\d{4}$/;
    if (!e.target.value || telRegex.test(e.target.value)) setTelError(false);
    else setTelError(true);
    setTel(e.target.value);
    };

  const validation = () => {
    if(!id) setUserIdError(true);
    if(!email) setEmailError(true);
    if(!password) setPasswordError(true);
    if(!passwordConfirm) setConfirmPasswordError(true);
    if(!name) setUserNameError(true);
    if(!tel) setTelError(true);

    if(id && password && passwordConfirm && name && email && tel) return true;
    else return false;
  }

  const onSubmit = (e) => {
    if(!validation()){
      setPopup({
        open: true,
        title: "Error",
        message: "빈칸을 채워주세요"
    });
    }
    else{
      setTokenClick(false);
      const url = '/mw/signUp'
      const header = {"Content-type":"application/json"}
      const data= {
        email: email,
        name: name,
        passwd: password,
        telNum: tel,
        userId: id,
        accessToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk0Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NTk1MjYzOTIsImp0aSI6IjQ0MTU5ZjhjLWM3MTctNDllNi04MmI3LTZjZTE4NTQxMWI4YSJ9.wJ15ZF9iLt0ovUPa6zj7yAZXTvhAF_FxFrsnpwTvEtM",
        expiration: 20990714,
        refreshToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk0Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NjAzOTAzOTIsImp0aSI6IjBiZDQ0MTNkLTBmODUtNDVjOC05ZDhiLTQ2ZTgwNjNjOWYyZiJ9.9WF3BHxJNm8ns77xYvE1THyHTQ0jJsrYQWZXzaviZs0",
        registerToken: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk0Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NjAzOTAzOTIsImp0aSI6IjBiZDQ0MTNkLTBmODUtNDVjOC05ZDhiLTQ2ZTgwNjNjOWYyZiJ9.9WF3BHxJNm8ns77xYvE1THyHTQ0jJsrYQWZXzaviZs0",
        userSerialNumber: 1234132,
        }
      axios.post(url, data, header,{ withCredentials: true })
      .then(function (response) {
      if(response.status == 200){
        setPopup({
          open: true,
          title: "Confirm",
          message: "Join Success!", 
          callback: function(){
              navigate("/");
          }
        });
      } else {
        let message = response.data.message;
        if(response.status == 500){
            message = "User ID is duplicated. Please enter a different User ID. "
        }
        setPopup({
          open: true,
          title: "Error",
          message: message
        });
      }
      }).catch(function (error) {
      console.log(error);
      });
    }
  }

  const now = new Date();
  let years = [];
  let month = [];
  let days = [];
  
  const [form, setForm] = useState({
    year: now.getYear() + 1,
    month: "01",
    day: "01",
  });

  let date = new Date(form.year, form.month, 0).getDate();
  
  for (let y = now.getFullYear(); y >= 1930; y -= 1) {
    years.push(y);
  }
  
  for (let m = 1; m <= 12; m += 1) {
    if (m < 10) {
      month.push("0" + m.toString());
    } else {month.push(m.toString());}
  }
  
  for (let d = 1; d <= date; d += 1) {
    if (d < 10) {
      days.push("0" + d.toString());
    } else {days.push(d.toString());}
  }

  return (
    <div className={styles.container}>
      <Popup open = {popup.open} setPopup = {setPopup} message = {popup.message} title = {popup.title} callback = {popup.callback}/>
      <section id="signUp" className={styles.signUpContainer}>
        <h1 className={styles.title}>회원 가입</h1>
        <label className={styles.subTitle} for='name'>이름</label>
        <input type="text" name="name" id="name" maxLength="20" value={name}
        placeholder="name" className={styles.input} onChange={onChangeUserName}/>
        {nameError && <div class={styles.invalidInput}>필수사항입니다.</div>}
        
        <label className={styles.subTitle} for='email'>이메일</label>
        <input type="text" name=""email id="email" maxLength="50" value={email}
        placeholder="Email" className={styles.input} onChange={onChangeEmail}/>
        {emailError && <div class={styles.invalidInput}>이메일 형식이 맞지 않습니다.</div>}
        
        <label className={styles.subTitle} for='id'>아이디</label>
        <div className={styles.btnBox}>
            <input type="text" name="id" id="id" maxLength="20" value={id}
            placeholder="ID" className={styles.input} onChange={onChangeUserId}/>
            {idError && <div class={styles.invalidInput}>최소 5자 이상, 영어와 숫자만 포함해주세요.</div>}
            <button className={styles.btn}>중복 확인</button>
        </div>
        
        <div id="signUpPw" className={styles.passwordBox}>
            <label className={styles.subTitle} for='password'>비밀번호</label>
            <input type="Password" name="password" id="password" maxLength="20" value={password}
            placeholder="비밀번호" className={styles.input} onChange={onChangePassword}/>
            {passwordError && <div class={styles.invalidInput}>최소 8자 이상, 영어와 숫자, 특수문자를 포함해주세요. </div>}
            <input type="Password" name="password" id="passwordConfirm" maxLength="20" value={passwordConfirm}
            placeholder="비밀번호 재확인" className={styles.input} onChange={onChangeConfirmPassword}/>
            {passwordConfirmError && <div class={styles.invalidInput}>비밀번호가 일치하지 않습니다.</div>}
        </div>
        <label className={styles.subTitle} for='tel'>전화번호</label>
        <input type="text" name="tel" id="tel" maxLength="13"
        placeholder="-포함하여 입력해주세요" className={styles.input} onChange={onChangeTel}/>
        {telError && <div class={styles.invalidInput}>-를 포함하여 입력해주세요</div>}
        <div className={styles.btnBox}>
            {tokenClick?
              <div className={`${styles.token} ${styles.tokenSucess}`}>토큰발급완료</div>:
              <button className={`${styles.token}`}
              onClick={() => {setTokenClick(true);
                let oauthGrant = window.open('http://ec2-54-167-218-248.compute-1.amazonaws.com:8080/api/oauth/grant', '오픈뱅킹공동업무','width=430,height=500,location=no,status=no,scrollbars=yes')
              }}>토큰발급하기</button>}
        </div>
        
        <div className={styles.submitBtnBox}>
            <button className={`${styles.singUpSubmit} ${onSubmit? 'styles.btnAction' : 'styles.btnInaction'}`}
        onClick={onSubmit}>가입하기</button> 
        </div>
      </section>
    </div>
  );
});

export default SignUp;