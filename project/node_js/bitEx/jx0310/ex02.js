let express = require('express');
let app = express();
app.listen(3000, function(){
    console.log("Express 서버가 3000번 포트에서 start");
});
let static = require('serve-static');
let path = require('path');

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
                originalname = files[index].originalname; filename = files[index].filename;
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

// 에러 핸들러 모듈 사용
const expressErrorHandler = require('express-error-handler');
// 404 에러 페이지 처리
const errorHandler = expressErrorHandler({
    static: {
        '404': __dirname+'/public/404.html' // 적절한 위치에 404.html 배치
    }
});
app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);
