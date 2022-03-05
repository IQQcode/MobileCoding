package top.iqqcode.forceout

import android.app.Activity


/**
 * @Author: iqqcode
 * @Date: 2022-02-13 11:10
 * @Description: 创建一个Activity类来管理所有的活动
 */
object ActivityCollector {

    var activities: MutableList<Activity> = ArrayList()

    /**
     * 定义泛型数组activities 添加activity活动的方法addActivity
     * @param activity
     */
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    /**
     * 移除activity活动的方法removeActivity
     * @param activity
     */
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    /**
     * 遍历所有活动将其停止完成
     */
    fun findAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

}