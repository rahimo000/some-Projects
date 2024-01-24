import math

import matplotlib.pyplot as plt
from pandas import *
import math
import csv
from sklearn import neural_network


def distance(x, y, x1, y1):
    return math.sqrt(((x - x1) ** 2) + ((y - y1) ** 2))


def getVarity(k, p):
    types = {
        0: 0,
        1: 0,
        2: 0
    }
    print(p)
    for i in range(k):
        types[p[i][1]] += 1
    print(types)
    return max(types, key=types.get)


cm = {
    0: 'green',
    1: 'red',
    2: 'blue'
}
pl = []
pw = []
sp = []

tab = []
with open("iris.csv") as csvfile:
    reader = csv.reader(csvfile, delimiter=",", quotechar="|")  # change contents to floats
    next(reader, None)  # skip the headers
    for row in reader:  # each row is a list
        pl.append(float(row[0]))  # push the result in array
        pw.append(float(row[1]))  # push the result in array
        sp.append(float(row[2]))  # push the result in array
    csvfile.close()

for v in set(sp):
    x = [pl[i] for i in range(len(sp)) if sp[i] == v]
    y = [pw[i] for i in range(len(sp)) if sp[i] == v]
    plt.scatter(x, y, c=cm[int(v)])

# add other point
x = 5.1
y = 1.65


# calcule distance
distanceTable = []
distances_with_variety = []
for i in range(len(pl)):
    x_i = pl[i]
    y_i = pw[i]
    distance = math.sqrt(pow((x_i - x), 2) + pow((y_i - y), 2))
    variety_i = sp[i]
    distances_with_variety.append((distance, variety_i))

distances_with_variety.sort()
variety = getVarity(4,distances_with_variety)
print(variety)
plt.scatter(x, y, c=cm[variety], marker='s')

plt.xlabel('l')
plt.ylabel('w')

plt.legend()
plt.show()
