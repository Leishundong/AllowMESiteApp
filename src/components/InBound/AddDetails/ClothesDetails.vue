<template>
  <div class="box">
    <div class="bar-box">
      <p>瑕疵：</p>
      <div style="background: white;text-align: center">
        <select  v-model="Before">
          <option  v-for="option in BeforeOptions">
            {{ option.type }}
          </option>
        </select>
      </div>
      <i class="iconfont icon-arrow_down_full img"></i>
      <span class="button"><div class="span" @click="addBeforeData"><span>添加</span></div></span>
    </div>
    <div  class="data-box">
      <div class="top"><span class="float" v-for="item in BeforeData">{{item}}</span></div>
    </div>
    <div style="clear: both"></div>
    <div class="bar-box">
      <p>洗后效果：</p>
      <div style="background: white;text-align: center">
        <select class="select" v-model="After">
          <option class="option" v-for="option in AfterOptions">
            {{ option.type }}
          </option>
        </select>
      </div>
      <i class="iconfont icon-arrow_down_full img"></i>
      <span class="button"><div class="span"><span @click="addAfterData">添加</span></div></span>
    </div>
    <div  class="data-box">
      <div class="top"><span class="float" v-for="item in AfterData">{{item}}</span></div>
    </div>
    <div style="clear: both"></div>
    <div style="clear: both"></div>
  </div>
</template>
<script>
  export default {
    name:'ClothesDetails',
    data(){
      return{
        Message:'备注信息',
        Before:'请选择',
        After:'请选择',
        Remarks:'',
        BeforeOptions:[
          { type:'缺腰带'},
          { type:'缺腰带袖带'},
          { type:'缺毛领'},
          { type:'缺帽'},
          { type:'缺扣'},
          { type:'勾丝'},
          { type:'衣服划痕'},
          { type:'衣服掉色色花'},
          { type:'面料发黄'},
          { type:'面料有色差'},
          { type:'衣服边角有磨损'},
          { type:'衣服有烫伤'},
          { type:'起球起毛'}],
        AfterOptions: [
          { type:'白色皮毛洗后易局部发黄'},
          { type:'洗后革破会扩大'},
          { type:'洗后涂层破损扩大'},
          { type:'洗后会有渍迹残留'},
          { type:'洗后珠不保会掉'},
          { type:'洗后可能会变形'},
          { type:'装饰物洗后会掉'},
          { type:'洗后可能串色'},
          { type:'洗后可能发黄'},
          { type:'陈旧黄斑可能洗不掉'},
          { type:'洗后可能发硬'},
          { type:'洗后可能褪色'},
          { type:'洗后可能掉毛'},
          { type:'笔迹可能洗不掉'},
          { type:'化工类污渍可能洗不掉'},
          { type:'洗后涂层破损扩大'},
          { type:'洗后可能会色差'},
          { type:'洗后可能缩水'},
          { type:'洗后深浅面料搭配可能搭色'},
          { type:'洗后可能局部破损'},
          { type:'磨损严重部位洗后会扩大'},
          { type:'洗后可能颜色不均'},
          { type:'不可拆的附件可能被污染，腐蚀'},
          { type:'发黑严重上色后风格与软度会轻度改变'},
          { type:'彩色皮上色后与原色有误差'},
          { type:'浅色皮上色后发黑的盖不住'},
          { type:'有孔皮上色和清洗会影响里布'},
          { type:'轻薄皮衣经上色后皮感会轻度发硬'},
          { type:'革已破，洗后会扩大'},
          { type:'纽扣洗后会掉色'},
          { type:'洗后填充物不均'},
          { type:'洗后可能掉绒'}
        ]
      }
    },
    props:{
      BeforeData:{
        type:Array,
        required:true
      },
      AfterData:{
        type:Array,
        required:true
      }
    },
    methods:{
      addBeforeData(){
        let type = true;
        if(this.BeforeData!=''){
          this.BeforeData.forEach((item,index)=>{
            if(item == this.Before){
              type = false;
              return
            }
          })
        };
        if(type == true){
          this.BeforeData.push(this.Before);
          this.Before = '';
          let Before = '';
          this.BeforeData.forEach((item,index)=>{
            if(index==this.BeforeData.length-1){
              Before = Before+item
            }else {
              Before = Before+item+','
            }
          });
          this.$emit('Before',Before);
        }else {
          this.$alert('重复输入')
        }
      },
      addAfterData(){
        let type = true;
        if(this.AfterData!=''){
          this.AfterData.forEach((item,index)=>{
            if(item == this.After){
              type = false;
              return
            }
          })
        };
        if(type == true){
          this.AfterData.push(this.After);
          this.After = '';
          let After = '';
          this.AfterData.forEach((item,index)=>{
            if(index==this.AfterData.length-1){
              After = After+item
            }else {
              After = After+item+','
            }
          });
          this.$emit('After',After);
        }else {
          this.$alert('重复输入')
        }
      },
      remark(){
        this.$emit('Remarks',this.Remarks);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  @font-face {
    font-family: MRBlod;
    src: url('../../../../static/font/MRBlod.ttf');
  }
  .box{
    margin-left: px2rem(36);
    .data-box{
      .top{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
       .float{
         margin-top: px2rem(30);
         @include font(2);
         font-family: MRBlod ;
         color: black;
         margin-left: px2rem(30);
         float: left;
       }
      }
    }
    p{
      @include font(5);
      color: $color-background-general;
    }
    input{
      @include font(5);
      float: left;
      margin-top: px2rem(22);
      text-indent: px2rem(12);
      width: px2rem(520);
      line-height: px2rem(73);
    }
    select{
      @include font(5);
      float: left;
      background: white;
      margin-top: px2rem(22);
      text-indent: px2rem(12);
      width: px2rem(520);
      height: px2rem(73);
      appearance:none;
      option{
        color: black;
        border:0;
        text-align:center;
        @include font(-1);
        background: white;
      }

    }
    .note{
      width: px2rem(688);
    }
    .bar-box{
      margin-top: px2rem(47);
      height: px2rem(73);
      .img{
        position: absolute;
        @include font(5);
        color: $color-background-general;
        margin-left: px2rem(-50);
        margin-top: 5%;
      }
    }
    .button{
      margin-top: px2rem(22);
      float: left;
      text-align: center;
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
</style>
