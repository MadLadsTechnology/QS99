<template>
  <div>
    <form class="form" @submit.prevent="submit()">
      <h1>Create a subject</h1>

      <BaseInput
          v-model.lazy="code"
          :error="errors.email"
          label="Subject code"
          type="text"
      />
      <BaseInput
          v-model.lazy="name"
          :error="errors.firstname"
          label="Subject name"
          type="text"
      />
      <BaseInput
          v-model.lazy="description"
          :error="errors.email"
          label="Description"
          type="text"
      />
      <BaseInput
          v-model="year"
          :error="errors.password"
          label="Year of lecturing"
          type="text"
      />

      <button :disabled="!isValid" type="submit">Submit</button>

    </form>
  </div>
</template>

<script>
import {useField, useForm} from "vee-validate";
import {object, string} from "yup";
import axios from "axios";
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
    submit() {
      axios
          .post("/subject/create", null, {
            params: {
              subjectName: this.name,
              subjectCode: this.code,
              subjectDescription: this.description,
              year: this.year,
            },
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
      name: string().required(),
      code: string().required(),
      description: string().required(),
      year: string().required(),
    });
    const {errors} = useForm({
      validationSchema,
    });

    const {value: name} = useField("name");
    const {value: code} = useField("code");
    const {value: description} = useField("description");
    const {value: year} = useField("year");

    return {
      name,
      code,
      description,
      year,
      errors,
    };
  },
  computed: {
    isValid() {
      if (
          this.errors.name ||
          this.errors.code ||
          this.errors.description ||
          this.errors.year
      ) {
        return false;
      } else {
        return this.name && this.code && this.description && this.year;
      }
    },
  },
};
</script>

<style scoped src="@/styles/views/professor/CreateSubject.css"/>

