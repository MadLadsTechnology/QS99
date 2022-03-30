<template>
  <div>
    <h1>{{ subject.code }}</h1>

    <div id="nav">
      <router-link :to="{ name: 'SubjectQueue' }">Queue</router-link>
      |
      <router-link :to="{ name: 'SubjectDetails' }">Details</router-link>
      |
      <router-link :to="{ name: 'SubjectAssignments' }"
        >Assignments</router-link
      >
    </div>
    <router-view :subject="subject" />
  </div>
</template>

<script>
export default {
  props: ["id"],
  created() {
    document.title = "QS99 - " + this.subject.code;
  },
  data() {
    return {
      subject: {
        id: 1,
        code: "IDATT1234",
        name: "Tester",
      },
    };
  },
};
</script>
