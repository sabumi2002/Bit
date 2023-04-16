<!-- RouterView: 뷰라우터 패키지에서 제공하는 전체영역에서 쓸수있는 전역화 되어져있는 라우터뷰를 사용해서 우리가 연결한 페이지(라우터)들을 사용할수있다.  -->
<template>
  <Header />
  <RouterView />  
  <Footer />
</template>

<script>
import Header from '~/components/Header'
import Footer from '~/components/Footer'

export default {
  components: {
    Header,
    Footer
  }
}
</script>
