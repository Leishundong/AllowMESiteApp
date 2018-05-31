<template>
  <div class="Login-box">
    <span>工号:</span><input v-model="UserName"/>
    <div class="line"></div>
    <span>密码:</span><input type="password" v-model="Password" />
    <button class="button" @click="Loging">登录</button>
  </div>
</template>
<script>
  import SrcData from "../../json/src.json"
  export default {
    name:'Login',
    data(){
      return{
        UserName:'',
        Password:'',
      }
    },
    methods:{
      Loging(){
        console.log(SrcData);
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.Login.http;
        this.$ajax({
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
