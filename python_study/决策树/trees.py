'''
决策树
'''
from math import log
import operator

#创建数据的函数
def createDataSet():
    dataSet = [
        [1,1,'yes'],
        [1,1,'yes'],
        [1,0,'no'],
        [0,1,'no'],
        [0,1,'no']
    ]
    labels = ['no surfacing','flippers']
    return dataSet,labels

#计算给定数据的熵
def calcShannonEnt(dataSet):
    numEntries = len(dataSet)
    labelCounts = {}
    for featVec in dataSet:
        #取数组的最后一个元素
        currentLabel = featVec[-1]
        if currentLabel not in labelCounts.keys():
            labelCounts[currentLabel] = 0
        labelCounts[currentLabel] +=1
    shannotEnt = 0.0
    for key in labelCounts:
        prob = float(labelCounts[key])/numEntries
        shannotEnt -=prob*log(prob,2)
    return shannotEnt

#划分数据集，按照给定的特征划分数据集
def splitDataSet(dataSet,axis,value):
    retDataSet = []
    for featVec in dataSet:
        if featVec[axis] == value:
            reducedFeatVec = featVec[:axis]
            reducedFeatVec.extend(featVec[axis+1:])
            retDataSet.append(reducedFeatVec)
    return retDataSet

#选择最好的数据集划分方式
def chooseBestFeatureToSplit(dataSet):
    numFetures = len(dataSet[0])-1
    #计算数据集的熵
    baseEntropy = calcShannonEnt(dataSet)
    bastInfoGain = 0.0;bestFeature = -1;
    for i in range(numFetures):
        # 列表表达式  example[i] for example in dataSet  取出第几列
        featList = [example[i] for example in dataSet]
        #去除重复
        uniqueVals = set(featList)
        newEntroy = 0.0
        for value in uniqueVals:
            subDataSet = splitDataSet(dataSet,i,value)
            prob = len(subDataSet)/float(len(dataSet))
            newEntroy +=prob * calcShannonEnt(subDataSet)
        infoGain = baseEntropy - newEntroy
        if(infoGain > bastInfoGain):
            bastInfoGain = infoGain
            bestFeature = i
    return bestFeature

#用于找出出现次数最多的分类名称和函数
def majorityCnt(classList):
    classCount = {}
    for vote in classList:
        if vote not in classCount:
            classCount[vote] = 0
        classCount[vote]+=1
    #排序
    sortedClassCount = sorted(classCount.items(),operator.itemgetter(1),reversed=True)
    return sortedClassCount[0][0]

#用于创建树的代码
def createTree(dataSet,labels):
    #取出数组，每一行的最后一个，即矩阵最后一列，列函数
    classList = [example[-1] for example in dataSet]
    #如果数组包含的元素都相同，则直接返回
    if classList.count(classList[0]) == len(classList):
        return classList[0]
    #遍历所有的角色，选出最优的
    if(len(dataSet[0]) == 1):
        return majorityCnt(classList)
    bestFeat = chooseBestFeatureToSplit(dataSet)
    bestFeatLabel = labels[bestFeat]
    myTree = {bestFeatLabel:{}}
    del(labels[bestFeat])
    #取出最优划分的列
    featValues = [example[bestFeat] for example in dataSet]
    #去重
    uniqueVals = set(featValues)
    for value in uniqueVals:
        subLabels = labels[:]
        myTree[bestFeatLabel][value] = createTree(splitDataSet(dataSet,bestFeat,value),subLabels)
    return myTree


#实用决策树进行分类的函数
def classify(inputTree,featLabels,testVec):
    firstStr = list(inputTree.keys())[0]
    secondDict = inputTree[firstStr]
    featIndex = featLabels.index(firstStr)
    for key in secondDict.keys():
        if testVec[featIndex] == key:
            if type(secondDict[key]).__name__ == 'dict':
                classLabel = classify(secondDict[key],featLabels,testVec)
            else:
                classLabel = secondDict[key]
    return classLabel


















































































