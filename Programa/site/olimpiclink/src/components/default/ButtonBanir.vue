<template>
    <button id="btn_ban">
        <span class="text small">Banir <br> {{ title }}</span>
        <span class="icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"></path>
            </svg>
        </span>
    </button>
</template>

<script>
    export default {
        name: 'ButtonBanir',
        props:{
            title: String
        }
    }
</script>

<style>
#btn_ban{
        height: 40px;
        width: 130px;
        cursor: pointer;
        display: flex;
        align-items: center;
        background: rgb(197, 8, 8);
        border: none;
        border-radius: 3px;
        box-shadow: 1px 1px 3px rgba(0,0,0,0.15);
        background: rgba(197, 8, 8, 0.808);
        margin-top: 10px;
    }
    #btn_ban, #btn_ban span {
        transition: 200ms;
    }

    #btn_ban .text {
        transform: translateX(30px);
        color: white;
        font-weight: bold;
    }
    #btn_ban .icon {
        position: absolute;
        border-left: 1px solid rgb(136, 28, 28);
        transform: translateX(100px);
        height: 40px;
        width: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    #btn_ban svg {
    width: 15px;
    fill: #eee;
    }

    #btn_ban:hover {
    background: rgb(156, 27, 27);
    }

    #btn_ban:hover .text {
    color: transparent;
    }

    #btn_ban:hover .icon {
    width: 130px;
    border-left: none;
    transform: translateX(0);
    }

    #btn_ban:focus {
    outline: none;
    }

    #btn_ban:active .icon svg {
    transform: scale(0.8);
    }
</style>