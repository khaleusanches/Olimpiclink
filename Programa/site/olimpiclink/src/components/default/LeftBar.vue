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
                <router-link to="/UsersList">Users</router-link>
            </li>
            <li class="dropdown" @mouseenter="reports=true" v-on:mouseleave="reports=false">
                <div class="teste1">
                    <img class="icon" src="../../assets/logo.png" alt="">
                    <p>Reports</p>
                </div>
                <div class="teste2" v-show="reports==true">
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Publications</router-link>
                    </div>
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <div class="link">
                            <router-link to="/Reportations/Events">Eventos</router-link>
                        </div>
                    </div>
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <div class="link">
                            <router-link to="/Reportations/Publications">Comus</router-link>
                        </div>
                    </div>
                </div>
            </li>
            <li class="dropdown" @mouseenter="verificacoes=true" v-on:mouseleave="verificacoes=false">
                <div class="teste1">
                    <img class="icon" src="../../assets/logo.png" alt="">
                    <p>Verificações</p>
                </div>
                <div class="teste2" v-show="verificacoes==true">
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Verifications/Localizations">Localizações</router-link>
                    </div>
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <div class="link">
                            <router-link to="/Reportations/Events">Comuni</router-link>
                        </div>
                    </div>
                    <div class="teste3">
                        <div class="LinhaVertical"></div>
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <div class="link">
                            <router-link to="/Reportations/Publications">Categorias</router-link>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'LeftPage',
    data(){
        return{
            reports:false,
            verificacoes:false
        }
    }
};
</script>

<style scoped>
*{
    text-decoration: none;
}
*:link{
    color: white;
}
*:visited{
    color:white;
}

.ContainerLogo {
    display: flex;
    align-items: center;
    margin-bottom: 35px;
}
.logo {
    width: 48px;
    margin-right: 15px;
}
.leftbar {
    padding-top: 50px;
    padding-left: 2%;
    height: 1000px;
    border: 3px rgb(224, 72, 2) solid;
    border-left: 0px;
    border-top: 0px;
    border-bottom: 0px;
    width: 15%;
    color: white;
    background-color: rgb(71, 71, 71);
    z-index: 999;
}
.icon {
    width: 24px;
    margin-right: 10px;
}
ul {
    font-size: 18px;
    list-style-type: none;
}

li {
    align-items: center;
    display: flex;
    padding-top: 8px;
    padding-bottom: 8px;
    font-weight: bold;
}
li:hover{
    background-color: rgb(49, 49, 49);
    border-radius: 8px;
    margin-right: 16px;
    padding-left: 16px;
}
.teste1{
    align-items: center;
    display: flex;
}
.teste1 p {
    margin-left: 14px;
}
.teste2{
    background-color: rgb(49, 49, 49);
    margin-left: 30px;
    margin-right: 16px;
    padding-left: 16px;
    padding-top: 8px;
    padding-bottom: 8px;
}
.teste3{
    align-items: center;
    display: flex;
}
.dropdown{
    display: block;
    margin-bottom: 12px;
}
.dropdown:hover{
    border-bottom-left-radius:8px; 
    border-top-left-radius:8px; 
    padding-top: 22px;
    padding-bottom: 8px;
}
.LinhaVertical{
    width: 1px;
    height: 50px;
    margin-right: 15px;
    background-color: rgb(224, 72, 2);
    
}
.link:hover{
    color: rgb(224, 72, 2);
}

@media (1400px <= width <= 1750px) {
  .leftbar{
    width: 20%; 
  }
}
@media (1200px <= width <= 1400px) {
  .leftbar{
    width: 25%; 
  }
}
@media (930px <= width <= 1200px) {
  .leftbar{
    width: 30%; 
  }
}
</style>
