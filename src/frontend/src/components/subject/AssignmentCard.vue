<template>
  <div class="card">
    <h2>Assignment {{ assignment.exerciseNumber }}</h2>
    <h3 v-if="assignment.approved">Approved</h3>
    <h3 v-else>Not approved</h3>
  </div>
</template>

<script>
export default {
  props: {
    assignment: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.card {
  align-items: flex-start;
  background-color: #ddd1c7;
  color: #4b4a67;
  border: solid 1px;
  padding: 20px;
  width: 200px;
  border-radius: 10px;
  box-shadow: 5px 10px darkgray;
}
</style>
