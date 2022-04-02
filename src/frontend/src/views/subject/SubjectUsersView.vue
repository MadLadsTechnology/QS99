<template>

  <table>
    <tr>
      <th>Last name</th>
      <th>First name</th>
      <th>Email</th>
      <th>Role</th>
      <th>Exercises</th>
      <th>Actions</th>
    </tr>

    <tr  v-for="user in users" :key="user.emailAddress">

      <td>{{ user.lastName }}</td>
      <td>{{ user.firstName }}</td>
      <td>{{ user.emailAddress }}</td>
      <td>{{ user.role }}</td>
      <td >
        <div class="exerciseWrapper">
          <ExerciseBox
              v-for="exercise in user.exercises" :key="exercise"
              :exercise="exercise"
          />
        </div>

      </td>

      <td><button @click="removeUser(user)">Remove</button></td>
    </tr>

  </table>
</template>

<script>


import axios from "axios";
import ExerciseBox from "@/components/ExerciseBox";

export default{

  name: "SubjectUsersView",
  props: ["subject"],
  components:{
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
          .delete("http://localhost:8001/subject/deleteUserFromSubject", {
            params: {
              subjectId: this.id,
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
          .delete("http://localhost:8001/exercise", {
            params: {
              subjectId: this.id,
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
        .get("http://localhost:8001/user/getAllUsersFromSubject", {
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

.exerciseWrapper{
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 2px;
}
</style>