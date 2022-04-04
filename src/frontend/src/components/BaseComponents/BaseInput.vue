<template>
  <div class="container">
    <label v-if="label" :for="uuid">
      {{ label }}
    </label>
    <input
        :id="uuid"
        :aria-describedby="error ? `${uuid}-error` : null"
        :aria-invalid="error ? true : false"
        :class="{ error }"
        :placeholder="label"
        :value="modelValue"
        class="field"
        v-bind="$attrs"
        @input="$emit('update:modelValue', $event.target.value)"
    />
    <BaseErrorMessage :id="`${uuid}-error`">
      {{ error }}
    </BaseErrorMessage>
  </div>
</template>

<script>
//import SetupFormComponent from "../features/SetupFormComponent";
import UniqueID from "../../features/UniqueID";
import BaseErrorMessage from "@/components/BaseComponents/BaseErrorMessage";

export default {

  components: {
    BaseErrorMessage,
  },
  props: {
    label: {
      type: String,
      default: "",
    },
    error: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
  },
  setup() {
    const uuid = UniqueID().getID();
    return {
      uuid,
    };
  },
};
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column;
  margin: 0;
  width: 100%;
}

label {
  text-align: left;
  margin: 0;
  font-size: 200%;
  width: 100%;
}

input {
  padding: 10px;
  font-size: 150%;
  margin: 0;
  width: 100%;
}

input:invalid {
  border-color: red;
}
</style>
