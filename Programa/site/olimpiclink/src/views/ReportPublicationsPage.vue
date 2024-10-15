<template>
    <div id="Report">
        <LeftBar></LeftBar>
        <div class="MenuConteudo">
            <div class="Menuses">
                <HeaderPage></HeaderPage>
            </div>
            <div class="ConteudoReported" v-for="reportes, index in reported_publications" :key="index">
                <ReportPublication :publication="reportes.publication"></ReportPublication>
                <ReportedPublicationInfos :publication="reportes.publication" :number_report_publi="reportes.denuncias_a_publicacao"></ReportedPublicationInfos>
                <ReportInfos :report="reportes.reportes"></ReportInfos>
            </div>
        </div>
    </div>

</template>
  
<script>
import ReportPublication from '@/components/default/ReportPublication.vue';
import LeftBar from '../components/default/LeftBar.vue';
import ReportedPublicationInfos from '@/components/default/ReportedPublicationInfos.vue';
import ReportInfos from '@/components/default/ReportInfos.vue';
import HeaderPage from '@/components/default/HeaderPage.vue';
import api from '@/services/api';
export default {
    name: 'ReportPublicationsPage',
    components: {
      LeftBar, ReportPublication, ReportedPublicationInfos, ReportInfos, HeaderPage
    },
    data(){
        return{
            reported_publications: [],
            reported_publication: [],
            number_denun: [],
            activated: false
        }
    },
    created(){
        api.get("/api/ReportedPublication").then(response => {
            this.reported_publications = response.data
            this.reported_publication = this.reported_publications[0].publication
            console.log(this.reported_publication)
            console.log(response.data)
        })
        
    }
}
</script>

<style>
    #Report{
        display: flex;
    }
    .MenuConteudo{
        width: 85%;
    }
    .ConteudoReported{
        display: flex;
        justify-content: center;
    }
</style>