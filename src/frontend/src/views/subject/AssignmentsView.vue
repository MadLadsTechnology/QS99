<template>


  <h3>Number of exercises: {{ exerciseCount }}</h3>
  <h5 v-for="sublist in assignments" :key="parseInt(sublist.id)">
    {{ sublist.numberOfMandatory }} exercises are mandatory in
    {{ sublist.exercises[0] }} ...
    {{ sublist.exercises[sublist.exercises.length - 1] }}
  </h5>


</template>

<script>

import axios from "axios";

export default {
  name: "AssignementView",
  props: ["subject"],
  components: {},

  methods: {},
  async created() {
    document.title = "QS99 - Subjects";

    //getting subjects assigments

    if (this.hasPrivileges) {
      await axios.get("/user/getAllUsersFromSubject", {
        params: {
          subjectId: parseInt(this.subject.id),
        }
      }).then(response => {
        this.students = response.data;
      })
    }
    await axios
        .get("/exercise/getBySubject", {
          params: {
            subjectId: parseInt(this.subject.id),
          },
        })
        .then((response) => {
          if (response.data.length > 0) {
            this.assignments = response.data;
          }
        });
  },


  data() {
    return {
      assignments: null,
      students: null,
    };
  },

  computed: {
    hasPrivileges: function () {
      if (this.subject.isStudAss || this.$store.getters.isProfessor || this.$store.getters.isAdmin) {
        return true;
      }
      return false;
    },

    exerciseCount: function () {
      let count = 0;

      if (this.assignments) {
        this.assignments.forEach(sublist => {
          count += sublist.exercises.length;
        });

        return count;
      }
      return null;

    }
  }
};
</script>

<style scoped src="@/styles/views/subject/AssignmentsView.css"/>

