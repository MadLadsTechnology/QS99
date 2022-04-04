<template>
  <table v-for="sublist in assignments" :key="parseInt(sublist.id)">
    <tr>
      <td colspan="2">{{ sublist.numberOfMandatory }} exercises are mandatory</td>
    </tr>
    <tr v-for="(exercise, index) in sublist.exercises" :key="parseInt(exercise.id)">
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

    sublists: function () {

      let sublistIds = []


      this.assignments.forEach(assignment => {
        if (!sublistIds.contains(assignment.sublistId)) {
          sublistIds.push(assignment.sublistId)
        }
      })
      return "";
    }
  }
};
</script>

<style scoped>
table {
  width: 200px;
  margin: auto;
  font-size: 200%;
}


</style>
