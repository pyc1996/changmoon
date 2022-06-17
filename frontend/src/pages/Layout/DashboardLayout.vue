<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>
      
      
      <sidebar-link to="/table">
        <md-icon>content_paste</md-icon>
        <p>통계데이터</p>
      </sidebar-link>
     

      <div v-if=isLogin @click="logout">
        <sidebar-link to="/login">
        <md-icon>lock</md-icon>
        <p>로그아웃</p>
        </sidebar-link>
      </div>  

      <div v-if=isLogin>
        <sidebar-link to="/user">
        <md-icon>person</md-icon>
        <p>사용자</p>
      </sidebar-link>
      </div>

      <div v-else>
        <sidebar-link to="/login">
        <md-icon>lock</md-icon>
        <p>로그인</p>
      </sidebar-link>
      <sidebar-link to="/signup">
        <md-icon>person</md-icon>
        <p>회원가입</p>
      </sidebar-link>
      </div>
      
    </side-bar>

    <div class="main-panel">

      <dashboard-content> </dashboard-content>

    </div>
  </div>
</template>

<script>
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";
import EventBus from '../../EventBus';

export default {


  
  components: {
    DashboardContent,
    MobileMenu,
  },
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/sidebar-2.jpg"),
      isLogin: false
      
    };
  },
  computed: {
    member() {
       return localStorage.getItem('jwt')
    },
    
  },
  created() {
    EventBus.$on("loginEmit", logins => {
      this.isLogin = logins
    })
  },
  methods: {
    logout: function() {
     
      this.isLogin = false
      localStorage.removeItem('jwt')
      alert('로그아웃되었습니다')
      this.$router.push('login')
      
    }
  }
};
</script>
