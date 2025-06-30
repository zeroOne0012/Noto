import axios from 'axios';

export async function login({ memberId, password }) {
  const response = await axios.post('/api/login', {
    member_id: memberId,
    password
  });
  return response.data;
}
