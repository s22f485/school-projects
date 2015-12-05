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
	sub $sp, $sp, 8
	add $t0, $t0, 2
	ble $t0, $t9, init
	
	li $t0, 1
	
outer:	add $t0, $t0, 2
	mul $t1, $t0, $t0
	bgt $t1, $t9, print
	
	# TODO: investigate moving $sp with counter instead of recalculating
	add	$t2, $s2, 0	# save the bottom of stack address to $t2
	sll	$t3, $t0, 2	# calculate the number of bytes to jump over		!!! Go to next multiple of counter
	sub	$t2, $t2, $t3	# subtract them from bottom of stack address
	add	$t2, $t2, 12	# add 2 words - we started counting at 2!
	lw $t3, ($t2)
	
	beq $t3, $s0, outer
	
	# print prime
	li	$v0, 1		# system code to print integer
	add	$a0, $s0, $t0	# the argument will be our prime number in $t3
	syscall			# print it!
	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall
	
	add $t1, $t0, 0
inner: 	add	$t2, $s2, 0	# save the bottom of stack address to $t2		!!! Remove all multiples of dat prime
	sll	$t3, $t1, 2	# calculate the number of bytes to jump over		!!! Move to the next multiple
	sub	$t2, $t2, $t3	# subtract them from bottom of stack address
	add	$t2, $t2, 12	# add 2 words - we started counting at 2!

	sw	$s0, ($t2)	# store 0's -> it's not a prime number!			!!! We know this isnt prime

	add	$t1, $t1, $t0	# do this for every multiple of $t0
	bgt	$t1, $t9, outer	# when every multiple < $t9 is covered, go back to the outer loop

	j	inner		# some multiples left? go back to inner loop

# TODO: Rewrite counter loop
count:	add	$t0, $t0, 2	# increment counter variable
	
print:	bgt	$t0, $t9, exit	# make sure to exit when all numbers are done

	add	$t2, $s2, 0	# save the bottom of stack address to $t2
	sll	$t3, $t0, 2	# calculate the number of bytes to jump over
	sub	$t2, $t2, $t3	# subtract them from bottom of stack address
	add	$t2, $t2, 12	# add 2 words - we started counting at 2!

	lw	$t3, ($t2)	# load the content into $t3
	beq	$t3, $s0, count	# only 0's? go back to count loop

	add	$t3, $s2, 0	# save the bottom of stack address to $t3

	li	$v0, 1		# system code to print integer
	add	$a0, $t0, 0	# the argument will be our prime number in $t3
	syscall			# print it!

	li	$v0, 4		# system code to print string
	la	$a0, space	# the argument will be a whitespace
	syscall			# print it!

	ble	$t0, $t9, count	# take loop while $t0 <= $t9
	
exit:	li	$v0,10		# set up system call 10 (exit)
	syscall	