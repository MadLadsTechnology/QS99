<template>
  <h3>Active queues</h3>

  <div class="cardHolder">
    <SubjectCardActiveQueue
      v-for="subject in activeSubjects"
      :key="subject.id"
      :subject="subject"
    />
  </div>
  <h3>Other subjects</h3>

  <div class="cardHolder">
    <SubjectCard
      v-for="subject in subjects"
      :key="subject.id"
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
      this.subjects = response;
    });
  },
  data() {
    return {
      activeSubjects: [
        { id: 1, code: "IDATT1234", name: "Testfag" },
        {
          id: 1,
          code: "IDATT1234",
          name: "Matematiske metoder 2",
        },
      ],
      subjects: [
        { id: 1, code: "IDATT2101", name: "Programmering 1" },
        { id: 1, code: "IDATT2124", name: "Systemutvikling" },
        { id: 1, code: "IDATT9012", name: "Fysikk data" },
        { id: 1, code: "IDATT1011", name: "Operativsystmer" },
      ],
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
