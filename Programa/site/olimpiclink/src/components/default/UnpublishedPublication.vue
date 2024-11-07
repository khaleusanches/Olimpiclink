<template>
    <div class="unpublished">
        <div class="unpublishedContainer">
            <div class="container">
                <img class="profile_picture" src="../../assets/logo.png" alt="">
                <input type="text" placeholder="What's happening">
            </div>
            <div class="bottombar">
                <div class="icons">
                    <img class="icon" src="../../assets/logo.png" alt="">
                    <img class="icon"  src="../../assets/logo.png" alt="">
                    <img class="icon" src="../../assets/logo.png" alt="">
                </div>
                <button>Publicar</button>
            </div>
        </div>
    </div>
</template>
<style>
    .unpublished{
        margin-top: 20px;
        padding-bottom: 20px;
        border: 2px, solid, rgba(224, 72, 2, 0.733);
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
    .unpublishedContainer{
        margin-left: 22px;
    }
    .container{
        display: flex;
        align-items: center;
    }
    .profile_picture{
        width: 40px;
        border-radius: 15px;
        margin-right: 10px;
    }
    .bottombar{
        display: flex;
        justify-content: space-between;
        margin-left: 50px;
    }
    .bottombar button{
        margin-right: 20px;
        padding: 10px;
        border-radius: 15px;
        background-color: rgb(224, 72, 2);
        color: white;
    }
    input {
        border: none;           /* Remove bordas */
        outline: none;          /* Remove o contorno ao focar */
        background: none;       /* Remove background */
        box-shadow: none;       /* Remove sombras */
        padding: 0;             /* Remove o espaçamento interno */
        margin: 0;              /* Remove margens */
        font: inherit;          /* Inherit the font from its parent */
        color: inherit;         /* Inherit text color from its parent */
        padding: 5px;
        width: 100%;
        margin-top: 15px;
        margin-bottom: 15px;
        margin-right: 20px;
    }
</style>