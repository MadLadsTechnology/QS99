<template>
  <div class="container">
    <h1>{{ subject.subjectName }}</h1>

    <h3>{{ subject.subjectDescription }}</h3>

  </div>

</template>
<script>
export default {
  props: ["subject"],
};
</script>
<style scoped>
.container {
  width: 80%;
  max-width: 600px;
}

</style>
