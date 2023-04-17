import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/words";

class WordService {
  list() {
    return axios.get(API_URL);
  }

  get(wordId) {
    return axios.get(`${API_URL}/${wordId}`);
  }
}

export default new WordService();
