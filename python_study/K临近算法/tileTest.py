'''
tile函数位于python模块 numpy.lib.shape_base中，
它的功能是重复某个数组。比如tile(A,n)，
功能是将数组A重复n次，构成一个新的数组，我们还是使用具体的例子来说明问题：

.函数的定义与说明

　　函数格式tile(A,reps)

　　A和reps都是array_like

　　A的类型众多，几乎所有类型都可以：array, list, tuple, dict, matrix以及基本数据类型int, string, float以及bool类型。

　　reps的类型也很多，可以是tuple，list, dict, array, int, bool.但不可以是float, string, matrix类型。
'''

from numpy import *


print(tile(1,(4,1)))
a=[0,1,2]
b= tile(a,2)
print(b)

b=tile(a,(1,2))
print(b)

b=tile(a,(2,1))
print(b)


print("--------复杂一些的----------")
print(tile(1,2))
print(tile((1,2,3),3))
a=array([[1,2,3],[4,5]])
print(tile(a,2))

print("--------用例的----------")
inX=(1,2)
group = array([[1.0,1.1],[1.0,1.0],[0,0],[0,0.1]])
print(tile(inX,(4,1)))
b = tile(inX,(4,1))-group
print(b)
print(b**2)
c= b**2
#axis=0, 表示列。
#axis=1, 表示行
print(c.sum(axis=1))