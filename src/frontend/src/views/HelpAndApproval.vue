<template>


</template>

<script>
import axios from "axios";

export default {
  name: "HelpAndApprove",
  props:["subjectId", "studentId"],


  async create(){

    axios.get("http://localhost:8001/user/getUserFromSubject", {
      params: {
        email: this.studentId,
        subjectId: this.subjectId,
      }

    })

  }
}
</script>