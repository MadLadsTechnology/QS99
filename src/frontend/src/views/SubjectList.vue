<template>
  <h3>Active queues</h3>

  <div class="teacherToolbar" v-if="this.$store.getters.isProfessor">
    <button></button>
  </div>

  <div class="cardHolder">
    <SubjectCard
      v-for="subject in subjects.active"
      :key="parseInt(subject.id)"
      :subject="subject"
    />
  </div>

  <h3>Other subjects</h3>

  <div class="cardHolder">
    <SubjectCard
      v-for="subject in subjects.inActive"
      :key="parseInt(subject.id)"
      :subject="subject"
    />
  </div>
</template>
<script>
import SubjectCard from "../components/subject/SubjectCard";
import { authComputed } from "@/store/helpers";
import axios from "axios";

export default {
  name: "HomeView",
  components: {
    SubjectCard,
  },
  async created() {
    document.title = "QS99 - Subjects";

    //getting subjects of the user
    await axios
      .get("http://localhost:8001/subject/getByUser")
      .then((response) => {
        this.allSubjects = response.data;
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
        if (this.allSubjects[i].queueActive === true) {
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

<style scoped>
.cardHolder {
  margin-top: 40px;
  width: 100%;
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
  gap: 30px;
}
</style>
