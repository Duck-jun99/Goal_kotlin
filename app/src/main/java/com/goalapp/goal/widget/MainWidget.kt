package com.goalapp.goal.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.goalapp.goal.R
import com.goalapp.goal.database.RemoteViewsService
import com.goalapp.goal.mainSrcreen.loading


/**
 * Implementation of App Widget functionality.
 */
class MainWidget : AppWidgetProvider() {
    /**
     * 위젯이 바탕화면에 설치될 때마다 호출되는 함수
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val serviceIntent = Intent(context, RemoteViewsService::class.java)
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            serviceIntent.data = Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))
            val widget = RemoteViews(context.packageName, R.layout.main_widget)
            widget.setRemoteAdapter(R.id.widget_listview, serviceIntent)
            val toastIntent = Intent(context, MainWidget::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            serviceIntent.data = Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))
            val toastPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            widget.setPendingIntentTemplate(R.id.widget_listview, toastPendingIntent)
            appWidgetManager.updateAppWidget(appWidgetIds, widget)
        }
        // RemoteViewsService 실행 등록시키는 함수


//        클릭이벤트 인텐트 유보.
        //보내기
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val mgr = AppWidgetManager.getInstance(context)
        if (intent.action == TOAST_ACTION) {
            val i = Intent(context, loading::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
        super.onReceive(context, intent)
    }

    companion object {
        const val TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION"
        const val EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM"

        /**
         * 위젯의 크기 및 옵션이 변경될 때마다 호출되는 함수
         * @param context
         * @param appWidgetManager
         * @param appWidgetId
         */
        fun updateAppWidget(
            context: Context?, appWidgetManager: AppWidgetManager?,
            appWidgetId: Int
        ) {
        }
    }
}