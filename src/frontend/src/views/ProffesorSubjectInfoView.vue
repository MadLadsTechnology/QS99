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

  <div>
    <h1>{{ subject.subjectCode }}</h1>
    <button @click="showSingleUserWindow(id, subject.subjectCode)">
      Add user
    </button>
    <button @click="showMultipleUserWindow(id, subject.subjectCode)">
      Add multiple users
    </button>
    <button @click="showAddExercisesWindow(id, subject.subjectCode)">
      Add exercises
    </button>

    <h2>Users</h2>

    <div v-for="user in users" :key="user.emailAddress">
      <h4>
        Name: {{ user.firstName + " " + user.lastName }} Email:
        {{ user.emailAddress }} Role: {{ user.role }}
        <button @click="removeUser(user)">Remove</button>
      </h4>
    </div>

    <h2>Assignments</h2>
    <div v-for="assignment in assignments" :key="assignment.id">
      <h4>
        Assignment: {{ assignment.exerciseNumber }} Mandatory:
        {{ assignment.mandatory }}
        <button @click="removeExercise(assignment)">Remove</button>
      </h4>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AddUserToSubject from "@/components/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/AddMultipleUsersToSubject";
import AddExercises from "@/components/AddExercises";
export default {
  name: "ProffesorSubjectInfoView",
  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
  },

  props: ["id"],
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
    await axios
      .get("http://localhost:8001/subject/admin/getSubject", {
        params: {
          subjectId: this.id,
        },
      })
      .then((response) => {
        this.subject = response.data;
      });
    await axios
      .get("http://localhost:8001/exercise/getBySubject", {
        params: {
          subjectId: this.id,
        },
      })
      .then((response) => {
        this.assignments = response.data;
      });
  },
  data() {
    return {
      users: [],
      subject: null,
      subjects: null,
      showAddSingleUser: false,
      showAddMultipleUsers: false,
      showAddExercises: false,
      currentSubject: null,
      assignments: [],
    };
  },
};
</script>

<style scoped></style>
