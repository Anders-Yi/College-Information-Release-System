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
          <option selected></option>
          <option value="0">未登录</option>
          <option value="1">已登录</option>
        </select>
      </div>
      <div class="operation">
        <button class="query-user" @click="findStudentByConditions">查询</button>
        <button class="add-user" @click="addStudent">新增</button>
      </div>
    </div>

    <!-- 弹窗模块 -->
    <adduser @AddStudentSon="getAddStudentCloseMsg" v-show="isAddStudent"></adduser>
    <updateuser @UpdateStudentSon="getUpdateStudentCloseMsg" ref="updatestudent" v-show="isUpdateStudent"></updateuser>

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
        <tbody v-if="stulist.length != 0">
          <tr v-for="(stu,index) in stulist" :key="stu.uid">
            <td>{{index+1}}</td>
            <td>{{stu.uid}}</td>
            <td>{{stu.username}}</td>
            <td>{{stu.sex == 0?"男":"女"}}</td>
            <td>{{stu.birthday}}</td>
            <td>{{stu.departId}}</td>
            <td>{{stu.clazzId}}</td>
            <td>{{stu.role == 0?"管理员":(stu.role == 1?"教职工":"学生")}}</td>
            <td>{{stu.position}}</td>
            <td>{{stu.ustatus == 0?"未登录":"已登录"}}</td>
            <td>
              <button class="btn btn1" @click="updateStudent(stu.uid)">修改</button>
              <button class="btn btn2" @click="deleStudentByUid(stu.uid)">删除</button>
              <button class="btn btn3" @click="resetPassword(stu.uid)">重置密码</button>
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
    <!-- <button @click="test">测试按钮</button> -->
  </div>
</template>

<script>
import AddUser from '@/components/main/inmain/teacher/child/AddStudent'
import UpdateUser from '@/components/main/inmain/teacher/child/UpdateStudent'

export default {
  name: 'StudentManage',
  mounted(){
    this.getStuList();
  },
  data(){
    return{
      uid: '',
      username: '',
      ustatus: '',
      role: '',
      isAddStudent: false,
      isUpdateStudent: false,
      stulist: []
    }
  },
  methods:{
    addStudent: function () {
      this.isAddStudent = true;
      this.isUpdateStudent = false;
    },
    updateStudent: function (uid) {
      this.isUpdateStudent = true;
      this.isAddStudent = false;
      this.queryStudentByUid(uid);
    },
    queryStudentByUid: function(uid){
      // console.log("uid=",uid);
      this.$axios({
        url: 'http://localhost:8085/user/queryuserbyuid',
        method: 'post',
        data: this.$qs.stringify({
          uid: uid
        })
      })
      .then(res=>{
        // console.log("user:",res.data);
        this.$refs.updatestudent.fillStudent(res.data);
      })
      .catch(err=>console.log(err));
    },
    getAddStudentCloseMsg: function (data) {
      // console.log("isAddUser:",data);
      this.isAddStudent = data;
    },
    getUpdateStudentCloseMsg: function (data) {
      // console.log("isUpdateUser:",data);
      this.isUpdateStudent = data;
    },
    // 获取所有学生用户
    getStuList: function () {
      this.$axios({
        url: 'http://localhost:8085/user/queryallstu',
        method: 'post',
        data:{}
      })
      .then(res=>{
        // console.log(res.data);
        this.stulist = res.data;
        // this.$forceUpdate();
      })
      .catch(err=>console.log(err));
    },
    // 通过条件查询学生用户
    findStudentByConditions: function () {
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
          this.role = 2;
        }
        // console.log("conditions:",{
        //   "uid": this.uid,
        //   "username": this.username,
        //   "ustatus": this.ustatus,
        //   "role": this.role
        // });
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
          this.stulist = res.data;
          this.uid = '';
          this.username = '';
          this.ustatus = '';
        })
        .catch(err=>console.log(err))
    },
    // 删除用户
    deleStudentByUid: function (uid) {
      // console.log("uid=",uid);
      let flag = confirm("确定要删除该学生?");
      if(flag){
        this.$axios({
          url: 'http://localhost:8085/user/deleuser',
          method: 'post',
          data: this.$qs.stringify({
            uid: uid
          })
        })
        .then(res=>{
          console.log(res.data);
          if(res.data == "cancellate user success"){
            alert("删除用户成功！");
            window.location.reload();
          }else{
            alert("删除用户失败！");
            window.location.reload();
          }  
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
        console.log(res.data);
        if(res.data == "reset userpassword success"){
          alert("重置密码成功！");
        }else{
          alert("重置密码失败！");
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
    /* border: 1px solid black; */
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
  /* .role {
    width: 75px;
    height: 26px;
    font-size: 16px;
    position: relative;
    top: -1px;
  }
  .role:focus {
    outline: none;
  } */
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