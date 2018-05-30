<template>
  <div class="list-box">
    <head-bar></head-bar>
    <div class="message">
      <p>衣物条形码<span>aaa</span></p>
      <p>类型<span>aaa</span></p>
      <p>洗后效果<span>aaa</span></p>
      <p>衣物备注<span>aaa</span></p>
    </div>
    <foot-bar></foot-bar>
  </div>
</template>
<script>
  import HeadBar from "./HeadBar.vue"
  import FootBar from "./FootBar.vue"
  export default {
    name:'HangUpClothes',
    components:{
      HeadBar,
      FootBar
    },
    data(){
      return{
        Where:''
      }
    },
    props:{
      WhereFrom:{
        type:String,
        required:true
      }
    },
    created(){
      this.Where = this.WhereFrom;
    },
    /*computed:{*/
    /* ShowList(){
       if(this.WhereFrom=='HangUp'){
         return true
       }else {
         return false
       }
     },
     Show(){
       if(this.WhereFrom=='Inbound'){
         return true
       }else {
         return false
       }
     }
   },*/
    methods:{
      toAddDetails(){
        this.$router.push({name:'AddDetails'})
      },
      toHangUpClothes(){
        this.$router.push({name:'HangUpClothes'})
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    background: $color-background-big;
    height: 100%;
    .message{
      margin-left: px2rem(35);
      p{
        margin-top: px2rem(30);
        @include font(2);
        color:$color-background-general;
        span{
          float: right;
          margin-right: px2rem(400);
        }
      }
    }
  }
</style>
