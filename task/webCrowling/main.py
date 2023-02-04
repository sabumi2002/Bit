from bs4 import BeautifulSoup
import urllib.request

stores= []; # 결과를 저장할 리스트
encText = urllib.parse.quote('서울')
for page in range(1,17):
    url= 'https://game.naver.com/lounge/Animal_Crossing_New_Horizons/db/villager'
    url+=encText
    print(url)
    html = urllib.request.urlopen (url)
    soup = BeautifulSoup(html, 'html.parser')

    # data_rows = soup.find("ul", attrs = {"class": "list_container__3HabI"}).find_all("li")
    data_rows = soup.find(attrs = {"class": "list_container__3HabI"})
    data_rows2 = data_rows.find_all(attrs = {"class": "card_elements__8rOLO"})
    for row in data_rows:

        print(row.find("li"))
