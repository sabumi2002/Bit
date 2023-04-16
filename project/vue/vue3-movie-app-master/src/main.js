import { createApp } from 'vue'
import App from './App'
// store(vuex) 가져오기
// 특정폴더의 index.js파일을 가져올떈 index.js를 생략해줄수있다.
import store from './store'  // Same as './store/index.js'
import router from './routes' // Same as './routes/index.js'
import loadImage from './plugins/loadImage'


// 페이지 관리
createApp(App)
  .use(store)
  .use(router)  // 현재 페이지에 연결하는 플러그인
  .use(loadImage)
  .mount('#app')
