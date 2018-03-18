package org.artfable.test.builder.core

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import kotlin.IllegalArgumentException

/**
 * @author artfable
 * 18.03.18
 */
@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: Exception, request: WebRequest): ResponseEntity<Any?> {
        return ResponseEntity.badRequest().body(exception.message)
    }
}