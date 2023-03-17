let express = require('express');
let app = express();
app.listen(3000, function(){
    console.log("Express 서버가 3000번 포트에서 start");
});
app.set('views', __dirname+'/views');
app.set('view engine', 'ejs');

const expressSession = require('express-session');
app.use(expressSession({
    secret: 'my key', // 쿠키를 임의로 변조하는 것을 방지하기 위한 값
    resave: true,  //세션을 항상 저장할 지 여부 결정( false 로 하는 것을 권장)
    saveUninitialized: true //세션이 저장되기 전에 uninitialized 상태로 미리 만들어서 저장
}));

const mysql = require('mysql2');
const conn = {
    host: '127.0.0.1',
    port: '3306',
    user: 'root',
    password: '1111',
    database: 'scottDB',
    connectionLimit: 10
};
let pool = mysql.createPool(conn);
// pool.getConnection((err, conn)=>{
//     let sql = "SELECT * FROM MEMBERS";
//     conn.query(sql, (err, result, fileds)=>{
//         if(!err){
//             console.log(result);
//             conn.release();
//         }else{
//             throw err;
//         }
//     });
// });





app.get('/', (req, res)=>{  // localhost:3000 들어갔을때 /process/product로 이동
    res.redirect('/loginFrm');
});
app.get('/process/product', (req, res)=>{  
    console.log('/process/product 호출됨.');
    // res.send({session: req.session});
    if(req.session.login){  // 세션.로그인이 있는경우
        res.redirect('/product.html');
    }else{  // 세션.로그인이 없는경우
        res.redirect('/login.html');
    }
});


let bodyParser = require('body-parser'), static = require('serve-static');
let path = require('path');

app.use(static(path.join(__dirname, 'public')));    // path설정 (path query에서 public폴더를 안보여줌)

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json());

app.get('/joinFrm', (req, res)=>{
    console.log('/process/joinFrm 처리함.');

    req.app.render("joinFrm", (err, html)=>{
        if(err){
            console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
            res.write('<p>'+ err.stack + '</p>');
            res.end();
            return;
        }
        res.end(html);
    });
})

app.post('/proc/join', (req, res)=>{
    console.log('/process/join 처리함.');
    let userId= req.body.userId;
    let userPwd= req.body.userPwd; 
    let userName= req.body.userName;
    let age= parseInt(req.body.age);
    console.log(userId, userPwd, userName, age);


    pool.getConnection((err, conn)=>{
        let sql = "INSERT INTO MEMBERS(userId, userPwd, userName, age) VALUES(?, ?, ?, ?)";
        let data = [userId, userPwd, userName, age]
        conn.query(sql, data, (err, result, fileds)=>{
            if(!err){
                conn.release();
                res.redirect("/joinResult");
            }else{
                res.redirect("/joinFrm");
                // throw err;
            }
        });
    });


})

app.get('/joinResult', (req, res)=>{
    console.log('/process/joinResult 처리함.');

    req.app.render("joinResult", context, (err, html)=>{
        if(err){
            console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
            res.write('<p>'+ err.stack + '</p>');
            res.end();
            return;
        }
        res.end(html);
    });
});








app.get('/loginFrm', (req, res)=>{
    console.log('/loginFrm 처리함.');

    req.app.render("loginFrm", (err, html)=>{
        if(err){
            console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
            res.write('<p>'+ err.stack + '</p>');
            res.end();
            return;
        }
        res.end(html);
    });
})

app.post('/proc/login', (req, res)=>{
    console.log('/process/login 처리함.');
    let paramUserId = req.body.userId;
    let paramUserPwd = req.body.userPwd;

    pool.getConnection((err, conn)=>{
        let sql = "SELECT * FROM scottdb.members WHERE userId=? and userPwd=?;";
        let data = [paramUserId, paramUserPwd ]
        conn.query(sql, data, (err, result, fileds)=>{
            if(!err) {
                let user = result;
                console.log(user);
                if(user[0] == null){
                    console.log("null");
                    conn.release();
                    res.redirect("/loginFrm");
                }else{
                    console.log(user[0].userId);
                    req.session.login = {   //세션에 login객체 저장
                        loginId: user[0].userId, 
                        loginPwd: user[0].userPwd,
                        loginName: user[0].userName, 
                        loginAge: user[0].age, 
                        authorized: true
                    };

                    conn.release();
                    res.redirect("/loginResult");
                }
            } else {
                throw err;
            }
        });
    });


})

app.get('/loginResult', (req, res)=>{
    console.log('/process/loginResult 처리함.');
    let context = {userId: req.session.login.loginId, userPwd: req.session.login.userPwd};

    req.app.render("loginResult", context, (err, html)=>{
        if(err){
            console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
            res.write('<p>'+ err.stack + '</p>');
            res.end();
            return;
        }
        res.end(html);
    });
});


app.get('/updateFrm', (req, res)=>{
    console.log(req.session.login);
    if(req.session.login == undefined){
        console.log("로그인 객체 없음.")
        res.redirect("/loginFrm");
    }else{

        console.log('/updateFrm 처리함.');
        let context = {
            userId: req.session.login.loginId,
            userPwd: req.session.login.loginPwd,
            userName: req.session.login.loginName,
            age: req.session.login.loginAge
        };
        console.log(context);

        // res.render("updateFrm", context);
        req.app.render("updateFrm", context, (err, html)=>{
            if(err){
                console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
                res.write('<p>'+ err.stack + '</p>');
                res.end();
                return;
            }
            res.end(html);
        });
    }
})

app.post('/proc/update', (req, res)=>{
    console.log('/process/login 처리함.');
    let paramUserId = req.session.login.loginId;
    let paramUserPwd = req.body.userPwd;
    let paramUserName = req.body.userName;
    let paramAge = req.body.age;

    pool.getConnection((err, conn)=>{
        let sql = "UPDATE members SET userPwd = ?, userName=?, age=? WHERE userId=?";
        let data = [paramUserPwd, paramUserName, paramAge, paramUserId ];
        conn.query(sql, data, (err, result, fileds)=>{
            if(!err) {
                conn.release();
                res.redirect("/updateResult");
                
            } else {
                conn.release();
                res.redirect("/updateFrm")
            }
        });
    });
})

app.get('/updateResult', (req, res)=>{
    console.log('/updateResult 처리함.');
    let context = {userId: req.session.login.loginId, userPwd: req.session.login.userPwd};

    req.app.render("updateResult", context, (err, html)=>{
        if(err){
            console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
            res.write('<p>'+ err.stack + '</p>');
            res.end();
            return;
        }
        res.end(html);
    });
});

app.get('/proc/logout', (req, res)=>{
    req.session.destroy(()=>{   // 세션 삭제
        res.redirect('/loginFrm');
    });
    
});


app.all('*', function(req, res){
    res.status(404).send('<h1>ERROR - 페이지를 찾을 수 없습니다.</h1>');
});
