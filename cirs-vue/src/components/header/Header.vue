<template>
  <div>
    <div class="v-header">
       <div class="sys-name">高校信息发布系统</div>

       <div class="btnsgroup1" v-if="isLoginStatus == 0">
         <span class="link" @click="changeLoginStatus">登录</span>
         <span class="link" @click="changeRegisterStatus">注册</span>
       </div>
       <div class="btnsgroup2" v-if="isLoginStatus == 1">
         <select class="userbox" v-model="selected" @change="getValueOfSelect(selected)">
           <option value="username">您好，{{user.username}}</option>
           <option value="userinfo">用户信息</option>
           <option value="modifypassword">修改密码</option>
           <option value="logout">退出</option>
         </select>
       </div>

       <login @LoginSon="getLoginCloseMsg" v-show="isLogin"></login>
       <register @RegisterSon="getRegisterCloseMsg" v-show="isRegister"></register>
       <userinfo @UserInfoSon="getUserInfoCloseMsg" v-show="isUserInfo"></userinfo>
       <modifypassword @ModifyPasswordSon="getModifyPasswordCloseMsg" v-show="isModifyPassword"></modifypassword>
    </div>
  </div>
</template>

<script>
  import Login from '@/components/Login'
  import Register from '@/components/Register'
  import UserInfo from '@/components/header/inheader/UserInfo'
  import ModifyPassword from '@/components/header/inheader/ModifyPassword'

  export default {
    name: 'Header',
    mounted(){  
      let userstr = localStorage.getItem("loginsuccess");
      // console.log("userjson:",userstr);
      if(userstr != null){
        let userobj = JSON.parse(userstr);
        this.user = userobj;
        // console.log("userobj:",userobj,"登录状态:",userobj.ustatus == 1?"已登录":"未登录");
        if(userobj == null){
          this.isLoginStatus = 0;
        }else{
          this.user.username = userobj.username;
          this.isLoginStatus = userobj.ustatus;
        }
      }  
    },
    data() {
      return {
        isLogin: false,
        isLoginStatus: 0,
        isRegister: false,
        isUserInfo: false,
        isModifyPassword: false,
        selected: 'username',
        user:{
          uid: '',
          username: '',
          sex: '',
          password: '',
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
      changeLoginStatus: function() {
        this.isLogin = !this.isLogin;
        this.isRegister = false;
      },
      changeRegisterStatus: function() {
        this.isRegister = !this.isRegister;
        this.isLogin = false;
      },
      getLoginCloseMsg: function (data) {
        // console.log("isLogin:",data);
        this.isLogin = data.loginSon;
      },
      getRegisterCloseMsg: function (data) {
        // console.log("isRegister:",data);
        this.isRegister = data.registerSon;
      },
      getUserInfoCloseMsg: function (data){
        // console.log("isUserInfo",data);
        this.isUserInfo = data.userinfoSon;
      },
      getModifyPasswordCloseMsg: function (data) {
        // console.log("isModifyPassword",data);
        this.isModifyPassword = data.modifypasswordSon;
      },
      getValueOfSelect: function (command) {
        // console.log(command);
        switch(command){
          case 'username':
             this.selected = 'username';
             break;
          case 'userinfo':
            this.selected = 'username';
            this.isUserInfo = true;
            this.isModifyPassword = false;
            break;
          case 'modifypassword':
            this.selected = 'username';
            this.isModifyPassword = true;
            this.isUserInfo = false;
            break;
          case 'logout':
            this.selected = 'username';
            this.$axios({
              url: 'http://localhost:8085/user/logout',
              method: 'post',
              data: this.$qs.stringify({
                  uid: this.user.uid
              })
            })
            .then(
              res=>{
                console.log(res);
                console.log(res.data);
                localStorage.removeItem("loginsuccess");
                console.log("localStorage删除成功");
                this.$router.push('/home');
                window.location.reload();
              }
            )
            .catch(err=>console.log(err))
            break;
        }
      }
    },
    components: {
      login: Login,
      register: Register,
      userinfo: UserInfo,
      modifypassword: ModifyPassword
    }
  }
</script>

<style scoped>
  .v-header {
    width: 100%;
    height: 90px;
    background-color: #1E90FF;
  }
  .sys-name {
    width: 290px;
    height: 48px;
    font-size: 35px;
    text-align: center;
    position: absolute;
    left: 10px;
    top: 20px;
  }
  .btnsgroup1 {
    width: 100px;
    height: 35px;
    position: absolute;
    right: 0;
    top: 35px;
  }
  .btnsgroup2 {
    width: 100px;
    height: 35px;
    position: absolute;
    right: 50px;
    top: 32px;
  }
  .userbox {
    width: 142px;
    height: 30px; 
    background-color: #1E90FF;
    color: black;
    font-size: 20px;
    border: none;
    outline: none;
    cursor: pointer;
  }
  .userbox option {
    cursor: pointer;
  }
  .link {
    text-decoration: none;
    color: black;
  }
  .link:hover {
    color: white;
  }

</style>
