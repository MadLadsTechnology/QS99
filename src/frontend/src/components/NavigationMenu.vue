<template>
  <div class="container">
    <router-link to="/">
      <img alt="Logo" src="../assets/logo.png"
      />
    </router-link>


    <div v-if="loggedIn">

      <div v-if="this.$store.getters.isAdmin" class="adminPanel">
        <router-link :to="{ name: 'users' }">
          <h3>Users</h3>

        </router-link>

        <router-link :to="{ name: 'allSubjects' }">
          <h3>Subjects</h3>
        </router-link>
      </div>

      <router-link v-else to="/subjects">
        <h3>Subjects</h3></router-link
      >
    </div>

    <router-link class="profileLink" to="/Profile">
      <div v-if="loggedIn" class="userInformation">
        <h3 class="role">Settings</h3>
      </div>
    </router-link>
    <button v-if="loggedIn" class="logOutBtn" @click="logOut()">Log out</button>

  </div>
</template>
<script>
import {authComputed} from "@/store/helpers";

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

.router-link-active {
  background-color: white;
  color: black;
}

.pageSelection {
  display: flex;
  height: 70px;
}

.pageSelection h3 {
  width: 80px;
  padding: 5px

}

.container {
  display: flex;
  flex-direction: row;
  align-items: right;
  gap: 30px;
  height: 70px;
  background-color: #2c3e50;
  color: white;
  margin: 0;

  text-decoration: none;
}

h3 {
  color: white;
}

a {
  text-decoration: none;
  color: white;
}

.container img {
  width: 50px;
}

.logOutBtn {
  text-align: center;
  text-decoration: none;
  margin-left: 30px;
}

.logOutBtn:hover {
  cursor: pointer;
}
</style>
