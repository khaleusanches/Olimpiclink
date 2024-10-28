<template>
    <button class="noselect ButtonAproved small">
        <span class="text">{{ title }} <br> {{ title2 }}</span>
        <span class="icon">
            <svg viewBox="0 0 24 24" height="24" width="24" xmlns="http://www.w3.org/2000/svg"></svg>
            <span class="ButtonAprovedSpan">+</span>
        </span>
    </button>
</template>

<script>
    export default {
        name: '.ButtonAproved',
        props:{
            title: String,
            title2: String
        },
    }
</script>

<style scoped>

.ButtonAproved {
  height: 40px;
  width: 130px;
  cursor: pointer;
  display: flex;
  align-items: center;
  background: rgb(94, 126, 199);
  border: none;
  border-radius: 3px;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.15);
  background: rgb(94, 126, 199);

}

.ButtonAproved,
.ButtonAproved span {
  transition: 200ms;
}

.ButtonAproved .text {
  transform: translateX(30px);
  color: white;
  font-weight: bold;
}

.ButtonAproved .icon {
  position: absolute;
  border-left: 1px solid rgb(72, 93, 141);
  transform: translateX(100px);
  height: 40px;
  width: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.ButtonAproved svg {
  width: 15px;
  fill: #eee;
}

.ButtonAproved:hover {
  background: rgb(60, 77, 117);
}

.ButtonAproved:active {
  background: rgb(118, 153, 235);
}

.ButtonAproved:hover .text {
  color: transparent;
}

.ButtonAproved:hover .icon {
  width: 130px;
  border-left: none;
  transform: translateX(0);
}

.ButtonAproved:focus {
  outline: none;
}

.ButtonAproved:active .icon svg {
  transform: scale(0.8);
}

.ButtonAprovedSpan {
  color: white;
  margin: 150px;
  font-size: 30px;
}


</style>