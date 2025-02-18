<script setup lang="ts">
const link = ref('')
const handleSubmit = (()=>{
  console.log(`代码行🚀: link ~ -> `,link.value)
})
</script>

<template>
  <div class="body">
    <div class="heading">从所有平台下载视频/提取图像</div>
    <div class="sub-heading">免费、快速、无需登录，支持100多个平台</div>
    <div class="input-group">
      <UButtonGroup orientation="horizontal">
        <UInput class="input-sty" v-model="link" size="xl" color="neutral" variant="outline" placeholder="请粘贴视频/图像链接" />
        <UButton class="input-btn" :style="link?'cursor: pointer;':''" :disabled="!link" color="neutral" variant="subtle" @click="handleSubmit" label="提交" />
      </UButtonGroup>
    </div>
    <div class="method-sty">
      <div class="left">
        <h1>最好用的视频/图片下载器</h1>
        <h2>没有限制,免登录使用</h2>
      </div>
      <div class="right">
        <ul>
          <li>
            <span>1</span>
            <p>复制可分享的视频/图集链接</p>
          </li>
          <li>
            <span>2</span>
            <p>将链接粘贴到上面的输入框中</p>
          </li>
          <li>
            <span>3</span>
            <p>点击视频/音频/图片下载按钮</p>
          </li>
        </ul>
      </div>
    </div>
    <div class="advantage">
      <ul>
        <li>
          <p>免费使用</p>
          <p>解析和下载视频、音频、图片等文件无需任何费用。</p>
        </li>
        <li>
          <p>无次数限制</p>
          <p>本站的解析和下载服务没有次数限制，您可以根据需要随意使用。</p>
        </li>
        <li>
          <p>无需注册</p>
          <p>视频解析和下载可以直接使用，无需注册帐户或提供个人信息，充分简化了流程并尊重用户隐私。</p>
        </li>
        <li>
          <p>高清音视频</p>
          <p>默认下载最高质量的视频，并根据情况提供HD、1080P、2K、4K等选择。</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped lang="scss">
.body{
  display: flex;
  flex-direction: column;
  .heading{
    width: 100%;
    margin: 30px 0 20px 0;
    text-align: center;
    color: #3864ED;
    font-weight: bold;
    font-size: 2rem;
  }
  .sub-heading{
    width: 100%;
    margin: 0 0 30px 0;
    text-align: center;
    font-weight: bold;
  }
  .input-group{
    margin: 0 auto;
    .input-sty{
      width: 500px;
    }
    .input-btn{
      background-color: #3864ED;
      color: white;
      width: 100px !important;
      text-align: center !important;
      .truncate{
        text-align: center;
        display: inline-block;
        width: 100%;
      }
    }
  }
  .method-sty{
    margin: 30px 0;
    display: flex;
    justify-content: space-between;
    .left{
      margin-top: 50px;
      h1{
        font-weight: bold;
        font-size: 1.5rem;
      }
      h2{
        margin-top: 20px;
        font-size: 1.2rem;
      }
    }
    .right{
      background-color: rgba(229, 240, 252, 0.42);
      padding: 0 150px 0 20px;
      ul{
        width: 150%;
        li{
          width: 100%;
          margin: 20px 0 0 0;
          border-bottom: 1px solid #d5d5d5;
          &:last-child{
            border-bottom: none;
          }
          display: flex;
          span{
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            font-size: 1.1rem;
            color: white;
            background-color: red;
            border-radius: 40px;
            margin-top: -5px;
            &:nth-child(1){
              background-color: #3864ED;
            }
          }
          p{
            margin-left: 10px;
            padding: 0 0 20px 0;
          }
        }
      }
    }
  }
  .advantage{
    margin: 30px 0;
    ul{
      display: flex;
      justify-content: space-between;
      width: 100%;
      flex-wrap: wrap;
      li{
        width: 40%;
        p:nth-child(1){
          font-weight: bold;
          font-size: 1.2rem;
          margin: 10px 0;
        }
        p:nth-child(2){
          font-size: 0.9rem;
          margin: 10px 0;
        }
      }
    }
  }
}
</style>

