<template>
  <AddExercises
      v-if="showAddExercises"
      v-bind:subject="currentSubject"
      v-on:closeWindow="closeWindow"
  />
  <AddUserToSubject
      v-if="showAddSingleUser"
      v-bind:subject="currentSubject"
      v-on:closeWindow="closeWindow"
  />
  <AddMultipleUsersToSubject
      v-if="showAddMultipleUsers"
      v-bind:subject="currentSubject"
      v-on:closeWindowMultipleUsers="closeWindow"
  />

  <AddAssistantToSubject
      v-if="showAddAssistant"
      v-bind:subject="currentSubject"
      v-on:closeWindow="closeWindow"
  />
  <div class="button-group">
    <button @click="showSingleUserWindow(subject.id, subject.subjectCode)">
      Add user
    </button>
    <button @click="showMultipleUserWindow(subject.id, subject.subjectCode)">
      Add multiple users
    </button>
    <button @click="showAddExercisesWindow(subject.id, subject.subjectCode)">
      Add exercises
    </button>
    <button @click="showSingleAssistantWindow(subject.id, subject.subjectCode)">
      Add student assistant
    </button>
  </div>


</template>

<script>
import AddUserToSubject from "@/components/PopUps/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/PopUps/AddMultipleUsersToSubject";
import AddExercises from "@/components/PopUps/AddExercises";
import axios from "axios";
import AddAssistantToSubject from "@/components/PopUps/AddAssistantToSubject";

export default {

  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
    AddAssistantToSubject,
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
      this.showAddAssistant = false;
      this.showAddExercises = false;

    },
    showMultipleUserWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = false;
      this.showAddAssistant = false;
      this.showAddMultipleUsers = true;
      this.showAddExercises = false;
    },
    showAddExercisesWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = false;
      this.showAddExercises = true;
      this.showAddAssistant = false;
    },
    showSingleAssistantWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddAssistant = true;
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = false;
      this.showAddExercises = false;
    },
    closeWindow() {
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = false;
      this.showAddAssistant = false;
      this.showAddExercises = false;
      location.reload();
    },
    async removeUser(user) {
      await axios
          .delete("/deleteUserFromSubject", {
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
      showAddAssistant: false,
      currentSubject: null,
      assignments: [],
    };
  },
}


</script>

<style scoped src="@/styles/components/ProfessorActions.css"/>