<template>
  <div class="window">
    <button @click="closeWindow()">Close</button>
    <form class="loginForm" @submit.prevent="submit()">
      <h1>Add exercises to {{ subject.code }}</h1>

      <BaseInput
          v-model.lazy="count"
          :error="errors.count"
          label="Number of exercises"
          type="number"
      />
      <BaseInput
          v-model.lazy="mandatory"
          :error="errors.mandatory"
          label="Number of mandatory"
          type="number"
      />
      <button :disabled="!isValid" type="submit">Submit</button>
    </form>
  </div>
</template>
<script>
import {useField, useForm} from "vee-validate";
import {number, object} from "yup";
import axios from "axios";
import BaseInput from "@/components/BaseComponents/BaseInput";

export default {
  props: {
    subject: {
      type: Object,
      required: true,
    },
  },
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
          .post("/exercise", null, {
            params: {
              subjectId: this.subject.id,
              numberOfExercises: this.count,
              numberOfMandatory: this.mandatory,
            },
          })
          .then(() => {
            this.closeWindow();
          })
          .catch((err) => {
            alert(err);
          });
    },

    closeWindow() {
      this.$emit("closeWindow");
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      count: number().min(1).required(),
      mandatory: number().min(1).required(),
    });
    const {errors} = useForm({
      validationSchema,
    });

    const {value: count} = useField("count");
    const {value: mandatory} = useField("mandatory");

    return {
      count,
      mandatory,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.count || this.errors.mandatory) {
        return false;
      } else {
        return this.count && this.mandatory;
      }
    },
  },
};
</script>

<style scoped>
.window {
  position: absolute;
  background-color: antiquewhite;
  border: solid;
  border-radius: 3px;
  padding: 50px;
  top: 123px;
}
</style>
