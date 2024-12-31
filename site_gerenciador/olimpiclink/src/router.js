import { createRouter, createWebHistory } from "vue-router";
import Home from './views/Home.vue'
import UsersView from './views/UsersView.vue';
import UserProfileView from './views/UserProfileView.vue';
import ReportPublicationsPage from './views/ReportPublicationsPage.vue';
import ReportedEventsPage from "./views/ReportedEventsPage.vue";
import VerificationLocalizationPage from "./views/VerificationLocalizationPage.vue";


const routes = [
    { path: '/', component: Home },
    { path: '/UsersList', component: UsersView},
    { path: '/UserProfile', component: UserProfileView},
    { path: '/Reportations/Publications', component: ReportPublicationsPage},
    { path: '/Reportations/Events', component: ReportedEventsPage},
    { path: '/Verifications/Localizations', component: VerificationLocalizationPage}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;