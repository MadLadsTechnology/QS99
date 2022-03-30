<template>
  <h1>Queue</h1>

  <div v-for="(entry, index) in queue" :key="entry.lastname" class="entry">
    <label>{{ index + 1 }}</label>

    {{ entry.lastname }}, {{ entry.firstname }}

    | Øving {{ entry.assignment }}
  </div>
</template>
<script>
export default {
  props: ["event"],

  data() {
    return {
      queue: [
        {
          lastname: "navnesen",
          firstname: "navn",
          assignment: 6,
        },
        {
          lastname: "navnesen",
          firstname: "navn",
          assignment: 6,
        },
        {
          lastname: "navnesen",
          firstname: "navn",
          assignment: 6,
        },
        {
          lastname: "navnesen",
          firstname: "navn",
          assignment: 6,
        },
      ],
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
