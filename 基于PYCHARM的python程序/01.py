"""
-*- coding:utf-8 -*-
Time: 2021-05-09
"""
# 导入依赖
import jieba


def fun():
    # 读取文本
    txt = open("top_click_news.txt", "r", encoding='utf-8').read()
    # 使用精确模式对文本进行分词
    words = jieba.lcut(txt)
    # 通过键值对的形式存储词语及其出现的次数
    counts = {}
    for word in words:
        # 去掉词语中的空格
        word = word.replace('  ', '')
        # 如果词语长度为1，则忽略统计
        if len(word) == 1:
            continue
        # 进行累计
        else:
            counts[word] = counts.get(word, 0) + 1
    # 将字典转为列表
    items = list(counts.items())
    # 根据词语出现的次数进行从大到小排序
    items.sort(key=lambda x: x[1], reverse=True)
    # 输出统计结果
    for item in items:
        word, count = item
        print(word,count)


# 主函数
if __name__ == '__main__':
    fun()

