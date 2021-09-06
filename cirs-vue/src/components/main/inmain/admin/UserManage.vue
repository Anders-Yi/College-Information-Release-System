<template>
  <div>
    <!-- 搜索栏部分 -->
    <div class="searchbar">
      <div class="blank">
        <span class="label">用户ID</span>
        <input type="text" class="uid" v-model="uid">
      </div>
      <div class="blank">
        <span class="label">用户名</span>
        <input type="text" class="username" v-model="username">
      </div>
      <div class="blank">
        <span class="label">用户状态</span>
        <select class="user-status" v-model="ustatus">
          <option value="3" selected></option>
          <option value="0">未登录</option>
          <option value="1">已登录</option>
        </select>
      </div>
      <div class="blank">
        <span class="label">角色</span>
        <select class="role" v-model="role">
          <option value="3" selected></option>
          <option value="0">管理员</option>
          <option value="1">教职工</option>
          <option value="2">学生</option>
        </select>
      </div>
      <div class="operation">
        <button class="query-user" @click="findUserByConditions">查询</button>
        <button class="add-user" @click="addUser">新增</button>
      </div>
    </div>

    <!-- 弹窗模块 -->
    <adduser @AddUserSon="getAddUserCloseMsg" v-show="isAddUser"></adduser>
    <updateuser @UpdateUserSon="getUpdateUserCloseMsg" ref="updateuser" v-show="isUpdateUser"></updateuser>

    <!-- 搜索结果显示 -->
    <div class="resultshow">
      <table class="tab" border="1">
        <thead>
          <tr>
            <th>序号</th>
            <th>用户ID</th>
            <th>用户名</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>部门编号</th>
            <th>班级编号</th>
            <th>角色</th>
            <th>职位</th>
            <th>登录状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody v-if="userlist.length != 0">
          <tr v-for="(user,index) in userlist" :key="user.uid">
            <td>{{index+1}}</td>
            <td>{{user.uid}}</td>
            <td>{{user.username}}</td>
            <td>{{user.sex == 0?"男":"女"}}</td>
            <td>{{user.birthday}}</td>
            <td>{{user.departId}}</td>
            <td>{{user.clazzId}}</td>
            <td>{{user.role == 0?"管理员":(user.role == 1?"教职工":"学生")}}</td>
            <td>{{user.position}}</td>
            <td>{{user.ustatus == 0?"未登录":"已登录"}}</td>
            <td>
              <button class="btn btn1" @click="updateUser(user.uid)">修改</button>
              <button class="btn btn2" @click="deleUserByUid(user.uid)">删除</button>
              <button class="btn btn3" @click="resetPassword(user.uid)">重置密码</button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="11">没有符合条件的用户</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import AddUser from '@/components/main/inmain/admin/child/AddUser'
  import UpdateUser from '@/components/main/inmain/admin/child/UpdateUser'

  export default {
    name: 'UserManage',
    mounted(){
      this.getUserList();
    },
    data(){
      return{
        uid: '',
        username: '',
        ustatus: '',
        role: '',
        isAddUser: false,
        isUpdateUser: false,
        userlist: []
      }
    },
    methods:{
      addUser: function () {
        this.isAddUser = true;
        this.isUpdateUser = false;
      },
      updateUser: function (uid) {
        this.isUpdateUser = true;
        this.isAddUser = false;
        this.queryUserByUid(uid);
      },
      queryUserByUid: function(uid){
        this.$axios({
          url: 'http://localhost:8085/user/queryuserbyuid',
          method: 'post',
          data: this.$qs.stringify({
            uid: uid
          })
        })
        .then(res=>{
          this.$refs.updateuser.fillUser(res.data);
        })
        .catch(err=>console.log(err));
      },
      getAddUserCloseMsg: function (data) {
        this.isAddUser = data;
      },
      getUpdateUserCloseMsg: function (data) {
        this.isUpdateUser = data;
      },
      // 获取所有用户
      getUserList: function () {
        this.$axios({
          url: 'http://localhost:8085/user/queryalluser',
          method: 'post',
          data:{}
        })
        .then(res=>{
          // console.log(res.data);
          this.userlist = res.data;
        })
        .catch(err=>console.log(err));
      },
      // 查询用户
      findUserByConditions: function () {
        if(this.uid == ''){
          this.uid = 0;
        }
        if(this.username == ''){
          this.username = "";
        }
        if(this.ustatus == ''){
          this.ustatus = 3;
        }
        if(this.role == ''){
          this.role = 3;
        }
        /* 搜索符合条件的用户 */
        this.$axios({
          url: 'http://localhost:8085/user/queryusers',
          method: 'post',
          data: this.$qs.stringify({
            uid: this.uid,
            username: this.username,
            ustatus: this.ustatus,
            role: this.role
          })
        })
        .then(res=>{
          console.log(res.data);
          this.userlist = res.data;
          this.uid = '';
          this.username = '';
          this.ustatus = "";
          this.role = "";
        })
        .catch(err=>console.log(err))
      },
      // 删除用户
      deleUserByUid: function (uid) {
        let flag = confirm("确定要删除该用户?");
        if(flag){
          this.$axios({
            url: 'http://localhost:8085/user/deleuser',
            method: 'post',
            data: this.$qs.stringify({
              uid: uid
            })
          })
          .then(res=>{
            if(res.data == "cancellate user success"){
              alert("用户删除成功!");
            }else{
              alert("用户删除失败！");
            }
            window.location.reload();
          })
          .catch(err=>console.log(err))
        }   
      },
      // 重置密码
      resetPassword: function (uid) {
        // console.log("uid=",uid);
        this.$axios({
          url: 'http://localhost:8085/user/resetuserpassword',
          method: 'post',
          data: this.$qs.stringify({
            uid: uid
          })
        })
        .then(res=>{
          if(res.data == "reset userpassword success"){
            alert("密码重置成功！");
          }
        })
        .catch(err=>console.log(err))
      }
    },
    components:{
      adduser: AddUser,
      updateuser: UpdateUser
    }
  }
</script>

<style scoped>
  /* 搜索栏 start */
  .searchbar {
    width: 1000px;
    height: 35px;
    margin: 10px auto;
  }
  .blank {
    margin-left: 10px;
    display: inline;
  }
  .label {
    font-size: 18px;
    line-height: 34px;
  }
  .uid {
    width: 180px;
    height: 20px;
    position: relative;
    top: -1px;
  }
  .uid:focus {
    outline: none;
  }
  .username {
    width: 180px;
    height: 20px;
    position: relative;
    top: -1px;
  }
  .username:focus {
    outline: none;
  }
  .user-status {
    width: 75px;
    height: 26px;
    font-size: 16px;
    position: relative;
    top: -1px;
  }
  .user-status:focus {
    outline: none;
  }
  .role {
    width: 75px;
    height: 26px;
    font-size: 16px;
    position: relative;
    top: -1px;
  }
  .role:focus {
    outline: none;
  }
  .operation {
    display: inline;
    position: relative;
    top: -1px;
    left: 10px;
  }
  .query-user {
    width: 80px;
    height: 28px;
    background-color: #28A745;
    color: white;
    border: 0;
    border-radius: 2px;
    font-size: 16px;
    letter-spacing: 10px;
    text-indent: 10px;
  }
  .query-user:focus {
    outline: none;
    opacity: 0.9;
  }
  .add-user {
    width: 80px;
    height: 28px;
    background-color: #6C757D;
    color: white;
    border: 0;
    border-radius: 2px;
    font-size: 16px;
    letter-spacing: 10px;
    text-indent: 10px;
    margin-left: 6px;
  }
  .add-user:focus {
    outline: none;
    opacity: 0.9;
  }
  /* 搜索栏 end */

  /* 搜索结果展示区 start */
  .resultshow {
    width: 1000px;
    height: auto;
    margin: 10px auto;
    /* border: 1px solid black; */
  }
  .tab {
    width: 100%;
    border-collapse: collapse;
  }
  .tab>tbody tr td {
    text-align: center;
    height: 30px;
  }
  .btn {
    width: 70px;
    height: 28px;
    border: none;
    border-radius: 2px;
  }
  .btn:focus {
    opacity: 0.8;
    outline: none;
  }
  .btn1 {
    background-color: #0069D9;
    color: white;
    font-size: 14px;
    letter-spacing: 10px;
    text-indent: 10px;
  }
  .btn2 {
    background-color: #DC3545;
    color: white;
    font-size: 14px;
    letter-spacing: 10px;
    text-indent: 10px;
  }
  .btn3 {
    width: 82px;
    font-size: 14px;
    letter-spacing: 4px;
    text-indent: 4px;
    background-color: #FFC107;
    color: white;
  }
  /* 搜索结果展示区 end */
</style>