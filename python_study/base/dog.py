#定义class
class dog:
    '''
    #定义属性 默认的公共的，外部可访问的，加__则是private是私有的


    类和类的相关方法
    1.私有变量的定义 __ 两个下划线
    2.构造函数   __init__
    3.析构函数   __del__
    4.打印转换   __repr__
    5.按照索引赋值  __setitem__
    6.按照索引获取值 __getitem__
    7.获取长度      __len__
    8.比较运算      __cmp__
    9.函数调用      __call__
    '''
    color="white"
    size="small"

    __name="dog"  #私有的
    #定义构造函数
    def __init__(self,color,size):
        self.color = color
        self.size = size
        self.__name = "abc"
    def bark(self):
        print("dog color="+self.color+",size="+self.size+" is barking...")
    '''
    python 的class 跟java c 最大的不同点是不支持重载
    定义相当的方法名，则最后一个生效
    '''
    def bark(self,sound):
        print("dog is "+sound)

a = dog("red","big")
#a.bark() 无参数的就不能使用的了
a.bark("dddddd")

