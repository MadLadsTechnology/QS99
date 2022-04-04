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

        <router-link v-else :to="{ name: 'SubjectAssignments' }"
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

<style scoped>

h1 {
  margin: 20px;
}

.container {
  text-align: center;
  margin: 20px auto auto;
  border-radius: 10px;
  min-height: 500px;
  width: 100%;
  max-width: 800px;
}

.nav {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 80%;
  min-width: 250px;
  max-width: 300px;
  border-bottom: solid;
  margin: auto;
}

a {
  text-decoration: none;
  color: inherit;
}

.nav div {
  padding: 10px;
  max-width: 100px;
  min-width: 50px;
}

.router-link-active div {
  background-color: #2c3e50;
  color: white;
}

</style>
