<template>
  <div class="Login-box">
    <span>工号:</span><input v-model="UserName"/>
    <div class="line"></div>
    <span>密码:</span><input type="password" v-model="Password" ref="psw" />
    <button class="button" @click="Loging">登录</button>
  </div>
</template>
<script>
  import SrcData from "../../json/src.json"
  import mp3 from "../../json/img.json"
  export default {
    name:'Login',
    data(){
      return{
        UserName:'',
        Password:'',
      }
    },
    methods:{
     playAudio(src) {
    // Play the audio file at url
    let  media = new Media(src,
      // success callback
      function () {
        console.log("playAudio():Audio Success");
      },
      // error callback
      function (err) {
        console.log("playAudio():Audio Error: " + err);
      }
    );
    console.log(media);
    // Play audio
    media.play();
  },
      Loging(){
      let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.Login.http;
        let mp3s = "./Schnappi.mp3";
        console.log(mp3s);
        this.playAudio(mp3s);
        /*this.$ajax({
          method: 'post',
          url: src,
          params:{
            username: this.UserName,
            password: this.Password
          }
        }).then( res => {
          let token = res.headers['x-auth-token'];
          this.$token.getToken(token);
          this.$alert("登陆成功").then(sucess => {
            this.getInfo();
            this.$router.push({name:'Home'})
          })
        })
          .catch( res => {
              this.$alert(res.response.data.msg).then(()=>{
               this.$refs.psw.focus()
              })
          })*/
      },
      getInfo(){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.Info.http;
        this.$ajax({
          method:'post',
          url:src,
          headers: {'x-auth-token': this.$token.token},
        }).then(res=>{
          let accountId = res.data.data.accountId;
          this.$token.getAccountId(accountId);
          window.JPush.setAlias({sequence: 1, alias:accountId},
            (result) => {
              console.log('res',result);
              let sequence = result.sequence;
              let alias = result.alias;
            }, (error) => {
              console.log('err',error);
              let sequence = error.sequence;
              let errorCode = error.code;
            });
        }).catch(res=>{
        })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .Login-box{
    margin-left: px2rem(63);
    margin-top: px2rem(113);
    width: px2rem(625);
    .line{
      border: #242424 px2rem(1) solid ;
      margin-top: px2rem(38);
      margin-bottom: px2rem(38);
    }
    input{
      text-align: center;
      outline:none;
      @include font(5);
    }
    span{
      color: $login-color;
      @include font(5);
    }
    .button{
      outline: none;
      border: none;
      background: $login-color;
      text-align: center;
      color: #ffffff;
      @include font(5);
      margin-top: px2rem(107);
      width: px2rem(654);
      height: px2rem(128);
    }
  }
</style>
