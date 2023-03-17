// const coffee = [];
// console.log(coffee);

// coffee.push({"name": "Americano"});
// coffee.push({"name": "Latte"});

// console.log(coffee);
// console.log(coffee[0]);
// console.log(coffee.length);


// const array = ['nodejs', {}, 10, true];
// const [node, obj, , bool]= array;

// console.log(node);
// console.log(obj);
// console.log(bool);

// const myFunc = function(){
//     console.log(arguments);
// }
// myFunc(1, 2, 3, 4);

// const myFunc1 = (...args) => {
//     console.log(args);
// }
// myFunc1(1, 2, 3, 4);


// // function add(a, b, callback){
// //     var result = a+b;
// //     callback(result);
// // }

// // add(10, 10, function(result){
// //     console.log("파라미터로 전달된 콜백 함수 호출됨.");
// //     console.log("더하기 (10, 10)의 결과 : %d", result);

// // })


// function add(a, b, callback){
//     let result = a+b;
//     callback(result);
//     let history = function() {
//         return a + '+' + b + '=' +result;
//     };
//     return history;
// }

// let add_history = add(10, 10, function(result){
//     console.log('파라미터로 전달된 콜백 함수 호출됨.');
//     console.log('더하기 (10, 10)의 결과 : %d', result);
// });
// console.log('결과 값으로 받은 함수 실행 결과 : ' + add_history);



// console.log('JSON 객체 보여주기 : %j', {name:'코난'});


// let result = 0;
// console.time('elapsedTime');
// for(var i =1; i<=1000000 ; i++){
//     result +=i;
// }
// console.timeEnd('elapsedTime');
// console.log('1부터 100000까지 합: %d', result);


// console.log('현재 실행한 파일의 이름: %s', __filename);
// console.log('현재 실행한 파일의 path: %s', __dirname);
// var Person = {name: "conan", age:10};
// console.dir(Person);

// console.log('argv 속성의 파라미터 수: '+ process.argv.length);
// console.dir(process.argv);
// process.argv.forEach(function(item, index){
//     console.log(index + " : "+ item);
// });
// console.log("세 번째 입력 파라미터: ", process.argv[2]);


// const nconf = require('nconf');
// nconf.env();
// console.log('OS 환경변수의 값: %s', nconf.get('OS'));

// const os = require('os');
// console.log('시스템의 hostname : %s', os.hostname());
// console.log('시스템의 메모리 : %d / %d', os.freemem(), os.totalmem());
// console.log('시스템의 CPU 정보\n');
// console.dir(os.cpus());
// console.log('시스템의 네트워크 인터페이스 정보\n');
// console.dir(os.networkInterfaces());

// let path = require('path');
// let directories = ["users", "conan", "docs"];
// let docsDirectory = directories.join(path.sep);
// console.log('문서 디렉토리: %s', docsDirectory);
// let curPath = path.join('/Users/conan', 'notepad.exe');
// console.log('파일 패스: %s', curPath);

// // let path = require('path');
// let filename = "C:\\Users\\conan\\notepad.exe";
// let dirname = path.dirname(filename);
// let basename = path.basename(filename);
// let extname = path.extname(filename);
// console.log('디렉토리: %s, 파일 이름: %s, 확장자: %s', dirname, basename, extname);

// let url = require('url');
// let curURL =
// url.parse('https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=자바스크립트');
// console.dir(curURL);

// let querystring = require('querystring');
// let param = querystring.parse(curURL.query);
// console.log('요청 파라미터 중 query의 값: %s', param.query);
// console.log('원본 요청 파라미터 : %s', querystring.stringify(param));


// let fs = require('fs');
// let data = fs.readFileSync('package.json', 'utf8');
// console.log(data);



// let fs = require('fs');
// fs.readFile('package.json', 'utf8', function(err, data){
//     console.log(data);
// });
// console.log('프로젝트 폴더 안의 package.json 파일 읽기');

// fs = require('fs');
// fs.writeFile('./output.txt', 'Hello World!', function (err){
//     if(err){
//         console.log('Error : '+ err);
//     }
//     console.log('output.txt 파일에 데이터 쓰기 완료.');
// });







// const fs = require('fs');
// fs.open('./output.txt', 'w', function (err, fd){
//     if(err)throw err;
//     const buf = Buffer.from('안녕!\n', 'utf-8');
//     fs.write(fd, buf, 0, buf.length, null, function(err, written, buffer){
//         if(err) throw err;
//         console.log(err, written, buffer);
//         fs.close(fd, function(){
//             console.log('파일 열고 데이터 쓰고 파일 닫기 완료.');
//         });
//     });
// });





// let fs = require('fs');
// fs.open('./output.txt', 'r', function(err, fd){
//     if(err) throw err;
//     let buf = Buffer.alloc(20);
//     console.log('버퍼 타입: %s', Buffer.isBuffer(buf));
//     fs.read(fd, buf, 0, buf.length, null, function (err, bytesRead, buffer){
//         if(err) throw err;
//         let inStr = buffer.toString('utf8', 0, bytesRead);
//         console.log('파일에서 읽은 데이터: %s', inStr);
//         console.log(err, bytesRead, buffer);
//         fs.close(fd, function(){
//             console.log('output.txt 파일을 열고 읽기 완료.');
//         });
//     });
// });




// let fs = require('fs');
// fs.mkdir('./docs', 0666, function (err){
//     if(err) throw err;
//     console.log('새로운 docs 폴더를 생성');
// });

// fs.rmdir('./docs', function (err) {
//     if(err) throw err;
//     console.log('docs 폴더를 삭제');
// })






// 웹서버 시작
// let http = require('http');
// let server = http.createServer();
// let port = 3000;
// server.listen(port, function(){
//     console.log('웹 서버 시작 : %d', port);
// })
// server.on('connection', function(socket){
//     let addr = socket.address();
//     console.log('클라이언트가 접속: %s, %d', addr.address, addr.port);
// });
// server.on('request', function(req, res){
//     console.log('클라이언트가 요청함.');
//     console.dir(req);
// })



// server.on('request', function(req, res){
//     console.log('클라이언트 요청');
//     res.writeHead(200, {"Content-Type": "text/html;charset=utf-8"});
//     res.write("<!DOCTYPE html>");
//     res.write("<html>");
//     res.write("<head>");
//     res.write("<title>응답페이지</title>");
//     res.write("</head>");
//     res.write("<body>");
//     res.write("<h1>노드제이에스로부터의 응답 페이지</h1>");
//     res.write("</body>");
//     res.write("</html>");
//     res.end();
// });



// let fs = require('fs');
// server.on('request', function(req, res){
//     console.log('클라이언트 요청');
//     let filename = 'conan.png';
//     fs.readFile(filename, function(err, data){
//         res.writeHead(200, {"Content-Type": "image/jpg"});
//         res.write(data);
//         res.end();
//     })
// })


const http=require("http");
const options = {
    host:'www.google.com',
    port:80,
    path:'/'
};
const req = http.get(options, function(res){
    let resData='';
    res.on('data', function(chunk){
        resData+=chunk;
    });
    res.on('end', function(){
        console.log(resData);
    });
});
req.on('error', function(err){
    console.log("오류 발생"+ err.message);
});