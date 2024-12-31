<template>
    <div id="Report">
        <LeftBar></LeftBar>
        <div class="MenuConteudo">
            <div class="Menuses">
                <HeaderPage></HeaderPage>
            </div>
            <div class="redondo" v-if="reported_events.length > 0">
                <div class="ConteudoReported" v-for="reportes, index in reported_events" :key="index">
                    <ReportedEvent :event="reportes.reported_event"></ReportedEvent>
                    <ReportedPublicationInfos :event="reportes.reported_event" :publication="[]" :number_report_publi="reportes.event_denuns"></ReportedPublicationInfos>
                    <ReportInfos :report="reportes.reports"></ReportInfos>
                    <div class="buttons_report">
                        <ButtonAproved title="-Vistar-" @click="markRead(reportes.event_id)"></ButtonAproved>
                        <ButtonBanir title="evento" @click="archiveEvent(reportes.event_id)"></ButtonBanir>
                        <ButtonBanir title="usuário"></ButtonBanir>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ButtonAproved from '@/components/default/ButtonAproved.vue';
import ButtonBanir from '@/components/default/ButtonBanir.vue';
import HeaderPage from '@/components/default/HeaderPage.vue';
import LeftBar from '@/components/default/LeftBar.vue';
import ReportedEvent from '@/components/default/ReportedEvent.vue';
import ReportedPublicationInfos from '@/components/default/ReportedPublicationInfos.vue';
import ReportInfos from '@/components/default/ReportInfos.vue';

import api from '@/services/api';

export default {
    name: 'ReportedEventsPage',
    components:{
        LeftBar, ReportedEvent, ReportedPublicationInfos, ReportInfos, HeaderPage, ButtonBanir, ButtonAproved
    },
    data(){
        return{
            reported_events: []
        }
    },
    created(){
        api.get("/api/ReportedEvents").then(response => {
            this.reported_events = response.data
            console.log(this.reported_events)
        })
    },
    beforeUpdate(){
        api.get("/api/ReportedEvents").then(response => {
            this.reported_events = response.data
            console.log(this.reported_events)
        })
    },
    methods:{
        markRead(event_id){
            api.put(`/api/ReportedEvents/read?id_event=${event_id}`)
            console.log(event_id)
        },
        archiveEvent(event_id){
            api.put(`/api/ReportedEvents/archive?id_event=${event_id}`)
            console.log(event_id)
        }
    }
}
</script>

<style scoped>
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
        justify-content: center;
    }
    .redondo{
        background-color: rgb(228, 228, 227);
        margin-right: 3%;
        margin-left: 3%;
        padding-top: 2%;
        padding-bottom: 15px;
        border-radius: 5px; 
        box-shadow: 0px 10px 10px rgba(69, 97, 163, 0.192);
    }
</style>