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

    <div v-if="loggedIn" class="userInformation">
      <h3>{{ this.$store.state.user.emailAddress }}</h3>
      <button class="logOutBtn" @click="logOut()">Log out</button>
    </div>
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
.container {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
  height: 10px;
  background: rgb(2, 0, 36);
  background: linear-gradient(
    90deg,
    rgba(2, 0, 36, 1) 0%,
    rgba(9, 9, 121, 1) 0%,
    rgba(0, 212, 255, 1) 100%
  );
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

.userInformation {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
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
