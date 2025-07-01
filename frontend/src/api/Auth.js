import axiosInstance from './axiosInstance';

export async function login({ memberId, password }) {
  const response = await axiosInstance.post('/login', {
    member_id: memberId,
    password
  });
  return response.data;
}
