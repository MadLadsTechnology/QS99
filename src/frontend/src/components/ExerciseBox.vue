<template>
  <div class="box" :style="{ backgroundColor: this.color }">
    {{this.exercise.exerciseNumber}}
  </div>

</template>

<script>

export default {
  props: {
    exercise: {
      type: Object,
      required: true,
    },
  },

  computed: {
    color: function(){
      if(this.exercise.approved === true){
        return "green";
      }
      return "red";
    }
  }
};
</script>

<style scoped>
.box{
  font-weight: bold;
  border-radius: 3px;
  width: 20px;
  height: 20px;
  text-align: center;
  border: solid black;
  color: white;
  padding: 5px

}
</style>