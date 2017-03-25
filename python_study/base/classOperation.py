#类型可以相互转换
x=123
x
print(type(x))
x=True
print(type(x))
x="abc"
print(type(x))

'''
防止字符串转义,
可用 \\ ,或者前面加入一个r
'''
x="abc'"
print(x)
x="abc\abc"
print(x)
x="abc\\abc"
print(x)
x=r"abc\abc"
print(x)

'''
布尔操作符
'''
x=1==2
print(x)

'''
**  表示幂运算
'''
x=2**3
print(x)
x=2**4
print(x)

'''
python 有三个比较特殊的类型

列表  []
列表的操作
1.索引
2.子列表，分片  用 ：
3.加法
4.乘法
-----------
元祖  ()
字典  {}
'''
print("---------列表--------")
x=['a','b','c']
print(x[0])
x='abvcccdd'
print(x[0])
print(x[3:7])

x=['0','1','2','3','4','5','6','7','8','9','10']
print(x[2:8:2])
'''
倒序
'''
print(x[8:0:-1])

x1=['11','12','13']
print(x1+x)
print(x1*3)

'''
------元祖-----
'''
x=(1,2,3)
'''索引'''
print(x[0])
print(x.count)


print("---------字典--------")
'''
------字典------
1.键值对  类似于 key：value
'''
dic={"a":1,"b":2}
print(dic["a"])
print(dic.values())
dic["c"]=3