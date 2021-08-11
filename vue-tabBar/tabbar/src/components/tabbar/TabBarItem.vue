<template>
  <div class="tab-bar-item" @click="clickItem">
    <div v-if="!isActive">
      <slot name="item-img"></slot>
    </div>
    <div v-else>
      <slot name="item-img-active"></slot>
    </div>
    <div :style="activeStyle">
      <slot name="item-text"></slot>
    </div>
  </div>
</template>

<script>
    export default {
        name: "TabBarItem",
        props: {
            // 父组件向子组件传递的数据
            path: String,
            activeColor: {
                type: String,
                default: '#de4646'
            }
        },
        data () {
            return {
                // isActive : false
            }
        },
        computed: {
            isActive() {
                // $route 里面存储的是当前活跃状态的数据
                return this.$route.path.indexOf(this.path) !== -1;
            },
            // 动态获取要展示的颜色， activeColor可以自己传递
            activeStyle() {
                return this.isActive ? {color : this.activeColor} : {}
            }
        },
        methods: {
            clickItem() {
                this.$router.replace(this.path)
            }
        }
    }
</script>

<style scoped>
  .tab-bar-item {
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
  }

  .tab-bar-item img {
    width: 24px;
    height: 24px;
    margin-top: 3px;
    vertical-align: middle;
    margin-bottom: 2px;
  }

</style>
