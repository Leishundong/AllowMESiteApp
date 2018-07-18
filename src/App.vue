<template>
  <div id="app" style="height: 100%">
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data(){
      return{
        backClick:0,
      }
    },
    mounted(){
      document.addEventListener("deviceready", this.onDeviceReady, false);
    },
    methods:{
      onDeviceReady() {
        document.addEventListener("backbutton", this.onBackKeyDown, false);
      },
      onBackKeyDown(){
        let routeName = this.$route.name;
        if(routeName == 'Home'||routeName == 'Login'){
          if(this.backClick==0){
            this.backClick =1;
            this.$msg.setShow('再按一次退出App');
            setTimeout(()=> {
              this.backClick =0;
              },2000)
          }else if(this.backClick==1){
            navigator.app.exitApp();
          }
        }else if(routeName == 'Inbound'){
          this.$router.push({name:'Home'})
        }else if(routeName == 'Details'){
          this.$router.push({name:'Inbound'})
        }else if(routeName == 'AddDetails'){
          this.$router.push({name:'ClothesList',params:{Obj:''}})
        }else if(routeName == 'ClothesList'){
          this.$router.push({name:'Details'})
        }else if(routeName == 'Receipt'){
          this.$router.push({name:'ClothesList'})
        }else if(routeName == 'HangUps'){
          this.$router.push({name:'Home'})
        }else if(routeName == 'MallDetails'){
          this.$router.push({name:'Inbound'})
        }else if(routeName == 'HangUpsList'){
          this.$router.push({name:'HangUps'})
        }else if(routeName == 'HangUpDe'){
          this.$router.push({name:'HangUp'})
        }else if(routeName == 'AllOrder'){
          this.$router.push({name:'Home'})
        }else if(this.$route.name == 'AllOrderDetails'){
          this.$router.push({name:'AllOrder'})
        }
      }
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
  @import "~common/css/base";
  @import "../static/img/iconfont/iconfont.css";
  @font-face{
    font-family: 微软雅黑常规;
    src: url('../static/font/MicrosoftYaHei.ttf');
  }
  body{
    font-family: $General-font;
  }
</style>
