package com.example.mx_player_plugin;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** MxPlayerPlugin */
public class MxPlayerPlugin implements MethodCallHandler {
  /** Plugin registration. */
  private Registrar mRegistrar;
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "mx_player_plugin");
    channel.setMethodCallHandler(new MxPlayerPlugin(registrar));
  }
  MxPlayerPlugin(Registrar registrar){
    mRegistrar = registrar;
  }

  @TargetApi(16)
  private void openMx(String url,String subUrl){
    Parcelable[] parcels = {Uri.parse(subUrl)};
    Intent intent = new Intent(Intent.ACTION_VIEW);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
      intent.setPackage("com.mxtech.videoplayer.ad");
    }
    intent.setData(Uri.parse(url));
    intent.putExtra("subs", parcels);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

    try {
      if (null != intent)
        mRegistrar.context().startActivity(intent);
    } catch (ActivityNotFoundException e) {
      
    }
  }

  private void openVlc(String url){

    Uri uri = Uri.parse(url);
    Intent vlcIntent = new Intent(Intent.ACTION_VIEW);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
        vlcIntent.setPackage("org.videolan.vlc");
    }

    vlcIntent.setDataAndTypeAndNormalize(uri, "video/*");
    vlcIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    vlcIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
    try {
      if (null != vlcIntent)
        mRegistrar.context().startActivity(vlcIntent);
    } catch (ActivityNotFoundException e) {

    }
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

    if(call.method.equals("openWithMxPlayer")){
      openMx(call.argument("url").toString(),call.argument("subUrl").toString());
    }
    else if(call.method.equals("openWithVlcPlayer")){
      openVlc(call.argument("url").toString());
    }
    else {
      result.notImplemented();
    }
  }
}
