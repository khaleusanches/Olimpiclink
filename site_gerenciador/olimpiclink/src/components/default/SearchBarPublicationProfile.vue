<template>
    <div class="search">
        <form @submit.prevent="searchPublication">
            <input type="text" v-model="texto_publicacao" placeholder="Texto da publicação">
            <input type="submit">
        </form>
    </div>
</template>
<script>
import api from "@/services/api";
export default {
    name: "SearchBarPublicationProfile",
    data(){
        return{
            texto_publicacao: '',
            apia: []
        }
    },
    props:{
        user_id: Int16Array,
    },
    methods: {
        searchPublication(){
            api.get("/api/publication/user/"+this.user_id+"/text/"+this.texto_publicacao).then(response => {
                this.apia = response.data
            })
            console.log(this.texto_publicacao)
        }
    }
}
</script>
<style>
</style>