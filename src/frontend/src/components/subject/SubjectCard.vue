<template>
  <router-link :to="{ name: 'SubjectDetails', params: { id: subject.id } }">
    <div class="card">
      <h1>{{ subject.code }}</h1>
      <h3>{{ subject.name }}</h3>
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
  border: solid 1px;
  padding: 20px;
  border-radius: 10px;
}
</style>
