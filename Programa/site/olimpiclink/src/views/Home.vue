<template>
    <div id="Home">
        <LeftBar></LeftBar>
        <h1>Ana júlia</h1>
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
    #Home{
        display: flex;
    }
</style>