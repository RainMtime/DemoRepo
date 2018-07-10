package rainmtime.com.demorepo.kotlintest

import java.util.*

/**
 * Created by chunyu on 2018/5/23 下午2:54.
 * Email: 746431278@qq.com
 */
//public class JavaCode {
//    public String toJSON(Collection<Integer> collection) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        Iterator<Integer> iterator = collection.iterator();
//        while (iterator.hasNext()) {
//            Integer element = iterator.next();
//            sb.append(element);
//            if (iterator.hasNext()) {
//                sb.append(", ");
//            }
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//}

class JavaCode {
    fun toJSON(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}


data class Person(val name: String, val age: Int) {


    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 4, 5)
        Collections.sort(arrayList, ::compara)

        return arrayList
    }

    fun compara(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    var arrayListData: List<Int> = arrayListOf(1, 5, 6, 32, 65)

    fun test() {
        arrayListData.sortedWith(Comparator<Int> { o1, o2 -> o2 - o1 })
    }
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int):Comparable<MyDate> {

    override operator fun compareTo(bDate: MyDate): Int {
        return when {
            bDate.year != year -> bDate.year - year
            bDate.month != month -> bDate.month - month
            bDate.dayOfMonth != dayOfMonth -> bDate.dayOfMonth - dayOfMonth
            else -> -1
        }
    }

}

fun test2(){
    val numbers = listOf(1,2);
}

class LazyProperty(val initializer:() ->Int){
    var value:Int? = null
    val lazy:Int
         get() {
             if (value == null){
                 value = initializer()
             }
             return  value!!
         }
}
