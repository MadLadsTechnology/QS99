<template>
  <h1>Welcome {{ user.mail }}</h1>

  <h3>Here are your active subjects</h3>

  <button @click="showAll = true">Alle fag</button>

  <SubjectList subjectCode="I" subjectName="yeha" link="/" />
</template>
<script>
import { mapState } from "vuex";
// @ is an alias to /src
export default {
  data() {
    return {
      showAll: true,
    };
  },
  computed: {
    ...mapState({
      isLoggedIn: "isLoggedIn",
      user: "user",
    }),
  },
  name: "HomeView",
  components: {},
};
</script>
