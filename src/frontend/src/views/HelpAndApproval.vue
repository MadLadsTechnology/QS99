<template>
{{object}}
</template>

<script>
import axios from "axios";

export default {
  name: "HelpAndApprove",
  props:["subjectId", "studentId"],

  data(){
    return{
      object:null,
    }
  },


  async created(){
    axios.get("http://localhost:8001/user/getUserFromSubject", {
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