<template>
  <div :class="getClass(exercise.approved)" class="box" @click="toggleApprove">
    {{ this.exercise.exerciseNumber }}
  </div>

</template>
<script>

import axios from "axios";

export default {

  props: {
    subjectId: {
      required: true,
      type: Number,
    },
    exercise: {
      type: Object,
      required: true,
    },
    student: {
      required: true,
    },
  },
  methods: {

    toggleApprove() {

      let message = "Are you sure you want to approve exercise " + this.exercise.exerciseNumber + " for " + this.student.firstName + "?"

      if (!this.exercise.approved) {
        message = "Are you sure you remove the approval of exercise " + this.exercise.exerciseNumber + " for " + this.student.firstName + "?"
      }
      if (confirm(message)) {
        axios.post("http://localhost:8001/exercise/approveExercise", null, {
          params: {
            subjectId: this.subjectId,
            exerciseNumber: this.exercise.exerciseNumber,
            studentEmail: this.student.emailAddress,
            isApproved: !this.exercise.approved,
          }
        }).then(location.reload())
      }
    },

    getClass(approved) {
      if (approved == true) {
        return "approved"
      }
      return "";
    }


  },


};
</script>

<style scoped>
.box {
  background-color: red;
  font-weight: bold;
  border-radius: 3px;
  width: 20px;
  height: 20px;
  text-align: center;
  border: solid black;
  color: white;
  padding: 5px

}

.approved {
  background-color: green;
}

.box:hover {
  cursor: pointer;
  background-color: #2c3e50;
}
</style>