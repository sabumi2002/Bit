module.exports = {
    env: {
        // 브라우저환경와 노드js환경에서 모두 코드검사할것인지
        browser: true, 
        node: true
    },
    // 코드검사를할 기본적인 규칙
    extends: [
        
        // vue
        // 'plugin:vue/vue3-essential', // Lv1
        'plugin:vue/vue3-strongly-recommended', // Lv2
        // 'plugin:vue/vue3-recommended', // Lv3

        //js
        'eslint: recommended'
    ],
    // 코드 분석할수있는 분석기를 지정해주어야함
    parserOptions: {
        // babel: es6 이상의 문법을 구버전 문법에 맞게 es로 변경해주는 역할
        parser: 'babel-eslint'
    },
    rules: {
        
    }
} 