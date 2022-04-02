<template>
  <div class="container">
    <label v-if="label" :for="uuid">
      {{ label }}
    </label>
    <input
      class="field"
      v-bind="$attrs"
      @input="$emit('update:modelValue', $event.target.value)"
      :id="uuid"
      :value="modelValue"
      :placeholder="label"
      :aria-describedby="error ? `${uuid}-error` : null"
      :aria-invalid="error ? true : false"
      :class="{ error }"
    />
    <BaseErrorMessage :id="`${uuid}-error`">
      {{ error }}
    </BaseErrorMessage>
  </div>
</template>

<script>
//import SetupFormComponent from "../features/SetupFormComponent";
import UniqueID from "../features/UniqueID";

export default {
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
  width: 100%;
}
label {
  text-align: left;
  font-size: 100%;
}
input {
  padding: 10px;
}
input:invalid {
  border-color: red;
}
</style>
