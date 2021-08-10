/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.observability.tracing.test.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.observability.tracing.Span;
import org.springframework.observability.tracing.TraceContext;

/**
 * A test span implementation.
 *
 * @author Marcin Grzejszczak
 * @since 1.0.0
 */
public class SimpleSpan implements Span {

	/**
	 * Map of tags.
	 */
	public Map<String, String> tags = new HashMap<>();

	/**
	 * Span started.
	 */
	public boolean started;

	/**
	 * Span ended.
	 */
	public boolean ended;

	/**
	 * Span abandoned.
	 */
	public boolean abandoned;

	/**
	 * Span start timestamp in micros.
	 */
	public long startMicros;

	/**
	 * Span end timestamp in micros.
	 */
	public long endMicros;

	/**
	 * Throwable corresponding to the span.
	 */
	public Throwable throwable;

	/**
	 * Remote service name of the span.
	 */
	public String remoteServiceName;

	/**
	 * Span kind.
	 */
	public Span.Kind spanKind;

	/**
	 * List of events.
	 */
	public List<String> events = new ArrayList<>();

	/**
	 * Span name.
	 */
	public String name;

	/**
	 * Remote service ip.
	 */
	public String ip;

	/**
	 * Remote service port.
	 */
	public int port;

	/**
	 * Is span no op.
	 */
	public boolean noOp;

	@Override
	public boolean isNoop() {
		return this.noOp;
	}

	@Override
	public TraceContext context() {
		return new NoOpTraceContext();
	}

	@Override
	public SimpleSpan start() {
		this.started = true;
		return this;
	}

	@Override
	public Span start(long micros) {
		start();
		this.startMicros = micros;
		return this;
	}

	@Override
	public SimpleSpan name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public SimpleSpan event(String value) {
		this.events.add(value);
		return this;
	}

	@Override
	public Span event(long micros, String value) {
		return null;
	}

	@Override
	public SimpleSpan tag(String key, String value) {
		this.tags.put(key, value);
		return this;
	}

	@Override
	public SimpleSpan error(Throwable throwable) {
		this.throwable = throwable;
		return this;
	}

	@Override
	public Span remoteIpAndPort(String ip, int port) {
		this.ip = ip;
		this.port = port;
		return this;
	}

	@Override
	public void end() {
		this.ended = true;
	}

	@Override
	public void end(long micros) {
		end();
		this.endMicros = micros;
	}

	@Override
	public void abandon() {
		this.abandoned = true;
	}

	@Override
	public Span remoteServiceName(String remoteServiceName) {
		this.remoteServiceName = remoteServiceName;
		return this;
	}

	@Override
	public String toString() {
		return "SimpleSpan{" + "tags=" + this.tags + ", started=" + this.started + ", ended=" + this.ended
				+ ", abandoned=" + this.abandoned + ", startMicros=" + this.startMicros + ", endMicros="
				+ this.endMicros + ", throwable=" + this.throwable + ", remoteServiceName='" + remoteServiceName + '\''
				+ ", spanKind=" + this.spanKind + ", events=" + this.events + ", name='" + this.name + '\'' + ", ip='"
				+ this.ip + '\'' + ", port=" + this.port + ", noOp=" + this.noOp + '}';
	}

}
