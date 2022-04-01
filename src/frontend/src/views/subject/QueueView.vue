<template>
  <h1>Queue</h1>

  <button>Join queue</button>

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
    axios.get("http://localhost:8001/queue", {
      params: {
        id: this.subject.id,
      },
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
