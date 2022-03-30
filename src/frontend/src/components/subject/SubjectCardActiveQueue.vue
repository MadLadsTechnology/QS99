<template>
  <router-link :to="{ name: 'SubjectQueue', params: { id: subject.id } }">
    <div class="card">
      <h1>{{ subject.code }}</h1>
      {{ subject.name }}
      <br />
    </div>
  </router-link>
</template>

<script>
export default {
  props: {
    subject: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.card {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  background-color: #ddd1c7;
  color: #4b4a67;
  border: solid 1px;
  width: 200px;
  text-align: left;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 5px 10px darkgray;
}

.card h1 {
  margin: 0;
}
a {
  text-decoration: none;
}
</style>
