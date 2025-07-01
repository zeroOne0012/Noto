import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: '/api', // proxy 전제
  withCredentials: true
});

export default axiosInstance;