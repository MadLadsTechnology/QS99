<template>
  <NavigationMenu/>
  <router-view/>
</template>
<script>
import NavigationMenu from "@/components/NavigationMenu";
import axios from "axios";

export default {
  created() {
    axios.defaults.baseURL = "http://localhost:8001";
    const userString = localStorage.getItem("user");
    if (userString) {
      const userData = JSON.parse(userString);
      this.$store.commit("SET_USER_DATA", userData);
    }
    document.title = "QS99";
  },
  components: {NavigationMenu},
  computed: {},
};
</script>

<style>
body {
  margin: 0px;
  padding: 0px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
