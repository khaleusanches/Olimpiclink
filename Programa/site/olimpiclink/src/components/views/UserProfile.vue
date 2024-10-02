<template>
    <div class="profile">
        <div class="background">

        </div>
        <div class="ProfileDados">
            <img src="" alt="">
            <h2>{{ user[0].name_user }}</h2>
            <h5>@{{user[0].login_user}}</h5>
            <p>Descricao</p>
        </div>
        <hr>
        <h5>Publications</h5>
    </div>
</template>

<script>
import api from '@/services/api';

    export default {
        name: 'UserProfile',
        data(){
            return{
                login_user: localStorage.getItem('login_user'),
                user: [{'name_user': "teste"}],
                name_user: ''
            }
        },
        mounted(){
            api.get("/api/users/" + this.login_user).then(response => {
                this.user = response.data;
                this.name_user = this.user[0].name_user
                console.log(this.user[0].name_user)
            })
        }
    }
</script>

<style>
.profile{
    width: 60%;
    margin: auto;
    margin-top: 30px;
}
.background{
    background-color: rgb(224, 72, 2);
    width: 100%;
    height: 150px;
}
</style>