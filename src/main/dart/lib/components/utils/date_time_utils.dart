part of fremad;

class DateTimeUtils{
  
  static String elapedTime(DateTime dateTime){
    if(getYears(dateTime) > 0){
      return getYears(dateTime) == 1 ?  getYears(dateTime).toString() + " year" : getYears(dateTime).toString() + " years";
    } else if(getMonths(dateTime) > 0){
      return getMonths(dateTime) == 1 ?  getMonths(dateTime).toString() + " month" : getMonths(dateTime).toString() + " months";
    } else if(getWeeks(dateTime) > 0){
      return getWeeks(dateTime) == 1 ?  getWeeks(dateTime).toString() + " week" : getWeeks(dateTime).toString() + " weeks";
    } else if(getDays(dateTime) > 0){
      return getDays(dateTime) == 1 ?  getDays(dateTime).toString() + " day" : getDays(dateTime).toString() + " days";
    } else if(getHours(dateTime) > 0){
      return getHours(dateTime) == 1 ?  getHours(dateTime).toString() + " hour" : getHours(dateTime).toString() + " hours";
    } else if(getMinutes(dateTime) > 0){
      return getMinutes(dateTime) == 1 ?  getMinutes(dateTime).toString() + " minute" : getMinutes(dateTime).toString() + " minutes";
    } else{
      return getSeconds(dateTime) == 1 ?  getSeconds(dateTime).toString() + " second" : getSeconds(dateTime).toString() + " seconds";
    }
    return "";
  }
  
  static int getDiff(DateTime dateTime){
    return (new DateTime.now().toLocal().millisecondsSinceEpoch - dateTime.millisecondsSinceEpoch);
  }
  
  static int getSeconds(DateTime dateTime){
    return (getDiff(dateTime)~/1000);
  }
  static int getMinutes(DateTime dateTime){
    return (getSeconds(dateTime)~/60);
  }
  static int getHours(DateTime dateTime){
    return (getMinutes(dateTime)~/60);
  }
  static int getDays(DateTime dateTime){
    return (getHours(dateTime)~/24);
  }
  static int getWeeks(DateTime dateTime){
    return (getDays(dateTime)~/7);
  }
  static int getMonths(DateTime dateTime){
    return (getDays(dateTime)~/30);
  }
  static int getYears(DateTime dateTime){
    return (getDays(dateTime)~/365);
  }
}