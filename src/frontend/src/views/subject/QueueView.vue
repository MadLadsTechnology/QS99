<template>
  <h1>Queue</h1>

  <button @click="this.$router.push('JoinQueue')">Join queue</button>

  <table>
    <tr>
      <th>Place</th>
      <th>Last name</th>
      <th>First name</th>
      <th>Assignment</th>
      <th>Type</th>
      <th>Table</th>
      <th v-if="!this.$store.getters.isStudent">Actions</th>
    </tr>
    <tr v-for="(entry, index) in queue" :key="entry.lastname">
      <td>{{ index }}</td>
      <td>{{ entry.lastName }}</td>
      <td>{{ entry.firstName }}</td>
      <td>
        <text v-for="assignment in entry.exercises" v-bind:key="assignment">
          {{ assignment }},
        </text>
      </td>
      <td>{{ entry.type }}</td>
      <td>{{ entry.tableNumber }}</td>
    </tr>
  </table>
</template>
<script>
import axios from "axios";

export default {
  props: ["subject"],

  created() {
    axios
      .get("http://localhost:8001/queue", {
        params: {
          subjectId: this.subject.id,
        },
      })
      .then((response) => {
        console.log(response.data);
        this.queue = response.data;
      });
  },

  methods: {},

  data() {
    return {
      queue: null,
    };
  },
};
</script>

<style>
.entry {
  border: solid 1px;
  margin: auto;
  margin-top: 5px;
  padding: 10px;
  width: 70%;
}
</style>
