const config = require('./config');
console.log(config);
console.log(config.PORT);

let express = require('express');
let app = express();

app.set('PORT', config.PORT);

app.listen(app.get('PORT'), function(){
    console.log("Express 서버가 "+app.get('PORT')+"번 포트에서 start");
});

const mysql = require('mysql2');
const conn = {
    host: config.HOST,
    port: config.DBPORT,
    user: config.USER,
    password: config.PASSWORD,
    database: config.DATABASE,
    connectionLimit: config.CONNECTIONLIMIT
};
let pool = mysql.createPool(conn);

app.get('/', (req, res)=>{
    console.log('홈 get 요청');

    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
    res.write('<p>홈 get 요청</p>');
    res.end();

})
let fs = require('fs');
let data = fs.readFileSync('./dorian.txt', 'utf8');
app.get('/readFile', (req, res)=>{
    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
    res.write('<p>'+data+'</p>');
    res.end();
})
app.set('views', __dirname+'/views');
app.set('view engine', 'ejs');
let bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json());


pool.getConnection((err, conn)=>{
    let sql = "SELECT * FROM MEMBERS";
    conn.query(sql, (err, result, fileds)=>{
        if(!err){
            let users= result;
            console.log(users);
            conn.release();
        }else{
            throw err;
        }
    });
});

app.get('/list_member', function(req, res){
    pool.getConnection((err, conn)=>{
        let sql = "SELECT * FROM MEMBERS";
        conn.query(sql, (err, result, fileds)=>{
            if(!err){
                let users= result;
                res.render('list_member', 
                {
                    "Users": users
                }
            )
                conn.release();
            }else{
                throw err;
            }
        });
    });
})
app.post('/memberDelete', function(req, res){
    let userId = req.body.userId;
    let userName = req.body.userName;
    let age = req.body.age;
    console.log(userId);
    console.log(userName);
    pool.getConnection((err, conn)=>{
        let sql = "DELETE FROM members WHERE userId=?";
        let data = [userId]
        conn.query(sql, data, (err, result, fileds)=>{
            if(!err){
                conn.release();
                res.redirect("/list_member");
            }else{
                res.redirect("/list_member");
            }
        });
    });

})



app.get('/insert_member', (req, res)=>{

    req.app.render("insert_member", (err, html)=>{
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
                console.log("성공");
                conn.release();
                res.redirect("/list_member");
            }else{
                console.log("실패");
                res.redirect("/list_member");
                // throw err;
            }
        });
    });


})

