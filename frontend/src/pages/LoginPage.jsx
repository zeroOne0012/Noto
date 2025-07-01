import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/Auth';

function LoginPage() {
  const [memberId, setMemberId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = await login({ memberId, password }); // token = 문자열
      if (token) {
        localStorage.setItem('token', token); // 브라우저의 localStorage에 로그인 토큰(JWT 등) 저장
        alert('로그인 성공!');
        navigate('/home');
      } else {
        alert('로그인 실패');
      }
    } catch (err) {
      alert('로그인 중 오류 발생');
    }
  };

  return (
    <div style={styles.container}>
      <h2>로그인</h2>
      <form onSubmit={onSubmit} style={styles.form}>
        <input
          type="text"
          placeholder="ID"
          value={memberId}
          onChange={(e) => setMemberId(e.target.value)}
          required // 반드시  입력
          style={styles.input}
        />
        <input
          type="password"
          placeholder="PASSWORD"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required // 반드시  입력
          style={styles.input}
        />
        <button type="submit" style={styles.button}>로그인</button>
        <button
          type="button"
          onClick={() => navigate('/signup')}
          style={styles.subButton} // 스타일은 따로 지정
        >회원가입</button>
      </form>
    </div>
  );
}

const styles = {
  container: { maxWidth: '400px', margin: '100px auto', textAlign: 'center' },
  form: { display: 'flex', flexDirection: 'column', gap: '10px' },
  input: { padding: '10px', fontSize: '16px' },
  button: { padding: '10px', fontSize: '16px', backgroundColor: '#4CAF50', color: 'white' },
  subButton: {
    marginTop: '1rem',
    padding: '0.75rem 1.5rem',
    backgroundColor: '#ffffff',
    color: '#333333',
    border: '2px solid #888888',
    borderRadius: '8px',
    fontSize: '1rem',
    fontWeight: '500',
    cursor: 'pointer',
    transition: 'all 0.2s ease-in-out',}
};

export default LoginPage;