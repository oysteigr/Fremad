library image;

class Image {
  int id;
  int uploaderId;
  DateTime date;
  String imageType;
  String title;
  String url;

  Image(this.id, this.uploaderId, this.date, this.imageType, this.title, this.url);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "uploaderId": uploaderId,
    "date": date.millisecondsSinceEpoch,
    "imageType": imageType,
    "title": title,
    "url": url
  };

  Image.fromJson(Map<String, dynamic> json) : this(json['id'], json['uploaderId'],  
      new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false), json['imageType'], 
      json['title'], json['url']);
  
}