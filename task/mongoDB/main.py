from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup
import time
import re #정규표현식
import urllib.request
import requests
import json
import pymongo

conn = pymongo.MongoClient()
db = conn.bitDB

min_page = 1
max_page = 5000
key = 'EDyLzlBWODTSIrTAV%2FoKFfpd0nZRuvtDtHzeQEf3lLobD5DUiVZRZAACSmfkG1zxYbp5nH8FsbJqdKx2I7Z%2B4A%3D%3D'

url = 'https://api.odcloud.kr/api/15007122/v1/uddi:95d6cbf2-f800-4ce3-a4f7-f57823274732?serviceKey=%s&pageNo=%d&perPage=%d&type=json' % (key, min_page, max_page)
print(url)
response = requests.get(url)
contents = response.text

json_ob = json.loads(contents)

health_info = list()

for json in json_ob['data']:
    del json['(혈청지오티)ALT']
    del json['(혈청지오티)AST']
    del json['HDL 콜레스테롤']
    del json['LDL 콜레스테롤']
    del json['감마 지티피']
    del json['구강검진 수검여부']
    del json['요단백']
    del json['치아우식증유무']
    del json['트리글리세라이드']
    del json['혈색소']
    del json['혈청크레아티닌']

index = 0

# 가입자 일련번호, 기준년도, 성별코드, 수축기 혈압, 시력(우), 시력(좌), 식전혈당(공복혈당), 신장(5Cm단위),
# 연령대 코드(5세단위), 이완기 혈압, 청력(우), 청력(좌), 체중(5Kg 단위), 치석, 허리둘레, 흡연상태
for _ in range(8):
    for json in json_ob['data']:
        dict1 = dict()
        index += 1
        dict1['가입자 일련번호'] = str(index)
        dict1["기준년도"] = str(json['기준년도'])
        dict1["성별코드"]= str(json['성별코드'])
        dict1["수축기 혈압"]= str(json['수축기 혈압'])
        dict1["시력(우)"]= str(json['시력(우)'])
        dict1["시력(좌)"]= str(json['시력(좌)'])
        dict1["식전혈당(공복혈당)"]= str(json['식전혈당(공복혈당)'])
        dict1["신장(5Cm단위)"]= str(json['신장(5Cm단위)'])
        dict1["연령대 코드(5세단위)"]= str(json['연령대 코드(5세단위)'])
        dict1["이완기 혈압"]= str(json['이완기 혈압'])
        dict1["청력(우)"]= str(json['청력(우)'])
        dict1["청력(좌)"]= str(json['청력(좌)'])
        dict1["체중(5Kg 단위)"]= str(json['체중(5Kg 단위)'])
        dict1["치석"]= str(json['치석'])
        dict1["허리둘레"]= str(json['허리둘레'])
        dict1["흡연상태"]= str(json['흡연상태'])

        health_info.append(dict1)

for health in health_info:
    print(health)

db.healths.insert_many(health_info)




























