<template>
  <Headline />
  <!-- 3.search컴포넌트 사용하기 -->
  <Search />
  <MovieList />
</template>

<script>
import Headline from '~/components/Headline'
// 1.search첨포넌트 임포트하기
import Search from '~/components/Search'
import MovieList from '~/components/MovieList'

export default {
  components: {
    Headline,
    // 2.search 컴포넌트 등록하기
    Search,
    MovieList
  },
  created() {
    this.$store.commit('movie/resetMovies')
  }
}
</script>
