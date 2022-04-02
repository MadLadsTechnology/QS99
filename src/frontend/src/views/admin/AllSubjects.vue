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
  <AddAssistantToSubject
    v-if="showAddAssistant"
    v-on:closeWindow="closeWindow"
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
            name: 'SubjectLayout',
            params: { id: object.id },
          }"
        >
          <button>Info</button>
        </router-link>

        <button @click="showSingleUserWindow(object.id, object.subjectCode)">
          Add student
        </button>
        <button @click="showMultipleUserWindow(object.id, object.subjectCode)">
          Add multiple students
        </button>
        <button
          @click="showSingleAssistantWindow(object.id, object.subjectCode)"
        >
          Add student assistant
        </button>
        <button @click="showAddExercisesWindow(object.id, object.subjectCode)">
          Add exercises
        </button>
        <button @click="deleteSubject(object)">
          Delete Subject
        </button>
      </td>
    </tr>
  </table>
</template>
<script>
import AddUserToSubject from "@/components/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/AddMultipleUsersToSubject";
import AddExercises from "@/components/AddExercises";
import AddAssistantToSubject from "@/components/AddAssistantToSubject";
import { authComputed } from "@/store/helpers";
import axios from "axios";

export default {
  name: "HomeView",
  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
    AddExercises,
    AddAssistantToSubject,
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
      this.showAddExercises = false;
      this.showAddAssistant = false;
    },
    showMultipleUserWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.showAddSingleUser = false;
      this.showAddMultipleUsers = true;
      this.showAddExercises = false;
      this.showAddAssistant = false;
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
      this.showAddExercises = false;
      this.showAddAssistant = false;
    },

    deleteSubject(subject){
      let confirmAction = confirm("Are you sure to execute this action?");
      if (confirmAction) {
        axios.delete("http://localhost:8001/subject", {params:{
          subjectId: subject.id,
          }})
        location.reload()
      }
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
      showAddAssistant: false,
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
