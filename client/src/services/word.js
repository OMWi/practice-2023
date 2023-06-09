import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/words";

const WordService = {
  list(page, size, searchText, categoryId, difficultyId) {
    let params = {};
    if (page) {
      params.page = page - 1;
    }
    if (size) {
      params.size = size;
    }
    if (searchText && searchText !== "") {
      params.searchText = searchText;
    }
    if (categoryId && categoryId !== "") {
      params.categoryId = categoryId;
    }
    if (difficultyId && difficultyId !== "") {
      params.difficultyId = difficultyId;
    }
    return axios.get(API_URL, { params: params });
  },

  get(wordId) {
    return axios.get(`${API_URL}/${wordId}`);
  },
};

export default WordService;
