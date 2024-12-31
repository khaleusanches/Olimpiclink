<template>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Detalhes</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="post">
                            <div class="UserInfo d-flex align-items-center">
                                <img class="d-block mb-4" :src="publication[0].url_profile_picture_user" alt="">
                                <div class="infos align-items-center">
                                    <h4 class="h6"><strong>@{{ publication[0].login_user }}</strong></h4>
                                    <p class="small">{{ publication[0].date_publication }}</p>
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
                        <ReportedPublicationInfos :publication="publication" :event="[]" :number_report_publi="1"></ReportedPublicationInfos>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
import ReportedPublicationInfos from './ReportedPublicationInfos.vue';

export default {
    name: "ReportedPublicationModal",
    components:{
        ReportedPublicationInfos
    },
    props:{
        publication: Array
    }
}
</script>

<style>
.modal-content{
    width: 800px;
    margin-right: 500px;
}
.modal-body{
    display: grid;
    grid-template-columns: 50% 50%;
    align-items: center;
}
.modal-body div {

    
}
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
}
.post{
    text-align: justify;
    border: 2px solid rgb(199, 199, 199);
    padding: 10px;
    margin-top: 10px;
    border-radius: 5px;
    margin-right: 10px;
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