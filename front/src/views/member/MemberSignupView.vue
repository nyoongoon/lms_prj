<template>
	<form
		action="/members/new"
		role="form"
		method="post"
		th:object="${memberFormDto}"
	>
		<div class="form-group">
			<label for="name">이름</label>
			<input
				@focus="err.name = ''"
				v-model="name"
				type="text"
				class="form-control"
				placeholder="이름을 입력해주세요"
			/>
			<p class="fieldError">{{ err.name }}</p>
		</div>
		<div class="form-group">
			<label for="email">이메일주소</label>
			<input
				@focus="err.email = ''"
				v-model="email"
				type="email"
				class="form-control"
				placeholder="이메일을 입력해주세요"
			/>
			<p class="fieldError">{{ err.email }}</p>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input
				@focus="err.password = ''"
				v-model="password"
				type="password"
				class="form-control"
				placeholder="비밀번호 입력"
			/>
			<p class="fieldError">{{ err.password }}</p>
		</div>
		<div class="form-group">
			<label for="address">주소</label>
			<input
				@focus="err.address = ''"
				v-model="address"
				type="text"
				class="form-control"
				placeholder="주소를 입력해주세요"
			/>
			<p class="fieldError">{{ err.address }}</p>
		</div>
		<div style="text-align: center">
			<button
				@click.prevent="signup"
				type="submit"
				class="btn btn-primary"
				style=""
			>
				회원가입
			</button>
		</div>
	</form>
</template>

<script setup>
import { reactive, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
const name = ref('');
// const nameErr = ref('');
const email = ref('');
// const emailErr = ref('');
const password = ref('');
// const passwordErr = ref('');
const address = ref('');
// const addressErr = ref('');
const router = useRouter();
const err = reactive({});

const signup = () => {
	axios
		.post('/api/members/new', {
			name: name.value,
			email: email.value,
			password: password.value,
			address: address.value,
		})
		.then(res => {
			// console.log(res);
			//router.replace({ name: 'ItemList' }); //replace 뒤로가기 막기. push는 뒤로갈 수 있음
			res.data.forEach(ele => {
				// console.log(ele);
				// console.log(ele.field);
				err[ele.field] = ele.defaultMessage;
			});
			err = res.data;
		})
		.catch(error => {
			// alert(error.response.data.message);
			err.email = error.response.data.message;
		});
};
</script>

<style scoped>
.fieldError {
	color: #bd2130;
}
</style>
