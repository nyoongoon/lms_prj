<script setup></script>

<template>
	<el-header class="header">
		<!--      <nav>-->
		<!--        <RouterLink to="/">Home</RouterLink>-->
		<!--        <RouterLink to="/write">글작성</RouterLink>-->
		<!--      </nav>-->
		<el-menu mode="horizontal" router>
			<el-menu-item index="/">Home</el-menu-item>
			<el-menu-item index="/write">sv글 작성s</el-menu-item>
		</el-menu>
	</el-header>
</template>

<style scoped>
.header {
	padding: 0;
	height: 60px;
}
</style>
