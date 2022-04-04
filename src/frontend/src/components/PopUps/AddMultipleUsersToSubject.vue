<template>
  <div class="background">
    <div class="window">
      <button @click="closeWindow()">Close</button>
      <h3>Add multiple users to {{ subject.code }}</h3>

      <h5>Send a list on the form "lastname firstname email" separated by new lines</h5>
      <h5>Existing users will be added, if the user doesnt exist it will be created as a student and added.</h5>

      <textarea
          v-model="users"
          placeholder="Paste emails separated by new lines"
      >
      </textarea><br>
      <button :disabled="users == null" @click="submit">Submit</button>
    </div>
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
          .post(
              "/subject/addUsers",
              {data: this.users},
              {
                params: {
                  subjectId: this.subject.id,
                },
              }
          )
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

<style scoped src="@/styles/PopUp.css"/>

