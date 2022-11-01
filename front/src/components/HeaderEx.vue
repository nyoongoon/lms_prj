<template>
	<nav class="navbar navbar-expand-sm bg-primary navbar-dark" router>
		<button
			class="navbar-toggler"
			type="button"
			data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03"
			aria-expanded="false"
			aria-label="Toggle navigation"
		>
			<span class="navbar-toggler-icon"></span>
		</button>
		<router-link class="navbar-brand" to="/">Shop</router-link>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item" sec:authorize="hasAnyAuthority('ROLE_ADMIN')">
					<router-link class="nav-link" to="/admin/item/new"
						>상품 등록</router-link
					>
				</li>
				<li class="nav-item" sec:authorize="hasAnyAuthority('ROLE_ADMIN')">
					<router-link class="nav-link" to="/admin/items"
						>상품 관리</router-link
					>
				</li>
				<li class="nav-item" sec:authorize="isAuthenticated()">
					<router-link class="nav-link" to="/cart">장바구니</router-link>
				</li>
				<li class="nav-item" sec:authorize="isAuthenticated()">
					<router-link class="nav-link" to="/orders">구매이력</router-link>
				</li>
				<li class="nav-item">
					<router-link class="nav-link" to="/loginForm">로그인</router-link>
				</li>
				<li class="nav-item" sec:authorize="isAuthenticated()">
					<router-link class="nav-link" to="/members/logout"
						>로그아웃</router-link
					>
				</li>
				<li class="nav-item" sec:authorize="isAnonymous()">
					<router-link class="nav-link" to="/members/signup"
						>회원가입</router-link
					>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0" th:action="@{/}" method="get">
				<input
					name="searchQuery"
					class="form-control mr-sm-2"
					type="search"
					placeholder="Search"
					aria-label="Search"
				/>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">
					Search
				</button>
			</form>
		</div>
	</nav>
</template>

<script setup></script>

<style lang="scss" scoped></style>
