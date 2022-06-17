<template>
<div class>
</div>
</template>
<script>
import axios from '../../common/http-common';

export default {
  name: "edit-profile-form",
  props: {
    dataBackgroundColor: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      form: {
          age: null,
          money: null,
      },
      ages: [{
          text: '나이대를 선택하세요 ',
          value: null
      }, '20', '30', '40', '50', '60', '70'],
      moneys: [{
          text: '창업자본금을 선택하세요 (억이하)',
          value: null
      }, '1', '3', '5', '10'],
    };
  },
  methods: {
      updateInfo: function() {
        let token = 'Bearer ' +localStorage.getItem('jwt');
          axios.patch("api/member/modify", {
              age: this.form.age,
              money: this.form.money
          }, {
          headers: {
            Authorization:
              token,
          },}
          )
          .then(res => {
              alert('회원 정보가 수정되었습니다');
              this.$router.go()
          })
          .catch(err => {
              alert("회원 정보 수정을 실패하였습니다.");
          })
      },
    }
};
</script>
<style>

</style>
