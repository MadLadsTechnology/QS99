<template>
  <div>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Register a new user</h1>

      <BaseInput
        label="Last name"
        type="lastname"
        v-model.lazy="lastname"
        :error="errors.lastname"
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

      <div v-if="this.$store.getters.isAdmin">
        <input
          type="radio"
          id="one"
          value="Professor"
          checked="checked"
          v-model="userType"
        />
        <label for="one">Professor</label>
        <br />
        <input type="radio" id="two" value="Student" v-model="userType" />
        <label for="two">Student</label>
      </div>

      <button :disabled="!isValid" type="submit">Submit</button>

      <p v-if="error">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";
import axios from "axios";

export default {
  data() {
    return {
      error: null,
      userType: "",
    };
  },

  created() {
    document.title = "QS99 - Login";
  },

  methods: {
    //Method for submitting form
    submit() {
      let url = "http://localhost:8001/user/registerStudent";
      if (this.userType === "Professor") {
        url = "http://localhost:8001/user/registerProfessor";
      }
      axios
        .post(url, null, {
          params: {
            lastname: this.lastname,
            firstname: this.firstname,
            email: this.email,
          },
        })
        .then(() => {
          this.$router.push("/admin/users");
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
    });
    const { errors } = useForm({
      validationSchema,
    });

    const { value: lastname } = useField("lastname");
    const { value: firstname } = useField("firstname");
    const { value: email } = useField("email");

    return {
      lastname,
      firstname,
      email,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.email || this.errors.lastname || this.errors.firstname) {
        return false;
      } else {
        return this.email && this.lastname && this.firstname;
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
