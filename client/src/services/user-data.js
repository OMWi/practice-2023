import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/users-data";

const UserDataService = {
  get(userId) {
    return axios.get(`${API_URL}/${userId}`, { headers: authHeader() });
  },

  getTop(limit) {
    return axios.get(`${API_URL}/top/${limit}`, { headers: authHeader() });
  },

  getUserWords(userId) {
    return axios.get(`${API_URL}/${userId}/words`, { headers: authHeader() });
  },

  getUserWord(userId, wordId) {
    return axios.get(`${API_URL}/${userId}/words/${wordId}`, {
      headers: authHeader(),
    });
  },

  getRandomUnlearnedUserWord(userId) {
    return axios.get(`${API_URL}/${userId}/words/random`, {
      headers: authHeader(),
    });
  },

  addUserWord(userId, wordId) {
    return axios.post(
      `${API_URL}/${userId}/words/${wordId}`,
      {},
      {
        headers: authHeader(),
      },
    );
  },

  incUserWordGuessStreak(userId, wordId) {
    console.log(userId, wordId);
    return axios.put(
      `${API_URL}/${userId}/words/inc`,
      { wordId: wordId },
      { headers: authHeader() },
    );
  },

  setZeroGuessStreak(userId, wordId) {
    return axios.put(
      `${API_URL}/${userId}/words`,
      {
        wordId: wordId,
        isLearned: false,
        guessStreak: 0,
      },
      { headers: authHeader() },
    );
  },

  deleteUserWord(userId, wordId) {
    return axios.delete(`${API_URL}/${userId}/words/${wordId}`, {
      headers: authHeader(),
    });
  },

  getUserWordLists(userId) {
    return axios.get(`${API_URL}/${userId}/word-lists`, {
      headers: authHeader(),
    });
  },

  getUserWordList(userId, wordListId) {
    return axios.get(`${API_URL}/${userId}/word-lists/${wordListId}`, {
      headers: authHeader(),
    });
  },

  addUserWordList(userId, wordListId) {
    return axios.post(
      `${API_URL}/${userId}/word-lists/${wordListId}`,
      {},
      { headers: authHeader() },
    );
  },

  deleteUserWordList(userId, wordListId) {
    return axios.delete(`${API_URL}/${userId}/word-lists/${wordListId}`, {
      headers: authHeader(),
    });
  },
};

export default UserDataService;
