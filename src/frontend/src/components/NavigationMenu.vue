<template>
  <div class="container">
    <router-link to="/">
      <img src="../assets/logo.png" alt="Logo"
    /></router-link>

    <div v-if="loggedIn">
      <router-link
        class="routerLink"
        v-if="this.$store.getters.isAdmin"
        to="/subjects"
      >
        <h3>Dashboard</h3></router-link
      >
      <router-link class="routerLink" v-else to="/subjects">
        <h3>Subjects</h3></router-link
      >
    </div>

    <router-link class="profileLink" to="/Profile">
      <div v-if="loggedIn" class="userInformation">
        <h3 class="role">{{ this.$store.state.user.role }}:</h3>
        <h3>{{ this.$store.state.user.emailAddress }}</h3>

      </div>
    </router-link>
    <button v-if="loggedIn" class="logOutBtn" @click="logOut()">Log out</button>

  </div>
</template>
<script>
import { authComputed } from "@/store/helpers";

export default {
  methods: {
    async logOut() {
      await this.$store.dispatch("logout");
      await this.$router.push("/login");
    },
  },
  computed: {
    ...authComputed,
  },
};
</script>

<style scoped>

.profileLink{
  margin-left: auto;
}
.role {
  font-style: italic;
  margin-right: 0;
}
.container {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
  height: 10px;
  background-color: #2c3e50;
  color: white;
  margin: 0;
  padding: 30px;
  text-decoration: none;
}
a {
  text-decoration: none;
  color: white;
}
.container img {
  width: 50px;
}
nav li.router-link-active {
  background-color: black;
}
.userInformation {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  margin-left: auto;
  height: 40px;
}

.logOutBtn {
  text-align: center;
  text-decoration: none;
}

.logOutBtn:hover {
  cursor: pointer;
}
</style>
