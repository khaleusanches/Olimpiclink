<template>
    <div id="Users">
        <LeftBar></LeftBar>
        <div class="conteudo">
            <HeaderPage></HeaderPage>
            <div class="cont">
                <UserTable></UserTable>
            </div>
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
    .cont{
        margin: auto;
        margin-top: 32px;
        padding-top: 32px;
        padding-bottom: 32px;
        width: 90%;
        border-radius: 16px;
        background-color: white;
        box-shadow: 0px 10px 10px rgba(73, 70, 92, 0.671);
    }
</style>