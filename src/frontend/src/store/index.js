import { createStore } from "vuex";
import axios from "axios";

export default createStore({
  state: {
    user: null,
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
  },
  mutations: {
    SET_USER_DATA(state, user) {
      state.user = user.data;
      localStorage.setItem("user", JSON.stringify(user));
      axios.defaults.headers.common["authorization"] = "Bearer ${user.token}";
    },
    CLEAR_USER_DATA() {
      localStorage.removeItem("user");
      location.reload();
    },
  },
  actions: {
    login({ commit }, credentials) {
      return axios
        .post("http://localhost:8001/user/login", null, {
          params: {
            email: credentials.email,
            password: credentials.password,
          },
        })
        .then(({ response }) => {
          commit("SET_USER_DATA", response);
        });
    },
    register({ commit }, credentials) {
      return axios
        .post("http://localhost:8001/user/register", credentials)
        .then((response) => {
          commit("SET_USER_DATA", response);
        });
    },
    logout({ commit }) {
      commit("CLEAR_USER_DATA");
    },
  },

  modules: {},
});
