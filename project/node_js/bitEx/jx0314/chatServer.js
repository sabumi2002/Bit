let express = require('express');
let app = express();
// app.listen(3000, function(){
//     console.log("Express 서버가 3000번 포트에서 start");
// });

let static = require('serve-static');
let path = require('path');
app.use(static(path.join(__dirname, 'public')));

app.set('port', 3000);
app.set('views', __dirname+'/views');
app.set('view engine', 'ejs');

let socketio = require('socket.io');
let server = app.listen(app.get('port'), function(){
    console.log('서버가 시작됨 포트 : ' + app.get('port'));
});
let io= socketio(server);
var login_userIds={};

io.sockets.on('connection', function (socket){
    console.log('connection info: ', socket.request.connection._peername);
    socket.remoteAddress = socket.request.connection._peername.address;
    socket.remotePort = socket.request.connection._peername.port;

    // message 이벤트를 받았을 때의 처리 추가
    socket.on('message', function(message){
        console.log('message 이벤트를 받았음.');
        console.dir(message);
        if(message.recepient == 'ALL'){
            // 나를 포함한 모든 클라이언트에게 메시지 전달
            console.dir('나 포함 모든 클라이언트에게 message 이벤트를 전송.');
            io.sockets.emit('message', message);
        }else{  // 특정 사용자에게 메시지 전달
            console.log('무슨 일이고?');
            console.log(login_userIds[message.recepient]);
            if(login_userIds[message.recepient]){
                io.sockets.to(login_userIds[message.recepient]).emit('message', message);
                // message 이벤트를 받았을 때 일대일 채팅인 경우 상대방 소켓을 찾아 메시지 전송
                sendResponse(socket, 'message', '200', '메시지를 전송완료.');
            }else{
                sendResponse(socket, 'login', '404', '상대방의 로그인 ID를 찾을 수 없음');
            }
        }
    });

    socket.on('login', function (login){    //'login' 이벤트를 받았을 때의 처리
        console.log('login 이벤트 발생');
        console.dir(login);
        // 기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        login_userIds[login.userId] = socket.id;    // socket.id는 고유 속성이므로 변경하지말 것
        socket.login_userId=login.userId;
        console.log('접속한 클라이언트 ID 개수: %d', Object.keys(login_userIds).length);
        sendResponse(socket, 'login', '200', '로그인되었음');
    });
    // 응답 메시지 전송 메소드
    function sendResponse(socket, command, code, message){
        console.log('socket from sendResponse'+socket);
        console.dir(socket);
        var statusObj = {command: command, code: code, message: message};
        socket.emit('response', statusObj);
    }



});

app.get('/', (req, res)=>{  // localhost:3000 들어갔을때 /process/product로 이동
    res.redirect('/chatClient.html');
});

