<template>
  <div>
    <button>Øvinger</button> <button>kø</button>

    v-f
    <div class="container">
      <h3>{{ subjectCode }}</h3>
      {{ subjectName }}
    </div>
  </div>
</template>

<script>
export default {
  props: {
    subjectCode: String,
    subjectName: String,
    link: String,
  },
};
</script>

<style scoped>
.wrapper {
  width: 200px;
  height: 200px;
}

a {
  text-decoration: none;
}

h1 {
  color: #f6f7eb;
}
h3 {
}
</style>
