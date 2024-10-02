<template>
    <header>
        <nav>

            <h2>{{ page }}</h2>
            <p>{{ contage }}</p>
        </nav>
    </header>
</template>

<script>
    export default{
        name: 'NavPage',
        props: {
            page: String,
        },
    }
</script>

<style>
    header{
        width: 100%;
    }
    nav{
        margin-top: 20px;
        padding: 5px;
        border: 2px, solid, rgba(224, 72, 2, 0.733);
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
    nav h2{
        margin-left: 10px;
        margin-bottom: 10px;
    }
</style>