<template>
  <div>
     <div class="nav">
        <router-link class="item" to="/home">&nbsp;&nbsp;&nbsp;首&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;</router-link>
        <router-link v-if="this.user.position == '管理员'" class="item" to="/admin/usermanage">用户管理</router-link>
        <router-link v-if="this.user.position == '行政老师'" class="item" to="/teacher/studentmanage">学生管理</router-link>
        <router-link v-if="this.user.position == '宣传老师' || this.user.position == '行政老师'" class="item" to="/teacher/infomanage">信息管理</router-link>
        <router-link class="item" to="/campusinfo">校园信息</router-link>
     </div>
     <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'Main',
    mounted(){
       let userstr = localStorage.getItem("loginsuccess");
      // console.log("userjson:",userstr);
      if(userstr != null){
        let userobj = JSON.parse(userstr);
        this.user = userobj;
        // console.log("user:",this.user);
      }
    },
    data(){
      return{
        user:{
          uid: '',
          username: '',
          sex: '',
          password: '',
          birthday: '',
          departId: '',
          clazzId: '',
          position: '',
          ustatus: ''
        }
      }
    },
    methods:{

    }
  }
</script>

<style scoped>
  .nav {
    width: 100%;
    height: 50px;
    text-align: center;
    border-bottom: 1px solid black;
    display: flex;
    justify-content: center;
  }
  .item {
    width: 150px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    text-decoration: none;
    border-right: 1px solid black;
    color: black;
    margin-top: 5px;
  }
  .item:last-child {
    border-right: none;
  }
  .router-link-active {
    color: blue;
  }
</style>
