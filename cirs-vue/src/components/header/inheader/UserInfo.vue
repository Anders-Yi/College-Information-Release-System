<template>
  <div>
    <div class="userinfocard">
      <span class="close" @click="closeUserInfo">X</span>
      <h1 class="userinfo-title">用户信息</h1>
      <table class="tab">
        <tr>
          <td class="label" style="letter-spacing: 5px;" width="70px">用户<span style="letter-spacing: 0px;">ID</span></td>
          <td colspan='3'>
            <input type="text" class="inputgroup" maxlength="50" style="width: 390px;" v-model="user.uid">
          </td>
        </tr>
        <tr>
          <td class="label" style="letter-spacing: 5px;">用户名</td>
          <td>
            <input type="text" maxlength="5" class="inputgroup" v-model="user.username">
          </td>
          <td width="70px" class="label" style="text-align: center; letter-spacing: 10px; text-indent: 5px;">性别</td>
          <td>
            <input type="text" maxlength="1" class="inputgroup" v-model="user.sex">
          </td>
        </tr>
        <tr>
          <td class="label">出生日期</td>
          <td>
            <input type="date" maxlength="8" style="width: 157px;" class="inputgroup" v-model="user.birthday">
          </td>
          <td class="label">{{user.departId==0?'班级编号':'部门编号'}}</td>
          <td>
            <input type="text" maxlength="7" class="inputgroup" v-if="user.clazzId==0" v-model="user.departId">
            <input type="text" maxlength="7" class="inputgroup" v-else v-model="user.clazzId">
          </td>
        </tr>
        <tr>
          <td class="label">身份类型</td>
          <td>
            <div class="textarea">{{this.user.position}}</div>
          </td>
          <td class="label">用户状态</td>
          <td>
            <div class="textarea">{{this.user.ustatus}}</div>
          </td>
        </tr>
        <tr>
          <td colspan='4' class="btns">
            <button class="modify" @click="modifyuserinfo">修改</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  mounted(){
    let userstr = localStorage.getItem("loginsuccess");
    if(userstr != null){
      let userobj = JSON.parse(userstr);
      this.user = userobj;
      this.user.sex = this.user.sex==0?'男':'女';
      this.user.ustatus = this.user.ustatus==1?'已登录':'未登录';
      // console.log("ustatus=",this.user.ustatus);
    }
  },
  data(){
    return{
      user:{
        uid: '',
        username: '',
        sex: '',
        birthday: '',
        departId: '',
        clazzId: '',
        role: '',
        position: '',
        ustatus: ''
      } 
    }
  },
  methods:{
    closeUserInfo: function () {
      this.$emit('UserInfoSon',false);
    },
    modifyuserinfo: function () {
      this.user.sex = (this.user.sex=="男"?0:1);
      this.user.ustatus = (this.user.ustatus=="已登录"?1:0);
      console.log({
        "uid": this.user.uid,
        "username": this.user.username,
        "sex": this.user.sex,
        "birthday": this.user.birthday,
        "departId": this.user.departId,
        "clazzId": this.user.clazzId
      })

      this.$axios({
        url: 'http://localhost:8085/user/modifyuserbaseinfo',
        method: 'post',
        data: this.$qs.stringify({
          uId: this.user.uid,
          username: this.user.username,
          sex: this.user.sex,
          birthday: this.user.birthday,
          departId: this.user.departId,
          clazzId: this.user.clazzId
        })
      })
      .then(res=>{
        if(res.data == "modify userbaseinfo success"){
          localStorage.setItem('loginsuccess',JSON.stringify(this.user));
          this.$emit('UserInfoSon',false); 
          alert("修改用户信息成功！");
          window.location.reload();
        }else{
          alert("修改用户信息失败，请重新输入");
        }
      })
      .catch(err=>console.log(err));
    }
  }
}
</script>

<style scoped>
.userinfocard {
  width: 550px;
  height: 300px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0px 0px 3px gray;
  position: absolute;
  left: 500px;
  top: 220px;
  z-index: 999;
}
.close {
  width: 10px;
  height: 10px;
  position: relative;
  left: 530px;
  top: 5px;
}
.close:hover {
  color: red;
  cursor:pointer;
}
.userinfo-title {
  width: 200px;
  margin: 0 auto;
  letter-spacing: 14px;
  text-indent: 14px;
}
.tab {
  margin: 25px auto;
}
.label {
  text-align: center;
}
.inputgroup {
  height: 26px;
  text-indent: 5px;
}
.inputgroup:focus {
  outline: 0;
}
.textarea {
  width: 160px; 
  letter-spacing: 15px; 
  text-indent: 15px;
  text-align: center;
  border-bottom: 1px solid black;
}
.btns {
  height: 30px;
  text-align: center;
}
.modify {
  width: 90px;
  height: 30px;
  background-color: #0069D9;
  font-size: 18px;
  letter-spacing: 10px;
  text-indent: 10px;
  border-radius: 4px;
  margin: 20px 0 10px 0;
  cursor: pointer;
}
.modify:focus {
  outline: 0;
}
</style>