<template>
  <LoginView v-if="isLoggedIn" />
  <HomeView v-else />
</template>
<script>
import { mapState } from "vuex";
import LoginView from "@/views/LoginView";
import HomeView from "@/views/HomeView";

export default {
  components: {
    LoginView,
    HomeView,
  },
  computed: {
    ...mapState({
      isLoggedIn: "isLoggedIn",
    }),
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
