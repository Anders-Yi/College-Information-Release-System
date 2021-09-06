<template>
  <div>
    <div class="addinfo-card">
      <div class="title">添加信息</div>
      <div class="close" @click="closeAddinformation">X</div>
      <table class="tab">
        <tr>
          <td>信息标题</td>
          <td>
            <input type="text" v-model="infoTitle" class="infoTitle">
          </td>
        </tr>
        <tr>
          <td>信息内容</td>
          <td>
            <textarea class="infoContent"  v-model="infoContent"></textarea>
          </td>
        </tr>
        <tr>
          <td>发布时间</td>
          <td>
            <input type="date" v-model="releaseTime" class="releaseTime">
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <button class="addinfo" @click="submitAddinformation">确认添加</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AddInformation',
  mounted(){
    let userstr = localStorage.getItem("loginsuccess");
    // console.log("userjson:",userstr);
    if(userstr != null){
      let userobj = JSON.parse(userstr);
      this.user = userobj;
      // console.log("userobj:",userobj,"登录状态:",userobj.ustatus == 1?"已登录":"未登录");
    }
    // this.getCurrentDate();
  },
  data(){
    return{
      infoTitle: '',
      infoContent: '',
      releaseTime: '',
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
    closeAddinformation: function(){
      this.$emit('AddinformationSon',false);
    },
    getCurrentDate: function(){
      var mydate = new Date();
      var year = mydate.getFullYear();
      var month = mydate.getMonth()+1;
      if(month<10){
        month = "0" + month;
      }
      var day = mydate.getDate();
      if(day < 10){
        day = "0" + day;
      }
      var res = year + "-" + month + "-" + day;
      this.releaseTime = res;
    },
    submitAddinformation: function(){
      this.$axios({
        url: 'http://localhost:8085/info/sendinfo',
        method: 'post',
        data: this.$qs.stringify({
          infoTitle: this.infoTitle,
          infoContent: this.infoContent,
          releaseTime: this.releaseTime,
          uId: this.user.uid,
          username: this.user.username,
          infoStatus: 0
        })
      })
      .then(res=>{
        console.log(res.data);
        if(res.data == 1){
          window.location.reload();
          alert("信息添加成功！");
        }else{
          alert("信息添加失败！");
        }
      })
      .catch(err=>console.log(err));
    }
  }
}
</script>

<style scoped>
.addinfo-card {
  width: 500px;
  height: 350px;
  border-radius: 10px;
  box-shadow:  0 0 5px gray;
  background-color: white;
  position: absolute;
  top: 180px; 
  left: 500px; 
  z-index: 999;
}
.title {
  width: 220px;
  height: 40px;
  font-size: 30px;
  letter-spacing: 10px;
  text-indent: 10px;
  /* margin: 0 auto; */
  position: relative;
  top: 25px;
  left: 170px;
}
.close {
  width: 20px;
  height: 20px;
  text-align: center;
  position: relative;
  top: -30px;
  left: 470px;
}
.close:hover {
  color: red;
}
.tab{
  width: 380px;
  height: auto;
  margin: 28px auto;
  border-collapse:separate; 
  border-spacing:0px 10px;
}
tr {
  height: 32px;
}
tr:last-child {
  height: 50px;
}
td {
  text-align: center;
}
.infoTitle {
  width: 280px;
  height: 30px;
}
.infoTitle:focus {
  outline: none;
}
.infoContent {
  width: 280px;
  height: 80px;
  font-size: 18px;
}
.infoContent:focus {
  outline: none;
}
.releaseTime {
  width: 280px;
  height: 30px;
  letter-spacing: 10px;
  text-indent: 18px;
}
.releaseTime:focus {
  outline: none;
}
.addinfo {
  width: 110px;
  height: 32px;
  border: none;
  border-radius: 4px;
  font-size: 18px;
  background-color: #FFC107;
  color: white;
  letter-spacing: 5px;
  text-indent: 5px;
}
.addinfo:focus {
  outline: none;
  opacity: 0.8;
}
</style>