# summary-week2
### Exception
1. Exception类是Throwable类的子类，当创建Exception的实例或其子类的实例时调用构造函数，实际是调用Exception父类Throwable的构造函数进行处理。
2. Throwable有两个构造函数：
`public Throwable(String message)`和`public Throwable(String message, Throwable cause)`
在第二个构造函数中，第二个参数是Throwable类型的变量cause。cause指代导致该异常抛出的异常，cause成员变量在定义时初始值为该异常本身。若调用第一个构造函数，则cause使用其初始值，表明该异常抛出不受其他异常的影响。调用第二个构造函数，传入非空的cause参数，表明该异常受其他的异常影响产生，cause构成异常的链式反应。
3. try + catch + finally  
不论try代码块中是否产生返回值/抛出异常/异常有没有处理，都会继续执行finally代码块中的内容。若在finally中产生返回语句，则try代码块中的返回值以及异常会失效。  
Condition1: try代码块中有返回值，且finally代码块中产生返回值
```
public int executeOrderOfTryCatchFinally(){
    try{  
        return 2;
    }finally {
        return 4;
    }
}//结果：4，执行了finally中的返回语句
```
Condition2: try代码块中抛出异常，finally代码块中有return语句
```
public int executeOrderOfTryCatchFinally(){
    try{
        throw new Exception();
    }finally {
        return 4;
    }
}

@Test
void test_try_finally() {
    assertEquals(4, executeOrderOfTryCatchFinally());
    assertThrows(Exception.class, this::executeOrderOfTryCatchFinally);
}
	//结果：第一句断言执行成功，方法返回4；第二句断言失败，没有异常被抛出。
```
- 一般不要在finally代码块中执行return语句，会使得try代码块中抛出的异常无法返回给上一级。
4. try-with-resources
     - 在try语句中可以声明一个或者多个资源
     - 任何实现了Java.lang.AutoClosable接口或者Java.lang.Closable接口的对象都可以作为一个资源
     - try-with-resources语句确保了一个资源在try代码块执行完后会被自动关闭
     -若不在try中声明资源，而在外部申明，可在finally代码块中进行非空判断，手动关闭资源
    - In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.
	- 资源的关闭顺序是和它的创建顺序相反执行的。
	- AutoClosable 和 Closable。AutoClosable是Closable的子类，Closable接口的close()方法会抛出IOException，而AutoClosable接口的close()方法抛出的是Exception类型。
5. Error和Exception都继承自throwable接口，Exception主要分为RuntimeException和非RuntimeException。Error和RuntimeException都是unchecked exception，unchecked exception不能用throw进行抛出，在编程时就应当处理掉这些问题。非RuntimeException需要使用throw进行抛出，因为这些异常是不可预测的，比如用户在进行操作时，因为输入失误，程序会向下抛出异常。

### 内部类Inner class
1. 内部类能够获取外部类的所有成员变量和方法。
2. 内部类是实例上的成员，内部类是外部类的一部分，要想创建inner class，得先创建外部类的实例。
3.  不能在inner class中定义static 类型的变量，但是可以定义static final类型的常量；例：

```
class theOuterClass{
    public class theInnerClass{
        private static int a;    // Compile error
        private static final int b = 1;  // OK
    }
}
```

4. 在inner class中调用的本地变量必须是final或者是effectively final的。
5. local class 是在类的成员方法中被定义的内部类，类的修饰符不能为public, private和protected，local inner class可以访问外部类的成员变量。
6. 匿名类anonymous class是一种特别的内部类，只能实例化一次，可访问外部类的成员变量。
7. 当在闭包范围内的变量名与外部闭包的变量名相同时，会产生variable shadowing的情况，即编译器无法识别该变量为外部类的变量名，此时从内部类获取外部类的成员变量方式为：
外部类名 + .this + .变量名
```
class theOuterClass{
    private int field;
    class theOuterClass{
        field = 1;
    }
    public class theInnerClass{
        void changeField(int field){
            theOuterClass.this.field += field;
        }
    }
}
```

### Generic 泛型
1. 泛型不能用于表示primitive datatype
2. Bounded type parameter
为了限制泛型的类型，可以给它限定边界条件，可使用<T extends className & interfaceName的形式，表明传入参数的类型必须是限定类的子类并且实现了规定的接口。当有类作为限制条件时，必须放在extends后第一位，否则会编译错误。例：
	-	`private static <T extends className> T methodName(T[] array){}   //OK`
	-	`private static <T extends interfaceName<T>> T methodName(T[] array){}   //OK`

	-	`private static <T extends interfaceName & className> T methodName(T[] array){} //compile error`

	-	`private static <T extends className & interfaceName<T>> T methodName(T[] array){} //OK`

	-	`private static <T extends className & interfaceName1 & interfaceName2> T methodName(T[] array){}  //OK`
3. 通配符?
	- upper bound wildcards上边界通配符。<? Extends className & interfaceName>
	- Unbounded Wildcards 无边界通配符。<?>
	- lower bounded wildcards 下边界通配符。<? Super className >

4. 类型擦除type erasure
    - 程序在编译的时候才会推断类型
    - 程序编译时，若泛型没有限定边界，则默认类型为Object；若限定边界，则默认类型为继承的第一个类型。



### Stream
1. 转换Colollection成stream：
```
List<Integer> list = new ArrayList<>();
List.stream(); //
```
2. 转换Array成stream
```
String[] words = {“a”,”b”,”c”};
Arrays.stream(words); //
```
对于non-primitive type类型的数组，调用Arrays的public static <T> Stream<T> stream(T[] array))方法转换得到的返回类型是Stream\<T>。但是如果实参为基本数据类型的数组，对于int，long，double来说，有专门的返回类型IntStream，LongStream，DoubleStream。
3. 生成流的方法
例：
	-	`Stream<Integer> intergerStream = Stream.of(1,2,3,4);`
	-	`Stream<Integer> intergerStream = Stream.generate(() -> 1);`
	-	`Stream<Integer> intergerStream = Stream.iterate(0, i -> i+1 );`
4. streamName.skip(n) ：跳过n个元素，当前元素个数低于n，则抛出IllegalArgumentException。
5. 5．
   - 一个流只能被使用一次
   - 若对流只使用中间操作（intermediate operation），则该操作不会被实际执行（lazy）。只有对流进行终端操作（terminal operation），其前面的所有中间操作才会被执行。




