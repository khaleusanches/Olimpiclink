import { createMemoryHistory, createRouter } from 'vue-router'
import Home from './views/Home.vue'
import UsersView from './views/UsersView.vue';
import UserProfileView from './views/UserProfileView.vue';


const routes = [
    { path: '/', component: Home },
    { path: '/UsersList', component: UsersView},
    {path: '/UserProfile', component: UserProfileView}
]

const router = createRouter({
  history: createMemoryHistory(),
  routes,
})

export default router;