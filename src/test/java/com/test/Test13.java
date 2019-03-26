package com.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;

import com.test.enum_test.ChildAnnotation;

public class Test13 {
	
	
	public static AnnotationAttributes getMergedAnnotationAttributes(
			LinkedMultiValueMap<String, AnnotationAttributes> attributesMap,
			Map<String, Set<String>> metaAnnotationMap, String annotationName) {

		// Get the unmerged list of attributes for the target annotation.
		List<AnnotationAttributes> attributesList = attributesMap.get(annotationName);
		if (attributesList == null || attributesList.isEmpty()) {
			return null;
		}

		// To start with, we populate the results with a copy of all attribute
		// values from the target annotation. A copy is necessary so that we do
		// not inadvertently mutate the state of the metadata passed to this
		// method.
		AnnotationAttributes results = new AnnotationAttributes(attributesList.get(0));

		Set<String> overridableAttributeNames = new HashSet<String>(results.keySet());
		overridableAttributeNames.remove(AnnotationUtils.VALUE);

		// Since the map is a LinkedMultiValueMap, we depend on the ordering of
		// elements in the map and reverse the order of the keys in order to traverse
		// "down" the annotation hierarchy.
		List<String> annotationTypes = new ArrayList<String>(attributesMap.keySet());
		Collections.reverse(annotationTypes);

		// No need to revisit the target annotation type:
		annotationTypes.remove(annotationName);

		for (String currentAnnotationType : annotationTypes) {
			List<AnnotationAttributes> currentAttributesList = attributesMap.get(currentAnnotationType);
			if (!ObjectUtils.isEmpty(currentAttributesList)) {
				Set<String> metaAnns = metaAnnotationMap.get(currentAnnotationType);
				if (metaAnns != null && metaAnns.contains(annotationName)) {
					AnnotationAttributes currentAttributes = currentAttributesList.get(0);
					for (String overridableAttributeName : overridableAttributeNames) {
						Object value = currentAttributes.get(overridableAttributeName);
						if (value != null) {
							// Store the value, potentially overriding a value from an
							// attribute of the same name found higher in the annotation
							// hierarchy.
							results.put(overridableAttributeName, value);
						}
					}
				}
			}
		}

		return results;
	}
	
	public static void main(String[] args) throws Exception {
		
//		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(Test13.class.getClassLoader());
//		ClassReader classReader = new ClassReader(Test13.class.getClassLoader().getResourceAsStream("com/test/Test10.class"));
//		classReader.accept(visitor, ClassReader.SKIP_DEBUG);
//		visitor.getAnnotationAttributes("com.test.enum_test.ParentAnnotation");
		
		Method mt = AnnotationUtils.class.getDeclaredMethod("getAttributeAliasNames", Method.class);
		mt.setAccessible(true);
		Method[] ms  = ChildAnnotation.class.getDeclaredMethods();
		for (Method method : ms) {
			if(method.getReturnType() != void.class && method.getParameterTypes().length == 0) {
				Object obj = mt.invoke(null, method);
				System.out.println(obj);
			}
		}
		
	}
	
}
