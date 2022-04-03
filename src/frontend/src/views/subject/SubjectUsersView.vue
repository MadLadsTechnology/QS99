<template>

  <table v-if="!!users">
    <tr>
      <th>Last name</th>
      <th>First name</th>
      <th>Email</th>
      <th>Role</th>
      <th>Exercises</th>
      <th v-if="!subject.isStudAss">Actions</th>
    </tr>

    <tr v-for="user in users" :key="user.emailAddress">

      <td>{{ user.lastName }}</td>
      <td>{{ user.firstName }}</td>
      <td>{{ user.emailAddress }}</td>
      <td>{{ user.role }}</td>
      <td>
        <div class="exerciseWrapper">
          <ExerciseBox
              v-for="exercise in user.exercises" :key="exercise"
              :exercise="exercise"
              :studentId="user.emailAddress"
              :subjectId="subject.id"

          />
        </div>

      </td>

      <td v-if="!subject.isStudAss">
        <button @click="removeUser(user)">Remove</button>
      </td>
    </tr>

  </table>
</template>

<script>


import axios from "axios";
import ExerciseBox from "@/components/ExerciseBox";

export default {

  name: "SubjectUsersView",
  props: ["subject"],
  components: {
    ExerciseBox
  },

  data() {
    return {
      users: [],
    }
  },

  methods: {
    async removeUser(user) {
      await axios
          .delete("http://localhost:8001/subject/qs/deleteUserFromSubject", {
            params: {
              subjectId: this.subject.id,
              emailAddress: user.emailAddress,
            },
          })
          .then((response) => {
            if (response) {
              location.reload();
            }
          });
    },
    async removeExercise(exercise) {
      await axios
          .delete("http://localhost:8001/qs/exercise", {
            params: {
              subjectId: this.subject.id,
              exerciseNumber: exercise.exerciseNumber,
            },
          })
          .then(() => {
            location.reload();
          });
    },
  },


  async created() {
    await axios
        .get("http://localhost:8001/user/qs/student/getAllUsersFromSubject", {
          params: {
            subjectId: parseInt(this.subject.id),
          },
        })
        .then((response) => {
          this.users = response.data;
          console.table(this.users);
        });

  },
}
</script>

<style>

.exerciseWrapper {
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 2px;
}
</style>