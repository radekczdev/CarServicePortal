package com.czajor.carserviceportal.files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeadersGenerator {
	public static HttpHeaders generate() {
		HttpHeaders headers = new HttpHeaders();
        String filename = "output.pdf";
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(filename, null);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return headers;
	}
}
