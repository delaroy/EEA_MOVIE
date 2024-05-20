package com.engie.eea_tech_interview.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class UnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcherProvider()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
}