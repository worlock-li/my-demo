<template>
  <tab-bar>
    <tab-bar-item path="/home" activeColor="#de4646">
      <img slot="item-img" src="../assets/img/tabbar/home.png" alt="">
      <img slot="item-img-active" src="../assets/img/tabbar/home_active.png" alt="">
      <div slot="item-text">首页</div>
    </tab-bar-item>

    <tab-bar-item path="/class">
      <img slot="item-img" src="../assets/img/tabbar/class.png" alt="">
      <img slot="item-img-active" src="../assets/img/tabbar/class_active.png" alt="">
      <div slot="item-text">分类</div>
    </tab-bar-item>

    <tab-bar-item path="/shop">
      <img slot="item-img" src="../assets/img/tabbar/shop.png" alt="">
      <img slot="item-img-active" src="../assets/img/tabbar/shop_active.png" alt="">
      <div slot="item-text">购物车</div>
    </tab-bar-item>

    <tab-bar-item path="/mine">
      <img slot="item-img" src="../assets/img/tabbar/mine.png" alt="">
      <img slot="item-img-active" src="../assets/img/tabbar/mine_active.png" alt="">
      <div slot="item-text">我的</div>
    </tab-bar-item>
  </tab-bar>
</template>

<script>

    import TabBar from "./tabbar/TabBar";
    import TabBarItem from "./tabbar/TabBarItem";

    export default {
        name: "MainTabBar",
        components: {
            TabBar,
            TabBarItem
        }
    }
</script>

<style scoped>

</style>
