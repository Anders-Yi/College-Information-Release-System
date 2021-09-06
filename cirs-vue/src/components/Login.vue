<template>
  <div>
    <div class="login-card">
      <div class="login-title">登录</div>
      <span class="close" @click="closeLogin">X</span>
      <form action="#" class="tab">
        <div class="item">
          <span style="display: block; text-indent: -190px;">用户ID</span>
          <input type="text" v-model="uid" id="uid" class="inputgroup" placeholder="请输入您的学号或工号">
        </div>

        <div class="item">
          <span style="letter-spacing: 14px; display: block; text-indent: -180px;">密码</span>
          <input type="password" maxlength="8" v-model="password" id="pass" class="inputgroup" placeholder="请输入您的密码">
        </div>

        <div class="item">
          <input type="button" value="登录" @click="submitLoginInfo" id="login">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        uid: '',
        password: ''
      }
    },
    methods: {
      closeLogin: function() {
        this.$emit('LoginSon', false);
      },
      submitLoginInfo: function() {
        if(uid != '' && password != ''){
          this.$axios({
            method: 'post',
            url: 'http://localhost:8085/user/login',
            data: this.$qs.stringify({
              uid: this.uid,
              password: this.password
            })
          })
          .then(
            res=>{
              if(res.data == ""){
                this.uid="";
                this.password="";
                alert("登录失败，请重新输入！");
              }else{
                let loginuser = JSON.stringify(res.data);
                localStorage.setItem("loginsuccess",loginuser);
                console.log("localStorage创建成功");
                this.uid="";
                this.password="";
                this.$emit('LoginSon', {loginSon: false, user: res.data});
                window.location.reload();
              }
              
            }
          )
          .catch(err=>console.log(err))
        }else{
          if(uid == ''){
            alert("用户id不能为空");
          }
          if(password == ''){
            alert("密码不能不空");
          }
        }
      }
    }
  }
</script>

<style scoped>
  .login-card {
    width: 400px;
    height: 270px;
    border-radius: 8px;
    box-shadow: 0px 0px 3px gray;
    background-color: white;
    position: absolute;
    left: 560px;
    top: 260px;
    z-index: 999;
  }
  .login-title {
    width: 100%;
    height: 40px;
    line-height: 70px;
    font-size: 30px;
    letter-spacing: 45px;
    text-indent: 38px;
    text-align: center;
  }
  .close {
    width: 15px;
    height: 15px;
    cursor: pointer;
    position: relative;
    top: -35px;
    left: 380px;
  }
  .close:hover {
    color: red;
    cursor:pointer;
  }
  .tab {
    margin: -15px auto;
  }
  .item {
    width: 100%;
    height: 54px;
    text-align: center;
    margin-top: 30px;
  }
  .item:nth-child(2) {
    margin-top: 10px;
  }
  .item:last-child {
    margin-top: 20px;
  }
  .inputgroup {
    width: 240px;
    height: 26px;
    outline: none;
  }
  input[type='button'] {
    width: 90px;
    height: 30px;
    font-size: 20px;
    text-indent: 15px;
    letter-spacing: 15px;
  }
</style>
