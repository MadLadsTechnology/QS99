<template>
  <div v-if="!!subject">
    <h1>{{ subject.subjectCode }}</h1>

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

  data() {
    return {
      subject: null,
    };
  },
  async created() {
    await axios
      .get("http://localhost:8001/subject/getSubject", {
        params: {
          subjectId: parseInt(this.id),
        },
      })
      .then((response) => {
        this.subject = response.data;
      })
      .catch((err) => {
        alert(err);
      });

    document.title = "QS99 - " + this.subject.subjectCode;
  },
};
</script>
