public
class
WorstCodeEver
                                                                {
public
static
void
main
(String                                                         []
args                                                            )
throws
NumberFormatException
                                                                ,
Exception
                                                                {
if
(args.length
!=
2                                                               )
                                                                {
System.
out.
println
("Invalid args"                                               )
                                                                ;
return
                                                                ;
                                                                }
Bit                                                              []
op
                                                                =
new
Bit[4]
                                                                ;
for
(int
i
=
0
                                                                    ;
i
<
op.
length
;
i
++)
                                                                    {
op[i]
=
new
Bit
(false)
                                                                    ;
                                                                    }
op[1]
.toggle
()                                                                                      ;
op[2]
.toggle
()                                                              ;
op[3]
.toggle
()                                                                  ;
System.
out.
println
(ALU.
doOP
(op
,
new
longword
(Integer.
parseInt
(args[0])
)
,
new
longword
(
Integer.
parseInt
(args[1])
))
.getSigned())
                                                                    ;
                                                                    }
                                                                    }

class
Bit
                                                                    {
private
boolean
value
                                                                    ;
public
Bit()
                                                                    {
this.
set
(false)
                                                                    ;
                                                                    }
public
Bit
                                                                    (
boolean
value
                                                                        )
                                                                        {
this.set(value);
                                                                    }
public void set(boolean value)                                              {
this.value = value;
                                                                        }

public void toggle()                                                     {
if (getValue())                                                         {
this.value = false                                                      ;
return;
                                                                        }
this.value = true;
                                                                    }

public void set()                                                           {
this.value = true;
                                                                        }

public void clear()                                                             {
this.value = false;
                                                                                        }

public boolean getValue() {
return this.value;
}

public Bit and(Bit other) {
Bit newBit = new Bit();
if (this.getValue()) {
if (other.getValue()) {
newBit.set(true);
return newBit;
}
}
newBit.set(false);
return newBit;
}

public Bit or(Bit other) {
Bit newBit = new Bit();
if (this.getValue()) {
newBit = this;
return newBit;
}
if (other.getValue()) {
newBit = other;
return newBit;
}
newBit.set(false);
return newBit;
}

public Bit xor(Bit other) {
Bit newBit = new Bit();
if
(this.getValue() ==
other.getValue()) {
newBit.set(false);
return newBit;
}
newBit.set(true);
return newBit;
}

public Bit not() {
Bit newBit = new Bit(this.getValue());
newBit.toggle();
return newBit;
}

@Override
public String toString() {
if (value) {
return "t";
}
return "f";
}
}

class
longword
implements
Ilongword
{
private
Bit[]
bits
;

public
longword()
{
this.bits
=
new
Bit[32]
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
this.bits[i]
=
new
Bit(false);
}
}

public
longword(int
value)
{
this.bits
=
new
Bit[32]
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
this.bits[i]
=
new
Bit(false);
}
this.set(value);
}

public
longword(longword
longwordCopy)
{
this.bits
=
new
Bit[32]
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
this.bits[i]
=
new
Bit(false);
}
this.copy(longwordCopy);
}

public
Bit
getBit(int
i)
{
return
this.bits[i];
}

public
void
setBit(int
i,
Bit
bit)
{
this.bits[i]
=
bit;
}

public
longword
and(longword
other)
{
longword
newLongword
=
new
longword()
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
newLongword.setBit(i,
this.getBit(i).and(other.getBit(i)));
}
return
newLongword;
}

public
longword
or(longword
other)
{
longword
newLongword
=
new
longword()
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
newLongword.setBit(i,
this.getBit(i).or(other.getBit(i)));
}
return
newLongword;
}

public
longword
xor(longword
other)
{
longword
newLongword
=
new
longword()
;
for
(int
i
=
0
                                                                        ;
i
<
32
                                                                        ;
i++)
                                                                        {
newLongword.setBit(i,
this.getBit(i).xor(other.getBit(i)));
                                                                        }
return
newLongword;
                                                                        }

public
longword
not()
                                                                        {
longword
newLongword
=
new
longword()
                                                                            ;
for
(int
i
=
0
;
i
<
32
;
i++)
                                                                        {
newLongword.setBit(i,
this.getBit(i).not());
                                                                            }
return
newLongword;
                                                                            }
public
longword
rightShift(int
amount)
{
if
(amount
<
0
||
amount
>
31)
{
throw
new
IndexOutOfBoundsException();
}
longword
newLongword
=
new
longword()
;
int
j
=
0
;

for
(int
i
=
0
;
i
<
amount
;
i++)
{
newLongword.setBit(i,
new
Bit());
}
for
(int
i
=
amount
;
i
<
32
;
i++)
{
newLongword.setBit(i,
this.getBit(j));
j++;
}
return
newLongword;
}

public
longword
leftShift(int
amount)
{
if
(amount
<
0
||
amount
>
31)
{
throw
new
IndexOutOfBoundsException();
}
longword
newLongword
=
new
longword()
;
int
j
=
31
;

for
(int
i
=
31
;
(31
-
amount)
<
i
;
i--)
{
newLongword.setBit(i,
new
Bit());
}
for
(int
i
=
(31
-
amount)
;
i
>
-
1
;
i--)
{
newLongword.setBit(i,
this.getBit(j));
j--;
}
return
newLongword;
}

@Override
public
String
toString()
{
StringBuilder
result
=
new
StringBuilder()
;
for
(int
i
=
0
;
i
<
32
;
i++)
{
result.append((this.bits[i].getValue()
?
"t"
:
"f"));
if
(i
!=
31)
result.append(", ");
}
result.append("\n");
return
result.toString();
}

public
long
getUnsigned()
{
long
result
=
0
;
int
j
=
0
;
for
(int
i
=
31
;
i
>
-
1
;
i--)
{
if
(this.bits[i].getValue())
{
result
+=
((int)
Math.pow(2,
j));
}
j++;
}
return
result;
}

public
int
getSigned()
{
int
result
=
0
,
j
=
0
;
if
(!this.bits[0].getValue())
return
(int)
getUnsigned();

longword
newLongword
=
this.not();
for
(int
i
=
31
;
i
>
1
;
i--)
{
if
(newLongword.getBit(i).getValue())
{
result
+=
(int)
Math.pow(2,
j);
}
j++;
}
return
(result
*
-1)
-
1;
}

public
void
copy(longword
other)
{
for
(int
i
=
0
;
i
<
32
;
i++)
{
this.setBit(i,
other.getBit(i));
}
}

public
void
set(int
value)
{
int
i
=
0
;
longword
temp
=
new
longword()
;
int
num
=
value;

if
(value
<
0)
{
value
*=
-1;
}

while
(value
!=
0)
{
if
(value
%
2
==
1)
{
temp.setBit(i,
new
Bit(true));
}
else
{
temp.setBit(i,
new
Bit(false));
}
value
/=
2;
i++;
}

i
=
31;
for
(int
j
=
0
;
j
<
32
;
j++)
{
this.setBit(j,
temp.getBit(i));
i--;
}

if
(num
<
0)
{
temp
=
this;
temp
=
temp.not();
Bit
tempBit;
if
(!temp.getBit(31).getValue())
{
tempBit
=
new
Bit(temp.getBit(31).getValue());
tempBit.toggle();
temp.setBit(31,
tempBit);

}
else
{
i
=
31;
while
(temp.getBit(i).getValue())
{
tempBit
=
new
Bit(temp.getBit(i).getValue());
tempBit.toggle();
temp.setBit(i,
tempBit);
i--;
}
tempBit
=
new
Bit(temp.getBit(i).getValue());
tempBit.toggle();
temp.setBit(i,
tempBit);
}
for
(i
=
0
;
i
<
32
;
i++)
{
this.setBit(i,
temp.getBit(i));
}
}
}
}

interface
Ilongword
{

Bit
getBit(int
i);

void
setBit(int
i,
Bit
value);

longword
and(longword
other);

longword
or(longword
other);

longword
xor(longword
other);

longword
not();

longword
rightShift(int
amount);

longword
leftShift(int
amount);

@Override
String
toString();

long
getUnsigned();

int
getSigned();

void
copy(longword
other);

void
set(int
value);
}

class
rippleAdder
{

public static longword add(longword firstLongword, longword secondLongword) {
long mili = 1000;
try {
Thread.sleep(mili);
} catch (InterruptedException e) {
e.printStackTrace();
}
longword newLongword = new longword();
boolean carryValue = false;
for (int i = 31; i > -1; i--) {
newLongword.setBit(i, (firstLongword.getBit(i).xor(secondLongword.getBit(i))).xor(new Bit(carryValue)));
carryValue = (firstLongword.getBit(i).and(secondLongword.getBit(i)))
.or((firstLongword.getBit(i).xor(secondLongword.getBit(i))).and(new Bit(carryValue)))
.getValue();
}
return newLongword;
}
public static longword subtract(longword firstLongword, longword secondLongword) {
return add(firstLongword, add(secondLongword.not(), new longword(1)));
}
}

class
multiplier
{
public static longword multiply(longword firstLongword, longword secondLongword) {
longword newLongword = new longword();
for (int i = 31; i > -1; i--) {
if (secondLongword.getBit(i).getValue())
newLongword.copy(rippleAdder.add(newLongword, ((new longword(firstLongword.leftShift(31 - i))))));
}
return newLongword;
}
}

class
ALU
{
public static longword doOP(Bit[] operation, longword firstLongword, longword secondLongword) throws Exception {
if (operation.length != 4) {
throw new Exception("Operation failure");
}
if (operation[0].getValue()) { 
if (operation[1].getValue()) { 
if (operation[2].getValue()) { 
if (operation[3].getValue()) {
return rippleAdder.subtract(firstLongword, secondLongword);
} else { 
return rippleAdder.add(firstLongword, secondLongword);
}
} else { 
if (operation[3].getValue()) { 
longword temp = new longword();
temp.copy(secondLongword);
for (int i = 26; i > -1; i--) {
temp.setBit(i, new Bit());
}
return firstLongword.rightShift(temp.getSigned());
} else {
longword temp = new longword();
temp.copy(secondLongword);
for (int i = 26; i > -1; i--) {
temp.setBit(i, new Bit());
}
return firstLongword.leftShift(temp.getSigned());
}
}
} else {
if (operation[2].getValue()) { 
if (operation[3].getValue()) { 
return firstLongword.not();
} else { 
return firstLongword.xor(secondLongword);
}
} else { 
if (operation[3].getValue()) { 
return firstLongword.or(secondLongword);
} else { 
return firstLongword.and(secondLongword);
                                                                        }
                                                                        }
                                                                        }
} else                                                                  {
if 
(operation[1].
getValue())                                                             {
if 
(operation[2].
getValue())                                                             {
if 
(operation[3].
getValue())                                                             {
return 
multiplier.
multiply
(firstLongword, 
secondLongword)                                                         ;
                                                                        }
                                                                        }
                                                                        }
                                                                        }
return new longword();
                                                                        }
                                                                        }
