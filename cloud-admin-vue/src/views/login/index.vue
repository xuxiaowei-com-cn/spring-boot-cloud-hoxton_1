<template>
  <el-container class="login">
    <el-header class="login-header">
      <div class="header-context">
        <h2>Spring Cloud Admin</h2>
      </div>
    </el-header>
    <el-main class="login-main">
      <el-form ref="form" :model="loginForm" class="login-form">
        <el-form-item>
          <el-input type="text" v-model="loginForm.username"
                    :autocomplete="loginFormAutocomplete.username"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="loginForm.password"
                    :autocomplete="loginFormAutocomplete.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="loginForm.rememberMe">记住我</el-checkbox>
          <el-link type="primary" target="_blank" :underline="false" href="#forget" class="forget">忘记密码？</el-link>
        </el-form-item>
        <el-form-item>
          <el-button @click="login()" class="login-button">登录</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script setup>
import {reactive} from 'vue'

// 登录参数
const loginForm = reactive({
  username: null,
  password: null,
  rememberMe: false,
})

// 是否自动填充
const loginFormAutocomplete = reactive({
  username: 'on',
  password: 'off',
})

function login() {
  console.log(loginForm)
}

</script>

<style lang="scss">
.login {
  .login-header {
    .header-context {
      text-align: center;
    }
  }

  .login-main {

    .login-form {
      margin-left: auto;
      margin-right: auto;

      .el-form-item {
        .forget {
          float: right;
        }

        .login-button {
          width: 100%;
        }

        .el-form-item__content {
          margin-left: auto;
          margin-right: auto;
        }
      }
    }
  }

}

/* 超小屏幕(手机<768px) */
@media screen and (max-width: 768px) {
  .login {
    padding: 60px 0 110px 0;

    .login-main {
      .login-form {
        width: 300px;

        .el-form-item {
          .el-form-item__content {
            width: 280px;
          }
        }
      }
    }
  }
}

/* 小屏幕(平板≥768px)、中等屏幕(桌面≥992px)、大型屏幕(桌面≥1200px) */
@media screen and (min-width: 768px) {
  .login {
    padding: 110px 0;

    .login-main {
      .login-form {
        width: 375px;

        .el-form-item {
          .el-form-item__content {
            width: 335px;
          }
        }
      }
    }
  }
}
</style>

<style scoped>

</style>
