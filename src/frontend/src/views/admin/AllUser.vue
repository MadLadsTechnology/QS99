<template>
  <h3>All users</h3>

  <router-link to="/register">
    <button>Register user</button>
  </router-link>

  <table>
    <thead>
    <th>Email</th>
    <th>Last name</th>
    <th>First name</th>
    <th>Role</th>
    <th>Actions</th>
    </thead>
    <tr v-for="user in users" :key="user">
      <td>{{ user.emailAddress }}</td>
      <td>{{ user.lastName }}</td>
      <td>{{ user.firstName }}</td>
      <td>{{ user.role }}</td>
      <td>
        <button
            v-if="user.emailAddress !== this.$store.state.user.emailAddress"
            @click="deleteUser(user)"
        >
          Delete
        </button>
      </td>
    </tr>
  </table>
</template>

<script>
import {authComputed} from "@/store/helpers";
import axios from "axios";

export default {
  name: "HomeView",
  methods: {
    deleteUser(user) {
      axios
          .delete("/user", {
            params: {
              email: user.emailAddress,
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
    axios.get("/user/getAllUsers").then((response) => {
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
<style scoped src="@/styles/views/admin/AllUsers.css"/>
