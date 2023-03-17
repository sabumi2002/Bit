const mysql = require('mysql2');
const conn = {
    host: '127.0.0.1',
    port: '3306',
    user: 'root',
    password: '1111',
    database: 'scottDB'
};
let connection = mysql.createConnection(conn);
connection.connect();

// let sql = "INSERT INTO MEMBERS (userId, userPwd, userName, age) VALUES (?, ?, ?, ?)";
// let data = ['conan', '1111', '코난', 10];
// let sql = "INSERT INTO MEMBERS SET ?";
// let data = {userId: 'rose', userPwd:'1234', userName:'장미', age:10};
let sql = "UPDATE MEMBERS SET userPwd = ? WHERE userId ='conan'"
let data = ['0000'];
connection.query(sql, data, function (err, results){
    if(err){console.log('err', err);}
    console.log('results', results);
});
sql = "SELECT * FROM MEMBERS";
connection.query(sql, function(err, results){
    if(err){
        console.log(err);
    }
    console.log(results);
});
connection.end();