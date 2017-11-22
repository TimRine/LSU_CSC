void int2bitstr(int I, char *str) 
{
	/* Variable declaration to set the unsigned integer i to the inputed integer and to initialize integer j*/
	unsigned int i = I;
	int j;
	/* We want to test 32 bits so we use the range 0 -> 31 as the bounds. Start the loop from the least significant bit and decrement by 1 each cycle */
	for (j = 31; j >= 0; j--)
	{      
		/* Remove the least significant bit using the "&" bitwise operator */
		unsigned int insert = i & 1;
		/* Check each element.If the bit assigned is 1, add a 1 at the index specified 
		If the bit assigned is 0, put a 0 at the index specified */
		if (insert == 1)
		{
		str[j] = '1';
		}
		else
		{
		str[j] = '0';
		}
	/* Shifting right once removes the least significant bit */
	i = i >> 1;
        }
	/* Allocate empty space for the last char into the array */
	str[32] = '\0';
}

int get_exp_value(float f) 
{
	/*  variable declaration to initialize the unsigned int f2u*float f) and int ui */
	unsigned f2u(float f);
	unsigned int ui = f2u(f);

	/* Left shift 1 time */
	ui = ui << 1; 
	/* Right shift 24 times */
	ui = ui >> 24;
	/* Subtract 127, the bias of float values given */
	ui = ui - 127;
	/* Return the exponent value of f, which is stored in the variable ui */
	return(ui);
}