<template>
  <router-link
    :to="{
      name: link,
      params: { id: subject.id },
    }"
  >
    <div class="card">
      <h1>{{ subject.subjectCode }}</h1>
      {{ subject.subjectName }}

      {{ subject.id }}
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
  computed: {
    link: function () {
      if (this.subject.queueActive) {
        return "SubjectQueue";
      }
      return "SubjectAssignments";
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
  padding: 20px;
  width: 200px;
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
