<template>
  <h1>Assignments</h1>

  <div v-for="(entry, index) in assignments" :key="entry" class="entry">
    <label>{{ index + 1 }}</label>

    {{ entry }}
  </div>
</template>
<script>
import axios from "axios";

export default {
  props: ["subject"],

  created() {
    axios
      .get("http://localhost:8001/subject/getByUser", {
        params: {
          id: this.subject.id,
        },
      })
      .then((response) => {
        this.assignments = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },

  methods: {},

  data() {
    return {
      assignments: null,
    };
  },
};
</script>

<style>
.entry {
  border: solid 1px;
  margin: 5px auto auto;
  padding: 10px;
  width: 70%;
}
</style>
