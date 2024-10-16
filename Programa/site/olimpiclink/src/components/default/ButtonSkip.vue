<template>
    <button class="noselect ButtonSkip">
        <span class="text">{{ title }} <br> {{ title2 }}</span>
        <span class="icon">
            <svg viewBox="0 0 24 24" height="24" width="24" xmlns="http://www.w3.org/2000/svg"></svg>
            <span class="ButtonSkipSpan">></span>
        </span>
    </button>
</template>

<script>
    export default {
        name: '.ButtonSkip',
        props:{
            title: String,
            title2: String
        }
    }
</script>

<style>
/* From Uiverse.io by UtariD86 */ 
.ButtonSkip {
  height: 35px;
  width: 100px;
  cursor: pointer;
  display: flex;
  align-items: center;
  background: rgb(69, 97, 163);
  border: none;
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.15);
  background: rgbrgb(69, 97, 163);

}

.ButtonSkip,
.ButtonSkip span {
  transition: 200ms;
}

.ButtonSkip .text {
  transform: translateX(23px);
  color: white;
  font-weight: bold;
}

.ButtonSkip .icon {
  position: absolute;
  border-left: 1px solid rgb(69, 97, 163);
  transform: translateX(70px);
  height: 30px;
  width: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.ButtonSkip svg {
  width: 15px;
  fill: #eee;
}

.ButtonSkip:hover {
  background: rgb(44, 61, 104);
}

.ButtonSkip:active {
  background: rgb(85, 120, 201);
}
  
.ButtonSkip:hover .text {
  color: transparent;
}

.ButtonSkip:hover .icon {
  width: 100px;
  border-left: none;
  transform: translateX(0);
}

.ButtonSkip:focus {
  outline: none;
}

.ButtonSkip:active .icon svg {
  transform: scale(0.8);
}

.ButtonSkipSpan {
  color: white;
  margin: 150px;
  font-size: 30px;
}


</style>