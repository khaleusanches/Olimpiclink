<template>
    <table>
        <tr>
        <th>id_user</th>
        <th>activated_user</th>
        <th>name_user</th>
        <th>login_user</th>
        <th>password_user</th>
        <th>email_user</th>
        <th>profile_picture_user</th>
        <th>created_at_user</th>
        <th>updated_at_user</th>
        <th>url_profile_picture_user</th>
        </tr>
        <tr v-for="(user, index) in users" :key="index"></tr>
    </table>
</template>
<script>
    export default {
        name: 'UserTable',
        data(){
            return{
                users : []
            }
        },
        methods: {
            teste(){
                
            }
        }
    }
</script>

<style>
table {
    border-collapse: collapse; /* Remove espaços entre bordas de células */
    width: 90%;
    margin: auto;
    margin-top: 15px;
  }
  th, td {
    border: 1px solid black; /* Define a borda das células */
    padding: 8px; /* Espaço interno das células */
    text-align: left; /* Alinhamento do texto */
  }
</style>