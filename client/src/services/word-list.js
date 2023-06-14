import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/word-lists";

const WordListService = {
  list(page, size, searchText, difficultyId, sortBy) {
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
    if (difficultyId && difficultyId !== "") {
      params.difficultyId = difficultyId;
    }
    if (sortBy && sortBy !== "") {
      params.sortBy = sortBy;
    }
    return axios.get(API_URL, { params: params });
  },

  get(wordListId) {
    return axios.get(`${API_URL}/${wordListId}`);
  },

  create(name, difficultyId, wordIdList) {
    return axios.post(
      `${API_URL}`,
      {
        name: name,
        difficultyId: difficultyId,
        wordIdList: wordIdList,
      },
      { headers: authHeader() },
    );
  },
};

export default WordListService;
