<template>
  <h1>Welcome {{ user.mail }}</h1>

  <h3>Here are your active subjects</h3>

  <button @click="showAll = true">Alle fag</button>

  <button>Fag 1</button>
  <button>Fag 2</button>
  <button>Fag 3</button>
  <button>Fag 4</button>

  <SubjectList v-if="showAll" />

  <SubjectPage v-else />
</template>

<script>
import { mapState } from "vuex";
import SubjectList from "@/components/SubjectList";
import SubjectPage from "@/components/SubjectPage";

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
  components: { SubjectList, SubjectPage },
};
</script>
