<template>
  <div>
    <!-- 搜索框界面 -->
    <div class="searchbox">
      <div class="item">
        <span class="label">信息标题</span>
        <input type="text" v-model="infoTitle" class="infotitle" />
      </div>
      <div class="item">
        <span class="label">作者</span>
        <input type="text" v-model="author" class="author" />
      </div>
      <div class="item">
        <input type="button" class="findinfo" @click="findInfoByConditions" value="查询" />
      </div>
    </div>
    
    <!-- 发布信息界面 -->
    <div class="sendinfoarea" v-if="user.position == '宣传老师'">
      <div class="title">发布信息</div>
      <button class="addinfo" @click="changeAddinformation">添加信息</button>
      <div class="infolist">
        <table class="tab tab1" border="1">
          <thead>
            <tr>
              <th>序号</th>
              <th>信息编号</th>
              <th>信息标题</th>
              <th>发布时间</th>
              <th>发布人</th>
              <th>信息状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody v-if="infolist1.length != 0">
            <tr v-for="(info,index) in infolist1" :key=info.infoId>
              <td>{{index+1}}</td>
              <td>{{info.infoId}}</td>
              <td>{{info.infoTitle}}</td>
              <td>{{info.releaseTime}}</td>
              <td>{{info.username}}</td>
              <td>{{info.infoStatus == 0?"待审核":(info.infoStatus == 1?"通过":"未通过")}}</td>
              <td>
                <button class="details" @click="changeUpdateinformation(info.infoId)">详情</button>
                <button class="deleinfo" @click="deleinfoByInfoId(info.infoId)">删除</button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="7">没有信息了</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 添加信息组件 -->
    <addinformation @AddinformationSon="getCloseAddinformationMsg" ref="myaddinfo" v-show="isAddinformation"></addinformation>
    <!-- 修改信息组件 -->
    <updateinformation @UpdateinformationSon="getCloseUpdateinformationMsg" ref="myupdateinfo" v-show="isUpdateinformation"></updateinformation>

    <!-- 审核信息界面 -->
    <div class="auditinfoarea" v-if="user.position == '行政老师'">
      <!-- <div class="title">审核信息</div> -->
      <div class="auditarea">
        <div class="audit-title">待审核</div>
        <div class="infolist">
          <table class="tab tab2" border="1">
          <thead>
            <tr>
              <th>序号</th>
              <th>信息编号</th>
              <th>信息标题</th>
              <th>发布时间</th>
              <th>发布人</th>
              <th>信息状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody v-if="infolist2.length != 0">
            <tr v-for="(info,index) in infolist2" :key=info.infoId>
              <td>{{index+1}}</td>
              <td>{{info.infoId}}</td>
              <td>{{info.infoTitle}}</td>
              <td>{{info.releaseTime}}</td>
              <td>{{info.username}}</td>
              <td>{{info.infoStatus == 0?"初始化":(info.infoStatus == 1?"通过":"未通过")}}</td>
              <td>
                <button class="pass" @click="auditInfoStatus(index,info.infoId,1)">通过</button>
                <button class="unpass" @click="auditInfoStatus(index,info.infoId,2)">不通过</button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="7">没有未审核的信息</td>
            </tr>
          </tbody>
          </table>
        </div>
      </div>

      <div class="resultarea">
        <div class="result-title">审核通过</div>
        <div class="infolist">
          <table class="tab tab3" border="1">
          <thead>
            <tr>
              <th>序号</th>
              <th>信息编号</th>
              <th>信息标题</th>
              <th>发布时间</th>
              <th>发布人</th>
              <th>信息状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody v-if="infolist3.length != 0">
            <tr v-for="(info,index) in infolist3" :key=info.infoId>
              <td>{{index+1}}</td>
              <td>{{info.infoId}}</td>
              <td>{{info.infoTitle}}</td>
              <td>{{info.releaseTime}}</td>
              <td>{{info.username}}</td>
              <td>{{info.infoStatus == 0?"待审核":(info.infoStatus == 1?"通过":"未通过")}}</td>
              <td>
                <button class="deleinfo" @click="deleinfoByInfoId(info.infoId)">删除</button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="7">没有审核通过的信息可展示</td>
            </tr>
          </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AddInformation from '@/components/main/inmain/teacher/child/AddInformation'
import UpdateInformation from '@/components/main/inmain/teacher/child/UpdateInformation'
export default {
  name: 'InfoManage',
  mounted(){
    let userstr = localStorage.getItem("loginsuccess");
    // console.log("userjson:",userstr);
    if(userstr != null){
      let userobj = JSON.parse(userstr);
      this.user = userobj;
      // console.log("userobj:",userobj,"登录状态:",userobj.ustatus == 1?"已登录":"未登录");
    }
    this.getInfoList();
    this.getInitInfoList();
    this.getSuccessInfoList();
  },
  data(){
    return{
      isAddinformation: false,
      isUpdateinformation: false,
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
      },
      infoTitle: '',
      author: '',
      infolist1: [],
      infolist2: [],
      infolist3: []
    }
  },
  methods: {
    changeAddinformation: function(){
      this.isAddinformation = !this.isAddinformation;
      this.isUpdateinformation = false;
      this.$refs.myaddinfo.getCurrentDate();
    },
    changeUpdateinformation: function(id){
      this.isUpdateinformation = !this.isUpdateinformation;
      this.isAddinformation = false;
      this.$refs.myupdateinfo.getInfoByInfoId(id);
    },
    getCloseAddinformationMsg: function(data){
      this.isAddinformation = data;
    },
    getCloseUpdateinformationMsg: function(data){
      // console.log("123456,",data);
      this.isUpdateinformation = data;
    },
    // 获取所有信息
    getInfoList: function(){
      this.$axios({
        url: 'http://localhost:8085/info/findallinfo',
        method: 'post',
        data: {}
      })
      .then(res=>{
        // console.log("所有信息infolist1:",res.data);
        this.infolist1 = res.data;
      })
      .catch(err=>console.log(err));
    },
    // 获取待审核的信息
    getInitInfoList: function(){
      // console.log("获取待审核的信息:",{
      //   "infoStatus": 0
      // });
      this.$axios({
        url: 'http://localhost:8085/info/findinfobyconditions',
        method: 'post',
        data: this.$qs.stringify({
          infoStatus: 0
        })
      })
      .then(res=>{
        // console.log("待审核的信息infolist2:",res.data);
        this.infolist2 = res.data;
      })
      .catch(err=>console.log(err));
    },
    // 获取审核通过的信息
    getSuccessInfoList: function(){
      // console.log("获取审核通过的信息:",{
      //   "infoStatus": 1
      // });
      this.$axios({
        url: 'http://localhost:8085/info/findinfobyconditions',
        method: 'post',
        data: this.$qs.stringify({
          infoStatus: 1
        })
      })
      .then(res=>{
        // console.log("审核通过的信息infolist3:",res.data);
        this.infolist3 = res.data;
      })
      .catch(err=>console.log(err));
    },
    // 通过条件查询信息
    findInfoByConditions: function(){
      console.log("info==>conditions:",{
        "infoTitle:": this.infoTitle,
        "author": this.author
      });
      this.$axios({
        url: 'http://localhost:8085/info/findinfobyconditions',
        method: 'post',
        data: this.$qs.stringify({
          title: this.infoTitle,
          username: this.author,
          infoStatus: ''
        })
      })
      .then(res=>{
        console.log(res.data);
        this.infolist1 = res.data;
        this.infolist2 = res.data;
        this.infolist3 = res.data;
      })
      .catch(err=>console.log(err));
    },
    // 审核信息
    auditInfoStatus: function(index,infoid,status){
      console.log("======>auditInfo",{
        "infoId": infoid,
        "infoStatus": status
      })
      this.$axios({
        url: 'http://localhost:8085/info/aduitinfo',
        method: 'post',
        data: this.$qs.stringify({
          infoId: infoid,
          infoStatus: status
        })
      })
      .then(res=>{
        console.log(res.data);
        this.getInitInfoList();
        window.location.reload();
      })
      .catch(err=>console.log(err));
    },
    // 通过信息编号删除信息
    deleinfoByInfoId: function(id){
      this.$axios({
        url: 'http://localhost:8085/info/deleinfobyinfoid',
        method: 'post',
        data: this.$qs.stringify({
          infoId: id
        })
      })
      .then(res=>{
          console.log(res.data);
          if(res.data == "1"){
            alert("删除成功！");
            window.location.reload();
          }
        })
      .catch(err=>console.log(err));
    }
  },
  components:{
    addinformation: AddInformation,
    updateinformation: UpdateInformation
  }
}
</script>

<style scoped>
/* 查询信息区 start */
.searchbox {
  width: 1000px;
  height: 40px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
.item {
  margin-left: 40px;
}
.label {
  width: auto;
  height: 26px;
  font-size: 20px;
  letter-spacing: 8px;
  line-height: 40px;
  position: relative;
  top: 2px;
}
.infotitle {
  width: 200px;
  height: 26px;
}
.infotitle:focus {
  outline: none;
}
.author {
  width: 200px;
  height: 26px;
}
.author:focus {
  outline: none;
}
.findinfo {
  width: 70px;
  height: 30px;
  border: none;
  letter-spacing: 10px;
  text-indent: 10px;
  font-size: 18px;
  border-radius: 2px;
  background-color: #28A745;
  color: white;
  position: relative;
  top: 8px;
}
.findinfo:focus {
  outline: none;
  opacity: 0.8;
}
/* 查询信息区 end */

/* 发布信息区 start */
.sendinfoarea {
  width: 1000px;
  height: auto;
  margin: 10px auto;
}
.title {
  width: 150px;
  height: 28px;
  font-size: 18px;
  display: inline-block;
  letter-spacing: 10px;
  text-indent: 22px;
  margin: 20px 0 0 20px;
}
.addinfo {
  width: 100px;
  height: 26px;
  border: none;
  letter-spacing: 5px;
  text-indent: 5px;
  border-radius: 2px;
  background-color: #0069D9;
  color: white;
  display: inline;
}
.addinfo:focus {
  outline: none;
  opacity: 0.8;
}
.infolist {
  width: 1000px;
  height: auto;
}
.tab {
  width: 935px;
  height: auto;
  border-collapse: collapse;
}
.tab tr td {
  height: 30px;
  text-align: center;
}
.tab1 {
  margin: 10px auto;
}
.details {
  width: 70px;
  height: 26px;
  border: none;
  letter-spacing: 10px;
  text-indent: 10px;
  border-radius: 2px;
  background-color: #0069D9;
  color: white;
}
.details:focus {
  outline: none;
  opacity: 0.8;
}
/* 发布信息区 end */
/* 审核信息区 start */
.auditinfoarea {
  width: 1000px;
  height: auto;
  margin: 10px auto;
}
.auditarea {
  width: 1000px;
  height: auto;
}
.audit-title {
  width: 150px;
  height: 28px;
  font-size: 16px;
  display: inline-block;
  letter-spacing: 10px;
  text-indent: 14px;
  margin: 10px 0 0 20px; 
}
.tab2 {
  margin: 0 auto;
}
.resultarea {
  width: 1000px;
  height: auto;
}
.result-title {
  width: 150px;
  height: 28px;
  font-size: 16px;
  display: inline-block;
  letter-spacing: 10px;
  text-indent: 14px;
  margin: 10px 0 0 20px; 
}
.tab3 {
  margin: 0 auto;
}
.pass {
  width: 70px;
  height: 26px;
  border: none;
  letter-spacing: 10px;
  text-indent: 10px;
  border-radius: 2px;
  background-color: #0069D9;
  color: white;
}
.pass:focus {
  outline: none;
  opacity: 0.8;
}
.unpass {
  width: 70px;
  height: 26px;
  border: none;
  letter-spacing: 6px;
  text-indent: 6px;
  border-radius: 2px;
  background-color: #FFC107;
  color: white;
}
.unpass:focus {
  outline: none;
  opacity: 0.8;
}
.deleinfo {
  width: 70px;
  height: 26px;
  border: none;
  letter-spacing: 10px;
  text-indent: 10px;
  border-radius: 2px;
  background-color: #DC3545;
  color: white;
}
.deleinfo:focus {
  outline: none;
  opacity: 0.8;
}
/* 审核信息区 end */
</style>