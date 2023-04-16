// store 생성
import { createStore } from 'vuex'
import movie from './movie'
import about from './about'

// createStroe함수 실행
export default createStore({
  // 모듈 생성
  modules: {
    movie,
    about
  }
})
