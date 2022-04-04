<template>

  <h1 v-if="subjectApproved">The subject is passed</h1>
  <h1 v-else>Not passed</h1>
  <table v-for="(sublist, index) in assignments" :key="parseInt(index)">
    <tr>
      <td colspan="2">Mandatory: {{ sublist.numberOfMandatory }}</td>
    </tr>
    <tr v-for="(exercise, index) in sublist.exercises" :key="parseInt(index)">
      <td>{{ index }}</td>
      <td v-if="exercise">✅</td>
      <td v-else>⛔️</td>
    </tr>
  </table>

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
        .get("/exercise/getByUser", {
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

    subjectApproved: function () {
      let isApproved = true;
      if (this.assignments) {
        this.assignments.forEach(sublist => {
          let count = 0;
          for (let index in sublist.exercises) {
            if (sublist.exercises[index]) {
              count += 1;
            }
          }
          if (count < sublist.numberOfMandatory) {
            isApproved = false;
          }
        })
      }

      return isApproved;
    }

  }
};
</script>

<style scoped>

table {
  width: 70%;
  max-width: 800px;
  border-collapse: collapse;
  margin: auto auto 50px;
}

th {
  background-color: lightgray;
}

th,
td {
  border: 1px solid #999;
  padding: 0.5rem;
  text-align: center;
  width: 50%;
}


</style>
