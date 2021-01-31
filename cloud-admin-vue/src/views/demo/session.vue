<template>
  <div class="session">
    <h1>Session</h1>
    <el-button @click="passportSession()">获取 Passport Session</el-button>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted} from "vue";
// 获取当前示例
const {ctx} = getCurrentInstance()

/**
 * 实例被挂载后调用
 */
onMounted(() => {

  /**
   * Passport Session
   */
  ctx.passportSession = function () {
    ctx.$axios.get('http://localhost:20001/passport/sessionId').then(response => {
      console.info(response)
    }).catch(response => {
      console.error(response)
    })
  }

})

</script>

<style lang="scss">

</style>

<style scoped>

</style>
