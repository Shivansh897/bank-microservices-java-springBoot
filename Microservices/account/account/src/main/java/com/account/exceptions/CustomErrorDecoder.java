package com.account.exceptions;

import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		log.info("ERROR DECODER {}, {}", methodKey, response);
		if (methodKey.contains("getCustomerWithAccount")) {
			throw new InvalidCustomerException("Customer Not found");
		}
		return null;
	}

}
