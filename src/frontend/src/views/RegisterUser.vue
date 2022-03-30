<template>
  <div>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Register</h1>

      <BaseInput
        label="Last name"
        type="lastname"
        v-model.lazy="lastname"
        :error="errors.email"
      />
      <BaseInput
        label="First name"
        type="text"
        v-model.lazy="firstname"
        :error="errors.firstname"
      />
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

      <button :disabled="!isValid" type="submit">Submit</button>

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

  created() {
    document.title = "QS99 - Login";
  },

  methods: {
    //Method for submitting form
    submit() {
      this.$store
        .dispatch("register", {
          lastname: this.lastname,
          firstname: this.firstname,
          email: this.email,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/subjects");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      lastname: string().required(),
      firstname: string().required(),
      email: string().email("Invalid email format").required(),
      password: string().required(),
    });
    const { errors } = useForm({
      validationSchema,
    });

    const { value: lastname } = useField("lastname");
    const { value: firstname } = useField("firstname");
    const { value: email } = useField("email");
    const { value: password } = useField("password");

    return {
      lastname,
      firstname,
      email,
      password,
      errors,
    };
  },
  computed: {
    isValid() {
      if (
        this.errors.email ||
        this.errors.password ||
        this.errors.lastname ||
        this.errors.firstname
      ) {
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
