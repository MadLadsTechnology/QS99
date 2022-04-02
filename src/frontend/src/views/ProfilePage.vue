<template>

  <h1>Hello {{ user.firstName }}  {{user.lastName}} </h1>

  <h3>Email:{{user.emailAddress}}</h3>

    <form class="loginForm" @submit.prevent="submit()">
      <h3>Change your password</h3>
      <div class="passwordInputs">
        <BaseInput
            label="Old password"
            type="password"
            v-model.lazy="oldPassword"
            :error="errors.lastname"
        />
        <BaseInput
            label="New password"
            type="password"
            v-model.lazy="newPassword"
            :error="errors.firstname"
        />

        <BaseInput
            label="Confirm new password"
            type="password"
            v-model.lazy="confirmPassword"
            :error="errors.email"
        />
        </div>

      <button :disabled="!isValid" type="submit">Submit</button>

    </form>

</template>

<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";
import axios from "axios";

export default {
  data() {
    return {
      error: null,
      user: this.$store.state.user,
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
          });
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      oldPassword: string().required(),
      newPassword: string().required(),
      confirmPassword: string().required(),
    });
    const { errors } = useForm({
      validationSchema,
    });

    const { value: oldPassword } = useField("oldPassword");
    const { value: newPassword } = useField("newPassword");
    const { value: confirmPassword } = useField("confirmPassword");

    return {
      oldPassword,
      newPassword,
      confirmPassword,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.oldPassword || this.errors.newPassword || this.errors.confirmPassword) {
        return false;
      } else {
        return this.oldPassword && this.newPassword && this.confirmPassword;
      }
    },
  },
};
</script>

<style>
.passwordInputs {
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
