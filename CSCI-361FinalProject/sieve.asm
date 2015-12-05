# Code adapted from http://www.marcell-dietl.de/downloads/eratosthenes.s
# Code changed by Lisa Peters and Mackenzie O'Bleness for CSCI-361
	
	.data			# the data segment to store global data
space:	.asciiz	" "		# whitespace to separate prime numbers

	.text			# the text segment to store instructions
	.globl 	main		# define main to be a global label
main:	li	$s0, 0x00000000	# initialize $s0 with zeros
	li	$s1, 0x11111111	# initialize $s1 with ones
	li	$t9, 200	# find prime numbers from 2 to $t9

	add	$s2, $sp, 0	# backup bottom of stack address in $s2

	li	$v0, 1		# system code to print integer
	add	$a0, $s0, 2	# the argument will be our prime number in $t3
	syscall			# print it!
	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall			# print it!
	
	li $t0, 3
	
init:	sw $s1, 0($sp)
	sub $sp, $sp, 4
	add $t0, $t0, 2
	ble $t0, $t9, init
	
	li $t0, 1
	add $s3, $s2, 4
	
outer:	add $t0, $t0, 2
	sub $s3, $s3, 4
	mul $t1, $t0, $t0
	bgt $t1, $t9, print
	
	lw $t3, ($s3)
	
	beq $t3, $s0, outer
	
	# print prime
	li	$v0, 1		# system code to print integer
	add	$a0, $s0, $t0	# the argument will be our prime number in $t3
	syscall			# print it!
	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall
	
	add $t1, $s3, 0 # og count address 
	sll $t4, $t0, 2 # mem jump amount
	add $t2, $t0, 0 # current count
	sll $t3, $t0, 1 # count jump amount
	
inner:	add $t2, $t2, $t3 	# increment count
	bgt $t2, $t9, outer	# when every multiple < $t9 is covered, go back to the outer loop
	sub $t1, $t1, $t4 	# move to mem address
	sw	$s0, ($t1)
	j	inner		# some multiples left? go back to inner loop

count:	add	$t0, $t0, 2	# increment counter variable
	sub 	$s3, $s3, 4
print:	bgt	$t0, $t9, exit	# make sure to exit when all numbers are done

	lw	$t3, ($s3)	# load the content into $t3
	beq	$t3, $s0, count	# only 0's? go back to count loop

	li	$v0, 1		# system code to print integer
	add	$a0, $t0, 0	# the argument will be our prime number in $t3
	syscall			# print it!

	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall			# print it!

	ble	$t0, $t9, count	# take loop while $t0 <= $t9
	
exit:	li	$v0,10		# set up system call 10 (exit)
	syscall	
