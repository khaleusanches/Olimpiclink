<template>
    <div class="ReportedPublicationInfo">
        <div class="informa d-flex" v-if="publication.length>0">
            <div class="comboinfo">
                <p>Nome: {{  this.publication[0].name_user }}</p>
                <p>Email: {{  this.publication[0].email_user }} </p>
                <p>Nome de usuário: {{  this.publication[0].login_user }}</p>
            </div>
            <div class="comboinfo">
                <p>Curtidas: </p>
                <p>Denúncias ao usuário: {{  this.publication[0].denuncias_a_user }}</p>
                <p>Denúncias á publicação: {{ this.number_report_publi }} </p>
            </div>
        </div>
        <div class="informa" v-else-if="event.length>0">
            <p>Nome da Comunidade: {{  this.event[0].name_comunity }}</p>
            <p>Nome de usuário do Lider: {{  this.event[0].leader_name }} </p>
            <p>Email: {{  this.event[0].leader_email }}</p>
            <p>Presenças marcadas: </p>
            <p>Denúncias ao lider: </p>
            <p>Denúncias ao evento: {{ this.number_report_publi }} </p>
            <p>Denúncias á comunidade: {{ this.event[0].comunity_denuns }} </p>
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'ReportedPublicationInfos',
    data(){
        return{

        }
    },
    props:{
        publication: Array,
        event: Array,
        number_report_publi: Int16Array
    },
    beforeUpdate(){

    }
}
</script>

<style>
.ReportedPublicationInfo{
    width: 100%;
    margin-top: 30px;
    border: 2px solid rgb(69, 97, 163);
    padding: 12px;
    box-shadow: 0px 6px 5px 0px rgba(0, 0, 0 , .1);
    display: flex;
    align-items: center;
    padding-bottom: 10px;
    min-height: 20%;
}

.ReportedPublicationInfo .informa p{
    text-align: justify;
    padding: 2px;
    font-size: 16px;
}

.comboinfo{
    
}

@media (1400px <= width <= 1750px) {
    .ReportedPublicationInfo .informa p{
        font-size: 17px;
    }
}
@media (1200px <= width <= 1400px) {
    .ReportedPublicationInfo .informa p{
        font-size: 14px;
    }
}
@media (930px <= width <= 1200px) {
    .ReportedPublicationInfo .informa p{
        font-size: 14px;
    }
    .ReportedPublicationInfo{
        padding: 8px;
    }
}
</style>