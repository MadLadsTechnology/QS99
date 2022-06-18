<template>
  <NavigationMenu/>
  <router-view/>
</template>
<script>
import NavigationMenu from "@/components/NavigationMenu";
import axios from "axios";

export default {
  created() {
    let ip = "eivindharboe.no"
    axios.defaults.baseURL = "http://"+ip+":8001";
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
  margin: 0;
  padding: 0;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}


button {
  -webkit-appearance: none;
  border-radius: 5px;
  padding: 10px;
  border: solid black 1px;
  color: black;
  margin: 0;
}
</style>
