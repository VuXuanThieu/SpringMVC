package com.laptrinhjavaweb.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BorrowDTO;
import com.laptrinhjavaweb.entity.BorrowEntity;

@Component
public class BorrowConverter {

	public BorrowDTO toDto(BorrowEntity entity) {
		BorrowDTO result = new BorrowDTO();
		
		
		result.setId(entity.getId());
		
		result.setUserId(entity.getUser().getId());
		result.setUserFullname(entity.getUser().getFullName());
		result.setBookId(entity.getBook().getId());
		result.setBookTitle(entity.getBook().getTitle());
		
		result.setCreatedBy(entity.getCreatedBy());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String strdate = sdf.format(entity.getCreatedDate().getTime());
		result.setCreatedTime(strdate);
		
		//0712
		
		strdate = sdf.format(entity.getReturnDate().getTime());
		result.setReturnDate(strdate);
		
		
		return result;
	}
	
	public BorrowEntity toEntity(BorrowDTO dto) {
		BorrowEntity result = new BorrowEntity();
		
		//0712
		
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getReturnDate());
			calendar.setTime(date);
			result.setReturnDate(calendar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public BorrowEntity toEntity(BorrowEntity result, BorrowDTO dto) {
		//0712
		
				Calendar calendar = Calendar.getInstance();
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getReturnDate());
					calendar.setTime(date);
					result.setReturnDate(calendar);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		return result;
	}
}
