import random
import math
import time
primeNums = []
def primeGen():
    count = 3
    global primeNums
    while True:
        isprime = True
        
        for i in range(2, int(math.sqrt(count) + 1)):
            if count % i == 0: 
                isprime = False
                break
        
        if isprime and count<15000:
            primeNums.append(count)
##            print(count)
        elif count >= 15000:
            break
        count += 1
        
def primeFind():
    try:
        global primeNums
        track = time.time()
        count = 0
        value1 = 0
        value2 = 0
        num1 = primeNums[value1]
        num2 = primeNums[value2]
        outcome = random.choice(primeNums)*random.choice(primeNums)
        factor = num1*num2
        percent = factor/outcome
        while (num1*num2) != outcome:
            if num2*primeNums[len(primeNums)-1] < outcome:
                value2 += 1
                num2 = primeNums[value2]
            elif num2*num1 > outcome:
                value2+=1
                value1 = 0
                num1 = primeNums[value1]
                num2 = primeNums[value2]
            if percent < 0.98:
                while True:
                    if percent < 0.60:
                        value1 += 10
                        num1 = primeNums[value1]
                        factor = num1*num2
                        percent = factor/outcome
                    elif percent < 0.80:
                        value1 += 5
                        num1 = primeNums[value1]
                        factor = num1*num2
                        percent = factor/outcome
                    else:
                        if len(primeNums) - 2 != value1:
                            value1 += 1
                            num1 = primeNums[value1]
                            factor = num1*num2
                            percent = factor/outcome
                        else:
                            value1 += 1
                    break
            if value2 < len(primeNums):
                if value1 < len(primeNums)-1:
                    value1 += 1
                    num1 = primeNums[value1]
                    factor = num1*num2
                    percent = factor/outcome
                    print('No Match', num1, '*', num2,'=', num1*num2,'    Looking for:',outcome,'percent =',percent)                
                else:
                    value1 = 0
                    value2 += 1
                    num1=primeNums[value1]
                    num2=primeNums[value2]
                    factor = num1*num2
                    percent = factor/outcome
                    print('No Match', num1, '*', num2,'=', num1*num2,'    Looking for:',outcome, 'percent =',percent)
            else:
                value2 = 0
                value1 = 0
                num1 = primeNums[value1]
                num2 = primeNums[value2]
                print('No Match', num1, '*', num2,'=', num1*num2,'    Looking for:',outcome)
        print('Match', num1, '*', num2, '=', num1*num2,'    Looking for:',outcome)
        track = (time.time()-track)//1
        minutes = int(track//60)
        seconds = int(track % 60)
                
        print('Time elapsed: ', str(minutes),':',str(seconds))
    except:
        print('values', value1, value2, len(primeNums))
            
primeGen()
primeFind()
