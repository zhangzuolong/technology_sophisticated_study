'''

K临近算法

'''

from numpy import *
import operator
from os import listdir

'''
构建原始数据集--即参考数据
'''
def createDataSet():
    group = array([[1.0,1.1],[1.0,1.0],[0,0],[0,0.1]])
    labels = ['A','A','B','B']
    return  group,labels

'''
inX      待测试的点
dataSet  数据集
labels   数据集对应的结果集
K        k临近算法的最近的K个
'''
def classify0(inX,dataSet,labels,k):
    #计算矩阵第一维度的长度
    dataSetSize = dataSet.shape[0]
    #构成改点跟所有数据集合的相差数据
    diffMat     = tile(inX,(dataSetSize,1)) - dataSet
    #然后平方
    sqDiffMat   = diffMat**2
    #按行计算和，结果为一个一维矩阵
    sqDistances = sqDiffMat.sum(axis=1)
    #然后对每一个点的结果进行开平方
    distances = sqDistances**0.5
    '''
    #argsort() 将x中的元素从小到大排列，提取其对应的index(索引)
    # x=array([1,4,3,-1,6,9])
    # y=x.argsort()
    # y=array([3,0,2,1,4,5])
    # 排序距离指数
    '''
    sortedDistIndicies = distances.argsort()
    classCount = {}
    for i in range(k):
        voteIlabel = labels[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel,0)+1
    '''
    iterable：是可迭代类型;
    cmp：用于比较的函数，比较什么由key决定;
    key：用列表元素的某个属性或函数进行作为关键字，有默认值，迭代集合中的一项;
    reverse：排序规则. reverse = True  降序 或者 reverse = False 升序，有默认值为升序。
    返回值：是一个经过排序的可迭代类型，与iterable一样。
    iteritems():items()和iteritems()方法都普遍用于for循环的迭代中，不同的是items()返回的是列表对象，
    而iteritems()返回的是迭代器对象。两者的用法差不多，但iteritems()的性能更快。
    operator.itemgetter()函数得到一个函数，这个函数可以获得对象的指定维度的数据

    '''
    sortedClassCount = sorted(classCount.items(),key=operator.itemgetter(1),reverse=True)
    return sortedClassCount[0][0]


'''
从文件中加载数据
'''
def file2matrix(filename):
    #打开一个文件,并获取
    fr = open(filename)
    numberOfLines = len(fr.readlines())
    #准备矩阵，numberOfLines行，3列
    returnMat = zeros((numberOfLines,3))
    #准备结果标签
    classLaberVector=[]
    #转换成numpy得数组格式
    returnMat = array(returnMat)
    #重新打开文件，读取数据
    fr = open(filename)
    index = 0
    for line in fr.readlines():
        '''
        声明：s为字符串，rm为要删除的字符序列
        s.strip(rm)        删除s字符串中开头、结尾处，位于 rm删除序列的字符
        s.lstrip(rm)       删除s字符串中开头处，位于 rm删除序列的字符
        s.rstrip(rm)      删除s字符串中结尾处，位于 rm删除序列的字符
        注意：
        当rm为空时，默认删除空白符（包括'\n', '\r',  '\t',  ' ')
        '''
        line = line.strip()
        #按照空格切分成数组[]
        listFromLine = line.split("\t")
        #把数组前三个赋值给对应下标的矩阵
        returnMat[index] = listFromLine[0:3]
        #将列表中的最后一列赋值给laber
        classLaberVector.append(int(listFromLine[-1]))
        index+=1
    return  returnMat,classLaberVector

'''
归一化
'''
def autoNorm(dataSet):
    #只取第一列的最小值
    minVals = dataSet.min(0)
    print(minVals)
    maxVals = dataSet.max(0)
    rangeVals = maxVals -minVals
    normDataSet = zeros(shape(dataSet))
    #只取第一维度的长度
    len = dataSet.shape[0]
    normDataSet = dataSet -tile(minVals,(len,1))
    normDataSet = normDataSet/tile(rangeVals,(len,1))
    return normDataSet,rangeVals,minVals

'''
测试算法--优化约会匹配
'''
def datingClassTest():
    #一半作为基础数据，一半作为测试数据
    hoRatio = 0.5
    #得到数据
    datingDataMat,datingLabels = file2matrix(r"D:\workspaces\idea\technology_sophisticated_study\python_study\K临近算法\file\datingTestSet2.txt")
    #数据归一化
    normMat,ranges,minVals = autoNorm(datingDataMat)
    #得到所有数据的第一维度长度
    len = normMat.shape[0]
    #得到测试数据长度
    testLen = int(len*hoRatio)
    #记录错误比例
    errorCount = 0.0
    for i in range(testLen):
        result =  classify0(normMat[i],normMat[testLen:len],datingLabels[testLen:len],10)
        print("this classifier came back with:%d,the real answer is %d"%(result,datingLabels[i]))
        if result != datingLabels[i]:
            errorCount +=1.0
    print("the total error rate is:%F"%(errorCount/float(testLen)))
    print(errorCount)

'''
加载数据
'''
def img2vector(filename):
    #构建数组
    returnVector = zeros((1,1024))
    fr = open(filename)
    for i in range(32):
        #读取一行
        line = fr.readline()
        for j in range(32):
            returnVector[0,32*i+j] = int(line[j])

    return returnVector

'''
手绘测试数据
'''
def handwritingClassTest():
    #训练数据目录
    trainingFileList = listdir(r"D:\workspaces\idea\technology_sophisticated_study\python_study\K临近算法\file\trainingDigits")
    #训练数据文件数量
    m = len(trainingFileList)
    #构建训练数据矩阵
    trainingMat = zeros((m,1024))
    #训练数据标记
    trainingLabels = []
    #训练数据
    for i in range(m):
        fileName = trainingFileList[i]
        labels = int(fileName.split(".")[0].split("_")[0])
        trainingLabels.append(labels)
        trainingMat[i] = img2vector(r"D:\workspaces\idea\technology_sophisticated_study\python_study\K临近算法\file\trainingDigits/%s"%fileName)
    #测试数据
    testFileList = listdir(r"D:\workspaces\idea\technology_sophisticated_study\python_study\K临近算法\file\testDigits")
    errorCount = 0.0
    mTest = len(testFileList)
    for i in range(mTest):
        fileName = testFileList[i]
        labels = int(fileName.split(".")[0].split("_")[0])
        testMat = img2vector(r"D:\workspaces\idea\technology_sophisticated_study\python_study\K临近算法\file\testDigits/%s"%fileName)
        deduceLabes = classify0(testMat,trainingMat,trainingLabels,3)
        print("this handwritingClassTest came back with:%d,the real answer is %d"%(deduceLabes,labels))
        if labels!=deduceLabes:
            errorCount +=1.0
    print("the total error rate is:%F"%(errorCount/float(mTest)))
    print(errorCount)


