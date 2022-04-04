<template>


  <div v-for="user in users" :key="user.emailAddress" class="user">


    <div class="information">
      <h4> {{ user.role }}</h4>
      <text> {{ user.lastName }}</text>
      <text> {{ user.firstName }}</text>
      <button v-if="!subject.isStudAss" @click="removeUser(user)">Remove</button>
    </div>


    <div class="exerciseWrapper">
      <ExerciseBox
          v-for="exercise in user.exercises" :key="exercise"
          :exercise="exercise"
          :student="user"
          :subjectId="subject.id"

      />
    </div>


  </div>


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
      if (confirm("Are you sure you want to remove " + user.emailAddress + " from subject " + this.subject.subjectCode))
        await axios
            .delete("/subject/deleteUserFromSubject", {
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
          .delete("/exercise", {
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
        .get("/user/getAllUsersFromSubject", {
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

<style scoped src="@/styles/views/subject/UsersInSubject.css"/>
