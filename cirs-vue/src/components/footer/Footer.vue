<template>
  <div>
    <div class="footer">
      <div class="schinfo">
        <p>天元市龙岗区朱雀村68号 查号台：+84-654-86421411 P.C.:160001 Copyright&copy;2020 天元某某大学网络与信息中心 渝ICP备06005761号</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Footer'
  }
</script>

<style scoped>
  .footer {
    width: 100%;
    height: 100px;
    background-color: #1E90FF;
    position: relative;
    left: 0;
    right: 0;
    bottom: 0;/*position: relative;*/
    /*bottom: -200px;*//*position: absolute*/
  }
  .schinfo {
    width: 495px;
    height: 44px;
    margin: 27px auto;
  }
  .schinfo>p {
    padding-top: 30px;
  }
</style>
