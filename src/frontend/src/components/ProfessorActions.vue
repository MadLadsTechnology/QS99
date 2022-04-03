<template>
  <AddExercises
      v-if="showAddExercises"
      v-bind:subject="subject"
      v-on:closeWindow="closeWindow"
  />
  <AddUserToSubject
      v-if="showAddSingleUser"
      v-bind:subject="subject"
      v-on:closeWindow="closeWindow"
  />
  <AddMultipleUsersToSubject
      v-if="showAddMultipleUsers"
      v-bind:subject="subject"
      v-on:closeWindowMultipleUsers="closeWindow"
  />

  <div class="button-group">
    <button @click="showSingleUserWindow(id, subject.subjectCode)">
      Add user
    </button>
    <button @click="showMultipleUserWindow(id, subject.subjectCode)">
      Add multiple users
    </button>
    <button @click="showAddExercisesWindow(id, subject.subjectCode)">
      Add exercises
    </button>
  </div>


</template>

<script>
import AddUserToSubject from "@/components/PopUps/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/PopUps/AddMultipleUsersToSubject";
import AddExercises from "@/components/PopUps/AddExercises";
import axios from "axios";

export default {

  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
  },
  props: {
    subject: {
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
          .delete("http://localhost:8001/deleteUserFromSubject", {
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

<style scoped>
.button-group {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  justify-content: center;
  width: 70%;
  margin: auto;
}

button {
  width: 100px;
  height: 40px;
  padding: 5px;
}

button:hover {
  cursor: pointer;
}
</style>