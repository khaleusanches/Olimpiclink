<template>
    <div id="Report">
        <LeftBar></LeftBar>
        <ReportedEvent></ReportedEvent>
    </div>
</template>

<script>
import LeftBar from '@/components/default/LeftBar.vue';
import ReportedEvent from '@/components/default/ReportedEvent.vue';

export default {
    name: 'ReportedEventsPage',
    components:{
        LeftBar, ReportedEvent
    }
}
</script>

<style scoped>
    #Report{
        display: flex;
    }
</style>