package com.luv2code.springboot.cruddemo.models.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

	private String label;
	private Object[] params;

	public ErrorResponse(String label) {
		this.label = label;
	}

}