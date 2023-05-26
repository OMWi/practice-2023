import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/difficulties";

const DifficultyService = {
  list() {
    return axios.get(API_URL);
  },

  get(difficultyId) {
    return axios.get(`${API_URL}/${difficultyId}`);
  },
};

export default DifficultyService;
