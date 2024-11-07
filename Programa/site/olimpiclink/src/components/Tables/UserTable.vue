<template>
    <table id="table">
        <tr id="tr">
        <th class="th">id_user</th>
        <th class="th">activated_user</th>
        <th class="th">name_user</th>
        <th class="th">login_user</th>
        <th class="th">email_user</th>
        <th class="th">created_at_user</th>
        <th class="th">updated_at_user</th>    
        <th class="th">url_profile_picture_user</th>
        </tr>
        <tr v-for="(user, index) in users" :key="index" @click="teste(user.login_user)">
            <td class="th">{{user.id_user}}</td>
            <td class="th">{{user.activated_user}}</td>
            <td class="th">{{user.name_user}}</td>
            <td class="th">{{user.login_user}}</td>
            <td class="th">{{user.email_user}}</td>
            <td class="th">{{user.created_at_user}}</td>
            <td class="th">{{user.updated_at_user}}</td>
            <td class="th">{{user.url_profile_picture_user}}</td>
        </tr>
    </table>
</template>
<script>
import api from "@/services/api";
    export default {
        name: 'UserTable',
        data(){
            return{
                users : [],
                testee : api.get('/api/users')
            }
        },
        mounted(){
            this.testee.then(response => {
                this.users = response.data
            })
        },
        methods: {
            teste(login_user){
                this.$router.push({path:'/UserProfile'})
                localStorage.setItem('login_user', login_user)
            }
        }
    } 
</script>

<style>
#table {
    border-collapse: collapse; /* Remove espaços entre bordas de células */
    width: 90%;
    margin: auto;
    margin-top: 15px;
    background-color: white;
    
  }
  .th{
    border: 1px solid black; /* Define a borda das células */
    padding: 8px; /* Espaço interno das células */
    text-align: left; /* Alinhamento do texto */
  }
  tr:hover{
    color: rgb(224, 72, 2);
    font-weight: 600;
  }
</style>
