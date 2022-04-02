<template>
  <AddExercises
    v-if="showAddExercises"
    v-on:closeWindow="closeWindow"
    v-bind:subject="currentSubject"
/>
  <AddUserToSubject
      v-if="showAddSingleUser"
      v-on:closeWindow="closeWindow"
      v-bind:subject="currentSubject"
  />
  <AddMultipleUsersToSubject
      v-if="showAddMultipleUsers"
      v-on:closeWindowMultipleUsers="closeWindow"
      v-bind:subject="currentSubject"
  />

  <button @click="showSingleUserWindow(id, subject.subjectCode)">
    Add user
  </button>
  <button @click="showMultipleUserWindow(id, subject.subjectCode)">
    Add multiple users
  </button>
  <button @click="showAddExercisesWindow(id, subject.subjectCode)">
    Add exercises
  </button>

</template>

<script>
import AddUserToSubject from "@/components/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/AddMultipleUsersToSubject";
import AddExercises from "@/components/AddExercises";
import axios from "axios";

export default{

  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
  },
  props:{
    subject:{
      type: Object,
      required: true,
    }
  },

  methods: {
    setCurrentSubject(id, subjectCode) {
      this.currentSubject = {
        code: subjectCode,
        id: id,
      };
    },
    showSingleUserWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = true;
      this.showAddMultipleUsers = false;
    },
    showMultipleUserWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = true;
    },
    showAddExercisesWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = false;
      this.showAddExercises = true;
    },
    closeWindow() {
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = false;
      this.showAddExercises = false;
      location.reload();
    },
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
            subjectId: this.id,
          },
        })
        .then((response) => {
          this.users = response.data;
          console.table(this.users);
        });

  },
  data() {
    return {
      users: [],
      subjects: null,
      showAddSingleUser: false,
      showAddMultipleUsers: false,
      showAddExercises: false,
      currentSubject: null,
      assignments: [],
    };
  },
}


</script>