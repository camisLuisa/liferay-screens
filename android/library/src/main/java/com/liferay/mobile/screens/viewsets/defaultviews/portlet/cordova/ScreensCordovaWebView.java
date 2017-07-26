package com.liferay.mobile.screens.viewsets.defaultviews.portlet.cordova;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import com.liferay.mobile.screens.util.EventBusUtil;
import com.liferay.mobile.screens.viewsets.defaultviews.portlet.ScreensWebView;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.ScreensCordovaActivity;

/**
 * @author Víctor Galán Grande
 */

public class ScreensCordovaWebView implements ScreensWebView, CordovaLifeCycleListener {

	private ScreensWebView.Listener listener;
	private WebView webView;
	private ScreensCordovaActivity cordovaActivity;

	public ScreensCordovaWebView(Activity activity, CordovaLifeCycleObserver observer) {
		cordovaActivity = new ScreensCordovaActivity(activity);

		CordovaWebView cordovaWebView = cordovaActivity.initCordova();

		observer.setListener(this);

		webView = ((WebView) cordovaWebView.getView());
	}

	public void onEvent(CordovaEvent event) {
		if (listener != null) {
			if (event.getEventType().equals(CordovaEvent.CordovaEventType.PAGE_STARTED)) {
				listener.onPageStarted();
			} else if (event.getEventType().equals(CordovaEvent.CordovaEventType.PAGE_FINISHED)) {
				listener.onPageFinished(event.getParam());
			}
		}
	}

	@Override
	public WebView getView() {
		return webView;
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public void onAttached() {
		EventBusUtil.register(this);
	}

	@Override
	public void onDetached() {
		EventBusUtil.unregister(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		cordovaActivity.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		cordovaActivity.handlePause();
	}

	@Override
	public void onStop() {
		cordovaActivity.handleStop();
	}

	@Override
	public void onStart() {
		cordovaActivity.handleStart();
	}

	@Override
	public void onResume() {
		cordovaActivity.handleResume();
	}

	@Override
	public void onDestroy() {
		cordovaActivity.handleDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		cordovaActivity.onSaveInstanceState(outState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		cordovaActivity.onConfigurationChanged(newConfig);
	}
}
