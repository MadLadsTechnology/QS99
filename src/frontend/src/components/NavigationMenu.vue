<template>
  <div class="container">
    <router-link to="/">Subjects</router-link>

    <button v-if="loggedIn" class="logOutBtn" @click="logOut()">Log out</button>
  </div>
</template>
<script>
import { authComputed } from "@/store/helpers";

export default {
  methods: {
    logOut() {
      this.$store.dispatch("logout");
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
  height: 100px;
  background-color: antiquewhite;
  padding: 30px;
}

.logOutBtn {
}
</style>
