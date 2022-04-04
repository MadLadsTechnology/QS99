<template>
  <button v-if="this.$store.getters.isStudent && !subject.isStudAss && !inQueue"
          @click="this.$router.push('JoinQueue')">
    Join queue
  </button>


  <button v-if="!this.$store.getters.isStudent" @click="toggleQueue">
    <text v-if="subject.queueActive">
      Deactivate queue
    </text>
    <text v-else>
      Activate queue
    </text>
  </button>

  <table v-if="!!queue && queue.length > 0 && subject.queueActive">

    <tr>
      <th>#</th>
      <th colspan="2">User</th>
      <th>Task(s)</th>
      <th>Type</th>
      <th>Room</th>
    </tr>
    <tr v-for="(entry, index) in queue" :key="entry.lastname" :class="getClass(entry)">
      <td>{{ index + 1 }}</td>
      <td>{{ entry.lastName }}</td>
      <td>{{ entry.firstName }}</td>
      <td>
        <text v-for="(assignment, index) in entry.exercises" v-bind:key="assignment">
          <text v-if="index < entry.exercises.length">{{ assignment }},</text>
          <text v-else>{{ assignment }}</text>
        </text>
      </td>
      <td v-if="subject.isStudAss">
        <button @click="helpAndApprove(entry)">{{ entry.type }}</button>
      </td>
      <td v-else>
        {{ entry.type }}
      </td>
      <td>{{ entry.tableNumber }}</td>
    </tr>
  </table>
  <div v-else>
    <text v-if="this.subject.queueActive">
      Queue is empty
    </text>
    <text v-else>
      Queue is not active
    </text>

  </div>
</template>
<script>
import axios from "axios";

export default {
  props: ["subject"],

  async created() {
    await axios
        .get("/queue", {
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


    toggleQueue() {
      let toggle = true;
      if (this.subject.queueActive) {
        toggle = false;
      }

      axios.post("/queue/setQueueStatus", null, {
        params: {
          isActive: toggle,
          subjectId: this.subject.id
        }
      }).then(() => {
        location.reload();
      }).catch(error => {
        alert(error)
      })

    },
    helpAndApprove(entry) {
      axios.post("/entry/setIsGettingHelp", null, {
        params: {
          entryId: entry.entryId,
          isGettingHelp: true,
        }
      })
      this.$router.push({
        name: 'helpAndApprove',
        params: {
          studentId: entry.studentId,
          subjectId: this.subject.id,
          entryId: entry.entryId
        }
      })

    },

    getClass(entry) {
      if (entry.isGettingHelp == true) {
        return "gettingHelp"
      } else if (entry.studentId === this.$store.state.user.emailAddress) {
        return "thisUser"
      }
      return "";
    }

  },

  computed: {},

  data() {
    return {
      queue: null,
      inQueue: false,
    };
  },
};
</script>

<style>
.gettingHelp {
  background-color: lightgreen;
}

.thisUser {
  background-color: lightgray;
}

table {
  width: 90%;
  margin: auto;
  max-width: 800px;
  border-collapse: collapse;

}

th,
td {
  border: 1px solid #999;
  padding: 0.5rem;
  text-align: left;

}

button {
  margin: 20px;
  padding: 10px;
}

</style>
