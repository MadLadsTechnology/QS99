<template>
  <div></div>
</template>
<script>
export default {
  data() {
    return {
      name: "",
      email: "",
      password: "",
    };
  },

  methods: {
    register() {
      this.$store
        .dispatch("register", {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password,
        })
        .then(() => {
          this.$router.push({ name: "HomeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
