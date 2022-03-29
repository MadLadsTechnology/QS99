<template>
  <div>
    <router-link :to="link">
      <div class="container">
        <h3>{{ subjectCode }}</h3>
        {{ subjectName }}

        <button>Øvinger</button> <button>kø</button>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    subject: {
      subjectCode: String,
      subjectName: String,
      link: String,
    },
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

.container {
  padding: 30px;
  display: flex;
  flex-direction: column;
  gap: 0;
  border-radius: 20px;
  box-shadow: 5px 10px darkgray;
  border: solid;
  width: 200px;
  height: 100px;
}
h1 {
  color: #f6f7eb;
}
h3 {
}
</style>
