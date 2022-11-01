<template>
	<div>
		<Header />
		<RouterView />
		<!-- <Main /> -->
		<Footer />
	</div>
</template>

<script setup>
//import Header from '@/components/Header.vue';
import Header from '@/components/HeaderEx.vue';
// import Main from '@/components/ItemCardList.vue';
import Footer from '@/components/Footer.vue';
</script>

<style lang="scss" scoped></style>
