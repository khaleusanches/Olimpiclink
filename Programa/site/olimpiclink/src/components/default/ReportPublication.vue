<template>
    <div class="ReportedPublication">
        <div class="UserInfo">
            <img :src="this.url_profile_picture_user" alt="">
            <div class="infos">
                <h4>@{{ login_user }}</h4>
                <p>{{ date }}</p>
            </div>
        </div>
        <div class="TextPublication">
            <p>{{ text_publication }}</p>
        </div>
        <div class="imagens">
            
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'ReportPublication',
    data(){
        return{
            date: '',
            login_user: '',
            text_publication: '',
            url_profile_picture_user: ''
        }
    },
    props:{
        publication: Array
    },
    created(){
        this.date = this.publication[0].date_publication,
        this.login_user = this.publication[0].login_user,
        this.text_publication = this.publication[0].text_publication
        this.url_profile_picture_user = this.publication[0].url_profile_picture_user
    }
}

//<img :src="publication.url_image_one_publication" alt="">
//<img :src="publication.url_image_two_publication" alt="">
//<img :src="publication.url_image_three_publication" alt="">
//<img :src="publication.url_image_four_publication" alt="">
</script>

<style>
.ReportedPublication{
    width: 25%;
    margin-left: 30px;
    margin-top: 30px;
    border: 2px solid rgb(199, 199, 199);
    padding: 12px;
    border-radius: 15px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    box-shadow: -3px 6px 10px 0 rgba(0, 0, 0 , .1);
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
    border-radius: 15px;
}
</style>