<template>
    <div id="User">
        <LeftBar></LeftBar>
        <div class="conteudo">
            <HeaderPage></HeaderPage>
            <UserProfile></UserProfile>
        </div>
    </div>
    
</template>

<script>
import HeaderPage from '@/components/default/HeaderPage.vue';
import LeftBar from '../components/default/LeftBar.vue';
import UserProfile from '@/components/UserProfile.vue';
export default {
    name: 'UsersView',
    components: {
      LeftBar,
      HeaderPage,
      UserProfile
    }
}
</script>

<style>
    #User{
        display: flex;
    }
    .conteudo{
        width: 85%;
    }
</style>