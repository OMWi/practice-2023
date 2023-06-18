import axios from "axios";
import authHeader from "./auth-header";

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

  updateCredentials(email, password) {
    return axios.put(
      `${API_URL}`,
      {
        id: AuthService.getCurrentUser().userId,
        email: email,
        password: password,
      },
      { headers: authHeader() },
    );
  },

  logout() {
    localStorage.removeItem("user");
  },

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  },
};

export default AuthService;
