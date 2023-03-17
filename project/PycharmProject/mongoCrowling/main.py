
# def print_hi(name):
#     print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
#
# if __name__ == '__main__':
#     print_hi('PyCharm')

from bs4 import BeautifulSoup
import urllib.request

stores= []; # 결과를 저장할 리스트
encText = urllib.parse.quote('서울')
for page in range(1,17):
    Hollys_url= 'https://www.hollys.co.kr/store/korea/korStore.do?pageNo=%d&gugun=&store=&sido='%(page)
    Hollys_url+=encText
    print(Hollys_url)
    html = urllib.request.urlopen (Hollys_url)
    soupHollys = BeautifulSoup(html, 'html.parser')
    tag_tbody = soupHollys.find('tbody')
    for store in tag_tbody.find_all('tr'):
        store_info= dict() #개별 매장 정보를 사전 형태로 저장
        store_td = store.find_all('td')
        store_name = store_td[1].string; store_info["store_name"] = store_td[1].string
        store_sido = store_td[0].string; store_info["store_sido"] = store_td[0].string
        store_address = store_td[3].string; store_info["store_address"] = store_td[3].string
        store_phone= store_td[5].string; store_info["store_phone"] = store_td[5].string
        print('매장명',store_name, '시도구',store_sido,'주소', store_address,'전화번호', store_phone )
        stores.append(store_info) #사전 형태로 준비된 매장 정보를 리스트에 추가
for store in stores:
    print(store)