<template>

  <div>

    <table>
      <tr>
        <th>Number</th>
        <th>Approved</th>
      </tr>
      <tr v-for="assignment in assignments" :key="parseInt(assignment.id)">
        <td>{{ assignment.exerciseNumber }}</td>
        <td>{{ assignment.isApproved }}</td>
      </tr>

    </table>

  </div>
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
    }
  }
};
</script>
