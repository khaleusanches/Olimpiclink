<template>
    <div id="Report">
        <LeftBar></LeftBar>
        <div class="MenuConteudo">
            <div class="Menuses">
                <HeaderPage></HeaderPage>
            </div>
            <div class="redondo">
                <div class="ConteudoReported">

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import HeaderPage from '@/components/default/HeaderPage.vue';
import LeftBar from '@/components/default/LeftBar.vue';


import api from '@/services/api';

export default {
    name: 'ReportedEventsPage',
    components:{
        LeftBar, HeaderPage
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