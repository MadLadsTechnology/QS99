import { createRouter, createWebHashHistory } from "vue-router";
import Subjects from "../views/SubjectList.vue";
import SubjectLayout from "../views/subject/LayoutView.vue";
import SubjectDetails from "../views/subject/DetailsView.vue";
import SubjectQueue from "../views/subject/QueueView.vue";
import SubjectAssignments from "../views/subject/AssignmentsView.vue";

import RegisterUser from "../views/RegisterUser.vue";
import LoginUser from "../views/LoginUser.vue";

const routes = [
  {
    path: "/",
    name: "subjects",
    component: Subjects,
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
        path: "queue",
        name: "SubjectQueue",
        component: SubjectQueue,
      },
      {
        path: "details",
        name: "SubjectDetails",
        component: SubjectDetails,
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
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  if (authRequired && !loggedIn) {
    return next("/login");
  } else if (loggedIn && !authRequired) {
    return next("/");
  }
  next();
});

export default router;
