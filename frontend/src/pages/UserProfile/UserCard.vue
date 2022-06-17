<template>
  <div class="content" style="margin-left: 500px">
    <div class="md-layout">
      <div class="md-layout-item" style="margin-top: 200px; text-align: center">
        <b-form @submit="updateInfo">
          <b-form-group id="input-group-1" label="아이디" label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.memberId"
              type="id"
              placeholder="아이디를 입력하세요"
              disabled
              class="update-form"
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-2" label="이름" label-for="input-2">
            <b-form-input
              id="input-2"
              v-model="form.name"
              placeholder="이름을 입력하세요"
              required
              class="update-form"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
            <b-form-input
              type="password"
              id="input-2"
              v-model="form.password"
              placeholder="새 비밀번호를 입력하세요"
              required
              class="update-form"
            ></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="나이" label-for="input-2">
            <b-form-input
              type="input-3"
              id="input-2"
              v-model="form.age"
              required
              disabled
              class="update-form"
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="input-group-3"
            label="창업자본금 변경"
            label-for="input-3"
          >
            <b-form-select
              id="input-3"
              v-model="form.money"
              :options="moneys"
              required
              class="update-form"
            ></b-form-select>
          </b-form-group>
          <b-button type="submit" variant="success">수정하기</b-button>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import EditProfileForm from "./EditProfileForm.vue";
import axios from "../../common/http-common";

export default {
  created: function () {
    axios({
      method: "get",
      url: `api/member/me`,
      headers: { Authorization: `Bearer ` + localStorage.getItem("jwt") },
    })
      .then((res) => {
        const memberInfo = res.data;
        this.form = memberInfo;
        this.form.memberId = memberInfo.memberId;
        this.form.name = memberInfo.name;
        this.form.age = memberInfo.age;
        this.form.money = memberInfo.money;
        this.form.password = "";
      })
      .catch((err) => {});
  },

  data() {
    return {
      form: {
        memberId: "",
        name: "",
        age: "",
        password: "",
        money: null,
      },

      updateDate: {
        memberId: "",
        memberName: "",
        memberPassword: "",
        age: "",
        money: "",
      },

      moneys: [
        {
          text: "창업자본금을 선택하세요 (억이하)",
          value: "asd",
        },
        "1",
        "3",
        "5",
        "10",
      ],
    };
  },
  methods: {
    updateInfo() {
      axios
        .put(
          "api/member/modify",
          {
            memberName: this.form.name,
            memberPassword: this.form.password,
            memberId: this.form.memberId,
            age: this.form.age,
            money: this.form.money,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("jwt"),
            },
          }
        )
        .then((res) => {
          alert("수정이 완료되었습니다");
          this.$router.push("/home");
        })
        .catch((err) => {});
    },
  },
};
</script>
<style>
form {
  display: inline-block;
  width: 400px;
}
.update-form {
  margin: 20px;
}
</style>
