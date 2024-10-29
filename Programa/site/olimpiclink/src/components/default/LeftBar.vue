<template>
    <div class="leftbar">
        <div class="ContainerLogo">
            <img class="logo" src="../../assets/logo.png" alt="">
            <h2>Olimpiclink</h2>
        </div>
        <div class="dropdown">
            <li class="btn">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Publications</router-link>
                    </div>
                </li>
        </div>
        <div class="dropdown">
            <li class="btn">
                <img class="icon" src="../../assets/logo.png" alt="">
                <router-link to="/UsersList">Usuários</router-link>
            </li>
        </div>
        <div class="dropdown">
            <div class=" dropdown-toggle p-3" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <img class="icon" src="../../assets/logo.png" alt="">
                <a class="" href="#" >
                    <b>Denuncías</b>
                </a>
            </div>
            <ul class="dropdown-menu">
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Publications</router-link>
                    </div>
                </li>
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Events">Eventos</router-link>
                    </div>
                </li>
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Comus</router-link>
                    </div>
                </li>
            </ul>
        </div>
        <div class="dropdown align-items-start">
            <div class="dropdown-toggle p-3" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <img class="icon" src="../../assets/logo.png" alt="">
                <a class="" href="#" >
                    <b>Verificações</b>
                </a>
            </div>
            <ul class="dropdown-menu">
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Publications</router-link>
                    </div>
                </li>
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Events">Eventos</router-link>
                    </div>
                </li>
                <li class="dropdown-item">
                    <div class="teste3">
                        <img class="icon" src="../../assets/logo.png" alt="">
                        <router-link to="/Reportations/Publications">Comus</router-link>
                    </div>
                </li>
            </ul>
        </div>
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
    border-radius: 10px;
}
.dropdown:hover{
    background-color: rgb(49, 49, 49);
    width: 84%;
}
.dropdown-menu{
    background-color: rgb(49, 49, 49);
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
.teste3:hover{
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
