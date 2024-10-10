<template>
    <div class="profile" v-if="user.length">
        <div class="ProfileDados">
            <img :src="user[0].url_profile_picture_user" alt="">
            <div>
                <h2>{{ user[0].name_user }}</h2>
                <h5>@{{user[0].login_user}}</h5>
                <p>Descricao</p>
            </div>
        </div>
        <div class="BoxAbas">
            <h3 ref="publications" @click="activated = true, publicationsActivated()">Publicações ativas</h3>
            <h3 ref="arquiveds" @click="activated = false, arquivedsActivated()">Publicações excluídas</h3>
        </div>
        <PublicationUserTable class="PublicationUserTable" :user_id="user[0].id_user" :activated=activated></PublicationUserTable>
    </div>
</template>

<script>
import api from '@/services/api';
import PublicationUserTable from '../Tables/PublicationsUser.vue';

    export default {
        name: 'UserProfile',
        components: {PublicationUserTable},
        data(){
            return{
                login_user: localStorage.getItem('login_user'),
                user: [],
                name_user: '',
                id_user: 0,
                activated: true
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
        },
        methods: {
            arquivedsActivated(){
                this.$refs.arquiveds.style.backgroundColor = 'rgb(224, 72, 2)'
                this.$refs.arquiveds.style.color = 'white'
                this.$refs.publications.style.backgroundColor = 'white'
                this.$refs.publications.style.color = 'black'
            },
            publicationsActivated(){
                this.$refs.publications.style.backgroundColor = 'rgb(224, 72, 2)'
                this.$refs.publications.style.color = 'white'
                this.$refs.arquiveds.style.backgroundColor = 'white'
                this.$refs.arquiveds.style.color = 'black'
            },
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
.ProfileDados{
    display: flex;
    align-items: center;
}
.ProfileDados img{
    width: 10%;
    margin-top: 16px;
}
.ProfileDados div{
    margin-left: 16px;
}
.background{
    background-color: rgb(224, 72, 2);
    width: 100%;
    height: 150px;
}
.BoxAbas{
    display: flex;
    justify-content: center;

}
.BoxAbas h3{
    border: 2px solid rgb(224, 72, 2);
    padding: 4px;
    padding-right: 32px;
    padding-left: 32px;
    cursor: pointer;
}
.BoxAbas h3:hover{
    background-color: rgb(224, 72, 2);
    color: white;
}
.PublicationUserTable{
    width: 100%;
    border: 2px solid rgb(224, 72, 2);
    padding: 2px;
}
.testeee{
    display: none;
}

</style>