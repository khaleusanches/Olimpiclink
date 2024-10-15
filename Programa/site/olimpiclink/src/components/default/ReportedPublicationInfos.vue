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
    border: 2px solid rgb(199, 199, 199);
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
</style>