<template>
    <div class="profile" v-if="user.length">
        <div class="ProfileDados">
            <img src="" alt="">
            <h2>{{ user[0].name_user }}</h2>
            <h5>@{{user[0].login_user}}</h5>
            <p>Descricao</p>
        </div>

        <h3>Publications</h3>
        <h3></h3>
        <PublicationUserTable class="PublicationUserTable" :user_id="user[0].id_user"></PublicationUserTable>
    </div>
</template>

<script>
import api from '@/services/api';
import PublicationUserTable from '../Tables/PublicationUserTable.vue';

    export default {
        name: 'UserProfile',
        components: {PublicationUserTable},
        data(){
            return{
                login_user: localStorage.getItem('login_user'),
                user: [],
                name_user: '',
                id_user: 0 
            }
        },
        mounted(){
            api.get("/api/users/" + this.login_user).then(response => {
            this.user = response.data;
            this.name_user = this.user[0].name_user;
            this.id_user = this.user[0].id_user;
            console.log(this.user[0].name_user);
            console.log(this.user[0].id_user);
        }).catch(error => {
            console.error("Error fetching user:", error);
        });
        }
    }
</script>

<style>
.profile{
    width: 60%;
    margin-left: 50px;
    margin-top: 30px;
    background-color: white;
    padding-right: 50px;
    padding-left: 50px;
    border-radius: 8px;
}
.background{
    background-color: rgb(224, 72, 2);
    width: 100%;
    height: 150px;
}
.PublicationUserTable{
    width: 100%;
}
.testeee{
    display: none;
}

</style>