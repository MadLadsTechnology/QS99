<template>
  <div>
    <form v-if="!!assignments" class="form" @submit.prevent="submit()">
      <h1>Get in the queue!</h1>

      <BaseInput
          v-model.lazy="room"
          :error="errors.room"
          label="Room"
          type="text"
      />
      <BaseInput
          v-model.lazy="building"
          :error="errors.building"
          label="Building"
          type="text"
      />
      <BaseInput
          v-model.lazy="tableNumber"
          :error="errors.tableNumber"
          label="Table number"
          type="number"
      />

      <div class="checkBoxHolder">
        <div v-for="(sublist, index) in assignments" v-bind:key="index">

          <div v-for="(exercise, index) in sublist.exercises" :key="parseInt(index)">
            <input
                :id="index"
                v-model="exercises"
                :disabled="exercise"
                :value="parseInt(index)"
                type="checkbox"
            />
            <label :for="index">{{ index }}</label>

          </div>

        </div>
      </div>

      <input
          id="one"
          v-model="helpType"
          checked="checked"
          type="radio"
          value="help"
      />
      <label for="one">Help</label>
      <br/>
      <input id="two" v-model="helpType" type="radio" value="approval"/>
      <label for="two">Approval</label>

      <br/>
      <button :disabled="!isValid" type="submit">Submit</button>

      <p v-if="error">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import {useField, useForm} from "vee-validate";
import {number, object, string} from "yup";
import axios from "axios";
import BaseInput from "@/components/BaseComponents/BaseInput";

export default {
  props: ["subject"],
  components: {
    BaseInput,
  },
  data() {
    return {
      error: null,
      helpType: "help",
      assignments: null,
      exercises: [],
    };
  },

  created() {
    axios
        .get("/exercise/getByUser", {
          params: {
            subjectId: parseInt(this.subject.id),
          },
        })
        .then((response) => {
          this.assignments = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    document.title = "QS99 - Join queue";
  },

  methods: {
    //Method for submitting form
    submit() {
      console.log(this.exercises);
      axios
          .post(
              "/queue/addEntry",
              {exercises: this.exercises},
              {
                params: {
                  room: this.room,
                  building: this.building,
                  tableNumber: this.tableNumber,
                  type: this.helpType,
                  subjectId: this.subject.id,
                },
              }
          )
          .then(() => {
            this.$router.push("/subjects/" + this.subject.id + "/queue");
            console.log(this.exercises);
          })

          .catch((err) => {
            console.log(err);
          });
    },
  },

  setup() {
    //Setting up form validation
    const validationSchema = object({
      room: string().required(),
      building: string().required(),
      tableNumber: number().required(),
    });
    const {errors} = useForm({
      validationSchema,
    });

    const {value: room} = useField("room");
    const {value: building} = useField("building");
    const {value: tableNumber} = useField("tableNumber");

    return {
      room,
      building,
      tableNumber,
      errors,
    };
  },
  computed: {
    isValid() {
      if (this.errors.room || this.errors.building || this.errors.tableNumber) {
        return false;
      } else {
        return this.room && this.building && this.tableNumber;
      }
    },
  },
};
</script>

<style scoped src="@/styles/views/subject/Queue/JoinQueue.css"/>

