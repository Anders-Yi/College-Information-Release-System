<template>
  <div id="adduser">
    <div class="adduser-card">
      <div class="adduser-title">添加学生</div>
      <span class="close" @click="closeAddStudent">X</span>
      <form action="#" method="post" class="form-table">
        <table class="tab" border="1">
          <tr>
            <td style="text-align: center;">
              <div class="label">姓名</div>
            </td>
            <td style="text-align: center;">
              <input type="text" class="form-input" v-model="username" />
            </td>
            <td style="text-align: center;">
              <div class="label">学号</div>
            </td>
            <td style="text-align: center;">
              <input type="text" class="form-input" v-model="uid">
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">
              <div class="label">性别</div>
            </td>
            <td style="letter-spacing: 10px;">
              <input style="margin-left: 35px;" type="radio" value="0" v-model="sex" />男
              <input type="radio" value="1" v-model="sex" />女
            </td>
            <td style="text-align: center;">出生日期</td>
            <td style="text-align: center;">
              <input type="date" class="form-input" v-model="birthday" />
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">输入密码</td>
            <td style="text-align: center;">
              <input type="password" maxlength="8" class="form-input" v-model="password" />
            </td>
            <td style="text-align: center;">确认密码</td>
            <td style="text-align: center;">
              <input type="password" maxlength="8" class="form-input" v-model="checkpassword" />
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">班级编号</td>
            <td style="text-align: center;">
              <input type="text" class="form-input" placeholder="有则填，无则填0" v-model="clazzId">
            </td>
            <td style="text-align: center;">部门编号</td>
            <td style="text-align: center;">
              <input type="text" class="form-input" placeholder="有则填，无则填0" v-model="departId">
            </td>
          </tr>
          <tr>
            <td>
              <div class="label">角色</div>
            </td>
            <td style="text-align: center;">
              <select class="role" v-model="role">
                <!-- <option value="0">管理员</option>
                <option value="1">教职工</option> -->
                <option value="2">学生</option>
              </select>
            </td>
            <td>
              <div class="label">职位</div>
            </td>
            <td style="text-align: center;">
              <input type="text" class="form-input" v-model="position" />
            </td>
          </tr>
        </table>
        <input type="button" @click="submitAddStudent" class="adduser-btn" value="添加学生">
      </form>
    </div>
  </div>
</template>

<script>
export default{
  name: 'AddStudent',
  data() {
      return {
        uid: '',
        username: '',
        sex: '',
        birthday: '',
        password: '',
        checkpassword: '',
        departId: '',
        clazzId: '',
        role: '',
        position: ''
      }
    },
    methods:{
      closeAddStudent: function() {
        // console.log("closeRegister被执行了");
        this.$emit('AddStudentSon', false);
      },
      submitAddStudent: function() {
        /* 添加用户信息打印 start */
        console.log({
          "uid=": this.uid,
          "username=": this.username,
          "sex=": this.sex,
          "birthday=": this.birthday,
          "password=": this.password,
          "checkpassword=": this.checkpassword,
          "departId": this.departId,
          "clazzId=": this.clazzId,
          "role": this.role,
          "position=": this.position
        });
        /* 添加用户信息打印 end */
        let flag = true;
        if(this.uid == ''){
           flag = false;
           console.log("flag=",flag);
           return ;
        }
        if(this.username == ''){
           flag = false;
           console.log("flag=",flag);
           return ;
        }
        if(this.sex == ''){
           flag = false;
           console.log("flag=",flag);
           return ;
        }
        if(this.birthday == ''){
           flag = false;
           console.log("flag=",flag);
           return ;
        }
        if(this.password == '' && this.checkpassword == ''){
           flag = false;
           console.log("flag=",flag);
           return ;
        }
        if(this.departId == ''){
          flag = false;
          console.log("flag=",flag);
          return ;
        }
        if(this.clazzId == ''){
            flag = false;
            console.log("flag=",flag);
            return ;
        }
        if(this.role == ''){
          flag = false;
          console.log("flag=",flag);
          return ;
        }
        if(this.position == ''){
            flag = false;
            console.log("flag=",flag);
            return ;
        }
        if(this.password == this.checkpassword){
           flag = true;
           console.log("flag=",flag);
        }
        
        // 学生注册
        if(flag && this.departId == '0'){
          console.log("进入了学生注册");
          if(this.uid.startsWith(this.clazzId)){
            console.log("发送学生注册请求",this.uid.startsWith(this.clazzId));
            this.$axios({
              url: 'http://localhost:8085/user/register',
              method: 'post',
              data: this.$qs.stringify({
                uId: this.uid,
                username: this.username,
                sex: this.sex,
                birthday: this.birthday,
                password: this.password,
                departId: this.departId,
                clazzId: this.clazzId,
                role: this.role,
                position: this.position
              })
            })
            .then(
              res=>{
                console.log(res.data);
                if(res.data == "register success"){
                  this.$emit('AddUserSon', false);
                  this.username="";
                  this.uid="";
                  this.sex="";
                  this.birthday="";
                  this.password="";
                  this.checkpassword="";
                  this.clazzId="";
                  this.departId="";
                  this.role = "";
                  this.position="";
                  alert("添加学生成功！");
                  this.$emit('AddStudentSon', false);
                  window.location.reload();
                }else{
                  alert("添加学生失败，请重新添加！");
                }
              }
            )
            .catch(err=>console.log(err))
          }else{
            alert("请检查用户编号前七位是否与班级编号一致!");
          }
        }
        // 教职工或管理员注册
        if(flag && this.clazzId == '0'){
          console.log("进入了教职工或管理员注册");
          if(this.uid.startsWith(this.departId)){
            console.log("发送教职工或管理员请求",this.uid.startsWith(this.departId));
            this.$axios({
              url: 'http://localhost:8085/user/register',
              method: 'post',
              data: this.$qs.stringify({
                uId: this.uid,
                username: this.username,
                sex: this.sex,
                birthday: this.birthday,
                password: this.password,
                departId: this.departId,
                clazzId: this.clazzId,
                role: this.role,
                position: this.position
              })
            })
            .then(
              res=>{
                console.log(res.data);
                if(res.data == "register success"){
                  this.$emit('AddUserSon', false);
                  this.username="";
                  this.uid="";
                  this.sex="";
                  this.birthday="";
                  this.password="";
                  this.checkpassword="";
                  this.clazzId="";
                  this.departId="";
                  this.role = "";
                  this.position="";
                  alert("注册成功！");
                  this.$emit('AddStudentSon', false);
                  window.location.reload();
                }else{
                  alert("注册失败，请重新注册！");
                }
              }
            )
            .catch(err=>console.log(err))
          }else{
            alert("请检查用户编号前七位是否与部门编号一致!");
          }
        }
      }
    }
}
</script>

<style scoped>
  .adduser-card {
    width: 600px;
    height: 300px;
    box-shadow: 0px 0px 3px gray;
    background-color: white;
    border-radius: 5px;
    position: absolute;
    left: 500px;
    top: 250px;
    z-index: 999;
  }
  .adduser-title {
    width: 100%;
    height: 45px;
    text-align: center;
    font-size: 35px;
    letter-spacing: 50px;
    text-indent: 60px;
    margin-top: 14px;
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
    margin-top: -25px;
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
  .role {
    width: 158px;
    height: 30px;
    font-size: 14px;
    letter-spacing: 15px;
    text-indent: 30px;
  }
  #position {
    width: 154px;
    height: 30px;
  }
  .adduser-btn {
    width: 90px;
    height: 30px;
    margin: 20px 0 0 190px;
  }
</style>