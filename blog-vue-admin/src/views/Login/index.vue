<template>
  <div class="login-container">
    <div class="login-layout">
      <div class="left">
        <h1>login</h1>
        <p>欢迎光临, 请输入您的账号和密码进行登录!</p>
      </div>
      <div class="right">
        <!-- SVG提前准备好, 来自互联网 -->
        <svg viewBox="0 0 320 300">
          <defs>
            <!-- 定义线性渐变 -->
            <linearGradient inkscape:collect="always" id="linearGradient1" x1="13" y1="193.49992" x2="307"
                            y2="193.49992"
                            gradientUnits="userSpaceOnUse">
              <stop style="stop-color:#0ff;" offset="0"/>
              <stop style="stop-color:#f0f;" offset="1"/>
            </linearGradient>
          </defs>
          <path
              d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143"/>
        </svg>
        <div class="form">
          <label for="account">账号</label>
          <input type="text" id="account" autocomplete="off" v-model="inputUser.username">
          <label for="password">密码</label>
          <input type="password" id="password" v-model="inputUser.password">
          <button id="submit" @click="login">登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import anime from "animejs/lib/anime.es.js"
import VRouter from '@/router'
import {onMounted, onUnmounted, ref} from "vue";
import type {User} from "@/type";
import api from "@/api";
import {useMessage} from 'naive-ui'
import {useTokenStore} from "@/stores/useTokenStore";

const tokenStore = useTokenStore()
const message = useMessage()
const inputUser = ref<User>({
  username: "",
  password: ""

})
const login = function () {
  tokenStore.avatarToken.token=" "
  api.login(inputUser.value).then(({data}) => {
    if (data.code == 10000) {
      tokenStore.avatarToken.token= data.data.token
      tokenStore.avatarToken.avatarUrl=data.data.avatarUrl
      message.success("登录成功！")
      VRouter.replace('/')
    } else {
      message.error("登录失败，请检查用户名和密码")
    }
  })
}

let an: anime.AnimeInstance | null = null;

const playAnimation = (strokeDashoffset: number, strokeDasharray: string) => {
  if (an) {
    an.pause();
  }
  an = anime({
    targets: 'path',
    strokeDashoffset: {
      value: strokeDashoffset,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: strokeDasharray,
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
}

const handleAccountClick = () => playAnimation(0, '240 1386')
const handlePasswordClick = () => playAnimation(-336, '240 1386')
const handleSubmitMouseOver = () => playAnimation(-730, '530 1386')
const handleAccountFocus = () => playAnimation(0, '240 1386')
const handlePasswordFocus = () => playAnimation(-336, '240 1386')
const handleSubmitFocus = () => playAnimation(-730, '530 1386')

onMounted(() => {
  document.querySelector('#account')?.addEventListener('click', handleAccountClick)
  document.querySelector('#password')?.addEventListener('click', handlePasswordClick)
  document.querySelector('#submit')?.addEventListener('mouseover', handleSubmitMouseOver)
  document.querySelector('#account')?.addEventListener('focus', handleAccountFocus)
  document.querySelector('#password')?.addEventListener('focus', handlePasswordFocus)
  document.querySelector('#submit')?.addEventListener('focus', handleSubmitFocus)
})

onUnmounted(() => {
  document.querySelector('#account')?.removeEventListener('click', handleAccountClick)
  document.querySelector('#password')?.removeEventListener('click', handlePasswordClick)
  document.querySelector('#submit')?.removeEventListener('mouseover', handleSubmitMouseOver)
  document.querySelector('#account')?.removeEventListener('focus', handleAccountFocus)
  document.querySelector('#password')?.removeEventListener('focus', handlePasswordFocus)
  document.querySelector('#submit')?.removeEventListener('focus', handleSubmitFocus)
})

</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: center;
}

.login-layout {
  width: 640px;
  height: 320px;
  display: flex;
}

.left {
  width: 50%;
  height: calc(100% - 40px);
  background-color: #fff;
  position: relative;
  top: 20px;
}

.left h1 {
  color: #222;
  font-size: 50px;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 4px;
  margin: 55px 40px 40px 40px;
}

.left p {
  color: #999;
  font-size: 14px;
  margin: 40px;
  line-height: 22px;
}

.right {
  width: 50%;
  height: 100%;
  background-color: #474a59;
  color: #f1f1f1;
  position: relative;
  box-shadow: 0 0 40px 16px rgba(0, 0, 0, 0.2);
}

.right svg {
  position: absolute;
  width: 320px;
}

.right path {
  /* 取消填充 */
  fill: none;
  /* 定义一条线,引用定义好的线性渐变 */
  stroke: url(#linearGradient1);
  /* 线的厚度 */
  stroke-width: 4;
  /* 设置虚线:虚线长度 间距 */
  stroke-dasharray: 240 1386;
}

.form {
  margin: 40px;
  position: absolute;
}

.form label {
  color: #c2c2c2;
  display: block;
  font-size: 14px;
  margin-top: 20px;
  margin-bottom: 5px;
}

.form input {
  width: 100%;
  height: 30px;
  line-height: 30px;
  font-size: 20px;
  color: #f2f2f2;
  background-color: transparent;
  border: none;
  outline: none;
  text-indent: 2px;
}

.form button {
  width: 100%;
  height: 30px;
  color: #d0d0d0;
  font-size: 18px;
  background-color: transparent;
  border: none;
  margin-top: 40px;
  cursor: pointer;
  outline: none;
}
</style>
