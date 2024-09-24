<template>
    <div id="Users">
        <LeftBar></LeftBar>
        <div class="conteudo">
            <HeaderPage></HeaderPage>
            <UserTable></UserTable>
        </div>
    </div>
    
</template>

<script>
import HeaderPage from '@/components/default/HeaderPage.vue';
import LeftBar from '../components/default/LeftBar.vue';
import UserTable from '@/components/Tables/UserTable.vue';
export default {
    name: 'UsersView',
    components: {
      LeftBar,
      HeaderPage,
      UserTable
    }
}
</script>

<style>
    #Users{
        display: flex;
    }
    .conteudo{
        width: 85%;
    }
</style>