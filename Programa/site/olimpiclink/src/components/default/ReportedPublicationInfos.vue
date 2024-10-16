<template>
    <div class="ReportedPublicationInfo">
        <div class="informa" v-if="publication.length>0">
            <p>Nome: {{  this.publication[0].name_user }}</p>
            <p>Email: {{  this.publication[0].email_user }} </p>
            <p>Nome de usuário: {{  this.publication[0].login_user }}</p>
            <p>Curtidas: </p>
            <p>Denúncias ao usuário: {{  this.publication[0].denuncias_a_user }}</p>
            <p>Denúncias á publicação: {{ this.number_report_publi }} </p>
        </div>
        <div class="informa" v-else-if="event.length>0">
            <p>Nome da Comunidade: {{  this.event[0].leader_ }}</p>
            <p>Nome de usuário do Lider: {{  this.publication[0].email_user }} </p>
            <p>Email: {{  this.publication[0].login_user }}</p>
            <p>Presenças marcadas: </p>
            <p>Denúncias ao lider: {{  this.publication[0].denuncias_a_user }}</p>
            <p>Denúncias ao evento: {{ this.number_report_publi }} </p>
            <p>Denúncias á comunidade: {{ }} </p>
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
    width: 25%;
    margin-top: 30px;
    border: 2px solid rgb(69, 97, 163);
    border-left: 0;
    border-right: 0;
    padding: 12px;
    box-shadow: 0px 6px 5px 0px rgba(0, 0, 0 , .1);
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    min-height: 20%;
}

.ReportedPublicationInfo .informa p{
    text-align: justify;
    padding: 5px;
    font-size: 17px;
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