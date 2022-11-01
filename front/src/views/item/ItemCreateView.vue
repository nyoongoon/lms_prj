<template>
	<div>
		<form role="form" method="post" enctype="multipart/form-data">
			<p class="h2">상품 등록</p>

			<input type="hidden" th:field="*{id}" />

			<div class="form-group">
				<select v-model="itemSellStatus" class="custom-select">
					<option value="SELL">판매중</option>
					<option value="SOLD_OUT">품절</option>
				</select>
			</div>

			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">상품명</span>
				</div>
				<input
					v-model="itemNm"
					type="text"
					th:field="*{itemNm}"
					class="form-control"
					placeholder="상품명을 입력해주세요"
				/>
			</div>
			<p
				th:if="${#fields.hasErrors('itemNm')}"
				th:errors="*{itemNm}"
				class="fieldError"
			>
				Incorrect data
			</p>

			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">가격</span>
				</div>
				<input
					v-model="price"
					type="number"
					th:field="*{price}"
					class="form-control"
					placeholder="상품의 가격을 입력해주세요"
				/>
			</div>
			<p
				th:if="${#fields.hasErrors('price')}"
				th:errors="*{price}"
				class="fieldError"
			>
				Incorrect data
			</p>

			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">재고</span>
				</div>
				<input
					v-model="stockNumber"
					type="number"
					th:field="*{stockNumber}"
					class="form-control"
					placeholder="상품의 재고를 입력해주세요"
				/>
			</div>
			<p
				th:if="${#fields.hasErrors('stockNumber')}"
				th:errors="*{stockNumber}"
				class="fieldError"
			>
				Incorrect data
			</p>

			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">상품 상세 내용</span>
				</div>
				<textarea
					v-model="itemDetail"
					class="form-control"
					aria-label="With textarea"
					th:field="*{itemDetail}"
				></textarea>
			</div>
			<p
				th:if="${#fields.hasErrors('itemDetail')}"
				th:errors="*{itemDetail}"
				class="fieldError"
			>
				Incorrect data
			</p>

			<div th:if="${#lists.isEmpty(itemFormDto.itemImgDtoList)}">
				<div class="form-group" th:each="num: ${#numbers.sequence(1,5)}">
					<div class="custom-file img-div">
						<input type="file" class="custom-file-input" name="itemImgFile" />
						<label
							class="custom-file-label"
							th:text="상품이미지 + ${num}"
						></label>
					</div>
				</div>
			</div>

			<div th:if="${not #lists.isEmpty(itemFormDto.itemImgDtoList)}">
				<div
					class="form-group"
					th:each="itemImgDto, status: ${itemFormDto.itemImgDtoList}"
				>
					<div class="custom-file img-div">
						<input type="file" class="custom-file-input" name="itemImgFile" />
						<input
							type="hidden"
							name="itemImgIds"
							th:value="${itemImgDto.id}"
						/>
						<label
							class="custom-file-label"
							th:text="${not #strings.isEmpty(itemImgDto.oriImgName)} ? ${itemImgDto.oriImgName} : '상품이미지' + ${status.index+1}"
						></label>
					</div>
				</div>
			</div>

			<div
				th:if="${#strings.isEmpty(itemFormDto.id)}"
				style="text-align: center"
			>
				<button
					@click.prevent="createItemRequest"
					th:formaction="@{/admin/item/new}"
					type="submit"
					class="btn btn-primary"
				>
					저장
				</button>
			</div>
			<div
				th:unless="${#strings.isEmpty(itemFormDto.id)}"
				style="text-align: center"
			>
				<button
					th:formaction="@{'/admin/item/' + ${itemFormDto.id} }"
					type="submit"
					class="btn btn-primary"
				>
					수정
				</button>
			</div>
		</form>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const itemSellStatus = ref('');
const itemNm = ref('');
const price = ref(0);
const stockNumber = ref(0);
const itemDetail = ref('');
const itemImgList = ref('');

const createItemRequest = () => {
	axios
		.post('/api/admin/item/new', {})
		.then(res => {
			console.log(res);
		})
		.catch(error => {
			console.log(error);
		});
};
</script>

<style scoped>
.input-group {
	margin-bottom: 15px;
}
.img-div {
	margin-bottom: 10px;
}
.fieldError {
	color: #bd2130;
}
</style>
