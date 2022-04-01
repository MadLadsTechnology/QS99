<template>
  <div>
    <div class="cardHolder">
      <AssignementCard
        v-for="assignment in allAssignments"
        :key="parseInt(assignment.id)"
        :assignment="assignment"
      />
    </div>
  </div>
</template>

<script>
import AssignementCard from "../../components/subject/AssignmentCard";
import axios from "axios";

export default {
  name: "AssignementView",
  components: {
    AssignementCard,
  },
  async created() {
    document.title = "QS99 - Subjects";

    //getting subjects assigments
    await axios
      .get("http://localhost:8001/exercise/getByUser", {
        params: {
          subjectId: 1,
        },
      })
      .then((response) => {
        this.allAssignments = response.data;
        console.log(this.allAssignments);
      });
  },

  data() {
    return {
      allAssignments: [],
    };
  },
};
</script>
