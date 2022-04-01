<template>
  <h3>All users</h3>

  <router-link to="/register">Register user</router-link>
  <div v-for="user in users" :key="user" class="user">
    <p>{{ user.emailAddress }}</p>
    <p>{{ user.lastname }}</p>
    <p>{{ user.firstname }}</p>

    <button @click="deleteUser(user)">Delete</button>
  </div>
</template>
<script>
import { authComputed } from "@/store/helpers";
import axios from "axios";
export default {
  name: "HomeView",
  methods: {
    deleteUser(user) {
      axios
        .delete("http://localhost:8001/user/delete", {
          params: {
            email: user.email,
          },
        })
        .then((response) => {
          this.users = response.data;
        })
        .catch((err) => {
          alert(err.message);
        });
    },
  },
  components: {},
  created() {
    document.title = "QS99 - Students";
    //getting subjects of the user
    axios.get("http://localhost:8001/user/getAllUsers").then((response) => {
      this.users = response.data;
    });
  },
  data() {
    return {
      users: null,
    };
  },
  computed: {
    ...authComputed,
  },
};
</script>
<style>
.cardHolder {
  margin-top: 40px;
  width: 100%;
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
  gap: 30px;
}
</style>
