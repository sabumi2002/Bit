// 영화검색과 관련된 데이터 취급파일

import axios from 'axios'
import _uniqBy from 'lodash/uniqBy'

const _defaultMessage = 'Search for the movie title!'

export default {
  // 현재 파일(movie.js)을 Store 모듈로 활용하려면 다음 옵션이 필요합니다.
  namespaced: true,

  // Vue.js data 옵션과 유사합니다.
  // 상태(State)는 함수로 만들어서 객체 데이터를 반환해야 가변 이슈(데이터 불변성)가 발생하지 않습니다!
  state: () => ({
    movies: [],
    message: _defaultMessage,
    loading: false,
    theMovie: {}
  }),

  // Vue.js computed 옵션과 유사합니다.
  getters: {},

  // Vue.js methods 옵션과 유사합니다.
  // 상태(State)는 변이(Mutations)를 통해서만 값을 바꿀 수 있습니다.
  // store의 Mutations를 실행할 때는 .commit()메소드를 사용한다.
  mutations: {
    updateState(state, payload) {
      // ['movies', 'message', 'loading']
      Object.keys(payload).forEach(key => {
        state[key] = payload[key] // state['movie'] = payload['movie']
      })
    },
    resetMovies(state) {
      state.movies = []
      state.message = _defaultMessage
      state.loading = false
    }
  },

  // Vue.js methods 옵션과 유사합니다.
  // 변이(Mutations)가 아닌 나머지 모든 로직을 관리합니다.
  // 비동기로 동작합니다. (async : 비동기 작동)
  // actions를 사용할 때는 .dispatch() 메소드를 사용한다.
  actions: {
    // searchMovies(context, payload) { context.commit }
    // context를 사용할 state, commit만 뽑아쓰면 더 직관적이다.
    async searchMovies({ state, commit }, payload) {
      // const { title, type, number, year } = payload
      if (state.loading) return

      // {}안 데이터들은 payment란 매개변수로 넘어감
      commit('updateState', {
        message: '',
        loading: true
      })

      try {
        const res = await _fetchMovie({
          ...payload,
          page: 1
        })
        // Search, totalResults에 res.data안에있는 search, totalResults 데이터를 넣는다
        const { Search, totalResults } = res.data
        commit('updateState', {
          movies: _uniqBy(Search, 'imdbID')
        })

        // ceil = 올림!
        const total = parseInt(totalResults, 10)  // res로 받은 totalResult가 string데이터이기 때문에 int로 변환해주기
        const pageLength = Math.ceil(total / 10) // 총 페이지의 길이

        // 추가 요청!
        if (pageLength > 1) {
          for (let page = 2; page <= pageLength; page += 1) {
            if (page > (payload.number / 10)) break
            const res = await _fetchMovie({
              ...payload,
              page
            })
            const { Search } = res.data
            commit('updateState', {
              // 새로운 요청이 들어갈때마다 새로운 배열을 만들어서 다시 무비스에 할당하겠다.
              movies: [
                // ... : 전개연산자
                ...state.movies,
                ..._uniqBy(Search, 'imdbID')
              ]
            })
          }
        }
      } catch ({ message }) {
        commit('updateState', {
          movies: [],
          message
        })
      } finally {
        commit('updateState', {
          loading: false
        })
      }
    },
    async searchMovieWithId({ state, commit }, payload) {
      // const { id } = payload
      if (state.loading) return

      commit('updateState', {
        theMovie: {},
        loading: true,
      })

      try {
        const res = await _fetchMovie(payload)
        commit('updateState', {
          theMovie: res.data
        })
      } catch (error) {
        commit('updateState', {
          theMovie: {}
        })
      } finally {
        commit('updateState', {
          loading: false
        })
      }
    }
  }
}

// eslint-disable-next-line
async function _fetchMovie(payload) {
  return await axios.post('/.netlify/functions/movie', payload)
}