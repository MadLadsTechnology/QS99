<template>
  <h1> Currently assisting {{ object.firstName }}, {{ object.lastName }}</h1>

  <h5>Click the exercise you want to approve</h5>

  <div class="exerciseHolder">
    <ExerciseBox v-for="exercise in object.exercises" :key="exercise" :exercise="exercise" :student="object"
                 :studentId="studentId" :subjectId="subjectId"/>
  </div>


  <div class="buttons">
    <button @click="suspend">Suspend</button>
    <button @click="done">Done</button>
  </div>


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

.exerciseHolder {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
}

.buttons {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
  gap: 20px;

  margin-top: 20px;
}
</style>