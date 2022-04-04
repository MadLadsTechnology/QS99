<template>
  <div class="window">
    <button @click="closeWindow()">Close</button>
    <form class="loginForm" @submit.prevent="submit()">
      <h3>Add user to {{ subject.code }}</h3>
      <h5>Note: Must be an existing student or professor</h5>

      <BaseInput
          v-model.lazy="email"
          :error="errors.email"
          label="Email"
          type="email"
      />
      <button :disabled="!isValid" type="submit">Submit</button>
    </form>
  </div>
</template>
<script>
import {useField, useForm} from "vee-validate";
import {object, string} from "yup";
import axios from "axios";
import BaseInput from "@/components/BaseComponents/BaseInput";

export default {
  props: {
    subject: {
      type: Object,
      required: true,
    },
  },
  components: {
    BaseInput,
  },
  data() {
    return {
      error: null,
    };
  },

  created() {
    document.title = "QS99 - Login";
  },

  methods: {
    //Method for submitting form
    submit() {
      axios
          .post("/subject/addUser", null, {
            params: {
              subjectId: this.subject.id,
              email: this.email,
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
      this.$emit("closeWindow");
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      email: string().email("Invalid email format").required(),
    });
    const {errors} = useForm({
      validationSchema,
    });

    const {value: email} = useField("email");

    return {
      email,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.email) {
        return false;
      } else {
        return this.email;
      }
    },
  },
};
</script>

<style scoped src="@/styles/PopUp.css"/>

