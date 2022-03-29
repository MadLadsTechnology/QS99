<template>
  <div>
    <h1>{{}}</h1>
    <div id="nav">
      <router-link :to="{ name: 'SubjectDetails' }">Details</router-link>
      |
      <router-link :to="{ name: 'SubjectQueue' }">Queue</router-link>
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
