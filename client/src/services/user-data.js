import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/users-data";

const UserDataService = {
  // user data methods
  getUserData(userId) {
    return axios.get(`${API_URL}/${userId}`, { headers: authHeader() });
  },

  getLeaderboard() {
    return axios.get(`${API_URL}/leaderboard`, { headers: authHeader() });
  },

  // user words methods
  addUserWord(userId, wordId) {
    return axios.post(
      `${API_URL}/${userId}/words/${wordId}`,
      {},
      {
        headers: authHeader(),
      },
    );
  },

  listUserWords(userId) {
    return axios.get(`${API_URL}/${userId}/words`, { headers: authHeader() });
  },

  getUserWord(userId, wordId) {
    return axios.get(`${API_URL}/${userId}/words/${wordId}`, {
      headers: authHeader(),
    });
  },

  deleteUserWord(userId, wordId) {
    return axios.delete(`${API_URL}/${userId}/words/${wordId}`, {
      headers: authHeader(),
    });
  },

  getQuestionWord(userId) {
    return axios.get(`${API_URL}/${userId}/words/learn`, {
      headers: authHeader(),
    });
  },

  handleQuestionAnswer(userId, wordId, isCorrect) {
    return axios.post(
      `${API_URL}/${userId}/words/learn`,
      {
        wordId: wordId,
        isCorrect: isCorrect,
      },
      { headers: authHeader() },
    );
  },

  // user word lists methods
  addUserWordList(userId, wordListId, isFavorite) {
    return axios.post(
      `${API_URL}/${userId}/word-lists`,
      {
        wordListId: wordListId,
        isFavorite: isFavorite,
      },
      { headers: authHeader() },
    );
  },

  listUserWordLists(userId) {
    return axios.get(`${API_URL}/${userId}/word-lists`, {
      headers: authHeader(),
    });
  },

  getUserWordList(userId, wordListId) {
    return axios.get(`${API_URL}/${userId}/word-lists/${wordListId}`, {
      headers: authHeader(),
    });
  },

  updateUserWordList(userId, wordListId, isFavorite) {
    return axios.put(
      `${API_URL}/${userId}/word-lists`,
      {
        wordListId: wordListId,
        isFavorite: isFavorite,
      },
      {
        headers: authHeader(),
      },
    );
  },

  deleteUserWordList(userId, wordListId) {
    return axios.delete(`${API_URL}/${userId}/word-lists/${wordListId}`, {
      headers: authHeader(),
    });
  },
};

export default UserDataService;
