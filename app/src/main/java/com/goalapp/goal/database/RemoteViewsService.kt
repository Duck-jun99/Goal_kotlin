package com.goalapp.goal.database

import android.content.Intent
import android.widget.RemoteViewsService


/**
 * RemoteViewsService를 상속받은 개발자 서비스 클래스
 * RemoteViesFactory를 얻을 목적으로 인텐트 발생에 의해 실행.
 */
class RemoteViewsService : RemoteViewsService() {
    //필수 오버라이드 함수 : RemoteViewsFactory를 반환한다.
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return RemoteViewsFactory(applicationContext)
    }
}