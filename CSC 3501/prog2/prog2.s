.globl prog2

prog2:

#Start
        
#Push %ebp onto the stack
        pushl   %ebp
#Move the stack from %esp to %ebp
        movl %esp, %ebp
#Push %ebx onto the stack
        pushl %ebx

#Return j-i

#Move j to register %eax
        movl 12(%ebp), %eax
#Move i to resgister %ecx
        movl 8(%ebp), %ecx
#Subtract j-i
        subl %ecx, %eax

#Set *k=3*(*k)

#Move the pointer value to register %ebx
        movl 16(%ebp), %ebx
#Move k(%ebx) to the %edx register
        movl (%ebx), %edx
#Left shift of k
        sall $1, %edx
#Add k to k*2 to get 3*(*k)
        addl (%ebx), %edx
#Move k(%edx) to pointer
        movl %edx, (%ebx)

#Set *l = a[0] + a[1] + a[2] + a[3] + a[4];

#Move the array into register %edx
        movl 20(%ebp), %edx
#Move l to %ecx
        movl 24(%ebp), %ecx
#Move 0 to %edi 
        movl $0, %edi
#Add a[0] to %edi
        addl (%edx), %edi
#Point to the next value in the array
        addl $4, %edx
#Add a[1] to %edi
        addl (%edx), %edi
#Point to the next value in the array
        addl $4, %edx
#Add a[2] to %edi
        addl (%edx), %edi
#Point to the next value in the array
        addl $4, %edx
#Add a[3] to %edi
        addl (%edx), %edi
#Point to the next value in the array
        addl $4, %edx
#Add a[4] to %edi
        addl (%edx), %edi
#Move l to the pointer
        movl %edi, (%ecx)

#End

#Remove %ebx from the stack
        popl %ebx
#Remove %ebp from the stack
        popl %ebp
#Return value
        ret