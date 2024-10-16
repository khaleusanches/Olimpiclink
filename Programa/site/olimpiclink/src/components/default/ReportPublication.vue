<template>
    <div class="ReportedPublication">
        <div class="UserInfo">
            <img :src="publication[0].url_profile_picture_user" alt="">
            <div class="infos">
                <h4>@{{ publication[0].login_user }}</h4>
                <p>{{ publication[0].date_publication }}</p>
            </div>
        </div>
        <div class="TextPublication">
            <p>{{ publication[0].text_publication }}</p>
        </div>
        <div class="imagens imagens_reported">
            <div>
                <img :src="img+publication[0].id_publication+'/'+'1'" alt="">
                <img :src="img+publication[0].id_publication+'/'+'2'" alt="">
            </div>
            <div>
                <img :src="img+publication[0].id_publication+'/'+'3'" alt="">
                <img :src="img+publication[0].id_publication+'/'+'4'" alt="">
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'ReportPublication',
    data(){
        return{
            img: "http://192.168.0.158:5000/api/publication/imagens/"
        }
    },
    props:{
        publication: Array
    }
}

</script>

<style scoped>
.ReportedPublication{
    width: 25%;
    margin-left: 30px;
    margin-top: 30px;
    border: 2px solid rgb(78, 102, 156);
    padding: 12px;
    border-radius: 5px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    box-shadow: -3px 6px 10px 0 rgba(0, 0, 0, 0.116);
    padding-bottom: 20px;
    min-height: 20%;
}
.UserInfo{
    margin: auto;
    display: flex;
    align-items: center;
}
.UserInfo img{
    width: 15%;
    margin-right: 10px;
}
.infos p{
    margin-top: 5px;
    font-size: 12px;
    margin-left: 4px;
}
.ReportedPublication .TextPublication{
    text-align: justify;
    border: 2px solid rgb(199, 199, 199);
    padding: 10px;
    margin-top: 10px;
    border-radius: 5px;
}
.imagens_reported{
    display: flex;
    justify-content: center;
}
.imagens_reported div{
    margin: 5px;
}
.imagens_reported img{
    width: 100%;
    margin: 5px;
    border-radius: 5px;
}
</style>