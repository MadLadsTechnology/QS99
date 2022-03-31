<template>
  <h3>All students</h3>
  <div v-for="student in students" :key="student">
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
    axios.get("http://localhost:8001/user/getAllStudents").then((response) => {
      this.students = response.data;
    });
  },
  data() {
    return {
      students: null,
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
