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
    isStudent(state) {
      return state.user.role === "Student";
    },
  },
  mutations: {
    SET_USER_DATA(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
      axios.defaults.headers.common["authorization"] = "Bearer " + user.token;
    },
    async CLEAR_USER_DATA(state) {
      state.user = null;
      await localStorage.removeItem("user");
      location.reload();
    },
  },
  actions: {
    login({ commit }, credentials) {
      return axios
        .post("/user/login", null, {
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
