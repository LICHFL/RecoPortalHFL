package com.lichfl.exception;

import java.sql.SQLException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import com.lichfl.controller.RecoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OracleCustomException extends SqlExceptionHelper {

	public OracleCustomException(boolean logWarnings) {
		super(logWarnings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void logExceptions(SQLException e, String message) {

		log.error("SQL Exception occurred: " + message, e);
	}
}
