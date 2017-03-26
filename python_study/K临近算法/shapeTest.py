'''
shape函数是numpy.core.fromnumeric中的函数，它的功能是读取矩阵的长度，
比如shape[0]就是读取矩阵第一维度的长度。它的输入参数可以使一个整数表示维度，
也可以是一个矩阵。这么说你可能不太理解，我们还是用各种例子来说明他的用法：
'''


from numpy import *
'''
#一维矩阵[1]返回值为1L
print(np.shape([1]))
#二维矩阵，返回两个值
print(np.shape([[1],[2]]))
#一个单独的数值，则返回空
print(np.shape(3))
a = [[1,2],[4,5],[7,8]]

e = np.array(a)
print(e)
print(e.shape[0])
'''
a = zeros((1,3))
print(a)
a = array([
    [1,2],
    [4,3]
])
print(a.max(1))

'''
print(tile(1,(2,1)))
b = a - tile(1,(2,1))
print(b)
'''