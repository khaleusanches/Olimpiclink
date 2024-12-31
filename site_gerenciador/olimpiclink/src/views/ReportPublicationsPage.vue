<template>
    <div id="Report">
        <LeftBar></LeftBar>
        <div class="MenuConteudo">
            <div class="Menuses">

            </div>
            <div class="redondo m-5 pb-5 pt-3">
                <div class="ConteudoReported" v-for="reportes, index in reported_publications" :key="index">
                    <ReportPublication :publication="reportes.publication" data-bs-toggle="modal" data-bs-target="#exampleModal"></ReportPublication>
                <!--
                    <ReportedPublicationInfos :publication="reportes.publication" :event="[]" :number_report_publi="reportes.denuncias_a_publicacao"></ReportedPublicationInfos>
                    <ReportInfos :report="reportes.reportes"></ReportInfos>
                    <div class="buttons_report">
                        <ButtonAproved title="-Vistar-" @click="markRead(reportes.publication_id)"></ButtonAproved>
                        <ButtonBanir title="publica" @click="archivePublication(reportes.publication_id)"></ButtonBanir>
                        <ButtonBanir title="usuário"></ButtonBanir>
                    </div>-->
                </div>
            </div>
        </div>
    </div>

</template>
  
<script>
import api from '@/services/api';
import ReportPublication from '@/components/default/ReportPublication.vue';
import LeftBar from '../components/default/LeftBar.vue';
/* 
import ReportedPublicationInfos from '@/components/default/ReportedPublicationInfos.vue';
import ReportInfos from '@/components/default/ReportInfos.vue';
import ButtonBanir from '@/components/default/ButtonBanir.vue';
import ButtonAproved from '@/components/default/ButtonAproved.vue';
*/
export default {
    name: 'ReportPublicationsPage',
    components: {
      LeftBar, ReportPublication//, ReportedPublicationInfos, ReportInfos, ButtonBanir, ButtonAproved
    },
    data(){
        return{
            reported_publications: [],
            activated: false
        }
    },
    created(){
        this.reported_publications = [
            {
                publication_id: 1,
                publication: [{
                    "text_publication": "teste",
                    "date_publication": "2020-01-01",
                    "id_publication": "1",
                    "id_user": "1",
                    "login_user": "uelahk",
                    "name_user": "uelahk123",
                    "email_user":  "uelahk123@gmail.com",
                    "url_profile_picture_user": "https://cdn.pixabay.com/photo/2016/09/19/21/50/sun-flower-1681385_640.jpg",
                    "denuncias_a_user":1
                }],
                reportes:[{
                    "id_report_publication":1,
                    "reason": "seila",
                    "user_id": "1",
                    "created_at_report_publication": "22/11/2024"
                }]
            }
        ]
    },
    beforeUpdate(){
        //this.iniciar()
    },
    methods:{
        markRead(publication_id){
            api.put(`/api/ReportedPublication/read?id_publication=${publication_id}`)
            console.log(publication_id)
        },
        archivePublication(publication_id){
            api.put(`/api/ReportedPublication/archive?id_publication=${publication_id}`)
            console.log(publication_id)
        },
        iniciar(){
            //api.get("/api/ReportedPublication").then(response => {
            //    this.reported_publications = response.data
            //   console.log(response.data)
            //})
        }
    }
}
</script>

<style>
    #Report{
        display: flex;
        padding-bottom: 15px;
    }
    .MenuConteudo{
        width: 85%;
    }
    .Menuses{
        margin-bottom: 15px;
    }
    .ConteudoReported{
        display: flex;
    }
    .redondo{
        background-color: rgb(228, 228, 227);

        border-radius: 5px; 
        box-shadow: 0px 10px 10px rgba(69, 97, 163, 0.192);
    }
    .buttons_report{
        margin: auto;
        margin-left: 10px;
        margin-right: 5px;
    }

</style>