library fremad_main;


import 'package:angular/application_factory.dart';
import 'package:logging/logging.dart';
import 'package:fremad/fremad.dart';

main(){
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });

  applicationFactory().addModule(new FremadApp()).run();
}
