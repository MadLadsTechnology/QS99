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
      <br/>
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
      } else if (this.subject.isStudAss) {
        return "subjectUsers"
      }
      return "StudentSubjectAssignments";
    },
  },
};
</script>

<style scoped src="@/styles/components/subject/SubjectCard.css"/>
