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
      document.addEventListener("deviceready",()=>{
        this.onDeviceReady();
        window.plugins.jPushPlugin.init();
        window.plugins.jPushPlugin.getRegistrationID(res=> {
          console.log('jid',res);
        });
      },false);
    },
    methods:{
      onDeviceReady(){
        document.addEventListener("backbutton", this.onBackKeyDown, false);
        document.addEventListener("jpush.receiveRegistrationId", function (event) {
        }, false);
        document.addEventListener("jpush.receiveMessage",(event)=>{
          console.log("receiveMessage",event);
        }, false);
        document.addEventListener("jpush.openNotification", (event)=>{
          console.log("openNotification",event);
        }, false);
        document.addEventListener("jpush.receiveNotification",  (event)=>{
          console.log("receiveNotification",event);
         setTimeout(()=>{
           this.playAudio();
         },2000)
        }, false);
        this.initJPush();
      },
      initJPush(){
        if ('JPush' in window) {
          try {
            window.JPush.init();
            window.JPush.setDebugMode(true);
            if (device.platform != "Android") {
              window.JPush.setApplicationIconBadgeNumber(0);
            }
          } catch (exception) {
            console.log(exception);
          }
        } else {
          console.error('JPush is not exist...');
        }
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
      },
      playAudio() {
        let src = '/android_asset/www/static/advice.mp3';
        // Play the audio file at url
        let media = new Media(src,
          // success callback
          function () {
            console.log("playAudio():Audio Success");
          },
          // error callback
          function (err) {
            console.log(err);
          }
        );
        // Play audio
        media.play();
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
