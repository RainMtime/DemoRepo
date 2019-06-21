package rainmtime.com.demorepo.test_code.okhttp.listener;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 雨时光 on 2019-06-20 15:31
 */
public class OkHttpEventListener extends EventListener {

    private  long callId = -1;

    public OkHttpEventListener(long callId) {
        this.callId = callId;
    }

    /**
     * Invoked as soon as a call is enqueued or executed by a client. In case of thread or stream
     * limits, this call may be executed well before processing the request is able to begin.
     *
     * <p>This will be invoked only once for a single {@link Call}. Retries of different routes
     * or redirects will be handled within the boundaries of a single callStart and {@link
     * #callEnd}/{@link #callFailed} pair.
     */
    public void callStart(Call call) {
        Log.i("chunyu-test","callStart");
    }

    /**
     * Invoked just prior to a DNS lookup. See {@link Dns#lookup(String)}.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different host.
     *
     * <p>If the {@link Call} is able to reuse an existing pooled connection, this method will not be
     * invoked. See {@link ConnectionPool}.
     */
    public void dnsStart(Call call, String domainName) {
        Log.i("chunyu-test","dnsStart");
    }

    /**
     * Invoked immediately after a DNS lookup.
     *
     * <p>This method is invoked after {@link #dnsStart}.
     */
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        Log.i("chunyu-test","dnsEnd");
    }

    /**
     * Invoked just prior to initiating a socket connection.
     *
     * <p>This method will be invoked if no existing connection in the {@link ConnectionPool} can be
     * reused.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address, or a connection is retried.
     */
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        Log.i("chunyu-test","connectStart");
    }

    /**
     * Invoked just prior to initiating a TLS connection.
     *
     * <p>This method is invoked if the following conditions are met:
     * <ul>
     * <li>The {@link Call#request()} requires TLS.</li>
     * <li>No existing connection from the {@link ConnectionPool} can be reused.</li>
     * </ul>
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address, or a connection is retried.
     */
    public void secureConnectStart(Call call) {
        Log.i("chunyu-test","secureConnectStart");
    }

    /**
     * Invoked immediately after a TLS connection was attempted.
     *
     * <p>This method is invoked after {@link #secureConnectStart}.
     */
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        Log.i("chunyu-test","secureConnectEnd");
    }

    /**
     * Invoked immediately after a socket connection was attempted.
     *
     * <p>If the {@code call} uses HTTPS, this will be invoked after
     * {@link #secureConnectEnd(Call, Handshake)}, otherwise it will invoked after
     * {@link #connectStart(Call, InetSocketAddress, Proxy)}.
     */
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
                           @Nullable Protocol protocol) {
        Log.i("chunyu-test","connectEnd");
    }

    /**
     * Invoked when a connection attempt fails. This failure is not terminal if further routes are
     * available and failure recovery is enabled.
     *
     * <p>If the {@code call} uses HTTPS, this will be invoked after {@link #secureConnectEnd(Call,
     * Handshake)}, otherwise it will invoked after {@link #connectStart(Call, InetSocketAddress,
     * Proxy)}.
     */
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
                              @Nullable Protocol protocol, IOException ioe) {
        Log.i("chunyu-test","connectFailed");

    }

    /**
     * Invoked after a connection has been acquired for the {@code call}.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address.
     */
    public void connectionAcquired(Call call, Connection connection) {

        Log.i("chunyu-test","connectionAcquired");
    }

    /**
     * Invoked after a connection has been released for the {@code call}.
     *
     * <p>This method is always invoked after {@link #connectionAcquired(Call, Connection)}.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address.
     */
    public void connectionReleased(Call call, Connection connection) {
        Log.i("chunyu-test","connectionReleased");
    }

    /**
     * Invoked just prior to sending request headers.
     *
     * <p>The connection is implicit, and will generally relate to the last
     * {@link #connectionAcquired(Call, Connection)} event.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address.
     */
    public void requestHeadersStart(Call call) {
        Log.i("chunyu-test","requestHeadersStart");
    }

    /**
     * Invoked immediately after sending request headers.
     *
     * <p>This method is always invoked after {@link #requestHeadersStart(Call)}.
     *
     * @param request the request sent over the network. It is an error to access the body of this
     *     request.
     */
    public void requestHeadersEnd(Call call, Request request) {
        Log.i("chunyu-test","requestHeadersEnd");
    }

    /**
     * Invoked just prior to sending a request body.  Will only be invoked for request allowing and
     * having a request body to send.
     *
     * <p>The connection is implicit, and will generally relate to the last
     * {@link #connectionAcquired(Call, Connection)} event.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address.
     */
    public void requestBodyStart(Call call) {
        Log.i("chunyu-test","requestBodyStart");
    }

    /**
     * Invoked immediately after sending a request body.
     *
     * <p>This method is always invoked after {@link #requestBodyStart(Call)}.
     */
    public void requestBodyEnd(Call call, long byteCount) {
        Log.i("chunyu-test","requestBodyEnd");
    }

    /**
     * Invoked just prior to receiving response headers.
     *
     * <p>The connection is implicit, and will generally relate to the last
     * {@link #connectionAcquired(Call, Connection)} event.
     *
     * <p>This can be invoked more than 1 time for a single {@link Call}. For example, if the response
     * to the {@link Call#request()} is a redirect to a different address.
     */
    public void responseHeadersStart(Call call) {
        Log.i("chunyu-test","responseHeadersStart");
    }

    /**
     * Invoked immediately after receiving response headers.
     *
     * <p>This method is always invoked after {@link #responseHeadersStart}.
     *
     * @param response the response received over the network. It is an error to access the body of
     *     this response.
     */
    public void responseHeadersEnd(Call call, Response response) {
        Log.i("chunyu-test","responseHeadersEnd");
    }

    /**
     * Invoked just prior to receiving the response body.
     *
     * <p>The connection is implicit, and will generally relate to the last
     * {@link #connectionAcquired(Call, Connection)} event.
     *
     * <p>This will usually be invoked only 1 time for a single {@link Call},
     * exceptions are a limited set of cases including failure recovery.
     */
    public void responseBodyStart(Call call) {
        Log.i("chunyu-test","responseBodyStart");
    }

    /**
     * Invoked immediately after receiving a response body and completing reading it.
     *
     * <p>Will only be invoked for requests having a response body e.g. won't be invoked for a
     * websocket upgrade.
     *
     * <p>This method is always invoked after {@link #requestBodyStart(Call)}.
     */
    public void responseBodyEnd(Call call, long byteCount) {
        Log.i("chunyu-test","responseBodyEnd");
    }

    /**
     * Invoked immediately after a call has completely ended.  This includes delayed consumption
     * of response body by the caller.
     *
     * <p>This method is always invoked after {@link #callStart(Call)}.
     */
    public void callEnd(Call call) {
        Log.i("chunyu-test","callEnd:request:"+call.request().toString());
    }

    /**
     * Invoked when a call fails permanently.
     *
     * <p>This method is always invoked after {@link #callStart(Call)}.
     */
    public void callFailed(Call call, IOException ioe) {
        Log.i("chunyu-test","callFailed");
    }


    public static class OKHttpEventListenerFactory implements EventListener.Factory{
        final AtomicLong nextCallId = new AtomicLong(1L);

        @Override
        public EventListener create(Call call) {
            long id = nextCallId.incrementAndGet();
            return new OkHttpEventListener(id);
        }
    }

}
