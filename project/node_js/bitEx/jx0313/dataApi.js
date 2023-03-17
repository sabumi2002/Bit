// const axios=require('axios');
// var express = require('express');
// var app = express();
// app.use(express.json());
// app.get('/airkorea', async(req, res)=>{
//     const serviceKey = "EDyLzlBWODTSIrTAV%2FoKFfpd0nZRuvtDtHzeQEf3lLobD5DUiVZRZAACSmfkG1zxYbp5nH8FsbJqdKx2I7Z%2B4A%3D%3D";
//     const airUrl = "https://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?";
//     let params = encodeURI('serviceKey')+ '='+serviceKey;
//     params += '&'+encodeURI('numOfRows')+'='+encodeURI('20');
//     params += '&'+encodeURI('pageNo')+'='+encodeURI('1');
//     params += '&'+encodeURI('searchCondition')+'='+encodeURI('DAILY');
//     params += '&'+encodeURI('sidoName')+'='+encodeURI('서울');
//     params += '&'+encodeURI('returnType')+'='+encodeURI('json');
//     const url = airUrl + params;
//     console.log('url: ', url);
//     try{
//         const result = await axios.get(url);
//         let airData = result.data.response.body.items;
//         console.log('airData', airData);
//         res.writeHead(200, {'Content-Type': 'text/html;charset=utf8'});
//         airData.forEach(element=>{
//             res.write(`<div><strong>측정지역: ${element.cityName}</strong>`);
//             res.write(`<p>측정시간: ${element.dataTime}</p>`);
//             res.write(`<p>미세먼지/초미세먼지(ug/m3) : ${element.pm10Value}/${element.pm25Value}</p></div>`);
//         });

//         // res.json(result.data);
//     }catch(error){
//         console.log(error);
//     }
// });
// app.listen(3000, function () {
//     console.log('port 3000!');
// });



// 공공데이터API
// const dotenv = require('dotenv');
// const path = require('path');
// dotenv.config({path: path.resolve(__dirname, "./.env")});
// const axios=require('axios');
// var express = require('express');
// var app = express();
// app.set('port', process.env.PORT || 3000);
// app.use(express.json());
// app.get('/airkorea', async(req, res)=>{
//     const serviceKey = process.env.airServiceKey;
//     const airUrl = "https://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?";
//     let params = encodeURI('serviceKey')+ '='+serviceKey;
//     params += '&'+encodeURI('numOfRows')+'='+encodeURI('20');
//     params += '&'+encodeURI('pageNo')+'='+encodeURI('1');
//     params += '&'+encodeURI('searchCondition')+'='+encodeURI('DAILY');
//     params += '&'+encodeURI('sidoName')+'='+encodeURI('서울');
//     params += '&'+encodeURI('returnType')+'='+encodeURI('json');
//     const url = airUrl + params;
//     console.log('url: ', url);
//     try{
//         const result = await axios.get(url);
//         let airData = result.data.response.body.items;
//         console.log('airData', airData);
//         res.writeHead(200, {'Content-Type': 'text/html;charset=utf8'});
//         airData.forEach(element=>{
//             res.write(`<div><strong>측정지역: ${element.cityName}</strong>`);
//             res.write(`<p>측정시간: ${element.dataTime}</p>`);
//             res.write(`<p>미세먼지/초미세먼지(ug/m3) : ${element.pm10Value}/${element.pm25Value}</p></div>`);
//         });

//         // res.json(result.data);
//     }catch(error){
//         console.log(error);
//     }
// });
// app.listen(3000, function () {
//     console.log('port 3000!');
// });


// 네이버API
// 네이버 검색 API 예제 - 블로그 검색
const dotenv = require('dotenv');
let static = require('serve-static');
const path = require('path');
dotenv.config({path: path.resolve(__dirname, "./.env")});
var express = require('express');
var app = express();
app.set('views', __dirname+'/views');
app.set('view engine', 'ejs');
var client_id = process.env.naverClikentKey;
var client_secret = process.env.naverSecret;
app.get('/search/news', function (req, res) {
    let search = req.query.search;
    if(search == undefined){
        search= "이재명";
    }
   var api_url = 'https://openapi.naver.com/v1/search/news?query=' + encodeURI(search); // JSON 결과 (req.query.query)
//   var api_url = 'https://openapi.naver.com/v1/search/blog.xml?query=' + encodeURI(req.query.query); // XML 결과
   var request = require('request');
   var options = {
       url: api_url,
       headers: {'X-Naver-Client-Id':client_id, 'X-Naver-Client-Secret': client_secret}
    };

   request.get(options, function (error, response, body) {
    if(!error && response.statusCode == 200){
        let newsItem=JSON.parse(body).items;
        const newsArray = [];
        newsItem.forEach(element=>{
            let newsItem = {};
            newsItem.title=element.title.replace(/(<([^>]+)>)|&quot;/ig,"");
            newsItem.link=element.link;
            newsItem.description=element.description.replace(/(<([^>]+)>)|&quot;/ig,"");
            newsItem.pubDate=element.pubDate;
            newsArray.push(newsItem);
        });
        let context = { newsArray: newsArray };
        req.app.render('newsList', context, function(err, html){
            if(err){
                console.error('뷰 렌더링 중 오류 발생 : '+ err.stack);
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>뷰 렌더링 중 오류 발생<h2>');
                res.write('<p>'+ err.stack + '</p>');
                res.end();
                return;
            }
            console.log('rendered:'+html);
            res.end(html);
        });
    }
    //  if (!error && response.statusCode == 200) {
    //    res.writeHead(200, {'Content-Type': 'text/json;charset=utf-8'});
    //    res.end(body);
    //  } else {
    //    res.status(response.statusCode).end();
    //    console.log('error = ' + response.statusCode);
    //  }
   });
 });
 app.listen(3000, function () {
   console.log('http://127.0.0.1:3000/search/blog?query=검색어 app listening on port 3000!');
 });
 app.use('/semantic', static(path.join(__dirname, 'semantic')));

