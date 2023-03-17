// const timeout1 = setTimeout(() => {
//     console.log('1.5초 후 실행');
// }, 1500);
// const interval = setInterval(() => {
//     console.log('1초마다 실행 예정');
// }, 1000);
// const timeout2 = setTimeout(() => {
//     console.log('3초 후 실행 예정');
// }, 3000)    // 3초 후 실행
// setTimeout(()=>{    
//     // 3초 후 실행 예정인 타이머 함수 호출 취소
//     clearTimeout(timeout2);
//     // 1초마다 실행 예정인 타이머 함수 호출 취소
//     clearInterval(interval);
// }, 2500);   // 2.5초 후 타이머 함수 2개 호출 취소
// const immediate1 = setImmediate(()=>{
//     console.log('즉시 실행');
// });
// const immediate2 = setImmediate(()=>{
//     console.log('즉시 실행 예정');
// });
// // 즉시 실행 예정인 타이머 함수 중 2번쨰 호출 취소
// clearImmediate(immediate2);

// ----------------------------------------------------


// function work(sec, callback){
//     setTimeout(()=>{
//         callback(new Date().toLocaleString());
//     }, sec*1000);
// };


// console.log("비동기를 동기식으로 만들고 싶을떄 (실패) //같은시간에 끝남");
// work(1, (result)=>{
//     console.log("first", result);
// });
// work(1, (result)=>{
//     console.log("second", result);
// });
// work(1, (result)=>{
//     console.log("third", result);
// });


// console.log("비동기를 이용하여 동기식으로 처리했지만 가독성이 떨어짐1");
// work(1, (result)=> {
//     console.log('first', result);
//     work(1, (result) => {
//         console.log('second', result);
//         work(1, (result)=>{
//             console.log('third', result);
//         });
//     });
// });


// console.log("비동기를 이용하여 동기식으로 처리했지만 가독성이 떨어짐2");
// work(1, (result)=>{
//     console.log('first', result);
//     work(1, (result)=>{
//         work(1, (result)=>{
//             console.log('third', result);
//         });
//         console.log('second', result);
//     });
// });

// ----------------------------------------------------


// const promise = new Promise((resolve)=>{
//     setTimeout(()=>{
//         //resolve() 호출 시 promise 처리 완료
//         resolve();
//     }, 1000);
// })
// //then()으로 다음 작업
// promise.then(()=>{
//     console.log('다음 작업');   // 1초후 실행
// })



// const promise = new Promise((resolve)=>{
//     setTimeout(()=>{
//         //인수로 임의의 값 지정 가능
//         resolve('orange');
//     }, 1000);
// })
// promise.then((value)=>{
//     console.log(value); //'orange' 출력
// })



//promise(4/6)
// let flag = false;
// const promise = new Promise((resolve, reject)=>{
//     if(flag === true){
//         resolve('orange');
    
//     }else{
//         reject('apple');
//     }
// });
// promise.then((value)=>{
//     console.log(value); // 'orange'
// });
// promise.catch((value)=>{
//     console.log(value); // 'orange'
// });



// // 메소드 체인
// let flag = false;
// new Promise((resolve, reject)=>{
//     if(flag===true){
//         resolve('orange');
//     }else{
//         reject('apple');
//     }
// }).then((value)=>{
//     console.log(value);
// }).catch((value)=>{
//     console.log(value);
// });


// promise(5/6)
// const promise1 = Promise.resolve('성공1');
// const promise2 = Promise.resolve('성공2');
// const promise3 = Promise.reject('실패1');
// Promise.all([promise1, promise2, promise3])
// .then((result)=> {
//     console.log("success");
//     console.log(result);
// })
// .catch((error)=>{
//     console.log("error");
//     console.error(error);
// })


// const promise1 = Promise.resolve('성공1');
// const promise2 = Promise.resolve('성공2');
// const promise3 = Promise.reject('실패1');
// Promise.allSettled([promise1, promise2, promise3])
// .then((result)=>{
//     console.log(result);
// })
// .catch((error)=>{
//     console.error(error);
// })
// 결과값
//[
//     { status: 'fulfilled', value: '성공1' },
//     { status: 'fulfilled', value: '성공2' },
//     { status: 'rejected', reason: '실패1' }
//   ]


// function workP(sec){
//     // Promise 인스턴스를 반환하고, then에서는 성공시 콜백함수 호출
//     return new Promise((resolve, reject)=>{
//         //Promise 생성시 넘기는 callback
//         //resolve: 동작 완료시, reject: 오류 발생시
//         setTimeout(()=>{
//             resolve(new Date().toLocaleString());
//         }, sec*1000);
//     });
// }
// workP(1).then((result)=>{
//     console.log('first', result);
//     // workP()를 호출하고 반환하는 Promise 인스턴스를 넘겨 받아 
//     // resolve를 통해 받은 결과값을 사용할 수 있음
//     return workP(1);
// }).then((result)=>{
//     console.log('second', result);
//     return workP(1);
// }).then((result)=>{
//     console.log('third', result);
// });


// function workP(sec){
//     return new Promise((resolve, reject)=>{
//         setTimeout(()=>{
//             resolve(new Date().toLocaleDateString());
//         }, sec*1000);
//     });
// }
// function generalFunc(){     // 일반 함수
//     return 'general Function';
// }
// async function asyncFunc(){ // async 함수
//     return 'async Function';
// }
// console.log(generalFunc());
// console.log(asyncFunc());   //Promise 객체반환
// console.log(workP());   //Promise 객체반환




// function workP(sec){
//     return new Promise((resolve, reject)=>{
//         setTimeout(()=>{
//             resolve('workP Function');
//         }, sec*1000);
//     });
// }
// async function asyncFunc(){
//     //workP(3)의 작업이 완료될 때까지 기다림
//     const result_workP = await workP(3);
//     console.log(result_workP);
//     return 'async Function';
// }
// asyncFunc().then((result)=>{
//     console.log(result);
// })



// start();
// async function start(){
//     await new Promise((resolve)=>{
//         setTimeout(()=>{
//             console.log('첫 번째 Promise', new Date().toLocaleDateString());
//             resolve();
//         }, 1000);
//     });
//     await new Promise((resolve)=> {
//         setTimeout(()=>{
//             console.log('두 번째 Promise', new Date().toLocaleDateString());
//             resolve();
//         }, 1000);
//     });
// }


// 연습문제
// function workP(order, sec){
//     return new Promise((resolve, reject)=>{
//         setTimeout(()=>{
//             console.log(order, new Date().toLocaleString());
//             resolve();
//         }, sec*1000);
//     });
// }
// workP("first", 1).then(()=>{
//     workP("second", 1).then(()=>{
//         workP("third", 1);
//     });
// });


// let http = require('http');
// let server = http.createServer();
// let port = 3000;
// server.listen(port, ()=>{
//     console.log('웹 서버 시작 : %d', port);
//     console.log('클라이언트 요청');
// });
// let fs = require('fs');
// server.on('request', function(req, res){
//     console.log('클라이언트 요청');
//     let filename = './jx0309/fs-res.html';
//     fs.readFile(filename, function(err, data){
//         res.writeHead(200, {"Content-Type": "text/html;charset=utf-8"});
//         res.write(data);
//         res.end();
//     })
// })


// const http = require('http');
// http.createServer((req, res)=>{
//     console.log(req);
//     console.log(res);
// }).listen(3000, () =>{
//     console.log("3000포트에서 서버 연결");
// });



// const http = require('http');
// http.createServer((req, res)=>{
//     if(req.url ==='/'){
//         res.write('Hello');
//         res.end();
//     }
// }).listen(3000, () =>{
//     console.log("3000포트에서 서버 연결");
// });

// let express = require('express');
// let app = express();
// app.set('port', process.env.PORT || 3000);
// app.listen(app.get('port'), function(){
//     console.log('익스프레스 서버를 시작: '+ app.get('port'));
// });



// let express = require('express');
// let app = express();
// app.set('port', process.env.PORT || 3000);
// app.get('/', (req, res)=>{
//     res.send('Hello World');
// })
// app.listen(app.get('port'), ()=>{
//     console.log('익스프레스 서버를 시작: '+ app.get('port'));
// });


// let express = require('express');
// let app = express();
// // app.use(function(req, res, next){
// //     console.log('첫번째 미들웨어에서 요청을 처리함.');
// //     res.writeHead('200', {'Content-type': 'text/html;charset=utf8'});
// //     res.end('<h1>Express 서버 응답</h1>');
// // });
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });

// app.use(function(req, res, next){
//     console.log('첫 번째 미들웨어에서 요청 처리.');
//     req.user = 'conan';
//     next();
// });
// app.use('/', function(req, res, next){
//     console.log('두 번째 미들웨어에서 요청 처리.');
//     res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
//     res.end('<h1>Express 서버에서 '+ req.user + '가 응답중</h1>');
// });

// app.use(function(req, res, next){
//     console.log('첫 번째 미들웨어에서 요청 처리');
//     res.send({name: '코난', age:10});
// });

// app.use(function (req, res, next){
//     console.log("첫 번째 미들웨어에서 요청 처리.");
//     res.redirect('http://google.co.kr');
// });

// let path = require('path');
// const static = require('serve-static');
// app.use(static(path.join(__dirname, 'public')));

// app.use(function(req, res, next){
//     console.log('첫 번째 미들웨어에서 요청 처리중.');
//     let userAgent = req.header('User-Agent');
//     let paramName = req.query.name;
//     res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
//     res.write('<h1>Express 서버에서 응답한 결과</h1>');
//     res.write('<div><p>User-Agent: ' + userAgent + '</p></div>');
//     res.write('<div><p>Param name: ' + paramName + '</p></div>');
//     res.write('<div><p>Param age: ' + req.query.age + '</p></div>');
//     res.write('<div><img src="images/conan.png"></img></div>');
//     res.end();


// })










let express = require('express');
let app = express();
app.listen(3000, function(){
    console.log("Express 서버가 3000번 포트에서 start");
});

let bodyParser = require('body-parser'), static = require('serve-static');
let path = require('path');
app.use(static(path.join(__dirname, 'public')));

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json());

// app.use(function (req, res, next){
//     console.log("첫 번째 미들웨어에서 요청을 처리함.");
//     let paramUserId = req.body.userId || req.query.userId;
//     let paramUserPwd = req.body.userPwd || req.query.userPwd;
//     res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
//     res.write('<h1>Express 서버에서 응답한 결과</h1>');
//     res.write('<div><p>Param id: ' + paramUserId + '</p></div>');
//     res.write('<div><p>Param password: ' + paramUserPwd + '</p></div>');
//     res.end();
// })

let router = express.Router();
router.route('/process/login/:name').post(function(req, res){
    console.log('/process/login 처리함.');
    let paramName = req.params.name;

    let paramUserId = req.body.userId || req.query.userId;
    let paramUserPwd = req.body.userPwd || req.query.userPwd;
    res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
    res.write('<h1>Express 서버에서 응답한 결과</h1>');
    res.write('<div><p>Param name: '+ paramName + '</p></div>');
    res.write('<div><p>Param id: ' + paramUserId + '</p></div>');
    res.write('<div><p>Param password: ' + paramUserPwd + '</p></div>');
    res.write('<br><br><a href="/login.html">로그인 페이지로 돌아가기</a>');
    res.end();
})
app.use('/', router);
app.all('*', function(req, res){
    res.status(404).send('<h1>ERROR - 페이지를 찾을 수 없습니다.</h1>');
})








