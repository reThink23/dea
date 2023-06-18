from os.path import join, parent, abspath
import os
import time


# test QuickSort.java with different pivot strategies and different input files
for mode in ["first", "median3", "random"]:
	for i in [550000, 600000, 650000, 700000, 750000, 800000, 850000, 900000, 950000, 1000000, 10000000]:
		print("Test QuickSort.java with random numbers and pivot strategy " + str(i))
		filepath = join(parent(parent(abspath(__file__))), "/testfiles/desc/desc_"+str(i)+".txt")
		os.system("java QuickSort " + filepath + " " + mode)
time.sleep()