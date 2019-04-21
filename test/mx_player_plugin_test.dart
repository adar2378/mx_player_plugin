import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mx_player_plugin/mx_player_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('mx_player_plugin');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
  //  expect(await MxPlayerPlugin.platformVersion, '42');
  });
}
