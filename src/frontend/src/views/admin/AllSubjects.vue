<template>
  <AddUserToSubject
    v-if="addSingleUser"
    v-on:closeWindow="closeWindow"
    v-bind:subject="currentSubject"
  />
  <AddMultipleUsersToSubject
    v-if="addMultipleUsers"
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
      <button @click="addExercises(object.id, object.subjectCode)">
        Add user
      </button>
    </div>
  </div>
</template>
<script>
import AddUserToSubject from "@/components/AddUserToSubject";
import AddMultipleUsersToSubject from "@/components/AddMultipleUsersToSubject";
import { authComputed } from "@/store/helpers";
import axios from "axios";
export default {
  name: "HomeView",
  components: {
    AddUserToSubject,
    AddMultipleUsersToSubject,
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
      this.addSingleUser = true;
    },
    showMultipleUserWindow(id, subjectCode) {
      this.setCurrentSubject(id, subjectCode);
      this.addMultipleUsers = true;
    },
    closeWindow() {
      this.addSingleUser = false;
      this.addMultipleUsers = false;
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
      addSingleUser: false,
      addMultipleUsers: false,
      currentSubject: {
        code: null,
        year: null,
      },
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
