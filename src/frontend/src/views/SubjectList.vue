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
  async created() {
    document.title = "QS99 - Subjects";
    const listOfSubjects = [];

    //getting subjects of the user
    await axios
      .get("http://localhost:8001/subject/getByUser")
      .then((response) => {
        console.log(JSON.parse(JSON.stringify(response.data)));
        const res = JSON.parse(JSON.stringify(response.data));
        for (let value of res) {
          const subject = {
            id: value.id,
            isQueueActive: value.isQueueActive,
            subjectCode: value.subjectCode,
            subjectDescription: value.subjectDescription,
            subjectName: value.subjectName,
            subjectYear: value.subjectYear,
          };
          listOfSubjects.push(subject);
        }
        this.allSubjects = listOfSubjects;
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
      const inActive = [];
      for (let i = 0; i < this.allSubjects.length; i++) {
        console.log(this.allSubjects[i]);
        if (this.allSubjects[i].isQueueActive === "true") {
          active.push(this.allSubjects[i]);
        } else {
          inActive.push(this.allSubjects[i]);
        }
      }
      return {
        active,
        inActive,
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
