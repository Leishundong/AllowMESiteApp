<template>
  <div class="head">
    <div class="bar-box">
      <div class="left-button" v-if="IsShow">
        <span v-text="LeftButton" @click="goLogin"></span>
        <span class="title" v-text="Title"></span>
      </div>
      <div class="left-button" v-if="!IsShow">
        <span  v-text="LeftButton" @click="goBack"></span>
        <span class="title" v-text="Title"></span>
      </div>
    </div>
    <div class="height"></div>
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
      goLogin(){
        this.$router.push({name:'Login'})
      },
      goBack(){
       if(this.$route.name == 'Inbound'){
         this.$router.push({name:'Home'})
        }else if(this.$route.name == 'Details'){
           this.$router.push({name:'Inbound'})
        }else if(this.$route.name == 'AddDetails'){
         this.$router.push({name:'ClothesList',params:{Obj:''}})
        }else if(this.$route.name == 'ClothesList'){
         this.$router.push({name:'Details'})
        }else if(this.$route.name == 'Receipt'){
         this.$router.push({name:'ClothesList'})
        }else if(this.$route.name == 'HangUps'){
         this.$router.push({name:'Home'})
        }else if(this.$route.name == 'MallDetails'){
         this.$router.push({name:'Inbound'})
        }else if(this.$route.name == 'HangUpsList'){
         this.$router.push({name:'HangUps'})
       }else if(this.$route.name == 'HangUpDe'){
         this.$router.push({name:'HangUp'})
       }else if(this.$route.name == 'AllOrder'){
         this.$router.push({name:'Home'})
       }else if(this.$route.name == 'AllOrderDetails'){
         this.$router.push({name:'AllOrder'})
       }
        /*if(this.WhereFrom == 'HangUp'){
          this.$router.push({name:'HangUpDe'})
          if(this.WhereFrom == 'HangUp'){
           this.$router.push({name:'HangUpDe'})
         }else {
           this.$router.push({name:'Details'})
         }
        }else {
        }else if(this.$route.name == 'HangUpList'){

       }else if(this.$route.name == 'HangUp'){
         this.$router.push({name:'Home'})
        }else if(this.$route.name == 'HangUpClothes'){
         this.$router.push({name:'HangUpList'})
        }
       else if(this.$route.name == 'AddClothes'){
         this.$router.push({name:'Details'})
        }else if(this.$route.name == 'AddChildren'){
         this.$router.push({name:'AddClothes'})
       }*/
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
        }else if(this.$route.name == 'AddDetails'){
          this.Title = '物品详情';
        }else if(this.$route.name == 'ClothesList'){
          this.Title = '衣物列表';
        }else if(this.$route.name == 'Receipt'){
          this.Title = '小票预览';
        } else if(this.$route.name == 'HangUps'){
          this.Title = '整单上挂';
        }else if(this.$route.name == 'MallDetails'){
          this.Title = '商品详情';
        }else if(this.$route.name == 'HangUpsList'){
          this.Title = '整单上挂';
        }else if(this.$route.name == 'AllOrder'){
          this.Title = '全部订单';
        }else if(this.$route.name == 'AllOrderDetails'){
          this.Title = '订单详情';
        }/*else if(this.$route.name == 'HangUpList'){
          this.Title = '衣物列表';
        }else if(this.$route.name == 'HangUpDe'){
          this.Title = '订单详情';
        }*//*else if(this.$route.name == 'HangUp'){
          this.Title = '衣物上挂';
        }else if(this.$route.name == 'HangUpClothes'){
          this.Title = '衣物上挂';
        }*//*else if(this.$route.name == 'AddClothes'||this.$route.name == 'AddChildren'){
          this.Title = '添加衣物';
        }*/

      },
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .head{
    .bar-box{
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      color: white;
      position: fixed;
      z-index: 99999;
      box-shadow:px2rem(0) px2rem(0) px2rem(10) px2rem(5) #aaa;
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
    .height{
      height: px2rem(90);
    }
  }

</style>
