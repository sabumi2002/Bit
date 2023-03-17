// 네이버 검색 API 예제 - 블로그 검색
var express = require('express');
var app = express();
var client_id = 'uzKgKjCRxOrexjENNKcU';
var client_secret = 'q9zxz1jTlL';
app.get('/search/news', function (req, res) {
   var api_url = 'https://openapi.naver.com/v1/search/news?query=' + encodeURI('윤석열'); // JSON 결과 (req.query.query)
//   var api_url = 'https://openapi.naver.com/v1/search/blog.xml?query=' + encodeURI(req.query.query); // XML 결과
   var request = require('request');
   var options = {
       url: api_url,
       headers: {'X-Naver-Client-Id':client_id, 'X-Naver-Client-Secret': client_secret}
    };

   request.get(options, function (error, response, body) {
    if(!error && response.statusCode == 200){
        let newsItem=JSON.parse(body).items;
        const newsJson = {
            title:[],
            link:[],
            description:[],
            pubDate:[]
        }
        newsItem.forEach(element=>{
            newsJson.title.push(element.title.replace(/(<([^>]+)>)|&quot;/ig,""));
            newsJson.link.push(element.link);
            newsJson.description.push(element.description.replace(/(<([^>]+)>)|&quot;/ig,""));
            newsJson.pubDate.push(element.pubDate);
        });
        res.json(newsJson);
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

