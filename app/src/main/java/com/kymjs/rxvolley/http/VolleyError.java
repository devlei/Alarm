/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kymjs.rxvolley.http;

import static com.kymjs.rxvolley.http.VolleyError.ErrorConst.ERROR_CODE_UNKNOW;

/**
 * Exception style class encapsulating Volley errors
 */
@SuppressWarnings("serial")
public class VolleyError extends Exception {

    public static interface ErrorConst {
        int ERROR_CODE_NO_CONNECT = 101;
        int ERROR_CODE_TIME_OUT = 102;
        int ERROR_CODE_BAD_URL = 103;
        int ERROR_CODE_UNKNOW = -1;
        int ERROR_CODE_206 = 206;
        int ERROR_CODE_SERVER_ERROR=500;
    }

    public final NetworkResponse networkResponse;

    public int statusCode = ERROR_CODE_UNKNOW;

    public VolleyError() {
        networkResponse = null;
    }

    public VolleyError(NetworkResponse response) {
        super(null != response ? response.getData() : "");
        networkResponse = response;
    }

    public VolleyError(String exceptionMessage) {
        super(exceptionMessage);
        networkResponse = null;
    }

    public VolleyError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
    }

    public VolleyError(Throwable cause) {
        super(cause);
        networkResponse = null;
    }

    public int getStatusCode() {
        if (null != networkResponse) {
            return networkResponse.statusCode;
        } else {
            return statusCode;
        }
    }

    public void setStatusCode(int sCode) {
        statusCode = sCode;
    }
}
