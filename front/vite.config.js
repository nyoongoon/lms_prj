import { fileURLToPath, URL } from 'node:url';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueJsx from '@vitejs/plugin-vue-jsx';
const fs = require('fs');

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [vue(), vueJsx()],
	resolve: {
		alias: {
			'@': fileURLToPath(new URL('./src', import.meta.url)),
		},
	},
	server: {
		// https:{
		//   pfx: fs.readFileSync('/cert/_wildcard_.dasanpass.com_20221012A77E4.pfx'),
		//   passphrase: 'uwxxi20p'
		// },
		proxy: {
			'/api': {
				target: 'http://localhost:80',
				rewrite: path => path.replace(/^\/api/, ''),
			},
		},
		strictPort: true,
	},
});
