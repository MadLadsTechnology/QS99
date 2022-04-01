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

  <h3>All Subjects</h3>

  <router-link to="/createSubject"
    ><button>Create a subject</button></router-link
  >

  <table>
    <tr>
      <th>Code</th>
      <th>Name name</th>
      <th>Description</th>
      <th>Year</th>
      <th>Actions</th>
    </tr>
    <tr v-for="object in subjects" :key="object.id">
      <td>{{ object.subjectCode }}</td>
      <td>{{ object.subjectName }}</td>
      <td>{{ object.subjectDescription }}</td>
      <td>{{ object.subjectYear }}</td>
      <td>
        <router-link
          :to="{
            name: 'subjectInfo',
            params: { id: object.id },
          }"
        >
          <button>Info</button>
        </router-link>

        <button @click="showSingleUserWindow(object.id, object.subjectCode)">
          Add user
        </button>
        <button @click="showMultipleUserWindow(object.id, object.subjectCode)">
          Add multiple users
        </button>
        <button @click="showAddExercisesWindow(object.id, object.subjectCode)">
          Add exercises
        </button>
      </td>
    </tr>
  </table>
</template>
<script>
import AddUserToSubject from "@/components/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/AddMultipleUsersToSubject";
import AddExercises from "@/components/AddExercises";
import { authComputed } from "@/store/helpers";
import axios from "axios";

export default {
  name: "HomeView",
  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
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
    },
  },
  async created() {
    document.title = "QS99 - Students";
    //getting subjects of the user
    await axios
      .get("http://localhost:8001/subject/getAllSubject")
      .then((response) => {
        this.subjects = response.data;
      });
  },
  data() {
    return {
      subjects: null,
      showAddSingleUser: false,
      showAddMultipleUsers: false,
      showAddExercises: false,
      currentSubject: null,
    };
  },
  computed: {
    ...authComputed,
  },
};
</script>
<style>
table {
  margin: auto;
  width: 80%;
  border-collapse: collapse;
}

td,
th {
  border: 1px solid #999;
  padding: 0.5rem;
  text-align: left;
}
</style>
