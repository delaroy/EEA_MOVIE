package com.engie.eea_tech_interview.common

import com.engie.data.util.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class TestCoroutineDispatcherProvider : CoroutineDispatcherProvider {

    override fun io(): CoroutineDispatcher = Dispatchers.Unconfined

    override fun default(): CoroutineDispatcher = Dispatchers.Unconfined

    override fun main(): CoroutineDispatcher = Dispatchers.Unconfined
}