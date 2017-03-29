'''
决策树测试类
'''
import trees

'''
dataSet,lables = trees.createDataSet()
print(dataSet)
print(lables)
shannonEnt = trees.calcShannonEnt(dataSet)
print(shannonEnt)
'''
dataSet,labels = trees.createDataSet()
tree = trees.createTree(dataSet,labels)

d,l = trees.createDataSet()
result = trees.classify(tree,l,[1,0])

print(result)





