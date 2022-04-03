<template>
  <div>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Register a new user</h1>

      <div class="nameInputs">
        <BaseInput
            v-model.lazy="lastname"
            :error="errors.lastname"
            label="Last name"
            type="lastname"
        />
        <BaseInput
            v-model.lazy="firstname"
            :error="errors.firstname"
            label="First name"
            type="text"
        />
      </div>

      <BaseInput
          v-model.lazy="email"
          :error="errors.email"
          label="Email"
          type="email"
      />

      <div v-if="this.$store.getters.isAdmin" class="radioButtons">
        <div>
          <input id="professor" v-model="this.userType" type="radio" value="Professor">
          <label for="professor">Professor</label>
        </div>
        <div>
          <input id="student" v-model="this.userType" checked type="radio" value="Student">
          <label for="student">Student</label>
        </div>
      </div>

      <button :disabled="!isValid" type="submit">Submit</button>

      <p v-if="error">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import {useField, useForm} from "vee-validate";
import {object, string} from "yup";
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
      console.log(this.userType);
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
            alert("User already exists")
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
    const {errors} = useForm({
      validationSchema,
    });

    const {value: lastname} = useField("lastname");
    const {value: firstname} = useField("firstname");
    const {value: email} = useField("email");

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
.nameInputs {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 20px;
}

.radioButtons {
  display: flex;
  gap: 50px;
  align-items: center;
}

.loginForm {
  width: 70%;
  min-width: 300px;
  max-width: 600px;
  margin: auto;
}
</style>
