<template>
  <div id="adduser">
    <div class="updateuser-card">
      <div class="updateuser-title">修改学生信息</div>
      <span class="close" @click="closeUpdateStudent">X</span>
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
              <input style="margin-left: 35px;" name="sex" type="radio" value="0" v-model="sex" />男
              <input type="radio" name="sex" value="1" v-model="sex" />女
            </td>
            <td style="text-align: center;">出生日期</td>
            <td style="text-align: center;">
              <input type="date" class="form-input" v-model="birthday" />
            </td>
          </tr>
          <tr>
            <td style="text-align: center;">密码</td>
            <td style="text-align: center;">
              <input type="password" maxlength="8" class="form-input" v-model="password" />
            </td>
            <td style="text-align: center;">用户状态</td>
            <td style="text-align: center;">
              <select class="ustatus" v-model="ustatus">
                <option value="0">未登录</option>
                <option value="1">已登录</option>
              </select>
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
        <input type="button" @click="submitUpdateUser" class="updateuser-btn" value="修改用户">
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UpdateStudent',
  data() {
    return {
      uid: '',
      username: '',
      sex: '',
      birthday: '',
      password: '',
      departId: '',
      clazzId: '',
      role: '',
      position: '',
      ustatus: ''
    }
  },
  methods:{
    closeUpdateStudent: function() {
      // console.log("closeUpdateUser被执行了");
      this.$emit('UpdateStudentSon', false);
    },
    fillStudent: function(user){
      this.uid = user.uid;
      this.username = user.username;
      this.sex = user.sex;
      this.birthday = user.birthday;
      this.password = user.password;
      this.departId = user.departId;
      this.clazzId = user.clazzId;
      this.role = user.role;
      this.position = user.position;
      this.ustatus = user.ustatus;
    },
    submitUpdateUser: function() {
      /* 添加用户信息打印 start */
      // console.log({
      //   "uid": this.uid,
      //   "username": this.username,
      //   "sex": this.sex,
      //   "birthday": this.birthday,
      //   "password": this.password,
      //   "checkpassword": this.checkpassword,
      //   "departId": this.departId,
      //   "clazzId": this.clazzId,
      //   "role": this.role,
      //   "position": this.position,
      //   "ustatus": this.ustatus
      // });
      /* 添加用户信息打印 end */
      let flag = true;
      if(this.uid == ''){
        flag = false;
        console.log("uid:flag=",flag);
      }
      if(this.username == ''){
        flag = false;
        console.log("username:flag=",flag);
      }
      if(this.sex == '' && this.sex != 0){
        flag = false;
        console.log("sex:flag=",flag,"sex=",this.sex);
      }
      if(this.birthday == ''){
        flag = false;
        console.log("birthday:flag=",flag);
      }
      if(this.password == ''){
        flag = false;
        console.log("password:flag=",flag);
      }
      if(this.departId == '' && this.departId != 0){
        flag = false;
        console.log("departId:flag=",flag);
      }
      if(this.clazzId == ''){
        flag = false;
        console.log("clazzId:flag=",flag);
      }
      if(this.role == ''){
        flag = false;
        console.log("role:flag=",flag);
      }
      if(this.position == ''){
        flag = false;
        console.log("position:flag=",flag);
      }

      if(flag){
        this.$axios({
          url: 'http://localhost:8085/user/modifyuserinfo',
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
            position: this.position,
            ustatus: this.ustatus
          })
        })
        .then(
          res=>{
            console.log(res.data);
            if(res.data == "modify userinfo success"){
              alert("修改成功！");
              this.$emit('UpdateStudentSon', false);
              window.location.reload();
            }
          }
        )
        .catch(err=>console.log(err))
      }
    }
  }
}
</script>

<style scoped>
  .updateuser-card {
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
  .updateuser-title {
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
  .ustatus {
    width: 158px;
    height: 30px;
    font-size: 14px;
    letter-spacing: 15px;
    text-indent: 30px;
  }
  .ustatus:focus {
    outline: none;
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
  .role:focus {
    outline: none;
  }
  #position {
    width: 154px;
    height: 30px;
  }
  .updateuser-btn {
    width: 90px;
    height: 30px;
    margin: 20px 0 0 190px;
  }
</style>