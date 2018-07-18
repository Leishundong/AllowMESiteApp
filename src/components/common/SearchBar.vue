<template>
  <div class="search-box">
    <span class="span-message" v-text="Message"></span>
    <div class="search-lan" v-if="ShowBar">
      <div class="img-box">
        <img class="img" :src="BarCodeImg"/>
      </div>
      <input v-model="Search"/>
      <span class="button" @click="orderSelect"><div class="span"><span>搜索</span></div></span>
    </div>
    <div class="search-lan" v-else>
      <input class="add-details" ref="barCode" v-model="BarCode" @blur="getCode" @input="patternNumber()"  maxlength="7"/>
      <div class="img-box" @click="getBarCode">
        <img class="img" :src="BarCodeImg" @click="clickBarCode"/>
      </div>
    </div>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  import SrcData from "../../json/src.json"
  import {Scanner} from "../../common/js/cordovaPlugin"
  export default {
    name:'SearchBar',
    data(){
      return{
        BarCodeImg:'',
        Message:'',
        ShowBar:'',
        Search:'',
        BarCode:''
      }
    },
    props:{
      WhereFrom:{
        type:String,
        required:true
      },
      barCode:{
        type:String,
        required:true
      }
    },
    created(){
      this.getImg();
      this.modify();
      this.BarCode = this.barCode;
    },
    activated(){
      this.Search = '';
    },
    watch:{
      '$route'(){
        this.modify();
      }
    },
    methods:{
      getImg(){
        this.BarCodeImg = imgData.LinkerImg.BarCode.src;
      },
      clickBarCode(){
        Scanner().then((result)=>{
          this.BarCode=result.text;
          this.BarCode?alert('扫描成功'):'';
        }).catch((error)=>{
          alert("此操作需要手机权限");
        });
      },
      orderSelect(){
        let type = '';
        let str = this.Search;
        if(this.WhereFrom == "InBound"){
          if(str.length==11||str.length==4){
            type = 1;
          }else if(str.length==12){
            type = 0;
          }
          if(str.length<13){
            this.serchAjax(type);
          }
          //入站订单查询
        }else if(this.WhereFrom == "HangUps"){//上挂订单查询
          let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.SearchBars.http;
          this.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': this.$token.token},
            params:{
              barcode:str
            }
          }).then(res=>{
            if(res.data.data!=''&&res.data.data!=null){
              let SearchData = res.data;
              this.$emit('SearchData',SearchData);
              this.$emit('Search',str);
            }else if(res.data.data == ''&&res.data.data!=null){
              this.$alert('未查询到此订单');
              this.Search = '';
            }else {
              this.$msg.setShow('数据异常,请重试')
            }
          }).catch(res=>{
            this.$alert(res.response.data.msg).then(()=>{
              if(res.response.data.code == '1'){
                this.$router.push({name:'Login'})
              }
            });
            })
        }
      },//综合订单查询
      serchAjax(type){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.SearchOrders.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{
            key:this.Search,
            type:type
          }
        }).then(res=>{
          if(res.data.data!=''&&res.data.data!=null){
            let SearchData = res.data;
            this.$emit('SearchData',SearchData);
          }else if(res.data.data == ''&&res.data.data!=null){
            this.$alert('未查询到此订单').then(()=>{
              this.Search = '';
            })
          }else {
            this.$msg.setShow('数据异常,请重试')
          }
        }).catch(res=>{
          this.$alert(res.response.data.msg).then(()=>{
            this.Search = '';
            if(res.response.data.code == '1'){
              this.$router.push({name:'Login'})
            }
          });
        })
      },//入站订单查询
      patternNumber(){
        if(isNaN(Number(this.BarCode))==true){
          this.BarCode = this.BarCode.replace(/[^0-9]/ig,"");
          this.$msg.setShow('条码只能包含数字');
        }
      },
      getCode(){
        if(this.BarCode.length!=7){
          this.$msg.setShow('条码长度不能小于7');
          this.$refs.barCode.focus();
        }else {
          this.$emit('BarCode',this.BarCode);
        }
      },
      getBarCode(){
          Scanner().then((result)=>{
            this.BarCode = result.text;
            this.$emit('BarCode',this.BarCode);
          }).catch((error)=>{
            alert("此操作需要手机权限");
          });
      },
      modify(){
        if(this.$route.name=='Home'||this.$route.name=='Inbound'){
          this.Message = '请输入订单编号或手机号'
          this.ShowBar = true;
        }else if(this.$route.name == 'AddDetails'){
          this.Message = '请输入衣物条码';
          this.ShowBar = false;
        }else if(this.$route.name == 'HangUp'||this.$route.name == 'HangUps'||this.$route.name == 'HangUpsList'){
          this.Message = '请输入衣物编号';
          this.ShowBar = true;
        }
      },
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .search-box{
    margin-left: px2rem(36);
    margin-top: px2rem(72);
    .span-message{
      @include font(5);
      color: $General-font-color;
    }
    .search-lan{
      height: px2rem(73);
      margin-top: px2rem(20);
      .img-box{
        float: left;
        width: px2rem(165);
        height: px2rem(73);
        background: $color-background-general;
        .img{
          margin-left: px2rem(33);
          margin-top: px2rem(4);
          width: px2rem(100);
          height: px2rem(60);
        }
      }
      .add-details{
        width: px2rem(518);
        margin-left: px2rem(0);
      }
      input{
        float: left;
        @include font(5);
        text-indent: px2rem(12);
        margin-left: px2rem(5);
        width: px2rem(343);
        height: 100%;
      }
      .button{
        float: left;
        text-align: center;
        margin-left: px2rem(5);
        width: px2rem(165);
        height: px2rem(73);
        background: $color-background-general;
        color: white;
        @include font(5);
        .span{
          position: relative;
          top: 50%;
          transform: translateY(-50%);
        }
      }
    }
  }
</style>
