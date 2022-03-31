<template>
  <div>
    <h1>{{ subject.id }}</h1>

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
import axios from "axios";

export default {
  props: ["id"],

  async created() {
    await axios
      .get("http://localhost:8001/getSubject", null, {
        params: {
          subjectId: this.id,
        },
      })
      .then((response) => {
        this.subject = response.data;
      });

    document.title = "QS99 - " + this.subject.subjectCode;
  },
  data() {
    return {
      subject: null,
    };
  },
};
</script>
