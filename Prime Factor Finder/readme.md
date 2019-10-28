#Two files here, the goal of each is the same
#The idea is to find the two prime factors of a large number, 
#this initially started as a project for a class and just invlolved
#using pure brute force but after turning in the project it turned
#into somewhat of a passion project
#where i decided to try and make it more efficient by not 
#wasting time checking obviously too small combinations of numbers

#the algorithm in the unstable version of the program checks 
#percentages of desired numbers and compares that where it currently is
#for example:
#if the number we are looking for is 5005 and our list has the numbers [1,3,5, ..., 1009, 1013]
#the alorithm will first check to see if the number we are 
#currently testing against all other numbers is bigger than our desired number
#so we ask: is 1 x 1013 bigger than or equal to 5045? no, so skip it
#then we would move on to
#is 3 x 1013 bigger than 5045? no, skip it
#is 5 x 1013 bigger than 5045? yes! lets check combinations

#then we 5 vs all other numbers in the list increasing index's of the 
#list based of percentage to the desired product (5045 in this case)
#5x1 = 5. 5/5045 = .00099, increase index's to make this .9 then
#repeat but increase index's to make it .95
#repeat but increas index's to make it .98
#brute force until we either hit our number, or go over it, 
#in which case move on to the next number and repeat the process.
