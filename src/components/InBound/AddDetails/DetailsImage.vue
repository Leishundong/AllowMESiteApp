<template>
  <div class="img-box">
    <div v-for="(n,index) in parseInt(6)" class="box">
      <div class="img"  >
        <!--<div class="box">
          <i class="iconfont icon-weibiaoti1 imgcom"></i>
        </div>-->
        <input type="file" name="file" @change="PreviewImage"/>
        <img :src="imageUrl[index]" alt="" class="fileImg" >
        <span @click="checks(index)"><input class="checkBox" type="checkbox" :value="true" /></span><span class="p">注意问题</span>
      </div>
    </div>

  </div>
</template>
<script>
  import SrcData from "../../../json/src.json"
  export default {
    name:'DetailsImage',
    data(){
      return{
        Src:[],
        Value:'',
        imageUrl:[],
        SrcData:[],
      }
    },
    methods:{
      PreviewImage(event){
        var file = event.target.files[0];
        var formData = new FormData();
        formData.append('file', file);
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.UploadImg.http;
        this.$ajax({
          method: 'post',
          url:src,
          data: formData,
          headers: {'x-auth-token': this.$token.token},
        }).then(res=>{
          let src = res.data.data;
          let ImgAddress = SrcData.LinkerSrc.ImgAddress.http + src;
          this.imageUrl.push(ImgAddress);
          this.SrcData.push({
            imgSrc:src,
            pro:false
          });
          this.$emit('SrcData',this.SrcData);
        })
          .catch(res=>{

          })
      },
      checks(index){
        this.SrcData[index].pro = !this.SrcData[index].pro;
        this.$emit('SrcData',this.SrcData);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .img-box{
    margin-left: px2rem(5);
    background: $color-background-big;
    .fileImg{
      display: flex;
      position: absolute;
      margin-top: px2rem(1);
      width: px2rem(214);
      height: px2rem(206);
    }
    .p{
      float: left;
      margin-top: px2rem(230);
      margin-left: px2rem(10);
    }
    .checkBox{
      float: left;
      margin-left: px2rem(30);
      width: px2rem(30);
      height: px2rem(30);
      margin-top: px2rem(227);
    }
    .img{
      margin-top: px2rem(92);
      float: left;
      width: px2rem(214);
      height: px2rem(206);
      margin-left: px2rem(26);
      background: rgb(206,209,214);
      text-align: center;
     /* .box{
        position: relative;
        top: 50%;
        transform: translateY(-50%);

        .imgcom{
          @include font(31);
          color: white;
        }
      }*/
      p{
        @include font(2);
        margin-top: px2rem(120);
      }
    }
  }
</style>
