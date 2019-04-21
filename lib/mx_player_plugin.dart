import 'dart:async';

import 'package:flutter/services.dart';

class MxPlayerPlugin {
  static const MethodChannel _channel = const MethodChannel('mx_player_plugin');

  /// You should pass video url and the subtitle url and it will start the mx player
  static Future<void> openWithMxPlayer(String url, String subUrl) async {
    await _channel
        .invokeMethod('openWithMxPlayer', {"url": url, "subUrl": subUrl});
  }
}
