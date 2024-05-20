package com.engie.domain.util


abstract class SuspendActionUseCase<in Param> {

    abstract suspend operator fun invoke(param: Param)
}

// No need to pass "Unit" as a parameter
suspend operator fun SuspendActionUseCase<Unit>.invoke() = this(Unit)