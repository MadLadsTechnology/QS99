import { createStore } from "vuex";
import axios from "axios";

const store = createStore({
  state: {
    user: null,
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
    isProfessor(state) {
      return state.user.role === "Professor";
    },
    isAdmin(state) {
      return state.user.role === "Admin";
    },
  },
  mutations: {
    SET_USER_DATA(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
      axios.defaults.headers.common["authorization"] = "Bearer " + user.token;
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
        .then((response) => {
          commit("SET_USER_DATA", response.data);
        });
    },

    logout({ commit }) {
      commit("CLEAR_USER_DATA");
    },
  },

  modules: {},
});

export default store;
