<template>

  <ProfessorActions v-if="!this.$store.getters.isStudent"
                    :subject="subject"
  />
  <div v-if="!!subject" class="container">

    <h1>{{ subject.subjectCode }}</h1>

    <h4 v-if="subject.isStudAss">
      Hello, you are a student assistant in this subject
    </h4>

    <div class="nav">
      <router-link :to="{ name: 'SubjectQueue' }">
        <div>Queue</div>
      </router-link>

      <router-link :to="{ name: 'SubjectDetails' }"
      >
        <div>Details</div>
      </router-link
      >

      <router-link v-if="!this.$store.getters.isStudent || subject.isStudAss" :to="{ name: 'subjectUsers' }"
      >
        <div>Users</div>
      </router-link
      >

      <router-link v-else :to="{ name: 'SubjectAssignments' }"
      >
        <div>Assignments</div>
      </router-link
      >


    </div>
    <router-view :subject="subject"/>
  </div>
</template>

<script>
import axios from "axios";
import ProfessorActions from "@/components/ProfessorActions";

export default {
  components: {ProfessorActions},
  props: ["id"],

  data() {
    return {
      subject: null,
    };
  },
  async created() {
    await axios
        .get("http://localhost:8001/subject/qs/student/getSubject", {
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
