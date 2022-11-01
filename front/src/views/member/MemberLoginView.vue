<template>
	<form role="form" method="post" action="/members/login">
		<div class="form-group">
			<label for="email">이메일주소</label>
			<input
				v-model="email"
				type="email"
				name="email"
				class="form-control"
				placeholder="이메일을 입력해주세요"
			/>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input
				v-model="password"
				type="password"
				name="password"
				id="password"
				class="form-control"
				placeholder="비밀번호 입력"
			/>
		</div>
		<p v-if="loginErrorMsg" class="error">
			{{ loginErrorMsg }}
		</p>
		<button @click.self.prevent="login" class="btn btn-primary">로그인</button>
		<router-link type="button" class="btn btn-primary" to="/members/signup"
			>회원가입</router-link
		>
	</form>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
const loginErrorMsg = ref('');
const email = ref('');
const password = ref('');
const login = () => {
	console.log(email.value);
	console.log(password.value);
	axios
		.post('/api/members/login', {
			form: {
				email: email.value,
				password: password.value,
			},
		})
		.then(res => {
			console.log(res.data);
		})
		.catch(error => {
			console.log(error);
		});
};
</script>

<style lang="scss" scoped></style>
