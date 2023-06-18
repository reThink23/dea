@echo on
@REM run tests on quicksort with different pivot strategies and different input sizes

for loop %%k in (first random median) do (
	echo "Test with pivot strategy %%k"
	for loop %%i in (550000 600000 650000 700000 750000 800000 850000 900000 950000 1000000 10000000) do (
		echo "Test with input size %%i"
		java QuickSort.java %%i %%k
	)
)
sleep 5
```