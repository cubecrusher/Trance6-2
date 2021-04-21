package com.cubecrusher.trancej;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cubecrusher.trancej.TrJr;

public class AndroidLauncher extends AndroidApplication {
	/*
	protected boolean shouldAskPermissions() {
		return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
	}

	@TargetApi(23)
	protected void askPermissions() {
		String[] permissions = {
				"android.permission.READ_EXTERNAL_STORAGE",
				"android.permission.WRITE_EXTERNAL_STORAGE"
		};
		int requestCode = 200;
		requestPermissions(permissions, requestCode);
	}
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(TrJr.INSTANCE, config);
		/*
		if (shouldAskPermissions()) {
			askPermissions();
		}
		*/
	}
}
