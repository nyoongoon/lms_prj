import { createRouter, createWebHistory } from 'vue-router';
// import HomeView from '../views/HomeView.vue';
import ItemListView from '@/components/ItemListView.vue';
import WriteView from '../views/WriteView.vue';
import ReadView from '../views/ReadView.vue';
import EditView from '../views/EditView.vue';
import Promotion from '../views/PromotionView.vue';
import MemberSignup from '@/views/member/MemberSignupView.vue';
import MemberLogin from '@/views/member/MemberLoginView.vue';
import ItemCreate from '@/views/item/ItemCreateView.vue';

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/',
			name: 'ItemList',
			component: ItemListView,
		},
		{
			path: '/write',
			name: 'write',
			component: WriteView,
		},
		{
			path: '/read/:postId', //파라미터 설정
			name: 'read',
			component: ReadView,
			props: true,
		},
		{
			path: '/edit/:postId', //파라미터 설정
			name: 'edit',
			component: EditView,
			props: true,
		},
		{
			path: '/edit/:postId', //파라미터 설정
			name: 'edit',
			component: EditView,
			props: true,
		},
		{
			path: '/promotion', //파라미터 설정
			name: 'promotion',
			component: Promotion,
			props: true,
		},
		{
			path: '/members/signup', //파라미터 설정
			name: 'memberSignup',
			component: MemberSignup,
			props: true,
		},
		{
			path: '/loginForm', //파라미터 설정
			name: 'memberLogin',
			component: MemberLogin,
			props: true,
		},
		{
			path: '/admin/item/new', //파라미터 설정
			name: 'itemCreate',
			component: ItemCreate,
			props: true,
		},
	],
});

export default router;
