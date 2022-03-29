<template>
  <div>
    <h1>Welcome to QS 99</h1>
    <form class="loginForm" @submit.prevent="submit">
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
      <button :disabled="!isValid" type="submit" @click="handleSubmit">
        Log in
      </button>
    </form>
  </div>
</template>

<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";
import { ref } from "vue";
import { useStore } from "vuex";

export default {
  data() {
    return {
      user: ["email", "password"],
    };
  },

  setup() {
    var submitted = ref(false);
    var sending = ref(false);

    const validationSchema = object({
      email: string().email("Invalid email format").required(),
      password: string().required(),
    });

    const { handleSubmit, errors } = useForm({
      validationSchema,
    });

    const { value: email } = useField("email");
    const { value: password } = useField("password");

    const store = useStore();
    var error = "";

    const submit = handleSubmit((values) => {
      console.log(values);

      store
        .dispatch("login", {
          email: values.email,
          password: values.password,
        })
        .then(() => {
          this.$router.push({ name: "subjects" });
        })
        .catch((err) => {
          error = err;
        });
    });

    return {
      email,
      password,
      submit,
      errors,
      error,
      submitted,
      sending,
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
