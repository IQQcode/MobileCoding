package top.iqqcode.oop.proxy

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 09:34
 * @Description:代理类
 */
class PerProxyImpl : IPerson by PerStudent {
    override fun behavior() {
        println("let student proxy!")
        PerStudent.behavior()
    }
}