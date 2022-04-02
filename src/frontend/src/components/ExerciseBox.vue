<template>
  <div @click="approve" class="box" :class="getClass(exercise.approved)">
    {{this.exercise.exerciseNumber}}
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
    studentId: {
      required: true,
    },
},
  methods:{

    approve(){
      console.log( this.subjectId);
      console.log(this.exercise.exerciseNumber);
      console.log( this.studentId);


      axios.post("http://localhost:8001/exercise/approveExercise", null, {
        params: {
          subjectId: this.subjectId,
          exerciseNumber: this.exercise.exerciseNumber,
          studentEmail: this.studentId,
        }}).then(location.reload())


    },

    getClass(approved){
      if(approved == true){
        return "approved"
      }
      return "";
    }


  },


};
</script>

<style scoped>
.box{
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

.approved{
  background-color: green;
}
.box:hover{
  cursor: pointer;
  background-color: #2c3e50;
}
</style>