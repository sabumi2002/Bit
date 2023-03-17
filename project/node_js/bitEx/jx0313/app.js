// let express = require('express');
// let app = express();
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });

// app.set('views', __dirname+'/views');
// app.set('view engine', 'ejs');
// app.get('/', function(req, res){
//     res.render('index1', {menu:"home"});
// })

// app.get('/menu1', function(req, res){
//     res.render('index1', {menu:"Menu1"});
// })
// app.get('/menu2', function(req, res){
//     res.render('index1', {menu:"Menu2"});
// })
// app.get('/menu3', function(req, res){
//     res.render('index1', {menu:"Menu3"});
// })





// let express = require('express');
// let app = express();
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });
// app.set('views', __dirname+'/views');
// app.set('view engine', 'ejs');
// const expressSession = require('express-session');
// app.use(expressSession({
//     secret: 'my key', // 쿠키를 임의로 변조하는 것을 방지하기 위한 값
//     resave: true,  //세션을 항상 저장할 지 여부 결정( false 로 하는 것을 권장)
//     saveUninitialized: true //세션이 저장되기 전에 uninitialized 상태로 미리 만들어서 저장
// }));

// app.get('/', (req, res)=>{  // localhost:3000 들어갔을때 /process/product로 이동
//     res.redirect('/login.html');
// });
// let bodyParser = require('body-parser'), static = require('serve-static');
// let path = require('path');

// app.use(static(path.join(__dirname, 'public')));    // path설정 (path query에서 public폴더를 안보여줌)
// app.use(bodyParser.urlencoded({extended: false}))
// app.use(bodyParser.json());
// app.post('/process/login', (req, res)=>{
//     console.log('/process/login 처리함.');
//     let context = {userId: req.body.userId, userPwd: req.body.userPwd};

//     req.app.render("login_success", context, (err, html)=>{
//         if(err){
//             console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
//             res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
//             res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
//             res.write('<p>'+ err.stack + '</p>');
//             res.end();
//             return;
//         }
//         res.end(html);
//     });
// });
// app.all('*', function(req, res){
//     res.status(404).send('<h1>ERROR - 페이지를 찾을 수 없습니다.</h1>');
// })


