; TESTA TEMP1 <= TEMP2

MENORIG >
TEMP1 <
TEMP2 <
TEMP3 <

& /0000

MENORIG	JP /0000
		LD TEMP1
		-  TEMP2
		JZ VERD
		JN VERD
		LV /0000
		JP FIM
VERD	LV /0001
FIM 	MM TEMP3
		RS MENORIG
		
# MENORIG

