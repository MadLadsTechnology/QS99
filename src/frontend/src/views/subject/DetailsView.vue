<template>
  <h1>{{ subject.subjectName }}</h1>

  <h3>{{ subject.subjectDescription }}</h3>
</template>
<script>
export default {
  props: ["subject"],
};
</script>
<style></style>
