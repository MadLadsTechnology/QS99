<template>
  <h1>Welcome {{}}</h1>

  <h3>Here are your active subjects</h3>

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
import { authComputed } from "@/store/helpers";

export default {
  name: "HomeView",
  components: {
    SubjectCard,
  },
  data() {
    return {
      subjects: [
        { id: 1, code: "IDATT1234", name: "Testfag" },
        { id: 1, code: "IDATT1234", name: "Testfag" },
        { id: 1, code: "IDATT1234", name: "Testfag" },
        { id: 1, code: "IDATT1234", name: "Testfag" },
      ],
    };
  },
  computed: {
    ...authComputed,
  },
};
</script>
