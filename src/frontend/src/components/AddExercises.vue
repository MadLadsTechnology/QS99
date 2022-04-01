<template>
  <div class="window">
    <button @click="closeWindow()">Close</button>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Add exercises to {{ subject.code }}</h1>

      <BaseInput
        label="Number of exercises"
        type="number"
        v-model.lazy="count"
        :error="errors.email"
      />
      <BaseInput
        label="Number of mandatory"
        type="number"
        v-model.lazy="mandatory"
        :error="errors.email"
      />
      <button :disabled="!isValid" type="submit">Submit</button>
    </form>
  </div>
</template>
<script>
import { useField, useForm } from "vee-validate";
import { object, string, number } from "yup";
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
    };
  },

  created() {
    document.title = "QS99 - Login";
  },

  methods: {
    //Method for submitting form
    submit() {
      axios
        .post("http://localhost:8001/subject/addUser", null, {
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
      count: number().min(1).required(),
      mandatory: number().max(count.value).required(),
    });
    const { errors } = useForm({
      validationSchema,
    });

    const { value: count } = useField("count");
    const { value: mandatory } = useField("mandatory");

    return {
      count,
      mandatory,
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
