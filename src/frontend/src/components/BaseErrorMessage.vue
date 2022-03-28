<template>
  <p aria-live="assertive" class="errorMessage" :id="id">
    <slot />
  </p>
</template>

<script>
export default {
  props: {
    id: {
      type: [String, Number],
      required: true,
    },
  },
};
</script>
<style scoped>
p {
  margin-top: 0;
  min-height: 20px;
  max-height: 20px;
  text-align: left;
  color: red;
  padding: 5px;
}
</style>
