> [Kotlin集合—Map集合](https://www.cnblogs.com/it-tsz/p/11651536.html)

## 一、声明和创建Map集合

Kotlin提供了如下函数来创建Map集合：

- mapOf()：该函数返回不可变的Map集合。该函数可接受0个或多个key-value对，这些key-value对将作为Map的元素。 
- mutableMapOf()：该函数返回可变的MutableMap集合。该函数可接受0个或多个key-value对，这些key-value对将作为Map的元素。 
- hashMapOf()：该函数返回可变的HashMap集合。该函数可接受0个或多个key-value对，这些key-value对将作为Map的元素。 
- linkedMapOf()：该函数返回可变的LinkedHashMap集合。该函数可接受0个或多个key-value对，这些key-value对将作为Map的元素。 
- sortedMapOf()：该函数返回可变的TreeMap集合。该函数可接受0个或多个key-value对，这些key-value对将作为Map的元素。

```kotlin
//创建不可变集合，返回值是Map
var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
println(map)//按添加顺序排列
println("mapOf的返回对象的实际类型：${map.javaClass}")
//创建可变集合
var mutableMap = mutableMapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
println(mutableMap)//按添加顺序排列
println("mutableMapOf的返回对象的实际类型：${mutableMap.javaClass}")
//创建HashMap集合
var hashMap = hashMapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
println(hashMap)//不保证排列顺序
println("hashMapOf的返回对象的实际类型：${hashMap.javaClass}")
//创建LinkedHashMap
var linkedHashMap = linkedMapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
println(linkedHashMap)//按添加顺序排列
println("linkedMapOf的返回对象的实际类型：${linkedHashMap.javaClass}")
//创建TreeMap集合
var treeMap = sortedMapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
println(treeMap)//按key由小到大排列
println("sortedMapOf的返回对象的实际类型：${treeMap.javaClass}")
```


输出结果：

```kotlin
{Java=86, Kotlin=92, Go=78}
mapOf的返回对象的实际类型：class java.util.LinkedHashMap

{Java=86, Kotlin=92, Go=78}
mutableMapOf的返回对象的实际类型：class java.util.LinkedHashMap

{Go=78, Java=86, Kotlin=92}
hashMapOf的返回对象的实际类型：class java.util.HashMap

{Java=86, Kotlin=92, Go=78}
linkedMapOf的返回对象的实际类型：class java.util.LinkedHashMap

{Go=78, Java=86, Kotlin=92}
sortedMapOf的返回对象的实际类型：class java.util.TreeMap
```

<br>

## 二、使用Map的方法

```kotlin
//创建不可变集合，返回值是Map
var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
//判断是否所有key-value对的key的长度都大于4，value都大于80
println(map.all { it.key.length > 4 && it.value > 80 })
//判断是否任一key-value对的key的长豆都大于4、value都大于80
println(map.any { it.key.length > 4 && it.value > 80 })

println("Java" in map)
println("Go" !in map)

//对Map集合元素进行过滤：要求key包含li
val filteredMap = map.filter { "li" in it.key }
println(filteredMap)

//将每个key-value对映射成新值，返回所有新值组成的Map集合
val mappedList = map.map { "${it.key}有${it.value}节课" }
println(mappedList)

//根据key获取最大值
println(map.maxBy { it.key })
//根据value获取最小值
println(map.minBy { it.value })

var bMap= mapOf("Lua" to 67,"Erlang" to 73,"Kotlin" to 92)
//求并集
println(map+bMap)
//集合相减
println(map-bMap)
```


输出结果：

```kotlin
false
true
true
false

{Kotlin=92}
[Java有86节课, Kotlin有92节课, Go有78节课]
Kotlin=92
Go=78
{Java=86, Kotlin=92, Go=78, Lua=67, Erlang=73}
{Java=86, Kotlin=92, Go=78}
```

<br>

## 三、遍历Map
Map集合由多个key-value对组成，因此遍历Map集合时既可以通过对key-value对进行遍历，也可先遍历key，再通过key来获取对应的value进行遍历。

下面是对Map集合遍历的几种方式：

```kotlin
//创建不可变集合，返回值是Map
var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 76)

//遍历Map的key-value对，entris元素返回key-value对组成的Set
for (en in map.entries) {
    println("${en.key}->${en.value}")
}

//先遍历Map的key，再通过key获取value
for (key in map.keys) {
    println("${key}->${map[key]}")
}

//直接用for-in循环遍历Map
for ((key, value) in map) {
    println("${key}->${value}")
}

//用Lambda表达式遍历Map
map.forEach({ println("${it.key}->${it.value}") })
```

输出结果：

```kotlin
Java->86
Kotlin->92
Go->76

Java->86
Kotlin->92
Go->76

Java->86
Kotlin->92
Go->76

Java->86
Kotlin->92
Go->76
```

## 四、可变的Map

可变的Map为操作key-value对提供了如下方法：

- clear()：清空所有的key-value对。
- put(key:K,value:V)：放入key-value对。如果原来已有key，value将被覆盖。
- putAll(form:Map<out K,V>)：批量放入多个key-value对。
- remove(key:K)：删除key-value对。

```kotlin
var mutableMap = mutableMapOf("OC" to 96, "PHP" to 3400, "Perl" to 4300, "Ruby" to 5600, "Go" to 5600)
//以方括号语法放入key-value对
mutableMap["Swift"] = 9000
println(mutableMap)
//以put方法放入key-value对
mutableMap.put("OC", 8600)
println(mutableMap)
//删除key为"PHP"的key-value对
mutableMap.remove("PHP")
println(mutableMap)
println(mutableMap.size)
//删除所有元素
mutableMap.clear()
println(mutableMap)
println(mutableMap.size)
```


输出结果：

```kotlin
{OC=96, PHP=3400, Perl=4300, Ruby=5600, Go=5600, Swift=9000}
{OC=8600, PHP=3400, Perl=4300, Ruby=5600, Go=5600, Swift=9000}
{OC=8600, Perl=4300, Ruby=5600, Go=5600, Swift=9000}
5
{}
0
```