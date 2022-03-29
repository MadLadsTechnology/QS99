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

      <p v-if="error">{{ error }}</p>
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

  methods: {
    //Method for submitting form
    submit() {
      console.log(this.email);
      this.$store
        .dispatch("login", {
          email: this.email,
          password: this.password,
        })
        .then(() => {
          console.log("yep i live");
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
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
  width: 50%;
  margin: auto;
}
</style>
