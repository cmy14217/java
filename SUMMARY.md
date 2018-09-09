#总结-陈明月-week 1
###1. 
<table>
   <tr>
      <td></td>
      <td>占字节数(bytes)</td>
      <td>占比特数(bits)</td>
      <td>最小数</td>
      <td>最大数</td>
      <td>默认值(for fields)</td>
   </tr>
   <tr>
      <td>byte</td>
      <td>1</td>
      <td>8</td>
      <td>-2^7</td>
      <td>2^7-1</td>
      <td>0</td>
   </tr>
   <tr>
      <td>short</td>
      <td>2</td>
      <td>16</td>
      <td>-2^15</td>
      <td>2^15-1</td>
      <td>0</td>
   </tr>
   <tr>
      <td>int</td>
      <td>4</td>
      <td>32</td>
      <td>-2^31</td>
      <td>2^31-1</td>
      <td>0</td>
   </tr>
   <tr>
      <td>long</td>
      <td>8</td>
      <td>64</td>
      <td>-2^63</td>
      <td>2^63-1</td>
      <td>0L</td>
   </tr>
   <tr>
      <td>Float</td>
      <td>8</td>
      <td>64</td>
      <td></td>
      <td></td>
      <td>0.0f</td>
   </tr>
   <tr>
      <td>double</td>
      <td>8</td>
      <td>64</td>
      <td></td>
      <td></td>
      <td>0.0d</td>
   </tr>
   <tr>
      <td>boolean</td>
      <td>1</td>
      <td>8</td>
      <td></td>
      <td></td>
      <td>false</td>
   </tr>
   <tr>
      <td>char</td>
      <td>2</td>
      <td>16</td>
      <td></td>
      <td></td>
      <td>‘\u0000’</td>
   </tr>
</table>


-	如果基本类型在方法中是一个临时变量，需要对其初始化，否则会被编译错误；但是如果声明为一个数组（数组会被存储为一个对象，每个元素是个field），其每项都会设置为其数据类型的默认初始值。
-	如果基本类型在类中声明却不初始化，则其默认值为null。(例：private char value[];)

###2.	Overflow & underflow
当数值达到其类型的最大值，加1便会overflow，变成其类型的最小值开始向上计算；当数值达到其类型的最小值，减1便会underflow，变成其类型的最大值开始向下计算。

###3.	算数左移/右移 & 逻辑左移/右移（Arithmetic shift vs. Logical shift）
* 逻辑右移。 每一位向右移动n个bit，最低位丢失，最高位补0 。例如：10110010 >>> 3 结果为：00010110
* 逻辑左移。每一位向左移动n个bit，最高位丢失，最低位补0。例如：
10110010 <<< 3结果为：10010000
* 算数右移。依次右移一位，尾部丢失，符号位右移后，原位置上复制一个符号位。例如： 10110010 >> 3结果为：10010110
* 算数左移。依次左移一位，尾部补0，最高的符号位保持不变。例如：
10110010 << 3 结果为：10010000

###4.	数据类型的转换（隐式转换/显式转换）
<table>
   <tr>
      <td>from/to</td>
      <td>byte</td>
      <td>short</td>
      <td>int</td>
      <td>long</td>
      <td>float</td>
      <td>double</td>
   </tr>
   <tr>
      <td>byte</td>
      <td></td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
   </tr>
   <tr>
      <td>short</td>
      <td>显</td>
      <td></td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
   </tr>
   <tr>
      <td>int</td>
      <td>显</td>
      <td>显</td>
      <td></td>
      <td>隐</td>
      <td>隐</td>
      <td>隐</td>
   </tr>
   <tr>
      <td>long</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td></td>
      <td>隐</td>
      <td>隐</td>
   </tr>
   <tr>
      <td>float</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td></td>
      <td>隐</td>
   </tr>
   <tr>
      <td>double</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td>显</td>
      <td></td>
   </tr>
</table>


###5.	ASCII , Unicode , UTF-16
-	ASCII (ANSI X3.4)是美国信息交换标准代码。Unicode UTF-16编码的前128个字符是ASCII字符。
-	为了表示使用16位单位的完整字符范围，Unicode标准定义了一种称为UTF-16的编码。在这种编码中，补充字符被表示为16位编码单元对，第一个是高替代范围(U+D800到U+DBFF)，第二个是低替代范围(U+DC00到U+DFFF)。对于U+0000到U+FFFF范围内的字符，代码点和UTF-16代码单元的值是相同的。
-	Java编程语言使用UTF-16编码以16位代码单元的序列表示文本。


###6.	Conditional-AND(&&)，Condition-OR(||)，Bitwise-AND(&)，Bitwise-OR(|) 

* 	x && y : x和y都是true时返回true，当x为false，则不判断y,直接返回false；(短路特性short-circut)
* 	x || y : x和y其中一个为true则返回true，当x为true，则不判断y，直接返回true
* 	按位与&，按位或|，置反~的优先级比较：~   >  &  >  |

###7.	final:
-	变量：指向栈内数据的指针不可改，但是对应的数据可变
-	方法：该方法不能被子类重写override
-	类：该类不能被继承extends

###8.	String类的一些处理字符串的方法
* String的不可变性(immutable)
* 	public String replace(CharSequence target, CharSequence replacement) 将字符串中的target全部替换成replacement
*	public String trim()  去除字符串开头和结尾的空格
*	public String substring(int beginIndex, int endIndex) 截取字符串从beginIndex至endIndex-1的部分，
* public String[] split(String regex) 根据字符串regex对目标字符串进行分割，返回分割后的字符串数组
* 以上方法调用结果返回新的字符串，不会对使用该方法的字符串进行修改
* StringBuilder的append方法相对于String的++更加节省内存空间，StringBuffer同样也有append方法，相对于StringBuilder来说，它是线程安全的，因而在单线程情况下，一般使用StringBuilder进行字符串的追加。
* codePoint：代码点，在Java中，一个代码点由一个或两个char构成
* int codePointCount(int beginIndex, int endIndex)：返回该字符串在该区间内的代码点的个数
*int codePointAt(int index) 返回从字符串的index位置开始的第一个code point


###9. Object
*	Object reference: 方法引用。使用等号将对象赋值给另一个变量时，并未实际使用该对象，而是使用该对象的引用，因而并未在内存中分配新的空间给新的变量，而是创建了一个新的引用指向原对象指向的内存地址。
*	创建新对象时，类中的初始化顺序：
	* 静态成员变量和静态初始化块，按顺序执行
	* 实例成员变量和实例初始化块，按顺序执行
	* 有参构造函数
	* 默认构造函数
*	Varargs (variable arguments)可变长度实参。在方法的形参中，使用类型名+… 形参名的形式标明调用该方法时，输入的实参个数是可变的。在执行该方法时，会自动将输入的实参打包成形参类型的数组传递到该方法中。

###10.	the access to members permitted by different modifier:
<table>
   <tr>
      <td>Modifier</td>
      <td>Class</td>
      <td>Package</td>
      <td>Subclass</td>
      <td>World</td>
   </tr>
   <tr>
      <td>public </td>
      <td>Y</td>
      <td>Y</td>
      <td>Y</td>
      <td>Y</td>
   </tr>
   <tr>
      <td>protected</td>
      <td>Y</td>
      <td>Y</td>
      <td>Y</td>
      <td>N</td>
   </tr>
   <tr>
      <td>no modifier</td>
      <td>Y</td>
      <td>Y</td>
      <td>N</td>
      <td>N</td>
   </tr>
   <tr>
      <td>private</td>
      <td>Y</td>
      <td>N</td>
      <td>N</td>
      <td>N</td>
   </tr>
</table>

###11.	继承Inheritance
 * Object class is the super class of all class in java.
 * 在创建子类的实例时，若未声明调用父类的有参/无参构造函数，会默认调用父类的无参构造函数
 * 子类可以在其构造函数中使用super()方法调用父类的构造函数
 * 在类的每个构造函数方法中，只能调用一次super()或者this()，且必须放在构造函数的第一行
 * 若创建父类的对象，并用子类进行实例化，则该对象调用父类的方法时，会实际调用子类重写的同名方法
 * 在子类方法中，可使用super.+方法名的形式调用父类中的方法
 * 子类和父类有继承关系，但子类的数组和父类的数组间没有继承关系

###12.	The instanceof operator
用于比较一个对象和一个指定的类型，当该对象是一下几种情况时，可以使用instanceOf比较：

* an instance of a class (是一个类的实例)
* an instance of a subclass(是一个子类的实例)
* an instance of a class that implements a particular interface(是特定接口的实现类的实例)

###13.	equals()
* x.equals(y)=true => y.equals(x)=true （对称性）
* x.equals(x)=true
* x.equals(y)=true,y.equals(z)=true  => x.equals(z)=true (传递性)
* x.equals(null)=false
* x.equals(不同于x的类)=false

* Equality & Identity(相等性和一致性)
  - Equality => Identity (✖️)
  - Identity => Equality (☑️)

* Equality不能在父子类之间使用。

###14. Reflection
-	对于每种类型的对象，Java虚拟机都会实例化Java.lang.Class的不可变实例，Java.lang.Class提供用于检查对象的运行时属性(包括其成员和类型信息)的方法, 还提供了创建新类和对象的功能。最重要的是，它是所有反射api的入口点。
-	对象名+.getClass(): 获得实例对象的Class实例
-	实例对象的Class实例+.getName()：获得该对象代表的类或接口的名称
-	实例对象的Class实例+.newInstance()：创建该对象所代表的类的新的实例
-	实例对象的Class实例+.getDeclaredMethods()：返回该对象所属的类或接口映射的所有public,protected,private方法以及继承来的方法
-	实例对象的Class实例+.getMethods()：返回该对象所属的类或接口映射的所有public方法以及继承来的方法

-	使用Arrays.sort方法对对象数组进行排序时，需要让该对象实现Comparable接口，重写compareTo方法


###15. Interface
*	一个接口可以有多个实现类（multi-implementation）
*	接口中可以包含public method, default method 和static method
*	接口中default method可以有方法体，并且可以被继承
*	接口中的static method不能被其实现类override
*	接口的变量field可以是final, static, public
*	接口可以继承类，但不能实现类
*	接口可以是抽象的
*	接口是package-private的
*	接口可以作为类型
*	Function interface（an interface with exactly one abstract method）只能有一个方法。

###16. Lambda表达式
*	匿名类（anonymous class）
*	Lambada表达式是用来实现function interface中的唯一的抽象方法的匿名方法
*	闭包colsure：函数+运行环境
*	Lambda表达式能在闭包中捕获变量的值
*	当lambda表达式被执行的时候，才会去计算被捕获的变量的值
*	Lambda表达式中捕获的变量从它被定义的范围扩展到被使用的范围
*	Method reference:
<table>
   <tr>
      <td>Kind</td>
      <td>Example</td>
   </tr>
   <tr>
      <td>Reference to a static method</td>
      <td>ContainingClass::staticMethodName</td>
   </tr>
   <tr>
      <td>Reference to an instance method of a particular object</td>
      <td>containingObject::instanceMethodName</td>
   </tr>
   <tr>
      <td>Reference to an instance method of an arbitrary object of a particular type</td>
      <td>ContainingType::methodName</td>
   </tr>
   <tr>
      <td>Reference to a constructor</td>
      <td>ClassName::new</td>
   </tr>
 </table>

 
###17 常用快捷键：
-	Shift + ESC: 回到代码窗口
-	Shift + command + enter: 类，方法，变量名称的自动补全
-	Shift + command + -:折叠所有代码块
-	Command + down :进入某个类
-	Command + O: 列出可override的方法
-	Shift + F6 : rename
-	Control + option + O : 代码优化（optimize）
-	Control + option + P :现实调用层次结构
-	Option + up : 连续选中代码块
-	Command + F7: 在类中查找用法
-	Option + delete : 删除到单词的开头
-	F2 / shift + F2: 跳到上一个/下一个突出错误或警告
-	Command + F12:调出所有方法
-	Command + shift + uop/down : 移动代码行的位置
-	Command + option + T : 包围代码







