<template>
  <div>
    <div v-if="!!assignments" class="cardHolder">
      <AssignementCard
        v-for="assignment in assignments"
        :key="parseInt(assignment.id)"
        :assignment="assignment"
      />
    </div>
    <div v-else>
      <h3>No assignments have been registered to this subject yet</h3>
    </div>
  </div>
</template>

<script>
import AssignementCard from "../../components/subject/AssignmentCard";
import axios from "axios";

export default {
  name: "AssignementView",
  props: ["subject"],
  components: {
    AssignementCard,
  },
  async created() {
    document.title = "QS99 - Subjects";

    //getting subjects assigments
    await axios
      .get("http://localhost:8001/exercise/getByUser", {
        params: {
          subjectId: parseInt(this.subject.id),
        },
      })
      .then((response) => {
        if (response.data.length > 0) {
          this.assignments = response.data;
        }
      });
  },

  data() {
    return {
      assignments: null,
    };
  },
};
</script>
