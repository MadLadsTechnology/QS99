import { createRouter, createWebHashHistory } from "vue-router";
import Subjects from "../views/SubjectList.vue";
import SubjectLayout from "../views/subject/LayoutView.vue";
import SubjectDetails from "../views/subject/DetailsView.vue";
import SubjectQueue from "../views/subject/QueueView.vue";
import SubjectAssignments from "../views/subject/AssignmentsView.vue";

import RegisterUser from "../views/RegisterUser.vue";
import LoginUser from "../views/LoginUser.vue";

import AdminDashboard from "../views/admin/DashboardView.vue";
import AllStudents from "../views/admin/AllStudents";
import AllSubjects from "../views/admin/AllSubjects";

const routes = [
  {
    path: "/",
    redirect: "/subjects",
  },
  {
    path: "/subjects",
    name: "subjects",
    component: Subjects,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/subjects/:id",
    name: "SubjectLayout",
    props: true,
    component: SubjectLayout,
    meta: {
      requiresAuth: true,
    },
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
    path: "/admin",
    name: "admin",
    props: true,
    component: AdminDashboard,
    meta: {
      requiresAuth: true,
    },
    children: [
      {
        path: "students",
        name: "students",
        component: AllStudents,
      },
      {
        path: "allsubjects",
        name: "allsubjects",
        component: AllSubjects,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});
import store from "../store";

router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/register"];
  const publicPage = publicPages.includes(to.path);
  const adminPages = ["/admin"];
  const adminPage = adminPages.includes(to.path);
  const userPages = ["/subjects"];
  const userPage = userPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");
  const isAdmin = store.getters.role === "ADMIN";

  if ((adminPage && !loggedIn) || (userPage && !loggedIn)) {
    return next("/login");
  }

  if (publicPage && loggedIn && !isAdmin) {
    return next("/subjects");
  }

  if (adminPage && !isAdmin && loggedIn) {
    return next("/subjects");
  }
  if (
    (userPage && isAdmin && loggedIn) ||
    (publicPage && isAdmin && loggedIn)
  ) {
    return next("/admin");
  }

  return next();
});

export default router;
