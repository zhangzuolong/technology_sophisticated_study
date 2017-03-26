from numpy import *
#构建一个四行三列的矩阵
a = zeros((4,3))
a =array(a)
b = [0,1,2,3]

len = 4
for index in range(len):
    a[index] = b[0:3]
    print(a)