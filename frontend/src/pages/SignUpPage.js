import React, { useState } from 'react';
import axios from 'axios';
import './SignupPage.css'; // ✅ css 파일 import

const SignUpPage = () => {
  const [form, setForm] = useState({
    memberId: '',
    email: '',
    phone: '',
    nickname: '',
    password: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/signup', form);
      alert('회원가입 성공!');
    } catch (err) {
      console.error(err);
      alert('회원가입 실패');
    }
  };

  return (
    <div className="signup-container">
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <input
          className="signup-input"
          name="memberId"
          type="text"
          placeholder="아이디"
          value={form.memberId}
          onChange={handleChange}
          required
        />
        <input
          className="signup-input"
          name="email"
          type="email"
          placeholder="이메일"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          className="signup-input"
          name="phone"
          type="tel"
          placeholder="전화번호"
          value={form.phone}
          onChange={handleChange}
          required
        />
        <input
          className="signup-input"
          name="nickname"
          type="text"
          placeholder="닉네임"
          value={form.nickname}
          onChange={handleChange}
          required
        />
        <input
          className="signup-input"
          name="password"
          type="password"
          placeholder="비밀번호"
          value={form.password}
          onChange={handleChange}
          required
        />
        <button type="submit" className="signup-button">회원가입</button>
      </form>
    </div>
  );
};

export default SignUpPage;
