package com.goalapp.goal.otherActivities

import android.app.Activity
import android.content.Intent
import com.goalapp.goal.R


internal object ThemeUtils {
    private const val sTheme = 0
    const val THEME_DEFAULT = 0
    const val THEME_RED = 1
    const val THEME_PINK = 2
    const val THEME_YELLOW = 3
    const val THEME_GREEN = 4
    const val THEME_BLUE = 5
    const val THEME_PURPLE = 6
    const val THEME_WHITE = 7
    fun changeToTheme(activity: Activity, theme: Int) {
        ThemeUtils.sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
    }

    /** Set the theme of the activity, according to the configuration.  */
    fun onActivityCreateSetTheme(activity: Activity) {
        when (ThemeUtils.sTheme) {
            ThemeUtils.THEME_DEFAULT -> activity.setTheme(R.style.Theme_gray_Goal)
            ThemeUtils.THEME_RED -> activity.setTheme(R.style.Theme_red_Goal)
            ThemeUtils.THEME_PINK -> activity.setTheme(R.style.Theme_pink_Goal)
            ThemeUtils.THEME_YELLOW -> activity.setTheme(R.style.Theme_yellow_Goal)
            ThemeUtils.THEME_GREEN -> activity.setTheme(R.style.Theme_green_Goal)
            ThemeUtils.THEME_BLUE -> activity.setTheme(R.style.Theme_blue_Goal)
            ThemeUtils.THEME_PURPLE -> activity.setTheme(R.style.Theme_purple_Goal)
            ThemeUtils.THEME_WHITE -> activity.setTheme(R.style.Theme_white_Goal)
            else -> activity.setTheme(R.style.Theme_gray_Goal)
        }
    }
}