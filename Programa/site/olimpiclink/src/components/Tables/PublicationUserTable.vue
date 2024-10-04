<template>
      <div class="publication" v-for="(publication, index) in publications" :key="index"
      @mouseenter="showbutton = index" @mouseleave="showbutton = null">
        <div class="CaixaPublication">
          <p style="text-align: right;">Data: {{publication.date_publication}}</p>
          <p style="text-align: justify;">text: {{publication.text_publication}}</p>
          <img :src="publication.url_image_one_publication" alt="">
          <img :src="publication.url_image_two_publication" alt="">
          <img :src="publication.url_image_three_publication" alt="">
          <img :src="publication.url_image_four_publication" alt="">
          <p></p>
          <p>{{publication.comunity_id}}</p>
          <p>{{publication.place_id}}</p>
          <p>{{publication.name_place}}</p>
          <p>{{publication.event_id}}</p>
        </div>
        <button class="arquivar" v-show="showbutton === index" @click="arquivar(publication.id_publication)">Arquivar publicação</button>
      </div>
</template>
<script>
import api from "@/services/api";
    export default {
        name: 'PublicationUserTable',
        data(){
            return{
                publications : [],
                activated_publications : api.get('/api/publication'),
                arquived_publications : api.get('/api/publication'),
                showbutton: null
            }
        },
        props:{
          user_id: Int16Array
        },
        mounted(){
            this.fetchA();
        },
        methods: {
            fetchA(){
              api.get("/api/publication/user/"+this.user_id).then(response => {
                  this.publications = response.data
                  console.log(response.data)
              })
              
            },
            arquivar(publication_id){
              console.log(publication_id)
              api.put(`api/publication/arquivar?id_publication=${publication_id}`)
              return this.fetchA()
            }
            

        }
    } 
</script>

<style>
table {
    border-collapse: collapse; /* Remove espaços entre bordas de células */
    margin: auto;
    width: 90%;
    margin-top: 15px;
  }
  th, td {
    border: 1px solid black; /* Define a borda das células */
    padding: 8px; /* Espaço interno das células */
    text-align: left; /* Alinhamento do texto */
  }
  tr:hover{
    color: rgb(224, 72, 2);
  }
  .publication{
    display: flex;
    align-items: center;
  }
  .publication p, a{
    margin: 12px;
  }
  .publication img{
    width: 165px;
    margin-right: 5px;
  }
  .CaixaPublication{
    border: 2px rgb(219, 219, 219) solid;
    margin: auto;
    width: 90%;
    text-align: center;
    padding: 10px;
    margin-bottom: 10px;
    margin-top: 10px;
    border-radius: 12px;
  }
  .arquivar{
    width: 75px;
    margin-left: 10px;
  }
</style>
