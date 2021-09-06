<template>
    <div>
        <div class="infoarea">
            <div class="info" v-for="(item,index) in infolist" :key="item.infoId" v-show="item.infoStatus == 1">
                <div class="top-space"></div>
                <!-- 信息区域 -->
                <div class="title">{{item.infoTitle}}</div>
                <article class="art">
                    {{item.infoContent}}
                </article>
                <div class="bottompart">
                    <div class="author">作者：{{item.username}}</div>
                    <div class="release-time">发表时间：{{item.releaseTime}}</div>
                </div>
                <!-- 评论区域 -->
                <div class="commentarea">
                    <!-- 添加评论区域 -->
                    <div class="inputgroup">
                        <input type="text" class="commentinput" placeholder="请输入你的评论">
                        <button type="button" class="btn" @click="addComment(index)">评论</button>
                    </div>
                    <!-- 评论展示区 -->
                    <div class="show">
                        <div class="comment" v-for="comment in commentlist[item.infoId]" :key="comment.cid">
                            <span>{{comment.username}}</span>:
                            <p style="display: inline;">{{comment.content}}</p>
                            <div class="comment-close" v-show="user.position == '行政老师'" @click="delecomment(comment.cid)">x</div>
                            <hr style="width: 550px; margin: 0 atuo;" />
                        </div>
                    </div>
                </div>
                <div class="bottom-space"></div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CampusInfo',
    mounted(){
        this.getInfoList();
        var userstr = localStorage.getItem("loginsuccess");
        if(userstr != null){
            var userobj = JSON.parse(userstr);
            this.user = userobj;
        }
    },
    data(){
        return{
            infolist: [],
            commentlist:[],
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
        }
    },
    methods:{
        getInfoList: function(){
            this.$axios({
                url: 'http://localhost:8085/info/findallinfo',
                method: 'post',
                data:{}
            })
            .then(res=>{
                this.infolist = res.data;
                for(let info of this.infolist){
                    this.getCommentList(info.infoId);
                }
            })
            .catch(err=>console.log(err))
        },
        getCommentList: function(infoid){
            this.$axios({
                url: 'http://localhost:8085/comment/findcommentbyinfoid',
                method: 'post',
                data: this.$qs.stringify({
                    infoId: infoid
                })
            })
            .then(res=>{
                this.commentlist[infoid] = res.data;
                this.$forceUpdate();
            })
            .catch(err=>console.log(err))
        },
        addComment: function(index){
            let array = document.getElementsByClassName("commentinput");
            
            if(this.user.uid == ""){
                alert("未登录状态，不能进行评论")
            }else{
                if(array[index].value != ''){
                    this.$axios({
                        url: 'http://localhost:8085/comment/pulishcomment',
                        method: 'post',
                        data: this.$qs.stringify({
                            infoId: this.infolist[index].infoId,
                            content: array[index].value,
                            uId: this.user.uid,
                            username: this.user.username   
                        })
                    })
                    .then(res=>{
                        console.log(res.data);
                        if(res.data == "pulish comment success"){
                            alert("评论成功！");
                            window.location.reload();
                        }
                    })
                    .catch(err=>console.log(err));
                }else{
                    alert("提交的评论不能为空！");
                }  
            } 
        },
        delecomment: function(cid){
            var flag = confirm("你确定要删除该条评论！");
            if(flag){
                this.$axios({
                    url: 'http://localhost:8085/comment/delecomment',
                        method: 'post',
                        data: this.$qs.stringify({
                            cid: cid  
                        })
                })
                .then(res=>{
                    console.log(res.data);
                    if(res.data == 1){
                        alert("删除评论成功！")
                        window.location.reload();
                    }
                })
                .catch(err=>console.log(err))
            }
        }
    }
}
</script>

<style scoped>
.infoarea {
    width: 600px;
    min-height: 480px;
    border-left: 1px solid black;
    border-right: 1px solid black;
    margin: 0 auto;
}
.info {
    width: 580px;
    height: auto;
    border-radius: 6px;
    margin: 5px auto;
    background-color: lightblue;
}
.title {
    font-size: 18px;
    font-weight: 600;
    margin-left: 10px; 
}
.art {
    width: 560px;
    height: auto;
    margin: 0 auto;
    text-indent: 32px;
}
.bottompart{
    display: flex;
    justify-content: space-between;
}
.author {
    font-size: 18px;
    margin-left: 10px; 
}
.release-time {
    font-size: 18px;
    margin-right: 10px;
}
.inputgroup {
    width: 560px;
    margin: 5px auto;
}
.commentinput {
    width: 480px;
    height: 28px;
    text-indent: 5px;
}
.commentinput:focus {
    outline: none;
}
.btn {
    width: 70px;
    height: 32px;
    line-height: 16px;
    letter-spacing: 10px;
    text-indent: 10px;
}
.show {
    width: 560px;
    height: auto;
    margin: 0 auto;
    background-color: #F0F8FF;
    border-radius: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
}
.comment {
    width: 550px;
    height: 22px;
    margin-left: 5px;
}
.comment-close {
    width: 15px; 
    height: 15px; 
    text-align: center;
    line-height: 15px;
    float: right;
    margin-top: 2px;
}
.comment-close:hover {
    color: red;
}
.top-space {
    width:560px;
    height: 8px;
    margin: 0 auto;
}
.bottom-space {
    width:560px;
    height: 5px;
    margin: 0 auto;
}
</style>