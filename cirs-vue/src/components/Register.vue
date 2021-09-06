<template>
  <div id="register">
    <div class="reg-card">
      <div class="register-title">注册</div>
      <span class="close" @click="closeRegister">X</span>
      <form action="#" method="post" class="form-table">
        <table class="tab" border="1">
          <tr>
            <td style="text-align: center;">
              <div class="label">姓名</div>
            </td>
            <td style="text-align: center;">
              <input type="text" class="form-input" v-model="username" id="username">
            </td>
            <td style="text-align: center;">
              <div class="label">职位</div>
            </td>
            <td style="text-align: center;">
              学生
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">
              <div class="label">性别</div>
            </td>
            <td style="letter-spacing: 10px;">
              <input style="margin-left: 35px;" type="radio" name="sex" value="0" id="man" v-model="sex" />男
              <input type="radio" name="sex" value="1" id="woman" v-model="sex" />女
            </td>
            <td style="text-align: center;">出生日期</td>
            <td style="text-align: center;">
              <input type="date" id="birthday" v-model="birthday" />
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">输入密码</td>
            <td style="text-align: center;">
              <input type="password" maxlength="8" class="form-input" id="password" v-model="password" />
            </td>
            <td style="text-align: center;">确认密码</td>
            <td style="text-align: center;">
              <input type="password" maxlength="8" class="form-input" id="checkpassword" v-model="checkpassword" />
            </td>
          </tr>
          <tr>
            <td>班级编号</td>
            <td style="text-align: center;">
              <input type="text" class="form-input" id="numid" v-model="clazzId">
            </td>
            <td style="text-align: center;">
              <div class="label">学号</div>
            </td>
            <td style="text-align: center;">
              <input type="text" class="form-input" id="reguid" v-model="uid">
            </td>
          </tr>
        </table>
        <input type="button" @click="submitRegisterInfo" class="reg-btn" value="注册">
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Register',
    data() {
      return {
        uid: '',
        username: '',
        sex: '',
        birthday: '',
        password: '',
        checkpassword: '',
        clazzId: '',
        position: '学生'
      }
    },
    methods:{
      closeRegister: function() {
        // console.log("closeRegister被执行了");
        this.$emit('RegisterSon', false);
      },
      submitRegisterInfo: function() {
        let flag = true;
        if(this.uid == ''){
           flag = false;
           alert("请输入学号或工号！");
        }
        if(this.username == ''){
           flag = false;
           alert("请输入用户名");
        }
        if(this.sex == ''){
           flag = false;
           alert("请输入性别");
        }
        if(this.birthday == ''){
           flag = false;
           alert("请输入出生日期");
        }
        if(this.password == ''){
           flag = false;
           alert("请输入密码");
        }
        if(this.checkpassword == ''){
           flag = false;
           alert("请输入确认密码");
        }
        if(this.password == this.checkpassword){
          flag = true;
        }else{
          alert("前后密码不一致！");
        }
        if(this.clazzId == ''){
          flag = false;
          alert("请输入学号或工号中的一个，另一个输入0");
        }

        if(flag){
          if(this.uid.startsWith(this.clazzId)){
            this.$axios({
              url: 'http://localhost:8085/user/sturegister',
              method: 'post',
              data: this.$qs.stringify({
                uId: this.uid,
                username: this.username,
                sex: this.sex,
                password: this.password,
                birthday: this.birthday,
                clazzId: this.clazzId,
                position: this.position
              })
            })
            .then(
              res=>{
                console.log(res);
                // console.log("ustatus=",res.data.ustatus);
                if(res.data == "sturegister success"){
                  this.$emit('RegisterSon',false);
                  this.uid="";
                  this.username="";
                  this.password="";
                  this.checkpassword="";
                  this.sex="";
                  this.birthday="";
                  this.clazzId="";
                  alert("注册成功！");
                }else{
                  alert("该用户已存在！");
                } 
              }
            )
            .catch(err=>console.log(err))
          }else{
            alert("请检查学号前七位是否与班级编号一致!");
          }
        }
      }
    }
  }
  
</script>

<style scoped>
  .reg-card {
    width: 600px;
    height: 300px;
    box-shadow: 0px 0px 3px gray;
    background-color: white;
    border-radius: 5px;
    position: absolute;
    left: 500px;
    top: 220px;
    z-index: 999;
  }
  .register-title {
    width: 100%;
    height: 45px;
    text-align: center;
    font-size: 50px;
    letter-spacing: 50px;
    text-indent: 60px;
    margin-top: 10px;
  }
  .close {
    width: 15px;
    height: 15px;
    cursor: pointer;
    position: relative;
    top: -50px;
    left: 580px;
  }
  .close:hover {
    color: red;
    cursor:pointer;
  }
  .form-table {
    margin: 20px 55px;
  }
  .tab {
    width: 480px;
    border-collapse:collapse;
  }
  .label {
    width: 70px;
    height: 22px;
    letter-spacing: 10px;
    text-align: center;
    text-indent: 10px;
    margin-left: 2px;
  }
  .form-input {
    height: 26px;
    font-size: 14px;
  }
  .form-input:focus {
    outline: none;
  }
  .form-input:first-child {
    width: 154px;
  }
  #username {
    width: 154px;
  }
  #birthday {
    width: 154px;
    height: 30px;
  }
  #id-type {
    width: 76px;
    height: 30px;
  }
  #position {
    width: 154px;
    height: 30px;
  }
  .reg-btn {
    width: 90px;
    height: 30px;
    margin: 20px 0 0 190px;
  }
</style>
