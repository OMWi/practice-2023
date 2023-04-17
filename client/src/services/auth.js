import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/auth";

class AuthService {
  login(email, password) {
    return axios.post(`${API_URL}/login`, {
      email: email,
      password: password,
    });
  }

  async register(username, email, password) {
    const response = await axios.post(`${API_URL}/register`, {
      username: username,
      email: email,
      password: password,
    });
    return response.data;
  }

  logout() {
    localStorage.removeItem("user");
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
}

export default new AuthService();
