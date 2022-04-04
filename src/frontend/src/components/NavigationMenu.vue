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


      <h3 v-if="loggedIn" class="logOutBtn" @click="logOut()">Log out</h3>

    </div>
  </div>

</template>
<script>
import {authComputed} from "@/store/helpers";

export default {
  methods: {
    async logOut() {
      if (confirm("Are you sure you want to log out?")) {
        await this.$store.dispatch("logout");
        await this.$router.push("/login");
      }

    },
  },
  computed: {
    ...authComputed,
  },
};
</script>

<style scoped src="@/styles/components/NavigationMenu.css"/>