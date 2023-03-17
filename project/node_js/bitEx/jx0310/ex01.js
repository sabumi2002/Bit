// let express = require('express');
// let app = express();
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });


// const cookieParser = require('cookie-parser');
// app.use(cookieParser());
// app.get('/process/setUserCookie', (req, res)=> {
//     console.log('/process/setUserCookie 호출됨.');
//     res.cookie('user', {
//         id: 'conan', 
//         name: '코난', 
//         authorized: true
//     });
//     res.redirect('/process/showCookie');
// });
// app.get('/process/showCookie', (req, res)=>{
//     console.log('/process/showCookie 호출됨.');
//     res.send(req.cookies);

// });
// app.get('/process/clearUserCookie', (req, res)=>{
//     res.clearCookie('user');
//     res.redirect('/process/showCookie');
// });



// const logger = require('morgan');
// // ...
// // 익스프레스 객체 생성 후 등록
// // 개발시에는 'dev'옵션, 
// // 배포시에는 'combined'(보다 세부적인 정보)
// // common, short 등
// app.use(logger('combined'));

// let express = require('express');
// let app = express();
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });
// const expressSession = require('express-session');
// app.use(expressSession({
//     secret: 'my key', // 쿠키를 임의로 변조하는 것을 방지하기 위한 값
//     resave: true,  //세션을 항상 저장할 지 여부 결정( false 로 하는 것을 권장)
//     saveUninitialized: true //세션이 저장되기 전에 uninitialized 상태로 미리 만들어서 저장
// }));

// app.get('/process/setUserSession', (req, res)=> {
//     console.log('/process/setUserSession 호출됨.');
//     req.session.user = {
//         userId: 'conan', 
//         name: '코난', 
//         authorized: true
//     };
//     res.redirect('/process/showSession');
// });
// app.get('/process/showSession', (req, res)=>{
//     console.log('/process/showSession 호출됨.');
//     res.send({session: req.session});

// });
// app.get('/process/destroyUserSession', (req, res)=>{
//     req.session.destroy(()=>{
//         res.redirect('/process/showSession');
//     });
    
// });




let express = require('express');
let app = express();
app.listen(3000, function(){
    console.log("Express 서버가 3000번 포트에서 start");
});

const expressSession = require('express-session');
app.use(expressSession({
    secret: 'my key', // 쿠키를 임의로 변조하는 것을 방지하기 위한 값
    resave: true,  //세션을 항상 저장할 지 여부 결정( false 로 하는 것을 권장)
    saveUninitialized: true //세션이 저장되기 전에 uninitialized 상태로 미리 만들어서 저장
}));

// app.get('/', (req, res)=>{  // localhost:3000 들어갔을때 /process/product로 이동
//     res.redirect('/process/product');
// });
app.get('/process/product', (req, res)=>{  
    console.log('/process/product 호출됨.');
    // res.send({session: req.session});
    if(req.session.login){  // 세션.로그인이 있는경우
        res.redirect('/product.html');
    }else{  // 세션.로그인이 없는경우
        res.redirect('/login.html');
    }
});
app.get('/process/logout', (req, res)=>{
    req.session.destroy(()=>{   // 세션 삭제
        res.redirect('/process/product');
    });
    
});


let bodyParser = require('body-parser'), static = require('serve-static');
let path = require('path');

app.use(static(path.join(__dirname, 'public')));    // path설정 (path query에서 public폴더를 안보여줌)

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json());


app.post('/process/login', (req, res)=>{
    console.log('/process/login 처리함.');
    let paramUserId = req.body.userId;
    let paramUserPwd = req.body.userPwd;
    req.session.login = {   //세션에 login객체 저장
        loginId: paramUserId, 
        loginPwd: paramUserPwd, 
        authorized: true
    };
    res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
    res.write('<h1>로그인 성공</h1>');
    res.write('<div><p>Param id: ' + req.session.login.loginId + '</p></div>');
    res.write('<div><p>Param password: ' + req.session.login.loginPwd + '</p></div>');
    res.write('<br><br><a href="/product.html">상품 페이지로 돌아가기</a>');
    res.end();
});
app.get('/product.html', (req, res)=>{
    res.redirect('/');
})


app.all('*', function(req, res){
    res.status(404).send('<h1>ERROR - 페이지를 찾을 수 없습니다.</h1>');
})


let multer = require('multer');
let fs = require('fs');

app.use('/uploads', static(path.join(__dirname, 'uploads')));

const storage = multer.diskStorage({
    destination: function (req, file, callback){
        callback(null, 'uploads')
    },
    filename: function(req, file, callback){
        callback(null, file.originalname+Date.now())
    }
});
const upload = multer({
    storage: storage, 
    limits: {
        files: 10, fileSize: 1024*1024*1024
    }
})

app.post('/process/upload', upload.array('uploadedFile'), (req, res)=>{
    console.log('/process/upload 호출됨');
    try{
        let files = req.files; console.dir('#=== 업로드된 첫번째 파일 정보 ===#');
        console.dir(req.files[0]); console.dir('#=====')
        let originalname= '', filename = '', mimetype='', size =0;
        if(Array.isArray(files)){
            console.log('배열에 들어있는 파일 갯수: %d', files.length);
            for(let index =0; index<files.length; index++){
                originalname = files[index].originalname; filename = file[index].filename;
                mimetype = files[index].mimetype; size = files[index].size;
            }
        }
        console.log('현재 파일정보: '+ originalname+ ', '+ filename+', '+mimetype+', '+size);
        res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
        res.write('<h3>파일 업로드 성공</h3>');res.write('<hr/>');
        res.write('<p>원본 파일명: '+originalname+'->저장파일명: '+filename+'</p>');
        res.write('<p>MIME TYPE: '+mimetype+'</p>');res.write('<p>파일 크기:'+size+'</p>');
        res.end();
    }catch(err){ console.dir(err.stack)}
});