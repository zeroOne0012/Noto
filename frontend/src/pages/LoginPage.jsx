import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/Auth';
import './LoginPage.css'; // 👈 아래에서 만들 LoginPage 전용 CSS

function LoginPage() {
  const [memberId, setMemberId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = await login({ memberId, password });
      if (token) {
        localStorage.setItem('token', token);
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
    <div className="login-wrapper">
      <div className="login-container">
        <h2>로그인</h2>
        <form onSubmit={onSubmit} className="login-form">
          <input
            type="text"
            placeholder="ID"
            value={memberId}
            onChange={(e) => setMemberId(e.target.value)}
            required
            className="login-input"
          />
          <input
            type="password"
            placeholder="PASSWORD"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="login-input"
          />
          <button type="submit" className="login-button">로그인</button>
          <button
            type="button"
            onClick={() => navigate('/signup')}
            className="signup-button"
          >
            회원가입
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
