from math import *

#print((1+2)*3+(-1/2))
def clac():
    max = 0
    arr = [1,2,3,4,5,6,7]
    for x in arr:
        if max < x:
            max = x
    return max


def caldist(cord):
    x = abs(cord[0]-cord[1])
    y = abs(cord[2]-cord[3])
    return sqrt(x + y)


def caldistMan(cord):
    x = pow(cord[0]-cord[1], 2)
    y = pow(cord[2]-cord[3], 2)
    return sqrt(x + y)


def main():
    #x=7
    #print("hello",x)
    print(caldist([1,3,5,13]))


if __name__ == '__main__':
    main()