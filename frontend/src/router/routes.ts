import { RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    component: () => import("src/layouts/LoginLayout.vue"),
    children: [{ path: "", component: () => import("pages/IndexPage.vue") }],
  },
  {
    path: "/forum",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "topics", component: () => import("pages/TopicsPage.vue") },
      { path: "messages", component: () => import("pages/MessagesPage.vue") },
    ],
  },
  {
    path: "/forum/topics",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "mytopics", component: () => import("pages/MyTopicsPage.vue") },
      {
        path: "likedtopics",
        component: () => import("pages/LikedTopicsPage.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
