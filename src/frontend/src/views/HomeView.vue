<template>
  <h1>Welcome {{ user.mail }}</h1>

  <h3>Here are your active subjects</h3>

  <!-- ADD FOR LOOP TO LOOP THRU ALL SUBJECTS-->
  <SubjectCard
    subjectCode="IDATT1234"
    subjectName="Testfag"
    description=""
    link="null"
  />
</template>

<script>
import { mapState } from "vuex";
import SubjectCard from "@/components/SubjectCard";

// @ is an alias to /src
export default {
  computed: {
    ...mapState({
      isLoggedIn: "isLoggedIn",
      user: "user",
    }),
  },
  name: "HomeView",
  components: { SubjectCard },
};
</script>
