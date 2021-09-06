<template>
  <div>
    <div class="updateinfo-card">
      <div class="title">信息详情页</div>
      <div class="close" @click="closeUpdateinformation">X</div>
      <table class="tab">
        <tr>
          <td>信息标题</td>
          <td>
            <input type="text" class="infoTitle" v-model="info.infoTitle" />
          </td>
        </tr>
        <tr>
          <td style="letter-spacing: 8px; text-indent: 8px;">发布者</td>
          <td>
            <div class="username">{{info.username}}</div>
          </td>
        </tr>
        <tr>
          <td>信息内容</td>
          <td>
            <textarea class="infoContent"  v-model="info.infoContent"></textarea>
          </td>
        </tr>
        <tr>
          <td>发布时间</td>
          <td>
            <div class="releaseTime">{{info.releaseTime}}</div>
          </td>
        </tr>
        <tr>
          <td colspan='2'>
            <button class="sendinfo" @click="submitUpdateInfo">修改</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>

export default {
  name: 'UpdateInformation',
  data(){
    return{
      info:{
        infoId: '',
        infoTitle: '',
        infoContent: '',
        releaseTime: '',
        uid: '',
        username: '',
        infoStatus: ''
      }
    }
  },
  methods: {
    closeUpdateinformation: function(){
      this.$emit('UpdateinformationSon',false);
    },
    getInfoByInfoId: function(id){
      this.$axios({
        url: 'http://localhost:8085/info/findinfobyconditions',
        method:'post',
        data: this.$qs.stringify({
          infoId: id
        })
      })
      .then(res=>{
        this.info.infoId = res.data[0].infoId;
        this.info.infoTitle = res.data[0].infoTitle;
        this.info.infoContent = res.data[0].infoContent;
        this.info.releaseTime = res.data[0].releaseTime;
        this.info.uid = res.data[0].uid;
        this.info.username = res.data[0].username;
        this.info.infoStatus = res.data[0].infoStatus;
      })
      .catch(err=>console.log(err));
    },
    submitUpdateInfo: function(){
      console.log("info:",{
        "infoId": this.info.infoId,
        "infoTitle": this.info.infoTitle,
        "infoContent": this.info.infoContent,
        "releaseTime": this.info.releaseTime,
        "uid": this.info.uid,
        "username": this.info.username,
        "infoStatus": this.info.infoStatus
      });
      this.$axios({
        url: 'http://localhost:8085/info/updateinfo',
        method: 'post',
        data: this.$qs.stringify({
          infoId: this.info.infoId,
          infoTitle: this.info.infoTitle,
          infoContent: this.info.infoContent,
          releaseTime: this.info.releaseTime,
          uid: this.info.uid,
          username: this.info.username,
          infoStatus: this.info.infoStatus
        })
      })
      .then(res=>{
        // console.log(res.data);
        if(res.data == 1){
          window.location.reload();
          alert("信息修改成功！");
        }else{
          alert("信息修改失败！");
        }
      })
      .catch(err=>console.log(err));
    }
  }
}
</script>

<style scoped>
.updateinfo-card {
  width: 500px;
  height: 350px;
  border-radius: 10px;
  margin: 0 auto;
  box-shadow: 0 0 5px gray;
  background-color: white;
  position: relative;
  top: -160px;
}
.title {
  width: 220px;
  height: 40px;
  font-size: 30px;
  letter-spacing: 10px;
  text-indent: 10px;
  margin: 0 auto;
  position: relative;
  top: 25px;
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
.tab {
  width: 380px;
  height: auto;
  margin: 28px auto;
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
.username {
  width: 280px;
  height: 30px;
  margin: 0 auto;
  border-bottom: 1px solid black;
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
  margin: 0 auto;
  border-bottom: 1px solid black;
}
.sendinfo {
  width: 80px;
  height: 30px;
  border: none;
  border-radius: 4px;
  font-size: 18px;
  background-color: #FFC107;
  color: white;
  letter-spacing: 10px;
  text-indent: 10px;
}
.sendinfo:focus {
  outline: none;
  opacity: 0.8;
}
</style>