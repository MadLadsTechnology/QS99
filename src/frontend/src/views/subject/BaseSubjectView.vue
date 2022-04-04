<template>
  <div v-if="!!subject" class="container">
    <ProfessorActions v-if="!this.$store.getters.isStudent"
                      :subject="subject"
    />
    <div v-if="!!subject">

      <h1>{{ subject.subjectCode }}</h1>

      <h4 v-if="subject.isStudAss">
        You are a student assistant in this subject
      </h4>

      <div class="nav">
        <router-link :to="{ name: 'SubjectQueue' }">
          <div>Queue</div>
        </router-link>

        <router-link :to="{ name: 'SubjectDetails' }"
        >
          <div>About</div>
        </router-link
        >

        <router-link v-if="!this.$store.getters.isStudent || subject.isStudAss" :to="{ name: 'subjectUsers' }"
        >
          <div>Users</div>
        </router-link
        >

        <router-link v-else :to="{ name: 'StudentSubjectAssignments' }"
        >
          <div>Assignments</div>
        </router-link
        >

        <router-link v-if="!this.$store.getters.isStudent || subject.isStudAss" :to="{ name: 'assignments' }"
        >
          <div>Assignments</div>
        </router-link
        >


      </div>
      <router-view :subject="subject" class="router-view"/>
    </div>
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
        .get("/subject/getSubject", {
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

<style scoped src="@/styles/views/subject/BaseSubjectView.css"/>

