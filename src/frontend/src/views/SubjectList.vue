<template>
  <h3>Active queues</h3>

  <div class="cardHolder">
    <SubjectCardActiveQueue
      v-for="subject in subjects.active"
      :key="parseInt(subject.id)"
      :subject="subject"
    />
  </div>
  <h3>Other subjects</h3>

  <div class="cardHolder">
    <SubjectCard
      v-for="subject in subjects.isActive"
      :key="parseInt(subject.id)"
      :subject="subject"
    />
  </div>
</template>
<script>
import SubjectCard from "../components/subject/SubjectCard";
import SubjectCardActiveQueue from "../components/subject/SubjectCardActiveQueue";
import { authComputed } from "@/store/helpers";
import axios from "axios";

export default {
  name: "HomeView",
  components: {
    SubjectCard,
    SubjectCardActiveQueue,
  },
  created() {
    document.title = "QS99 - Subjects";

    //getting subjects of the user
    axios.get("http://localhost:8001/subject/getByUser").then((response) => {
      this.allSubjects = response.data;
      console.log(this.allSubjects);
    });
  },
  data() {
    return {
      allSubjects: [],
    };
  },

  computed: {
    ...authComputed,

    subjects: function () {
      const active = [];
      const isActive = [];
      for (const subject in this.allSubjects) {
        if (subject.isQueueActive) {
          active.push(subject);
        } else {
          console.log(subject);
          isActive.push(subject);
        }
      }
      return {
        active,
        isActive,
      };
    },
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
