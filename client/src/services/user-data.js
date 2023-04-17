import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/users-data";

const UserDataService = {
  get(userId) {
    return axios.get(`${API_URL}/${userId}`, { headers: authHeader() });
  },

  getUserWords(userId) {
    return axios.get(`${API_URL}/${userId}/words`, { headers: authHeader() });
  },

  getUserWordLists(userId) {
    return axios.get(`${API_URL}/${userId}/word-lists`, {
      headers: authHeader(),
    });
  },
};

export default UserDataService;
