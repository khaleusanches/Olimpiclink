<template>
    <div class="Report" @click="passar()">
        <div class="infos">
            <h4>Report:</h4>
            <p>22/12/2024</p>
        </div>
        <div class="TextPublication">
            <p>{{ report[i].reason }}</p>
        </div>
        <h5>- Anonímo</h5>
    </div>
</template>
  
<script>
export default {
    name: 'ReportInfos',
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
    border: 2px solid rgb(199, 199, 199);
    padding: 12px;
    border-radius: 15px;
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
    border-radius: 15px;
    
}
.Report h5{
    margin-top: 10px;
    margin-right: 25px;
    text-align: end;
}
</style>