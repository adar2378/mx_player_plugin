import 'dart:async';

import 'package:flutter/services.dart';

class PlayerPlugin {
  static const MethodChannel _channel = const MethodChannel('mx_player_plugin');

  static Future<void> openWithMxPlayer(String url, String subUrl) async {
    await _channel
        .invokeMethod('openWithMxPlayer', {"url": url, "subUrl": subUrl});
  }

  static Future<void> openWithVlcPlayer(String url) async {
    await _channel.invokeMethod('openWithVlcPlayer', {"url": url});
  }
}
