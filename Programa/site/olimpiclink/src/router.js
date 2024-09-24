import { createMemoryHistory, createRouter } from 'vue-router'
import Home from './views/Home.vue'
import UsersView from './views/UsersView.vue';


const routes = [
    { path: '/', component: Home },
    { path: '/UsersList', component: UsersView}
]

const router = createRouter({
  history: createMemoryHistory(),
  routes,
})

export default router;