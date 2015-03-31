library event_list;

import 'event.dart';

class EventList {
  bool empty;
  List<Event> eventList;

  EventList(this.empty, this.eventList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": eventList
  };

  EventList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<Event>.
      from(json['listObject'].map((x) => new Event.fromJson(x))));
}
