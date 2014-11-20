library fremad_main;

@MirrorsUsed(targets: const[
    'fremad_main'
],
override: '*')
import 'dart:mirrors';

import 'package:angular/application_factory.dart';
import 'package:logging/logging.dart';
import 'package:fremad/fremad.dart';

main(){
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });

  final inj = applicationFactory().addModule(new FremadApp()).run();
  GlobalHttpInterceptors.setUp(inj);
}
