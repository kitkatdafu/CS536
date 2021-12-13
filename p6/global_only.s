	.data
	.align 2
_a:	.space 4
	.data
	.align 2
_b:	.space 4
	.data
	.align 2
_c:	.space 4
	.data
	.align 2
_d:	.space 4
	.text
	.globl main
main:
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0
	lw    $ra, 0($fp)
	move  $t0, $fp
	lw    $fp, -4($fp)
	move  $sp, $t0
	jr    $ra
