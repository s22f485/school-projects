# Code adapted from http://www.marcell-dietl.de/downloads/eratosthenes.s
# Code changed by Lisa Peters and Mackenzie O'Bleness for CSCI-361
	
	.data			# the data segment to store global data
space:	.asciiz	" "		# whitespace to separate prime numbers

	.text			# the text segment to store instructions
	.globl 	main		# define main to be a global label
main:	li	$s0, 0x00000000	# initialize $s0 with zeros
	li	$s1, 0x11111111	# initialize $s1 with ones
	li	$t9, 200	# find prime numbers from 2 to $t9
	
	# Calculate sqrt($t9)
	mtc1 $t9, $f1		
	cvt.d.w $f2, $f1
	sqrt.d $f2, $f2
	cvt.w.d $f1, $f2
	mfc1 $t8, $f1
	
	# Load some constants that we use a lot for a little gain?
	li	$t6, 1
	li	$t7, 2

	add	$s2, $sp, 0	# Save the original stack address (its the start of our array of sorts)
	
	blt $t9, 2, exit	# There are no primes before 2, so just exit

	# Print 2! After this there are no evens.
	li 	$v0, 1		# system code to print integer
	add	$a0, $t7, 0	# the argument will be our prime number in $t3
	syscall			# print it!
	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall			# print it!
	
	# Start count at 3 and $sp+2 (because we have a triple unrolled loop)
	li $t0, 3
	add $sp, $sp, 2

	# Initalize the memory with ones to indicate that everything can be prime right now.
init:	sb $s1, -2($sp)
	sb $s1, -1($sp)
	sb $s1, 0($sp)
	sub $sp, $sp, 3
	add $t0, $t0, 6
	ble $t0, $t9, init
	
	# $t0 is our count variable, $s3 is the memory address of that count
	li $t0, 1
	add $s3, $s2, $t6
	
outer:	add $t0, $t0, $t7	# increment count by 2
	bgt $t0, $t8, print	# if count is greater than the sqrt($t9), then print 
	sub $s3, $s3, $t6	# increment mem pointer by 1 byte
	
	
	lb $t3, ($s3)
	
	beq $t3, $s0, outer
	
	# print prime
	li	$v0, 1 		# system code to print integer
	add	$a0, $s0, $t0	# the argument will be our prime number in $t3
	syscall			# print it!
	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall
	
	add $t1, $s3, 0 # og count mem address
	add $t2, $t0, 0 # current count
	sll $t3, $t0, 1 # count jump amount
	
inner:	add $t2, $t2, $t3 	# increment count by $t0
	bgt $t2, $t9, outer	# when every multiple < $t9 is covered, go back to the outer loop
	sub $t1, $t1, $t0 	# move to mem address
	sb	$s0, ($t1)
	j	inner		# some multiples left? go back to inner loop

count:	add	$t0, $t0, $t7	# increment counter variable
print:	bgt	$t0, $t9, exit	# make sure to exit when all numbers are done
	sub 	$s3, $s3, $t6	# increment counter mem address
	
	lb	$t3, ($s3)	# load the content into $t3
	beq	$t3, $s0, count	# only 0's? go back to count loop

	li	$v0, 1		# system code to print integer
	add	$a0, $t0, 0	# the argument will be our prime number in $t0
	syscall			# print it!

	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall			# print it!

	ble	$t0, $t9, count	# take loop while $t0 <= $t9
	
exit:	li	$v0,10		# set up system call 10 (exit)
	syscall	
