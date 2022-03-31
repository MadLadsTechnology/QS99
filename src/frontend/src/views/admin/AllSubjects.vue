<template>
  <h3>All students</h3>

  <router-link to="/createSubject"
    ><button>Create a subject</button></router-link
  >
  <div v-for="student in subjects" :key="student">
    {{ student }}
  </div>
</template>
<script>
import { authComputed } from "@/store/helpers";
import axios from "axios";
export default {
  name: "HomeView",
  components: {},
  created() {
    document.title = "QS99 - Students";
    //getting subjects of the user
    axios.get("http://localhost:8001/subject/getsSubjects").then((response) => {
      this.subjects = response.data;
    });
  },
  data() {
    return {
      subjects: null,
    };
  },
  computed: {
    ...authComputed,
  },
};
</script>
<style>
.cardHolder {
  margin-top: 40px;
  width: 100%;
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
  gap: 30px;
}
</style>
