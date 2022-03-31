<template>
  <div class="window">
    <button @click="closeWindow()">Close</button>
    <h1>Add multiple users to {{ subject.code }}</h1>

    <textarea v-model="users" placeholder="Paste emails separated by new lines">
    </textarea>
    <button :disabled="users == null" type="submit">Submit</button>
  </div>
</template>
<script>
import axios from "axios";

export default {
  props: {
    subject: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      error: null,
      users: "",
    };
  },

  created() {
    document.title = "QS99 - Login";
  },

  methods: {
    //Method for submitting form
    submit() {
      axios
        .post("http://localhost:8001/subject/addStudent", this.users, {
          params: {
            subjectCode: this.subject.code,
            year: this.subject.year,
          },
        })
        .then(() => {
          this.closeWindow();
        })
        .catch((err) => {
          alert(err);
        });
    },

    closeWindow() {
      this.$emit("closeWindowMultipleUsers");
    },
  },

  computed: {},
};
</script>

<style>
.window {
  position: absolute;
  background-color: antiquewhite;
  border: solid;
  border-radius: 3px;
  padding: 50px;
  top: 123px;
}
</style>
