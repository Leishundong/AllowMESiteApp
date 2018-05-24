<template>
  <div class="bar-box">
    <div class="left-button" v-if="isshow">
      <span v-text="leftbutton"></span>
      <span class="title" v-text="title"></span>
    </div>
    <div class="left-button" v-if="!isshow">
      <span  v-text="leftbutton" @click="goBack()"></span>
      <span class="title" v-text="title"></span>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        isshow:true,
        leftbutton:'',
        title:''
      }
    },
    created(){
      this.titleName();
    },
    watch:{
      '$route'(){
        this.titleName();
      }
    },
    methods:{
      goBack(){
        this.$router.go(-1)
      },
      titleName(){
        if(this.$route.name == 'home'){
          this.isshow = true;
          this.leftbutton = '注销'
        }else{
          this.isshow = false;
          this.leftbutton = '返回'
        };
        if(this.$route.name == 'home'){
          this.title = '功能菜单';
        }else if(this.$route.name == 'Inbound'){
          this.title = '衣物入站';
        }else if(this.$route.name == 'Details'){
          this.title = '订单详情';
        }
      },
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .bar-box{
    width: 100%;
    height: px2rem(90);
    background: $color-background-general;
    color: white;
    .left-button{
      position: relative;
      top: 50%;
      transform: translateY(-50%);
      @include font(5);
      margin-left: px2rem(17);
      .title{
        width: 50%;
        margin-left: 25%;
        @include font(5);
      }
    }

  }
</style>
