import requests
from bs4 import BeautifulSoup
from save import save_to_file

BASIC_URL = 'https://www.absolutdrinks.com'

main_result = requests.get(f'{BASIC_URL}/ko/browse/')

main_html = BeautifulSoup(main_result.text, 'html.parser')

main_pages = main_html.find_all("div", {"class":"swiper-wrapper"})

main_links = []
child_links = []
for main_page in main_pages[1:]:
  main_links.append(main_page.find('a').get('href'))

for main_link in main_links:
  tab = requests.get(f'{BASIC_URL}{main_link}')
  html = BeautifulSoup(tab.text, 'html.parser')
  pages = html.find_all("div", {"class" : "swiper-container w-full"})
  for page in pages:
    pp = page.find_all('a')
    for p in pp:
      child_links.append(p.get('href'))
recipes = []

for link in child_links:
  html = BeautifulSoup(requests.get(f'{BASIC_URL}{link}').text, 'html.parser')
  title = html.find("h1", {"class" : "font-body md:font-heading text-xl text-center md:text-left md:text-8xl mb-2 text-blue"}).text
  ll = html.find_all('a')
  imgs = []
  # for l in ll:
  #   if l.img:
  #     imgs.append(l.img['srcset'])
  # img = html.find("picture", {"class" : "absolute top-0 left-0 flex w-full h-full"})
  # img = html.find(itemprop="image")
  drink_content = html.find("div", {"class" : "drinks-content"})
  ingredient = drink_content.text
  how_to = drink_content.find('p').text
  
  recipes.append(
    {
      "name" : title,
      "ingredient" : ingredient,
      "how" : how_to
      # "img" : imgs
    }
  )


save_to_file(recipes)