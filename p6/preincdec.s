	.text
	.globl main
main:
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 8
	li    $t0, 10
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, -8($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t1, 0($t0)
	li    $t0, 20
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, -12($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t1, 0($t0)
	la    $t0, -8($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	add   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	la    $t0, -12($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	add   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	la    $t0, -12($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	add   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	la    $t0, -12($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	add   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	la    $t0, -8($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	sub   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	la    $t0, -12($fp)	#CodeGenForAssignment
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t1, 0($t0)	#PreInc: Get value at Id
	sub   $t1, $t1, 1		#PreInc: Add value by 1
	sw    $t1, 0($t0)	#PreInc: Save incred value to addr
	lw    $t0, -8($fp)	#CodeGenForExp
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L0:	.asciiz"\n"
	.text
	la    $t0, .L0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, -12($fp)	#CodeGenForExp
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L1:	.asciiz"\n"
	.text
	la    $t0, .L1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $ra, 0($fp)
	move  $t0, $fp
	lw    $fp, -4($fp)
	move  $sp, $t0
	jr    $ra
