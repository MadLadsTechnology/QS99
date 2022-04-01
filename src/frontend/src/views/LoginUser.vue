<template>
  <div>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Log in</h1>

      <BaseInput
        label="Email"
        type="email"
        v-model.lazy="email"
        :error="errors.email"
      />
      <BaseInput
        label="Password"
        type="password"
        v-model="password"
        :error="errors.password"
      />
      <button :disabled="!isValid" type="submit">Log in</button>

      <br />
      <router-link to="register">Dont have a user? Register here!</router-link>

      <p v-if="error">{{ errors }}</p>
    </form>
  </div>
</template>
<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";

export default {
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
          this.error = err;
        });
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      email: string().email("Invalid email format").required(),
      password: string().required(),
    });
    const { errors } = useForm({
      validationSchema,
    });

    const { value: email } = useField("email");
    const { value: password } = useField("password");

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

<style>
.loginForm {
  width: 70%;
  min-width: 300px;
  max-width: 600px;
  margin: auto;
}
</style>
