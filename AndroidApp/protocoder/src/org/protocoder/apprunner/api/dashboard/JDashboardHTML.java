/*
 * Protocoder 
 * A prototyping platform for Android devices 
 * 
 * 
 * Copyright (C) 2013 Motorola Mobility LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions: 
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 * 
 */

package org.protocoder.apprunner.api.dashboard;

import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;
import org.protocoder.apidoc.annotation.APIMethod;
import org.protocoder.apprunner.JInterface;
import org.protocoder.apprunner.JavascriptInterface;
import org.protocoder.network.CustomWebsocketServer;
import org.protocoder.utils.StrUtils;

import android.app.Activity;

public class JDashboardHTML extends JInterface {

    private static final String TAG = "JWebAppImage";
    String id;

    public JDashboardHTML(Activity a) {
	super(a);
    }

    @JavascriptInterface
    @APIMethod(description = "", example = "")
    public void add(String html, int posx, int posy) throws UnknownHostException, JSONException {
	this.id = StrUtils.generateRandomString();
	JSONObject msg = new JSONObject();

	msg.put("type", "widget");
	msg.put("action", "add");

	JSONObject values = new JSONObject();
	values.put("id", id);
	values.put("type", "html");
	values.put("x", posx);
	values.put("y", posy);
	values.put("html", html);

	msg.put("values", values);

	CustomWebsocketServer ws = CustomWebsocketServer.getInstance(a.get());
	ws.send(msg);

    }

    @JavascriptInterface
    @APIMethod(description = "", example = "")
    public void changeImage(String url) throws JSONException, UnknownHostException {
	JSONObject msg = new JSONObject();

	msg.put("type", "widget");
	msg.put("action", "changeImage");

	JSONObject values = new JSONObject();
	values.put("id", id);
	values.put("type", "label");
	values.put("url", url);
	msg.put("values", values);

	CustomWebsocketServer ws = CustomWebsocketServer.getInstance(a.get());
	ws.send(msg);

    }
}
