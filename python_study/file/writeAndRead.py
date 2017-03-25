
'''
读写文件的基本方法
写文件
'''
file = open(r"D:\Program Files\Python\example\test.txt","w")
file.write("hello word!")
file.write("\n")
file.write("hello word!")
file.write("\n")
file.write("hello word!")
file.write("\n")
file.write("hello word!")
file.write("\n")
file.write("hello word!")
file.flush()#刷新
file.close()
##读文件
file = open(r"D:\Program Files\Python\example\test.txt","r")
##读一行
print(file.readline())
'''
文件打开的几种模式
r 只读  w 写  a 追加 b 二进制  + 读写模式
\n  换行
等等
'''

'''
按照字节流方式来读取
'''
print("------按照字节流来读取------")
file = open(r"D:\Program Files\Python\example\test.txt","r")
char = file.read(1)
while char:
    print("current char=",char)
    char = file.read(1)
file.close()

print("---------按照行来读取---------")
file = open(r"D:\Program Files\Python\example\test.txt","r")
while 1:
    ##按照行来读
    line = file.readline()
    if not line:break
    print("current line:",line)
file.close()

