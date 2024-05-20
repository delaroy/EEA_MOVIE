package com.engie.eea_tech_interview

import androidx.multidex.MultiDexApplication
import com.engie.eea_tech_interview.di.appModule
import com.engie.eea_tech_interview.di.dataModule
import com.engie.eea_tech_interview.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class BaseApplication: MultiDexApplication() {

    private val allModules = arrayListOf(
        appModule,
        dataModule,
        domainModule
    ).flatten()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(allModules)
        }
    }
}
