'''
数据访问简单实例
'''
#引入数据模块  本地测试使用sqlite数据库
import sqlite3
conn = sqlite3.connect("D:\Program Files\Python\Python36\myDB.db")
#创建一个简单的表
conn.execute(r"create table IF NOT EXISTS  mytable(id int,name varchar(50))")
#插入一条记录
conn.execute(r"insert into mytable(id,name) values(1,'tom')")
conn.execute(r"insert into mytable(id,name) values(2,'lily')")
#查询mytable表数据
curs = conn.cursor()
curs.execute("select * from mytable")

#从游标中获取数据
names = [f[0] for f in curs.description]#取出列名
print(names)
for row in curs.fetchall():
    for pair in zip(names,row):
        print("%s:%s" % pair)