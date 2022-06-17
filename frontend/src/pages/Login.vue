<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item" style="text-align: center; margin-top: 300px">
        <b-form @submit="onLogin" @reset="onReset" v-if="show">
          <b-form-group id="input-group-1" label="아이디" label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.memberId"
              type="id"
              placeholder="아이디를 입력하세요"
              required
              class="login-form"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
            <b-form-input
              type="password"
              id="input-2"
              v-model="form.password"
              placeholder="비밀번호를 입력하세요"
              class="login-form"
              required
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="success">로그인</b-button>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "../common/http-common";
import EventBus from "../EventBus";
export default {
  data() {
    return {
      form: {
        memberId: "",
        password: "",
      },
      credentials: {
        grantType: null,
        accessToken: null,
        refreshToken: null,
      },
      isLogin: false,
      show: true,
    };
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(event) {
      event.preventDefault();
      this.form.memberId = "";
      this.form.name = "";
      this.form.password = "";
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    onLogin: function () {
      axios
        .post("api/auth/login", {
          memberId: this.form.memberId,
          memberPassword: this.form.password,
        })
        .then((res) => {
          localStorage.setItem("jwt", res.data.accessToken);
          this.isLogin = true;

          EventBus.$emit("loginEmit", this.isLogin);
          this.$router.push("/");
        })
        .catch((err) => {
          alert("아이디와 비밀번호를 확인해주세요");

          this.credentials.grantType = null;
          this.credentials.accessToken = null;
          this.credentials.refreshToken = null;
        });
    },
  },
};
</script>

<style>
form {
  display: inline-block;
  width: 400px;
}
.login-form {
  margin: 20px;
}
</style>


