from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup
import time
import pandas as pd
import re #정규표현식

# driver = webdriver.Chrome('C:\Users\Sabeom\Downloads\chromedriver_win32 (1)')
# 크롤링할 부분 설정
dining_url = "https://www.megabox.co.kr/movie"
chrome_options = webdriver.ChromeOptions()
# 크롬 WebDriver 객체생성
wd = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options = chrome_options)
wd.get(dining_url)
time.sleep(1)
html = wd.page_source
soup = BeautifulSoup(html, 'html.parser')

# stores = soup.select('div.InfoHeader>h2')
# scores = soup.select('div.Rate>p.Score')
# userScores = soup.select('div.Rate>p.UserScore')

# 영화제목
movieNames = soup.select('#movieList .tit-area p.tit')
# 영화등급
movieGrades = soup.select('#movieList .tit-area p.movie-grade')
# 영화설명
movieSummarys = soup.select('#movieList .movie-score .summary')
# 영화개봉일
movieReleases = soup.select('#movieList .rate-date .date')
#영화이미지
movieImgs = soup.select('#movieList .movie-list-info img.poster')
# 예매율
# 좋아요

temp1 = list()
temp2 = list()
temp3 = list()
temp4 = list()
temp5 = list()

for index, img in enumerate(movieImgs):
    temp1.append(img['src'])
    temp2.append(movieGrades[index]['class'][1])
    temp3.append(movieNames[index].text)
    temp4.append(movieSummarys[index].text)
    temp5.append(movieReleases[index].text)
movieImgs = temp1
movieGrades = temp2
movieNames = temp3
movieSummarys = temp4
movieReleases = temp5


print(movieGrades[0])
print(movieNames[0])
print(movieSummarys[10])
print(movieReleases[0])
print(movieImgs[0])

import pymysql
conn = pymysql.connect(host='localhost', user='root', password='1111', db='movie', charset='utf8')
cur = conn.cursor()
for index, movieName in enumerate(movieNames):
    rank = "\"" + str(movieGrades[index]).replace("age-", "")+"\""
    summary = "\"" + str(movieSummarys[index]).replace('\"', "\'")+"\""
    name = "\"" + str(movieNames[index]).replace("\"", "\'")+"\""
    img = "\"" + str(movieImgs[index])+"\""
    releases = "\"" + str(movieReleases[index]).replace("개봉일 ", "")+"\""

    cur.execute("INSERT INTO `movie`(`title`, `content`, `rank`, `release`, `img`) VALUES(  %s , %s , %s , %s, %s)" % (name, summary, rank, releases, img) )

conn.commit()
conn.close()






















