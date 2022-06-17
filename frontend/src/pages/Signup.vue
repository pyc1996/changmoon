<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item" style="margin-top: 100px; text-align: center">
        <b-form @submit="onSignUp" @reset="onReset">
          <b-form-group id="input-group-1" label="아이디" label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.memberId"
              type="id"
              placeholder="아이디를 입력하세요"
              required
              class="sign-form"
            ></b-form-input>
            <b-button
              type="button"
              variant="primary"
              @click="duplicateCheckMemberId"
              >중복확인</b-button
            >
          </b-form-group>

          <b-form-group id="input-group-2" label="이름" label-for="input-2">
            <b-form-input
              id="input-2"
              v-model="form.name"
              placeholder="이름을 입력하세요"
              required
              class="sign-form"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
            <b-form-input
              type="password"
              id="input-2"
              v-model="form.password"
              placeholder="비밀번호를 입력하세요"
              required
              class="sign-form"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-3" label="나이" label-for="input-3">
            <b-form-select
              id="input-3"
              v-model="form.age"
              :options="ages"
              required
              class="sign-form"
            ></b-form-select>
          </b-form-group>
          <b-form-group
            id="input-group-3"
            label="창업자본금"
            label-for="input-3"
          >
            <b-form-select
              id="input-3"
              v-model="form.money"
              :options="moneys"
              required
              class="sign-form"
            ></b-form-select>
          </b-form-group>
          <b-button type="submit" variant="success">가입하기</b-button>
          <b-button type="reset" variant="light">초기화</b-button>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "../common/http-common";
export default {
  data() {
    return {
      form: {
        memberId: "",
        name: "",
        age: null,
        password: "",
        money: null,
      },
      ages: [
        {
          text: "나이대를 선택하세요 ",
          value: "null",
        },
        "20",
        "30",
        "40",
        "50",
        "60",
        "70",
      ],
      moneys: [
        {
          text: "창업자본금을 선택하세요 (억이하)",
          value: null,
        },
        "1",
        "3",
        "5",
        "10",
      ],
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
      this.form.age = null;
      this.form.password = "";
      this.form.money = "";
    },
    onSignUp: function () {
      axios
        .post("api/auth/signup", {
          memberId: this.form.memberId,
          memberPassword: this.form.password,
          memberName: this.form.name,
          age: this.form.age,
          money: this.form.money,
        })
        .then((res) => {
          alert("가입완료 되었습니다");
          this.$router.push("login");
        })
        .catch((err) => {
          alert("가입완료 되었습니다");
          this.$router.push("login");
        });
    },

    duplicateCheckMemberId: function () {
      axios({
        method: "get",
        url: "api/member/duplicate" + "/" + this.form.memberId,
      })
        .then((res) => {
          if (res.data == false) {
            alert("가입이 가능합니다");
          } else {
            alert("다른 아이디를 입력해주세요");
          }
        })
        .catch((error) => {});
    },
  },
};
</script>

<style>
form {
  display: inline-block;
  width: 400px;
}
.sign-form {
  margin: 20px;
}
</style>
