<template>

  <div class="img-box" >
    <camera v-if="Flag" v-on:Status="getStatus"></camera>
    <div class="mall-remark">
      <p>{{Msg}}</p>
      <input  class="note" :placeholder="Message" v-model="Remarks" @blur="remarks" v-if="isShow"/>
    </div>
    <div v-for="(n,index) in parseInt(6)" v-if="relond">
      <div class="img" @click="">
        <div class="box" @click="clikImg(index)">
          <i class="iconfont icon-weibiaoti1 imgcom"></i>
        </div>
        <input type="file" name="file" @change="PreviewImage" class="input" :id="index"/>
        <img :src="imageUrl[index].src" alt="" class="fileImg"  v-if="imageUrl[index]!=null" @click="clikImg(index)">
        <input class="checkBox" type="checkbox" :checked="imageUrl[index]!=null?imageUrl[index].pro:false"  @click="checks(index)" :disabled="imageUrl[index]==null" /><span class="p">注意问题</span>
      </div>
    </div>
    <div class="msg">
      <p>加载图片与网络有关请耐心等待</p>
    </div>
  </div>
</template>
<script>
  import * as qiniu from 'qiniu-js';
  import SrcData from "../../../json/src.json"
  import Camera from "../../Common/Camera.vue"
  import ImgData from "../../../json/img.json"
  import {Photograph} from "../../../common/js/cordovaPlugin"
  export default {
    name:'DetailsImage',
    components:{
      Camera
    },
    data(){
      return{
        Src:[],
        Value:'',
        imageUrl:[],
        imgUrls:'',
        SrcData:[],
        Remarks:'',
        Message:'备注信息',
        Msg:'衣物备注',
        isShow:true,
        Flag:false,
        index:'',
        checked:[],
        thisImg:'',
        relond:true
      }
    },
    watch:{
      '$route'(){
        if(this.$route.name=="MallDetails"){
          this.Msg='上传运单图片:';
          this.isShow = false
        }else {
            this.isShow = true;
        }
      }
    },
    props:{
      remark:{
        type:String,
        required:true
      },
      srcdata:{
        type:Array,
        required:true
      },
      OrderId:{
        type:String,
        required:true
      }
    },

    created(){
      if(this.$route.name=="MallDetails"){
        this.Msg='上传运单图片:';
        this.isShow = false
      }if(this.$route.name=="AddDetails"){
        if(this.OrderId.indexOf('A03')==-1){
          this.Msg='上传家居图片:';
          this.isShow = false;
        }else {
          this.isShow = true;
        }
      };
      this.Remarks = this.remark;
      if(this.srcdata!=''){
        this.imageUrl = [];
        this.srcdata.forEach((item)=>{
          let ImgAddress = 'https://image.rwlai.com/' + item.src;
          this.imageUrl.push({
            src:ImgAddress,
            pro:item.pro
          });
        });
        console.log(this.srcdata);
        this.SrcData = [];
        this.SrcData = this.srcdata;
      }
    },
    methods:{
      remarks(){
        this.$emit('Remarks',this.Remarks);
      },
      /*getBlobBydataURI(dataURI,type) {
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for(var i = 0; i < binary.length; i++) {
          array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type:type });
      },
      getBase64(img) {
          var canvas = document.createElement("canvas");
          var width = img.width;
          var height = img.height;
          // calculate the width and height, constraining the proportions
          if(width > height) {
            if(width > 100) {
              height = Math.round(height *= 100 / width);
              width = 100;
            }
          } else {
            if(height > 100) {
              width = Math.round(width *= 100 / height);
              height = 100;
            }
          }
          canvas.width = width; /!*设置新的图片的宽度*!/
          canvas.height = height; /!*设置新的图片的长度*!/
          var ctx = canvas.getContext("2d");
          ctx.drawImage(img, 0, 0, width, height); /!*绘图*!/
          var dataURL = canvas.toDataURL("image/png", 0.8);
          console.log(dataURL);
          return dataURL;
      },*/
      convertBase64UrlToBlob(base64){
        var urlData =  base64.dataURL;
        var type = base64.type;
        var bytes = window.atob(urlData.split(',')[1]); //去掉url的头，并转换为byte
        //处理异常,将ascii码小于0的转换为大于0
        var ab = new ArrayBuffer(bytes.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < bytes.length; i++) {
          ia[i] = bytes.charCodeAt(i);
        }
        return new Blob( [ab] , {type : type});
      },
      getStatus(data){
        if(data == 'photo'){
          document.getElementById([this.index]).click()
        }if(data == 'camera'){
          Photograph().then((data)=>{
            let type = data.type.substring(6);
            let file = this.convertBase64UrlToBlob(data,type);
            this.Ajax(file);
          }).catch((err)=>{
            if(err===20){  //无相机权限
              alert('需要开启相机权限')
            }
          });
        }
        this.Flag=false;
      },
      Ajax(files,type){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.ImgToken.http;
         this.$ajax({
           method: 'post',
           url: src,
           headers: {'x-auth-token': this.$token.token}
         }).then((res)=>{
           if(res.data.data!=''&&res.data.data != null){
             let token = res.data.data;
             if(token!=''){
               let file = files;
               let key=new Date().getTime()+'.'+type;
               let config = {
                 useCdnDomain: true,
                 region: null
               };
               let putExtra = {
                 mimeType: null
               };
               let observer = {
                 error:(err)=>{
                   this.$alert(err);
                 },
                 complete:(res)=>{
                   if(res.key!=''){
                     let src = res.key;
                     let ImgAddress = 'https://image.rwlai.com/' + src;
                     if(this.SrcData[this.index]==null){
                       this.$msg.setShow('上传成功');
                       this.imageUrl.push({
                         src:ImgAddress,
                         pro:false
                       });
                       this.SrcData.push({
                         src:src,
                         pro:false
                       });
                     }else {
                       this.$msg.setShow('替换图片成功');
                       this.imageUrl[this.index]={
                         src:ImgAddress,
                         pro:false
                       };
                       this.SrcData[this.index]={
                         src:src,
                         pro:false
                       };
                     }
                     this.$emit('SrcData',this.SrcData);
                     this.relond = false;
                     this.relond = true;
                   }
                 }
               };
               let observable = qiniu.upload(file, key, token, putExtra, config);
               observable.subscribe(observer);
             }
           }else if(res.data.data==''&&res.data.data != null){
             this.$alert('未获得上传权限')
           }else {
             this.$msg.setShow('数据异常，请重试')
           }
         }).catch((res)=>{
           this.$alert(res.response.data.msg).then(()=>{
             if(res.response.data.code == '1'){
               this.$router.push({name:'Login'})
             }
           });
         })
      },
     /* isAjax(file){
        console.log(file);
        var formData = new FormData();
        formData.append('file', file);
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.UploadImg.http;
        this.$ajax({
          method: 'post',
          url:src,
          data: formData,
          headers: {'x-auth-token': this.$token.token},
        }).then(res=>{
          if(res.data.code == 0){
            if(this.SrcData[this.index]==null){
              this.$msg.setShow('上传成功');
              let src = res.data.data;
              let ImgAddress = 'https://image.rwlai.com/' + src;
              this.imageUrl.push({
                src:ImgAddress,
                pro:false
              });
              this.SrcData.push({
                src:src,
                pro:false
              });
              this.$emit('SrcData',this.SrcData);
            }else {
              this.$msg.setShow('替换图片成功');
              let src = res.data.data;
              let ImgAddress = 'https://image.rwlai.com/' + src;
              this.imageUrl[this.index]={
                src:ImgAddress,
                pro:false
              };
              this.SrcData[this.index]={
                src:src,
                pro:false
              };
              this.$emit('SrcData',this.SrcData);
            }
          }else {
            this.$msg.setShow('上传失败');
          }
        })
          .catch(res=>{
            this.$alert(res.data.msg+'可能为登录失效请重新登录').then(()=>{
              this.$router.push({name:'Login'})
            })

          })
      },*/
      clikImg(index){
        this.Flag = true;
        this.index = index;
        this.relond = false;
        this.relond = true;
      },
      PreviewImage(event){
        let file = event.target.files[0];
        let type = file.type.substring(6);
        this.Ajax(file,type);
      },
      checks(index){
        this.SrcData[index].pro = !this.SrcData[index].pro;
        this.imageUrl[index].pro = !this.imageUrl[index].pro;
        this.$emit('SrcData',this.SrcData);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .msg{
    text-align: center;
    align-items: center;
    line-height: px2rem(200);
    color: $color-background-general;
    top: px2rem(10);
    @include font(8)
  }
  .img-box{
    margin-left: px2rem(5);
    background: $color-background-big;
    .input{
      display: none;
      z-index: -9999;
    }
    .mall-remark{
      margin-left: px2rem(36);
      margin-top: px2rem(50);
      p{
        @include font(5);
        color: $color-background-general;
      }
      input{
        @include font(5);
        float: left;
        margin-top: px2rem(22);
        text-indent: px2rem(12);
        width: px2rem(660);
        line-height: px2rem(73);
      }
    }
    .fileImg{
      display: flex;
      position: absolute;
      margin-top: px2rem(-95);
      width: px2rem(214);
      height: px2rem(210);
    }
    .p{
      margin-left: px2rem(10);
    }
    .checkBox{
      margin-top: px2rem(118);
      width: px2rem(30);
      height: px2rem(30);
    }
    .img{
      margin-top: px2rem(92);
      float: left;
      width: px2rem(214);
      height: px2rem(206);
      margin-left: px2rem(26);
      background: rgb(206,209,214);
      text-align: center;
      .box{
        position: relative;
        top: 50%;
        transform: translateY(-50%);

        .imgcom{
          @include font(31);
          color: white;
        }
      }
      p{
        @include font(2);
        margin-top: px2rem(120);
      }
    }
  }
  .img-box:after{content:'';display:table;clear:both;}
</style>
