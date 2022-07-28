import React, { useState,useEffect, memo } from 'react';
import Header from '../components/Header/header';
import styles from './Mypage.module.css'
import axios from 'axios'
import Loader from '../components/Loader'

const MyPage = memo((props) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState("example@gmail.com");
  const [id, setId] = useState('');
  const [password, setPassword] = useState("");
  const [passwordConfirm, setConfirmPassword] = useState("");
  const [tel, setTel] = useState('010-0000-0000');
  const [accessToken, setAccessToken] = useState('');
  const [registDate, setRegistDate] = useState('');
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [passwordConfirmError, setConfirmPasswordError] = useState(false);
  const [telError, setTelError] = useState(false);
  
  const [data, setData] = useState();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const fetchData = async()=>{
    try{
      setError(null);
      setLoading(true);
      const url = '/api/moreInfo/basicInfo';
      const response = await axios.get(url, {withCredentials:true});
      setData(response.data);
      setEmail(response.data.email);
      setName(response.data.name);
      setId(response.data.userId);
      setTel(response.data.telNum);
      setAccessToken(response.data.accessToken);
      setRegistDate(response.data.registDate);
      } catch(e){
        setError(e);
        console.log(e);
      }
      setLoading(false);
    }
  useEffect(()=>{
    fetchData();
  },[])

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
  const onChangeConfirmPassword = ((e) => {
      if (password === e.target.value) setConfirmPasswordError(false);
      else setConfirmPasswordError(true);
      setConfirmPassword(e.target.value);
  });
  const onChangeTel = (e)=>{
      const telRegex = /^\\d{2,3}[-]\\d{3,4}[-]\\d{4}$/;
      if (!e.target.value || telRegex.test(e.target.value)) setTelError(false);
      else setTelError(true);
      setTel(e.target.value);
  };
  const validation = () => {
      if(!email) setEmailError(true);
      if(!password) setPasswordError(true);
      if(!passwordConfirm) setConfirmPasswordError(true);
      if(!tel) setTelError(true);
      if(password && passwordConfirm && email && tel) return true;
      else return false;
  }
  const onSave = (e) => {
      if(!validation()){
          alert('빈칸을 채워주세요');
      }
      else{}
      // call API
  }
  return (
      <div className={styles.container}>
      <Header title={'마이페이지'}/>
      {loading?<Loader type="spin" message='loding...' />:
      error?<div className={styles.container}>에러</div>:
      data?
      <><section className={styles.optContainer}>
          <h2 className={styles.title}>기본 설정</h2>
          <div className={styles.optBox}>
          
          <h3 className={styles.subTitle}>이름</h3>
          <div className={`${styles.output}`}>{name}</div>
          <h3 className={styles.subTitle}>이메일</h3>
          {emailError && <div class={styles.invalidInput}>이메일 형식이 맞지 않습니다.</div>}
          <input type="text" name=""email id="email" maxlength="50" value={email}
          placeholder="Email" className={styles.input} onChange={onChangeEmail}/>
          <h3 className={`${styles.subTitle}`}>ID</h3>
          <div className={`${styles.output}`}>{id}</div>
          
          <h3 className={`${styles.subTitle}`}>Password 확인/변경</h3>
          {passwordError && <div class={styles.invalidInput}>최소 8자 이상, 영어와 숫자, 특수문자를 포함해주세요. </div>}
          <input type="password" id='password' maxlength="20" value={password}
          placeholder="비밀번호" className={styles.input} onChange={onChangePassword}/>
          {passwordConfirmError && <div class={styles.invalidInput}>비밀번호가 일치하지 않습니다.</div>}
          <input type="Password" name="password" id="passwordConfirm" maxlength="20" value={passwordConfirm}
          placeholder="비밀번호 재확인" className={styles.input} onChange={onChangeConfirmPassword}/>
          
          <h3 className={`${styles.subTitle}`}>전화번호</h3>
          {telError && <div class={styles.invalidInput}>-를 포함하여 입력해주세요</div>}
          <input type="text" id="tel" maxlength="13" 
          value={tel} className={styles.input}
          onChange={onChangeTel}/>
          
          <div className={styles.btnBox}>
          <button className={`${styles.saveBtn} ${onSave? 'styles.btnAction' : 'styles.btnInaction'}`}
              onClick={onSave}>저장하기</button>                 
          </div>
          </div>
      </section>
      <section className={styles.optContainer}>
          <h2 className={styles.title}>토큰 설정</h2>
          <div className={styles.optBox}>
          <h3 className={styles.subTitle}>Access Token</h3>
          <div className={`${styles.output}`}>{accessToken}</div>
          <h3 className={`${styles.subTitle}`}>만료일</h3>
          <div className={`${styles.output}`}>{registDate}</div>                
          </div>
      </section></>:null}
      </div>
  );
});

export default MyPage;