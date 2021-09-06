<template>
  <div id="maincontent">
    <div>
      <!-- 第一个盒子 -->
      <div class="box1">
        <img :src="newsImgurl" class="headlines" alt="">
        <div class="describe">
          <span>我校召开党史学习教育动员大会</span>
          <p>本网讯（文/党委宣传部图/大学生记者团张三）为学习贯彻习近平总书记在党史教育动员大会上的重要讲话精神..[详情]</p>
        </div>
      </div>
      <!-- 第二个盒子 -->
      <div class="box2">
        <!-- 第二个盒子：内部左盒子 -->
        <div class="left-inbox">
          <img v-for="(imgurl,index) in imglist" 
               :src="imgurl" 
               :key="index" 
               class="pics"
               v-show="n == index"
          alt="">
          <ul class="circles">
            <li 
               v-for="(v,i) in imglist" 
               :key="i" 
               :class="i == n?'active':''"
               class="circle">
               {{i+1}}
            </li>
          </ul>
        </div>
        <!-- 第二个盒子：内部右盒子 -->
        <div class="right-inbox">
          <div class="infolist">
            <div class="info-title">
              <span class="small-title">校园动态</span>
              <span class="more">更多+</span>
            </div>
            <table class="tablelist" border="1">
              <tr class="line">
                <td class="info">
                  <div class="title">大数据学院学生在大学生数学建模等竞赛中喜获佳..</div>
                  <p class="information">本网讯（文/图大数据学院唐蕾）近日，2020年“亚太地区大学生数学建模竞赛”和第三..</p>
                </td>
                <td class="date">
                  <div class="day">12</div>
                  <div class="year-month">2021.03</div>
                </td>
              </tr>
              <tr class="line">
                <td class="info">
                  <div class="title">我校荣获“重庆市征兵工作成绩突出集体”荣誉称..</div>
                  <p class="information"></p>
                </td>
                <td>
                  <div class="day">11</div>
                  <div class="year-month">2021.03</div>
                </td>
              </tr>
              <tr class="line">
                <td class="info">
                  <div class="title">管理学院学生支部召开党史学习教育动员大会</div>
                  <p class="information">本网讯（文/图管理学院曾艳灵）为深入贯彻落实中央和学校党委党史学习教育动员大会..</p>
                </td>
                <td>
                  <div class="day">11</div>
                  <div class="year-month">2021.03</div>
                </td>
              </tr>
              <tr class="line">
                <td class="info">
                  <div class="title">我校召开2021年第一次党委理论学习中心组集中学..</div>
                  <p class="information">本网讯（文/党委宣传部图/大学生记者团汪桂琼）3月10日下午，重庆工程学院党委理论..</p>
                </td>
                <td>
                  <div class="day">10</div>
                  <div class="year-month">2021.03</div>
                </td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <!-- 第三个盒子 -->
      <div class="box3">
        <!-- 第三个盒子：内部左边的小盒子 -->
        <div class="inbox1">
          <div class="inbox-title">
            <div class="stitle">媒体聚焦</div>
            <div class="more1">更加+</div>
          </div>
          <div class="content1">
            <table class="medialist">
              <tr>
                <td>
                  <div class="month-day">
                    <span class="month">01月</span>
                    <span class="day1">19日</span>
                  </div>
                </td>
                <td>
                  <div class="article">【华龙网】重庆工程学院启动“精准”家访活动，..</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="month-day">
                    <span class="month">01月</span>
                    <span class="day1">06日</span>
                  </div>
                </td>
                <td>
                  <div class="article">【中国教育信息化网】十九届五中全会精神宣贯活..</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="month-day">
                    <span class="month">12月</span>
                    <span class="day1">31日</span>
                  </div>
                </td>
                <td>
                  <div class="article">【网易】一封感谢信承载重工育人之道</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="month-day">
                    <span class="month">11月</span>
                    <span class="day1">30日</span>
                  </div>
                </td>
                <td>
                  <div class="article">【网易】重庆阿里政企基地举行重庆工程学院订单..</div>
                </td>
              </tr>
            </table>
          </div>
        </div>
        <!-- 第三个盒子：内部中间的小盒子 -->
        <div class="inbox2">
          <div class="inbox-title">
            <div class="stitle">通知公告</div>
            <div class="more1">更多+</div>
          </div>
          <div class="content2">
            <table class="tab">
              <tr>
                <td width="20">
                  <div class="hollow-circle"></div>
                </td>
                <td width="300">
                  <a href="#" class="a-title">我校2021年起部分专业将实施大类招</a>
                </td>
                <td width="100">
                  <div class="date1">2021-03-12</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">重庆工程学院2021年春季网络双选会</a>
                </td>
                <td>
                  <div class="date1">2021-03-03</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">重庆工程学院2019-2020学年本科教</a>
                </td>
                <td>
                  <div class="date1">2021-01-04</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">“美好‘食’光”校园系列活动倡议</a>
                </td>
                <td>
                  <div class="date1">2020-11-24</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">新校史馆馆藏资料征集倡议书</a>
                </td>
                <td>
                  <div class="date1">2020-10-26</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">重庆工程学院2020年秋季校园双选会</a>
                </td>
                <td>
                  <div class="date1">2020-10-09</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">2020年迎新接待站点联系方式</a>
                </td>
                <td>
                  <div class="date1">2020-09-13</div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="hollow-circle"></div>
                </td>
                <td>
                  <a href="#" class="a-title">重庆工程学院（双桥校区）周边宾馆</a>
                </td>
                <td>
                  <div class="date1">2020-09-13</div>
                </td>
              </tr>
            </table>
          </div>
        </div>
        <!-- 第三个盒子：内部右边的小盒子 -->
        <div class="inbox3">
          <div class="deparnav">
            <h3>院系导航</h3>
          </div>
          <div class="item">
            <a href="" class="a-depar">大数据与人工智能学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">软件学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">电子信息学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">土木工程学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">数字艺术学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">管理学院</a>
          </div>
          <div class="item">
            <a href="" class="a-depar">通识学院</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        newsImgurl:'./static/img/first_news.png',
        imglist: [
          './static/img/n1.jpg',
          './static/img/n2.jpg',
          './static/img/n3.jpg',
          './static/img/n4.jpg',
          './static/img/n5.jpg'
        ],
        n: 0
      }
    },
    methods: {
      go() {
        setInterval(()=>{
          this.n++;
          if (this.n == this.imglist.length) {
            this.n = 0;
          }
        },2000)
      }
    },
    mounted() {
        this.go();
    }
  }
  
</script>

<style scoped>
  /* 第一个盒子 */
  .box1 {
    width: 1100px;
    height: 87px;
    margin: 0 auto;
    border-radius: 5px;
    background-color: #F0F8FF;
    position: relative;
    top: 20px;
  }
  .headlines {
    width: 101px;
    height: 87px;
    display: inline-block;
  }
  .describe {
    width: 850px;
    height: 60px;
    display: inline-block;
    position: relative;
    top: -26px;
    left: 10px;
  }
  .describe>span {
    font-size: 25px;
    color: #E60406;
  }
  .describe>p {
    font-size: 12px;
    margin-top: 2px;
  }
  /* 第二个盒子 */
  .box2 {
    width: 1100px;
    height: 300px;
    margin: 30px auto;
  }
  /* 第二个盒子：左边的小盒子 */
  .left-inbox {
    width: 440px;
    height: 300px;
    float: left;
    margin-left: 15px;
  }
  .pics {
    width: 480px;
    height: 300px;
  }
  .circles {
    width: 220px;
    height: 20px;
    border: 1px solid black;
    margin: -30px auto;
    display: flex;
  }
  .circle {
    width: 20px;
    height: 20px;
    line-height: 20px;;
    border-radius: 50%;
    text-align: center;
    background-color: gray;
    color: white;
    list-style: none;
    margin-left: 20px;
  }
  .active {
    background-color: red;
  }
  /* 第二个盒子：右边的小盒子 */
  .right-inbox {
    width: 550px;
    height: 300px;
    float: right;
    margin-right: 15px;
  }
  .infolist {
    width: 100%;
    height: auto;
  }
  .info-title {
    width: 100%;
    height: 35px;
    border-bottom: 2px solid #00008B;
  }
  .info-title>.small-title {
    width: 80px;
    height: 32px;
    font-size:20px;
    color: #0000CD;
    text-align: center;
    line-height: 30px;
    border-bottom: 4px solid #00008B;
    float: left;
  }
  .info-title>.more {
    width: 80px;
    height: 25px;
    line-height: 25px;
    text-align: center;
    border-radius: 30px;
    background-color: #00008B;
    color: white;
    margin: 5px 5px 0 0;
    float: right;
  }
  .tablelist {
    margin-top: 10px;
    border-collapse: collapse;
  }
  .line {
    height: 62px;
  }
  .line:nth-child(2)>.info .title {
    margin-top: -25px;
  }
  .info {
    width: 500px;
  }
  .info>.title {
    width: 450px;
    margin-left: 15px;
  }
  .info>.information {
    width: 450px;
    font-size: 12px;
    color: gray;
    margin-left: 15px;
  }
  .date{
    width: 50px;
  }
  .day{
    font-size: 26px;
    text-align: center;
    color: #6495ED;
  }
  .year-month {
    font-size: 12px;
    text-align: center;
    color: #6495ED;
  }
  /* 第三个大盒子 */
  .box3 {
    display: flex;
    justify-content: center;
  }
  /* 第三个大盒子：左边盒子 */
  .inbox1 {
    width: 400px;
    height: 260px;
    margin-right: 30px;
  }
  .inbox-title {
    width: 100%;
    height: 30px;
    display: flex;
    justify-content: space-between;
    border-bottom: 2px solid #00008B;
  }
  .inbox-title>.stitle {
    font-weight: 600;
    margin-top: 4px;
  }
  .inbox-title>.more1 {
    color: red;
    margin-top: 4px;
    cursor: pointer;
  }
  .month-day {
    width: 60px;
    height: 60px;
  }
  .month {
    width: 60px;
    display: block;
    text-align: center;
    background-color: #0A3F89;
    color: white;
  }
  .day1 {
    width: 60px;
    display: block;
    text-align: center;
    border-bottom: 1px solid #0A3F89;
  }
  .article {
    width: 100%;
    height: 30px;
    font-size: 14px;
    margin-top: -8px;
  }
  /* 第三个大盒子：中间盒子 */
  .inbox2 {
    width: 400px;
    height: 260px;
    margin-right: 30px;
  }
  .tab {
    border-spacing: 0px 7px;
  }
  .hollow-circle {
    width: 6px;
    height: 6px;
    border: 1px solid blue;
    border-radius: 50%;
  }
  .a-title {
    text-decoration: none;
    color: black;
  }
  .date1 {
    font-size: 14px;
    color: gray;
    margin-left: 15px;
  }
  /* 第三个大盒子：右边盒子 */
  .inbox3 {
    width: 240px;
    height: 260px;
  }
  .deparnav {
    width: 240px;
    height: 42px;
    line-height: 42px;
    background-color: #0A3F89;
  }
  .deparnav h3 {
    width: 75px;
    height: 30px;
    color: white;
    margin: 0 auto;
  }
  .item {
    width: 240px;
    height: 31px;
  }
  .item:nth-child(odd) {
    background-color: #EFEFEF;
  }
  .item:nth-child(even) {
    background-color: #E5E5E5;
  }
  .a-depar {
    width: 180px;
    height: 25px;
    line-height: 25px;
    position: relative;
    top: 2px;
    left: 25px;
    text-indent: 16px;
    display: block;
    text-decoration: none;
    color: black;
    border-left: 4px solid #0B3F8A;
  }
  .a-depar:hover {
    color: #0B3F8A;
  }
</style>