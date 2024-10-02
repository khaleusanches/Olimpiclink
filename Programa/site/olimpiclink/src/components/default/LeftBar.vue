<template>
    <div class="leftbar">
        <div class="ContainerLogo">
            <img class="logo" src="../../assets/logo.png" alt="">
            <h2>Olimpiclink</h2>
        </div>
        <ul>
            <li>
                <img class="icon" src="../../assets/logo.png" alt="">
                <router-link to="/">Home</router-link>
            </li>
            <li>
                <img class="icon" src="../../assets/logo.png" alt="">
                <router-link to="UsersList">Users</router-link>
            </li>
            <li>
                <img class="icon" src="../../assets/logo.png" alt="">
                <router-link to="/">Home</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'LeftPage'
    }
</script>

<style>
    .ContainerLogo{
        display: flex;
        align-items: center;
        margin-bottom: 35px;
    }
    .logo{
        width: 48px;
        margin-right: 15px;
    }
    .leftbar{
        padding-top: 50px;
        padding-left: 2%;
        height: 1000px;
        border: 3px, rgb(224, 72, 2), solid;
        border-left: 0px;
        border-top: 0px;
        border-bottom: 0px;
        width: 15%;
        color: white;
        background-color: rgb(39, 39, 39);
    }
    .icon{
        width: 24px;
        margin-right: 10px;
    }
    ul{
        font-size: 18px;
    }
    li{
        align-items: center;
        display: flex;
        padding-top: 8px;
        padding-bottom: 8px;
        font-weight: bold;
    }
    li:hover{
        background-color: black;
        border-radius: 32px;
        width: 90%;
        padding-left: 16px;
    }
</style>