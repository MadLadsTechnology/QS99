<template>
  <div class="container">
    <router-link to="/">
      <img alt="Logo" src="../assets/logo.png"
      />
    </router-link>


    <div v-if="loggedIn" class="nav">

      <router-link v-if="this.$store.getters.isAdmin" :to="{ name: 'users' }">
        <h3>Users</h3>

      </router-link>

      <router-link v-if="this.$store.getters.isAdmin" :to="{ name: 'allSubjects' }">
        <h3>Subjects</h3>
      </router-link>

      <router-link v-else to="/subjects">
        <h3>Subjects</h3></router-link
      >

      <router-link class="profileLink" to="/Profile">
        <h3 class="role">Profile</h3>
      </router-link>


      <button v-if="loggedIn" class="logOutBtn" @click="logOut()">Log out</button>

    </div>
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
  height: 100%;
  color: black;
}

.router-link-active h3 {
  color: black;
}

router-link:hover {
  background-color: green;
}

h3 {
  width: 100px;
}

.nav {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  width: 100%;
  padding: 10px;
}

.container {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 30px;
  height: 70px;
  background-color: #2c3e50;
  color: white;
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
  width: 60px;
  margin: 10px;
}

.logOutBtn {
  text-align: center;
  text-decoration: none;
}

.logOutBtn:hover {
  cursor: pointer;
}
</style>
