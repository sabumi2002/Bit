<template>
  <RouterLink
    :to="`/movie/${movie.imdbID}`"
    :style="{ backgroundImage: `url(${movie.Poster})` }"
    class="movie">
    <Loader
      v-if="imageLoading"
      :size="1.5"
      absolute />
    <div class="info">
      <div class="year">
        {{ movie.Year }}
      </div>
      <div class="title">
        {{ movie.Title }}
      </div>
    </div>
  </RouterLink>
</template>

<script>
import Loader from '~/components/Loader'

export default {
  components: {
    Loader
  },
  props: {
    movie: {
      // 객체 타입은 object로 하겠다.
      type: Object,
      // 기본값은 빈객체로 하겠다. , 함수로 만들어서 반환해야한다. 배열객체등 무엇을를 반환할지 모르기때문
      default: () => ({})
    }
  },
  data() {
    return {
      imageLoading: true
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    async init() {
      const poster = this.movie.Poster
      if (!poster || poster === 'N/A') {
        this.imageLoading = false
      } else {
        await this.$loadImage(poster)
        this.imageLoading = false
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.movie {
  $width: 200px;
  width: $width;
  height: $width * 3/2;
  margin: 10px;
  border-radius: 4px;
  background-color: $gray-400;
  background-size: cover;
  overflow: hidden;
  position: relative;
  &:hover::after {
    content: "";
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    border: 6px solid $primary;
  }
  .info {
    background-color: rgba($black, .3);
    backdrop-filter: blur(10px);
    width: 100%;
    padding: 14px;
    font-size: 14px;
    text-align: center;
    box-sizing: border-box;
    position: absolute;
    left: 0;
    bottom: 0;
    .year {
      color: $primary;
    }
    .title {
      color: $white;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
}
</style>
