package com.mts.crudprojetosweb.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
/* esta notação @ControllerAdvice é uma classe é um componente do spring, porém com proposito expecifico 
 * de tratar exeções de forma global. Para todos os controladores da aplicação.
 * */
	//para que não retorne sem uma explicação para o usuario, iremos personalizar o metodo abaixo
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.");
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
}
