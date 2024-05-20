package com.engie.data.common

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class UnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcherProvider()
}