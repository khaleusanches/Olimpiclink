<template>
    <div class="Report">
        <div class="infos">
            <h4>Report:</h4>
            <p>{{ report[i].created_at_report_publication }}</p>
        </div>
        <div class="TextPublication">
            <p>{{ report[i].reason }}</p>
        </div>
        <div class="flex">
            <p id="cont">{{ i+1 }} de {{ report.length}}</p>
            <h5>- Anonímo</h5>
        </div>
        <div class="div_btn_skip">
            <ButtonSkip title="Próx" @click="passar()"></ButtonSkip>
        </div>
    </div>
</template>
  
<script>
import ButtonSkip from './ButtonSkip.vue';

export default {
    name: 'ReportInfos',
    components:{ButtonSkip},
    data(){
        return{
            text_report: '',
            i: 0
        }
    },
    props:{
        report: Array
    },
    methods:{
        passar(){
            if(this.i<this.report.length-1){
                this.i++
            }
            else{
                this.i=0
            }
            console.log(this.i)
        }
    }
}

//<img :src="publication.url_image_one_publication" alt="">
//<img :src="publication.url_image_two_publication" alt="">
//<img :src="publication.url_image_three_publication" alt="">
//<img :src="publication.url_image_four_publication" alt="">
</script>

<style>
.Report{
    width: 25%;
    margin-top: 30px;
    border: 2px solid rgb(78, 102, 156);
    padding: 12px;
    border-radius: 5px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
    box-shadow: 3px 6px 10px 0 rgba(0, 0, 0 , .1);
    padding-bottom: 20px;
    min-height: 20%;
}
.UserInfo{
    margin: auto;
    display: flex;
    align-items: center;
}
.UserInfo img{
    width: 7%;
    margin-right: 10px;
}
.infos p{
    margin-top: 5px;
    font-size: 12px;
    margin-left: 4px;
}
.Report .TextPublication{
    text-align: justify;
    border: 2px solid rgb(199, 199, 199);
    padding: 10px;
    margin-top: 10px;
    border-radius: 5px;
    
}
.Report h5{
    margin-top: 10px;
    margin-right: 25px;
}
.flex{
    display: flex;
    justify-content: space-between;
}
#cont{
    margin-top: 10px;
    margin-left: 20px;
    font-size: 14px;
}
.div_btn_skip{
    display: flex;
    justify-content: center;
    margin-top: 25px;
}

</style>