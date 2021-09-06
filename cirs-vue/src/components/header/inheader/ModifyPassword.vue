<template>
  <div>
    <div class="modifypasswordcard">
      <span class="close" @click="closeModifyPassword">X</span>
      <h1 class="modifypassword-title">修改密码</h1>
      <table class="tab">
        <tr>
          <td style="letter-spacing: 5px; text-indent: 5px;">旧密码</td>
          <td>
            <input type="password" v-model="oldpassword" maxlength="20" class="inputgroup" />
          </td>
        </tr>
        <tr>
          <td style="letter-spacing: 5px; text-indent: 5px;">新密码</td>
          <td>
            <input type="password" v-model="newpassword" maxlength="20" class="inputgroup" />
          </td>
        </tr>
        <tr>
          <td style="text-align: center;">确认密码</td>
          <td>
            <input type="password" v-model="checkpassword" maxlength="20" class="inputgroup" />
          </td>
        </tr>
        <tr>
          <td colspan="2" style="text-align: center;">
            <button class="submitnewpwd" @click="modifypwd">修改确认</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import md5 from 'js-md5';
export default {
  name: 'ModifyPassword',
  mounted(){
    let userstr = localStorage.getItem("loginsuccess");
    if(userstr != null){
      let userobj = JSON.parse(userstr);
      this.user = userobj;
    }
  },
  data(){
    return{
      oldpassword: '',
      newpassword: '',
      checkpassword: '',
      user: {
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
  methods: {
    closeModifyPassword: function () {
      this.$emit('ModifyPasswordSon',false);
    },
    modifypwd: function () {
      this.oldpassword = md5(this.oldpassword);
      // console.log("oldpassword=",this.oldpassword);     
      // console.log("this.user.password=",this.user.password);
      // console.log("旧密码核对:",this.oldpassword == this.user.password);
      // console.log("新密码核对:",this.newpassword == this.checkpassword);
      if(this.oldpassword == this.user.password && "新密码核对:",this.newpassword == this.checkpassword){
        this.$axios({
          url: 'http://localhost:8085/user/modifyuserpassword',
          method: 'post',
          data: this.$qs.stringify({
            uid: this.user.uid,
            password: this.newpassword
          })
        })
        .then(res=>{
          console.log(res.data);
          if(res.data == "modify password success"){
            this.$emit('ModifyPasswordSon',false);
            this.oldpassword = '';
            this.newpassword = '';
            this.checkpassword = '';
            alert("密码修改成功！");
          }else{
            alert("修改密码失败，请重新输入");
          }
        })
        .catch(err=>console.log(err));
      }else{
        alert("修改密码失败，请重新输入");
      }
    }
  }
}
</script>

<style scoped>
  .modifypasswordcard {
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
  .modifypassword-title {
    width: 200px;
    margin: 0 auto;
    letter-spacing: 14px;
    text-indent: 14px;
  }
  .tab {
    border-collapse:separate;
    border-spacing:18px 10px;
    margin: 25px auto;
  }
  .inputgroup {
    width: 220px;
    height: 26px;
    text-indent: 5px;
  }
  .inputgroup:focus {
    outline: 0;
  }
  .submitnewpwd {
    width: 110px;
    height: 30px;
    background-color: #0069D9;
    font-size: 18px;
    letter-spacing: 5px;
    text-indent: 5px;
    border-radius: 4px;
    margin: 10px 0 10px 0;
    cursor: pointer;
  }
</style>