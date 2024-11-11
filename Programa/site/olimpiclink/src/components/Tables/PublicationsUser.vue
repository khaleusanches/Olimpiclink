<template>
  <div class="publication" v-for="(publication, index) in publications" :key="index"
    @mouseenter="show_button = index" @mouseleave="show_button = null">
    <div class="BoxPublication" v-show="show_confirmation_box !== index">
      <p style="text-align: right;">Data: {{publication.date_publication}}</p>
      <p style="text-align: justify;">text: {{publication.text_publication}}</p>
      <div class="imagens">
        <img :src="publication.url_image_one_publication" alt="">
        <img :src="publication.url_image_two_publication" alt="">
        <img :src="publication.url_image_three_publication" alt="">
        <img :src="publication.url_image_four_publication" alt="">
      </div>
      <p>{{publication.comunity_id}}</p>
      <p>{{publication.place_id}}</p>
      <p>{{publication.name_place}}</p>
      <p>{{publication.event_id}}</p>
    </div>
    <div class="ConfirmationBox" v-show="show_confirmation_box === index">
      <h3>Confirmar</h3>
      <button class="BtnConfirmArchive" @click="archivePublication(publication.id_publication)"></button>
    </div>
    <button v-if="activated == true" class="BtnArchive" v-show="show_button === index" @click="showBoxArquive(index)">Arquivar publicação</button>
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
                show_button: null,
                show_confirmation_box: null,
                teste: 1,
                archived: '',
            }
        },
        props:{
          user_id: Int16Array,
          activated: Boolean,
          texto_search_publicacao: String
        },
        created(){
          api.get("/api/publication/user/"+this.user_id).then(response => {
                    this.publications = response.data
                  })
        },
        watch:{
          texto_search_publicacao(newValue){
            console.log(newValue)
            if(newValue != ''){
              api.get("/api/publication/user/"+this.user_id+"/"+newValue).then(response => {
                this.publications = response.data
              })
              console.log("/api/publication/user/"+this.user_id+"/"+newValue)
              console.log(newValue)
            }
            else{
              api.get("/api/publication/user/"+this.user_id).then(response => {
                    this.publications = response.data
                  })
              this.$emit('activatedPublications')
            }
          },
          activated(newValue){
            if(newValue == true){
              api.get("/api/publication/user/"+this.user_id).then(response => {
                    this.publications = response.data
                  })
            }
            else{
              api.get("/api/publication/user/"+this.user_id+"/desactived").then(response => {
                    this.publications = response.data
                  })
            }
          },
          show_confirmation_box(newValue){
            if(newValue == null){
              api.get("/api/publication/user/"+this.user_id).then(response => {
                    this.publications = response.data
                  })
                  console.log(this.publications)
            }
            console.log(newValue)
          }
        },
        methods: {
          archivePublication(publication_id){
            console.log(publication_id)
            api.put(`api/publication/arquivar?id_publication=${publication_id}`)
            this.show_confirmation_box = null;
            api.get("/api/publication/user/"+this.user_id).then(response => {
                    this.publications = response.data
            })
          },
          showBoxArquive(index){
            if(this.show_confirmation_box != index){
              this.show_confirmation_box = index
            }
            else{
              this.show_confirmation_box = null
            }
          },
        }
    } 
</script>

<style>

.BoxPublication{
    border: 2px rgb(219, 219, 219) solid;
    margin: auto;
    width: 60%;
    text-align: center;
    padding: 10px;
    margin-bottom: 10px;
    margin-top: 10px;
    border-radius: 12px;
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

  .BtnArchive{
    width: 75px;
    margin-left: 10px;
  }
  .ConfirmationBox{
    border: 2px rgb(180, 180, 180) solid;
    margin: auto;
    width: 60%;
    text-align: center;
    padding: 10px;
    margin-bottom: 10px;
    margin-top: 10px;
    border-radius: 12px;
    height: 200px;
    background-color: rgb(219, 219, 219);
  }
  .ConfirmationBox h3{
    margin-top: 10px;
  }
  .BtnConfirmArchive{
    width: 20%;
    margin-top: 15px;
    height: 20%;
  }
  .imagens{
    width: 80%;
    margin: auto
  }

</style>
