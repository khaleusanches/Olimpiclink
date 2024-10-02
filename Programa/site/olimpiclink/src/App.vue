<template>
  <div id="app">
    <router-view/>
  </div>

</template>

<script>
export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  background-color: rgb(218, 218, 218);
}
*{
  margin: 0;
  list-style-type: none;
  padding: 0;
}
</style>
