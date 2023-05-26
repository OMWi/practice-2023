import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/words";

const WordService = {
  list(page, size) {
    let params = {};
    if (page) {
      params.page = page - 1;
    }
    if (size) {
      params.size = size;
    }
    return axios.get(API_URL, { params: params });
  },

  get(wordId) {
    return axios.get(`${API_URL}/${wordId}`);
  },
};

export default WordService;
