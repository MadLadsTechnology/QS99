<template>
  <h1> Currently assisting {{ object.firstName }}, {{ object.lastName }}</h1>


  <div class="exercises">
    <h3>Exercises</h3>
    <h5>Click the exercise you want to approve</h5>
    <ExerciseBox v-for="exercise in object.exercises" :key="exercise" :exercise="exercise" :studentId="studentId"
                 :subjectId="subjectId" :student="object"/>
  </div>

  <button @click="suspend">Suspend</button>
  <button @click="done">Done</button>


</template>

<script>
import axios from "axios";
import ExerciseBox from "@/components/ExerciseBox";

export default {
  name: "HelpAndApprove",
  props: ["subjectId", "studentId", "entryId"],
  components: {
    ExerciseBox,
  },

  data() {
    return {
      object: null,
    }
  },

  methods: {
    suspend() {
      axios.post("/entry/setIsGettingHelp", null, {
        params: {
          entryId: this.entryId,
          isGettingHelp: false,
        }
      }).then(this.$router.push("/subjects/" + this.subjectId + "/queue"))
    },
    done() {
      axios.delete("/entry", {
        params: {
          entryId: this.entryId,
        }
      }).then(
          this.$router.push("/subjects/" + this.subjectId + "/queue"))
    },
  },


  async created() {
    await axios.get("/user/getUserFromSubject", {
      params: {
        email: this.studentId,
        subjectId: this.subjectId,
      }
    }).then(response => {
      console.table(response);
      this.object = response.data;
    }).catch(error => {
      console.log(error)
    })

  }
}
</script>

<style scoped>
.exercises {
  display: flex;
  margin: auto;
  width: 100%;
  justify-content: center;
  gap: 20px;
  padding: 20px
}
</style>