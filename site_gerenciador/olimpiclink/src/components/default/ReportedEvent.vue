<template>
    <div class="ReportedEvent">
        <div class="Container EventInfosBox d-flex align-items-center">
            <img class="d-block mb-4" src="https://cdn-icons-png.flaticon.com/512/7022/7022927.png" alt="">
            <div class="EventInfos align-items-center">
                <h4 class="h6 mt-4"><b>{{ event[0].name_comunity }}</b></h4>
                <p class="small">{{ event[0].created_at_event }}</p>
            </div>
        </div>
        <h4 class="title h6">{{event[0].nameEvent}}</h4>
        <div class="CarrosselImages">

        </div>
        <p class="TextEvent TextPublication">{{ event[0].descriptionEvent }}</p>
        <div class="Container Datas">
            <p class="small">Início: {{ event[0].dateTimeEvent }}</p>
            <p class="small">Fim: {{ event[0].closingDateTimeEvent }}</p>
        </div>
        <p class="Localizacao small">Localização: {{ event[0].name_place }}</p>
    </div>
</template>

<script>

export default {
    name: 'ReportedEvent',
    components:{
        
    },
    props:{
        event: Array
    }
}
</script>

<style scoped>
.ReportedEvent{
    width: 20%;

    margin-left: 30px;
    margin-top: 30px;
    border: 2px solid rgb(78, 102, 156);
    padding: 12px;
    border-radius: 5px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    box-shadow: -3px 6px 10px 0 rgba(0, 0, 0, 0.116);
    padding-bottom: 10px;
    min-height: 20%;
}
.Container{
    display: flex;
    align-items: center;
}
.EventInfosBox{
    margin-bottom: 12px;
}
.EventInfosBox img{
    width: 50px;
    margin-right: 12px;
}
.EventInfos p{
    font-size: 12px;
}
.title{
    margin-left: 12px
}
.TextEvent{
    text-align: justify;
    border: 2px solid rgb(199, 199, 199);
    padding: 10px;
    margin-top: 4px;
    border-radius: 5px;
}
.Datas{
    justify-content: space-around;
    margin-top: 8px;
}
.Localizacao{
    margin-top: 12px;
    margin-left: 8px;
}
</style>