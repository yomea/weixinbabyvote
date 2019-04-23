package com.test.test;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.Assert;

public class CommonConversionService extends DefaultConversionService {

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Assert.notNull(targetType, "targetType to convert to cannot be null");
		if (sourceType == null) {
			Assert.isTrue(source == null, "source must be [null] if sourceType == [null]");
			return null;
		}
		if (source != null && !sourceType.getObjectType().isInstance(source)) {
			throw new IllegalArgumentException("source to convert from must be an instance of " +
					sourceType + "; instead it was a " + source.getClass().getName());
		}
		GenericConverter converter = getConverter(sourceType, targetType);
		if (converter != null) {
			Object result = convert(converter, source, sourceType, targetType);
			return result;
		}
		return handleConverterNotFound(source, sourceType, targetType);
	}
	
	private Object handleConverterNotFound(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		
		if(source instanceof String) {
			return null;
		}
		
		String convertedSource = parseStr(source);
		
		return convert(convertedSource, TypeDescriptor.valueOf(String.class), targetType);
	}
	
	private String parseStr(Object objIn) {
		
		if(objIn == null) {
			return null;
		}
		return objIn.toString();
	}
	
	private Object convert(GenericConverter converter, Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		
		try {
			return converter.convert(source, sourceType, targetType);
		}
		catch (ConversionFailedException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new ConversionFailedException(sourceType, targetType, source, ex);
		}
		
	}
	
	
}
