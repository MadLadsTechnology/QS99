<template>
  <h1>Queue</h1>

  <button @click="this.$router.push('JoinQueue')">Join queue</button>

  <div v-for="(entry, index) in queue" :key="entry.lastname" class="entry">
    <label>{{ index + 1 }}</label>

    {{ entry.lastname }}, {{ entry.firstname }}

    | Øving {{ entry.assignment }}
  </div>
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
