import { createStore } from "vuex";

export default createStore({
  state: {
    isLoggedIn: false,
    user: {
      mail: "usertest@mail.no",
      lastName: "",
      firstName: "",
      role: "",
    },
    token: "",
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});
