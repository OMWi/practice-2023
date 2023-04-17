import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/word-lists";

class WordListService {
  list() {
    return axios.get(API_URL);
  }

  get(wordListId) {
    return axios.get(`${API_URL}/${wordListId}`);
  }
}

export default new WordListService();
