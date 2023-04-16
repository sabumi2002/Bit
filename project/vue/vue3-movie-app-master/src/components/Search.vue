<template>
  <div class="container">

    <!-- @keyup 키 이벤트 input키 엔터누르면 apply 실행 -->
    <input
      v-model="title"
      class="form-control"
      type="text"
      placeholder="Search for Movies, Series & more"
      @keyup.enter="apply" />
    <div class="selects">

      <!-- key : 해당 데이터가 고유하다 알려줌 (v-for 쓰면 반드시 써줘야함)  -->
      <!-- v-model : 양방향데이터 바인딩 -->
      <select
        v-for="filter in filters"
        v-model="$data[filter.name]"
        :key="filter.name"
        class="form-select">
        <option
          v-if="filter.name === 'year'"
          value="">
          All Years
        </option>
        <option
          v-for="item in filter.items"
          :key="item">
          {{ item }}
        </option>
      </select>
    </div>
    <button
      class="btn btn-primary"
      @click="apply">
      Apply
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: '',
      type: 'movie',
      number: 10,
      year: '',
      filters: [
        {
          name: 'type',
          items: ['movie', 'series', 'episode']
        },
        {
          name: 'number',
          items: [10, 20, 30]
        },
        {
          name: 'year',
          items: (() => {
            const years = []

            // 현재년도 반환
            const thisYear = new Date().getFullYear()
            for (let i = thisYear; i >= 1985; i -= 1) {
              years.push(i)
            }
            return years
          })()
        }
      ]
    }
  },
  methods: {
    // search movie (영화검색 기능)
    apply() {
      // store의 actions를 실행할 때는 .dispatch() 메소드를 사용한다.
      // dispatch의 파라미터로 작성된 movie는 store/index.js에 모듈화한 이름을 넣어야한다.
      // 매개변수로 payload를 전달한다. (여기선 {}안의 title, type, number, year)
      this.$store.dispatch('movie/searchMovies', {
        title: this.title,
        type: this.type,
        number: this.number,
        year: this.year
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  // 모든 자식요소
  > * {
    margin-right: 10px;
    font-size: 15px;
    // 모든 자식요소중 마지막 요소
    &:last-child {
      margin-right: 0;
    }
  }
  .selects {
    display: flex;
    select {
      width: 120px;
      margin-right: 10px;
      &:last-child {
        margin-right: 0;
      }
    }
  }
  .btn {
    width: 120px;
    height: 50px;
    // 가로너비가 절대 감소하지않겠다. (방지)
    flex-shrink: 0;
    font-weight: 700;
  }

  @include media-breakpoint-down(lg) {
    display: block;
    input {
      margin-right: 0;
      margin-bottom: 10px;
    }
    .selects {
      margin-right: 0;
      margin-bottom: 10px;
      select {
        width: 100%;
      }
    }
    .btn {
      width: 100%;
    }
  }
}
</style>
