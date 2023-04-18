import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/auth";

const AuthService = {
  login(email, password) {
    return axios.post(`${API_URL}/login`, {
      email: email,
      password: password,
    });
  },

  register(username, email, password) {
    return axios.post(`${API_URL}/register`, {
      username: username,
      email: email,
      password: password,
    });
  },

  logout() {
    localStorage.removeItem("user");
  },

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  },
};

export default AuthService;
