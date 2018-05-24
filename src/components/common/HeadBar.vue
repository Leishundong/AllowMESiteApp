<template>
  <div class="bar-box">
    <div class="left-button" v-if="IsShow">
      <span v-text="LeftButton"></span>
      <span class="title" v-text="Title"></span>
    </div>
    <div class="left-button" v-if="!IsShow">
      <span  v-text="LeftButton" @click="goBack()"></span>
      <span class="title" v-text="Title"></span>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        IsShow:true,
        LeftButton:'',
        Title:''
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
        if(this.$route.name == 'Home'){
          this.IsShow = true;
          this.LeftButton = '注销'
        }else{
          this.IsShow = false;
          this.LeftButton = '返回'
        };
        if(this.$route.name == 'Home'){
          this.Title = '功能菜单';
        }else if(this.$route.name == 'Inbound'){
          this.Title = '衣物入站';
        }else if(this.$route.name == 'Details'){
          this.Title = '订单详情';
        }else if(this.$route.name == 'AddClothes'){
          this.Title = '添加衣物';
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
