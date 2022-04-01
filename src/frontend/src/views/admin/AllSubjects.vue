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
  <div class="subjectlist">
    <div v-for="object in subjects" :key="object.id" class="subject">
      <p>{{ object.subjectCode }}</p>
      <p>{{ object.subjectName }}</p>
      <p>{{ object.subjectDescription }}</p>
      <p>{{ object.subjectYear }}</p>

      <button @click="showSingleUserWindow(object.id, object.subjectCode)">
        Add user
      </button>
      <button @click="showMultipleUserWindow(object.id, object.subjectCode)">
        Add multiple users
      </button>
      <button @click="showAddExercisesWindow(object.id, object.subjectCode)">
        Add exercises
      </button>
    </div>
  </div>
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
.subjectlist {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin: auto;
  width: 50%;
}

.subject {
  display: flex;
  flex-direction: row;
  gap: 30px;
  align-items: center;
  border: solid;
  border-radius: 3px;
  padding: 10px;
}
.cardHolder {
  margin-top: 40px;
  width: 100%;
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
  gap: 30px;
}
</style>
