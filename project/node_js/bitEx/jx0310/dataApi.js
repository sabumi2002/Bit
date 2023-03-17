const axios=require('axios');
var express = require('express');
var app = express();
app.use(express.json());
app.get('/airkorea', async(req, res)=>{
    const serviceKey = "EDyLzlBWODTSIrTAV%2FoKFfpd0nZRuvtDtHzeQEf3lLobD5DUiVZRZAACSmfkG1zxYbp5nH8FsbJqdKx2I7Z%2B4A%3D%3D";
    const airUrl = "https://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?";
    let params = encodeURI('serviceKey')+ '='+serviceKey;
    params += '&'+encodeURI('numOfRows')+'='+encodeURI('20');
    params += '&'+encodeURI('pageNo')+'='+encodeURI('1');
    params += '&'+encodeURI('searchCondition')+'='+encodeURI('DAILY');
    params += '&'+encodeURI('sidoName')+'='+encodeURI('서울');
    params += '&'+encodeURI('returnType')+'='+encodeURI('json');
    const url = airUrl + params;
    console.log('url: ', url);
    try{
        const result = await axios.get(url);
        res.json(result.data);
    }catch(error){
        console.log(error);
    }
});
app.listen(3000, function () {
    console.log('port 3000!');
});