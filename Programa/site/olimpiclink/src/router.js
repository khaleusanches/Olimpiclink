import { createRouter, createWebHistory } from "vue-router";
import Home from './views/Home.vue'
import UsersView from './views/UsersView.vue';
import UserProfileView from './views/UserProfileView.vue';
import ReportPublicationsPage from './views/ReportPublicationsPage.vue';


const routes = [
    { path: '/', component: Home },
    { path: '/UsersList', component: UsersView},
    { path: '/UserProfile', component: UserProfileView},
    { path: '/Reportations/Publications', component: ReportPublicationsPage}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;