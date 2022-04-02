<template>
  <button v-if="this.$store.getters.isStudent && !subject.isStudAss" :disabled="inQueue" @click="this.$router.push('JoinQueue')">
    Join queue
  </button>

  <table v-if="!!queue">
    <tr>
      <th>Place</th>
      <th>Last name</th>
      <th>First name</th>
      <th>Assignment</th>
      <th>Type</th>
      <th>Table</th>
      <th v-if="!this.$store.getters.isStudent">Actions</th>
    </tr>
    <tr v-for="(entry, index) in queue" :key="entry.lastname" :class="getClass(entry.gettingHelp)" >
      <td>{{ index+1 }}</td>
      <td>{{ entry.lastName }}</td>
      <td>{{ entry.firstName }}</td>
      <td>
        <text v-for="assignment in entry.exercises" v-bind:key="assignment">
          {{ assignment }},
        </text>
      </td>
      <td  v-if="subject.isStudAss">
        <button @click="helpAndApprove(entry)">{{ entry.type }}</button>
      </td>
      <td v-else>
        {{ entry.type }}
      </td>
      <td>{{ entry.tableNumber }}</td>
    </tr>
  </table>
</template>
<script>
import axios from "axios";

export default {
  props: ["subject"],

  async created() {
    await axios
      .get("http://localhost:8001/queue", {
        params: {
          subjectId: this.subject.id,
        },
      })

      .then((response) => {
        console.log(response.data);
        this.queue = response.data;
        this.queue.some((element) => {
          if (element.studentId === this.$store.state.user.emailAddress) {
            this.inQueue = true;
          }
        });
      });
  },

  methods: {

    helpAndApprove(entry){
      axios.post("http://localhost:8001/entry/setIsGettingHelp", null, {params: {
        entryId: entry.entryId,
          isGettingHelp: true,
        }})
      this.$router.push({ name: 'helpAndApprove',
        params: {
          studentId: entry.studentId,
          subjectId: this.subject.id,
          entryId: entry.entryId
      }})

    },

    getClass(isGettingHelp) {
      if(isGettingHelp == true){
        return "gettingHelp"
      }
      return "";
    }

  },

  computed:{

  },

  data() {
    return {
      queue: null,
      inQueue: false,
    };
  },
};
</script>

<style>
.gettingHelp{
  background-color: lightgreen;
}
</style>
