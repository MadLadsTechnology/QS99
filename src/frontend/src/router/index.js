import { createRouter, createWebHashHistory } from "vue-router";
import Subjects from "../views/SubjectList.vue";
import SubjectLayout from "../views/subject/SubjectView.vue";
import SubjectDetails from "../views/subject/DetailsView.vue";
import SubjectQueue from "../views/subject/QueueView.vue";
import SubjectAssignments from "../views/subject/AssignmentsView.vue";
import SubjectUsersView from "../views/subject/SubjectUsersView";
import JoinQueue from "../views/subject/JoinQueue.vue";

import RegisterUser from "../views/admin/RegisterUser.vue";
import LoginUser from "../views/LoginUser.vue";
import Profile from "../views/ProfilePage.vue";


import AdminDashboard from "../views/admin/DashboardView.vue";
import AllUser from "../views/admin/AllUser";
import AllSubjects from "../views/admin/AllSubjects";
import CreateSubject from "../views/admin/CreateSubject";

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
      {
        path: "subjectUsers",
        name: "subjectUsers",
        component: SubjectUsersView,
      },
      {
        path: "joinQueue",
        name: "joinQueue",
        component: JoinQueue,
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: LoginUser,
  },
  {
    path: "/profile",
    name: "profile",
    component: Profile,
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
        path: "users",
        name: "users",
        component: AllUser,
      },
      {
        path: "subjects",
        name: "allSubjects",
        component: AllSubjects,
      },
    ],
  },
  {
    path: "/createSubject",
    name: "createSubject",
    component: CreateSubject,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});
import store from "../store";

router.beforeEach((to, from, next) => {
  const publicPages = ["/login"];
  const publicPage = publicPages.includes(to.path);
  const adminPages = ["/admin", "/register"];
  const adminPage = adminPages.includes(to.path);
  const userPages = ["/subjects"];
  const userPage = userPages.includes(to.path);
  const loggedIn = store.state.user;
  let isAdmin = false;
  if (loggedIn) {
    isAdmin = store.getters.isAdmin;
  }
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (!loggedIn) {
      return next({ name: "login" });
    } else if (publicPage && loggedIn && isAdmin) {
      return next("/subjects");
    } else if (adminPage && !isAdmin && loggedIn) {
      return next("/subjects");
    } else if (
      (userPage && isAdmin && loggedIn) ||
      (publicPage && isAdmin && loggedIn)
    ) {
      return next("/admin");
    } else {
      return next();
    }
  } else {
    return next();
  }
});

export default router;
