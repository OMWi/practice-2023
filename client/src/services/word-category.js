import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/word-categories";

const WordCategoryService = {
  list() {
    return axios.get(API_URL);
  },

  get(wordCategoryId) {
    return axios.get(`${API_URL}/${wordCategoryId}`);
  },
};

export default WordCategoryService;
