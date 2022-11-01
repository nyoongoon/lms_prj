<template>
	<div class="row">
		<div class="col-md-4 margin">
			<div class="card">
				<a class="text-dark">
					<img
						th:src="${item.imgUrl}"
						class="card-img-top"
						th:alt="${item.itemNm}"
						height="400"
					/>
					<div class="card-body">
						<h4 class="card-title">{{ item.itemNm }}</h4>
						<p class="card-text">{{ item.itemDetail }}</p>
						<h3 class="card-title text-danger">{{ item.price }}원</h3>
					</div>
				</a>
			</div>
		</div>
	</div>
</template>

<script setup>
import axios from 'axios';
import { defineProps, onMounted, reactive, ref } from 'vue';
const item = ref({
	id: '',
	itemNm: '',
	price: 0,
	itemDetail: '',
	sellStatCd: null,
	regTime: '',
	updateTime: null,
});

onMounted(() => {
	// 준비되면 메소드 실행
	axios.get(`/api/members/ex02`).then(response => {
		console.log(response);
		item.value = response.data;
		console.log(item.value);
	});
});
</script>

<style lang="scss" scoped></style>
