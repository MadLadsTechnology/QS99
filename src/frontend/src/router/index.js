import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import SubjectLayout from "../views/subject/LayoutView.vue";
import SubjectDetails from "../views/subject/DetailsView.vue";
import SubjectQueue from "../views/subject/QueueView.vue";
import SubjectAssignments from "../views/subject/AssignmentsView.vue";

import RegisterUser from "../views/RegisterUser.vue";
import LoginUser from "../views/LoginUser.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/login",
    name: "login",
    component: LoginUser,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterUser,
  },

  {
    path: "/subject/:id",
    name: "SubjectLayout",
    props: true,
    component: SubjectLayout,
    children: [
      {
        path: "",
        name: "SubjectDetails",
        component: SubjectDetails,
      },
      {
        path: "register",
        name: "SubjectQueue",
        component: SubjectQueue,
      },
      {
        path: "assignments",
        name: "SubjectAssignments",
        component: SubjectAssignments,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem("user");

  if (to.matched.some((record) => record.meta.requiresAuth) && !loggedIn) {
    next("/");
  } else {
    next();
  }
});

export default router;
