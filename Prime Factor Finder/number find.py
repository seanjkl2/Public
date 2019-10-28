##number finder
import random
import math
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
        
        if isprime and count<1000000:
            primeNums.append(count)
            print(count)
        elif count >= 100:
            break
               
        count += 1
def primeFind():
    global primeNums
    factor1 = primeNums[0]
    factor2 = primeNums[0]
    outcome = random.choice(primeNums) * random.choice(primeNums)
    for i in range(len(primeNums)):
        if (factor1*factor2) > outcome:
            i = i+1
        for j in range(len(primeNums)):
            if factor1 * factor2 == outcome:
                return('Match Found ' + str(factor1) + ' * ' + str(factor2) + '=' + str(outcome) +  '     Looking for: ' + str(outcome))
            else:
                print('No Match', factor1, '*', factor2,'=', factor1*factor2,'    Looking for:',outcome)
                factor1 = primeNums[j]
        if factor1 * factor2 == outcome:
            break
        factor2 = primeNums[i]

print(primeGen())
print(primeFind())
