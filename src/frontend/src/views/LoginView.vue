<template>
  <form class="loginForm" @submit.prevent="submit()">
    <h1>Log in</h1>

    <BaseInput
        v-model.lazy="email"
        :error="errors.email"
        label="Email"
        type="email"
    />
    <BaseInput
        v-model="password"
        :error="errors.password"
        label="Password"
        type="password"
    />
    <button data-test="button" :disabled="!isValid" class="button" type="submit">Log in</button>

  </form>
</template>
<script>
import {useField, useForm} from "vee-validate";
import {object, string} from "yup";
import BaseInput from "@/components/BaseComponents/BaseInput";

export default {

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
    async submit() {
      await this.$store
          .dispatch("login", {
            email: this.email,
            password: this.password,
          })
          .then(() => {
            this.$router.push("/subjects");
          })
          .catch((err) => {
            alert(err);
          });
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      email: string().email("Invalid email format").required(),
      password: string().required(),
    });
    const {errors} = useForm({
      validationSchema,
    });

    const {value: email} = useField("email");
    const {value: password} = useField("password");

    return {
      email,
      password,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.email || this.errors.password) {
        return false;
      } else {
        return this.email && this.password;
      }
    },
  },
};
</script>

<style scoped src="@/styles/views/LoginView.css"/>
