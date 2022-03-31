<template>
  <div id="nav">
    <router-link :to="{ name: 'users' }">Users</router-link>
    |
    <router-link :to="{ name: 'allSubjects' }">Subjects</router-link>
  </div>
  <router-view :subject="subject" />
</template>
