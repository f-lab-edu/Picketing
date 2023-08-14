package com.picketing.www.application.filter.encoding.password;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class PicketingServletInputStreamWrapper extends ServletInputStream {

	private final ByteArrayInputStream sourceStream;

	public PicketingServletInputStreamWrapper(String body) {
		this.sourceStream = new ByteArrayInputStream(body.getBytes());
	}

	@Override
	public boolean isFinished() {
		return sourceStream.available() == 0;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int read() throws IOException {
		return sourceStream.read();
	}
}
