import jieba
from wordcloud import WordCloud
import matplotlib.pyplot as plt

def read_txt(path):
	'''读文件'''
	txt = open(path, 'r', encoding='utf-8').read()
	for x in ' ，。“‘’”：！、《》；？　」「…":':
		txt = txt.replace(x, "")
		txt = txt.replace("\n","")
	return txt

def get_words(txt):
	'''用jieba进行分词'''
	new_words = ['中国', '美国', '伊拉克', '香港', '上海', '加拿大', '台湾']
	del_words = ['做好', '对于', '逐步', '做到', '推动', '根据', '要求', '返回']
	for i in new_words:
		jieba.add_word(i)
	for i in del_words:
		jieba.del_word(i)
	words = jieba.lcut(txt)
	return words

def deal_with_words(words):
	'''统计词频'''
	dic = {}
	for i in words:
		if len(i) > 1:
			dic[i] = words.count(i)
	words_list = list(dic.items())
	words_list.sort(key= lambda x:x[1], reverse=True)
	print ('字符\t词频')
	print ('=============')
	for i in range(30):
		word, count = words_list[i]
		print("{0:<10}{1:>5}".format(word, count))

def create_word_cloud(words):
	'''绘制词云'''
	txt = ' '.join(words)
	wc = WordCloud(font_path='./fonts/simhei.ttf',width=800, height=600, mode='RGBA', background_color=None).generate(txt)
	plt.imshow(wc, interpolation='bilinear')
	plt.axis('off')
	plt.show()

def main():
	'''主函数'''
	txt = read_txt('top_click_news.txt')
	words = get_words(txt)
	deal_with_words(words)
	create_word_cloud(words)

if __name__ == '__main__':
	main()
