<template>
    <div id="Home">
        <LeftBar></LeftBar>
    </div>

</template>
  
<script>
import LeftBar from '../components/default/LeftBar.vue';
export default {
    name: 'HomeView',
    components: {
      LeftBar
    }
}
</script>

<style>
    #home{
        display: flex;
    }
</style>