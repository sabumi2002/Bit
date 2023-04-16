// 페이지를 구성하는 하나의 구성파일
import { createRouter, createWebHashHistory } from 'vue-router'
import Home from './Home'   // Home.vue파일 컴포넌트 가져오기 (.vue 확장자가없는 이유는 웹팩에 생략할수있게 설정해놓았기때문)
import Movie from './Movie'
import About from './About'
import NotFound from './NotFound'

// 기본 내보내기
export default createRouter({
  // Hash , History 모드 두가지가있는데 hash모드 사용함.
  // Hash모드: http://google.com/#/search 특정 페이지에서 새로고침했을때 페이지를 찾을수없게 방지하기 위해
  history: createWebHashHistory(),
  scrollBehavior() {
    return { top: 0 }
  },

  // 웹사이트의 페이지들을 구분해주는 개념
  routes: [
    {
      path: '/',
      component: Home // 어떤 컴포넌트와 연결할지 명시 (vue파일)
    },
    {
      path: '/movie/:id',
      component: Movie
    },
    {
      path: '/about',
      component: About
    },
    {
      path: '/:notFound(.*)',
      component: NotFound
    }
  ]
})
