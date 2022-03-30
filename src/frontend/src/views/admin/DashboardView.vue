<template>
  Admin page
  <div id="nav">
    <router-link :to="{ name: 'students' }">Students</router-link>
    |
    <router-link :to="{ name: 'subjects' }">Subjects</router-link>
  </div>
  <router-view :subject="subject" />
</template>
