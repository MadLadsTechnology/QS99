<template>
  <div class="container" v-if="!!subject">
    <h1>{{ subject.subjectCode }}</h1>

    <div class="nav">
      <router-link :to="{ name: 'SubjectQueue' }"><div>Queue</div></router-link>

      <router-link :to="{ name: 'SubjectDetails' }"
        ><div>Details</div></router-link
      >

      <router-link :to="{ name: 'SubjectAssignments' }"
        ><div>Assignments</div></router-link
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

<style scoped>
.container {
  text-align: center;
}
.nav {
  display: flex;
  flex-direction: row;
  margin: auto;
  justify-content: space-around;
  width: 50%;
  min-width: 200px;
}
.nav div {
  width: 100px;
}
</style>
