package com.luv2code.springboot.cruddemo.models;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PagingResponse<T> implements Serializable  {

	private Long totalRecords;
	private List<T> content;

	public PagingResponse(Page<T> pagingResults) {
		totalRecords = pagingResults.getTotalElements();
		content = pagingResults.getContent();
	}
}