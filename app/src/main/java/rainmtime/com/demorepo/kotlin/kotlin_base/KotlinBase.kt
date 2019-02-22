package rainmtime.com.demorepo.kotlin.kotlin_base

import android.support.constraint.solver.widgets.Rectangle

/**
 * Created by chunyu on 2019/2/15 2:35 PM.
 * Email: 746431278@qq.com
 */

class KotlinBase {

    //定义函数
    //定义有2个Int的参数
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //将函数作为函数体,自动推断返回结果
    fun sum(a: Int, b: Int, c: Int) = a + b + c


    //函数返回无意义的值
    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    //Unit 返回值类型可以忽略
    fun printSum(a: Int, b: Int, c: Int) {
        println("sum of $a and $b equal ${a + b}")
    }

    //定义变量
    val a: Int = 1  //立即赋值
    val b = 2      //自动推断出Int类型

    //可以重新赋值的变量使用var关键字
    var x = 5

    //顶层变量
    val PI = 3.14
    var xx = 0

    fun incrementX() {
        x += 1
    }

    //字符串模版
    val aa = 1
    val s1 = "a is $aa"

    val s2 = "${s1.replace("is", "was")},but now is $aa"
    //输出  "a was 1, but now is 1

    //条件表达式
    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    //使用if表达式
    fun maxofPro(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    //使用可空值以及null检测
    fun parseInt(str: String): Int? {
        return null
    }

    //使用类型检测以及自动类型转换
    //关键词 is
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //这里已经自动转换成String类型了
            return obj.length
        }
        return null

    }

    //使用for循环
    val items = listOf("apple", "banana", "kiwifruit")

    fun printArrayList() {
        for (item in items) {
            print(item)
        }
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    //使用while循环
    fun useWhile() {
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    //使用when 表达式
    fun useWhen(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "World"
                is Long -> "is Long type"
                !is String -> "Not a String "
                else -> "Unknown"
            }

    //使用区间（range)
    fun useRange() {
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            println("fits in range")
        }

        if (x !in 1..5) {
            println("not fits range")
        }

        //数列区间迭代
        for (xx in 9 downTo 0 step 3) {
            print(xx)
        }

        for (yy in 1..10 step 2) {
            print(yy)
        }
    }

    //使用集合
    fun usecollect() {
        for (item in items) {
            print(item)
        }

        //使用in运算符，来判断集合内是否包含实例
        when {
            "orange" in items -> println("juicy")
            "banana" in items -> println("banana")
        }

        //使用lambda表达式过滤(filter）与映射(map)集合：
        val fruits = listOf("banana", "avocado")
        fruits.filter { it.startsWith("ba") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }


    }

    //创建基本类及其实例
    val rectangle = Rectangle()

}

class KotlinGrammar {

    //创建Data 对象
    data class Customer(val name: String, val email: String)

    //会为 Customer 类提供一下功能：
    //- getters（对于var 定义的还有setters）
    // equals()
    // hashCode()
    // toString()
    // copy()

    //函数的默认参数
    fun foo(a: Int = 0, b: String = "") {

    }

    //过滤list
    val items = listOf("apple", "banana", "kiwifruit")



}
