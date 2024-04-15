package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.demo.routing.*;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {
	private final Pattern READ_PATTERN;
	private final Pattern WRITER_PATTERN; // by default we use writer

	public DataSourceAspect(@Value("${spring.datasource.reader.pattern}") String readPattern,
							@Value("${spring.datasource.writer.pattern}") String writerPattern) {
		READ_PATTERN = Pattern.compile(getRegex(readPattern));
		WRITER_PATTERN = Pattern.compile(getRegex(writerPattern));
	}

	private String getRegex(String str) {
		return str.replaceAll("\\*", ".*")
				.replaceAll(" ", "")
				.replaceAll(",", "|");
	}

	@Around("within(@com.example.demo.aspect.DataSource *)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		point.getTarget();
		Method method = signature.getMethod();
		DataSource dataSource = method.getAnnotation(DataSource.class);
		if (dataSource != null) {
			// In order to have higher granularity,
			// I make method level annotation has higher priority than the class level.
			System.out.println("Setting up " + dataSource.value().name());
			DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
		} else {
			if (READ_PATTERN.matcher(method.getName()).matches()) {
				System.out.println("Setting up READER");
				DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.READER.name());
			} else {
				System.out.println("Setting up WRITER");
				DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.WRITER.name());
			}
		}

		try {
			return point.proceed();
		} finally {
			// clear data source after method's execution.
			DynamicDataSourceContextHolder.clearDataSourceType();
		}
	}
}