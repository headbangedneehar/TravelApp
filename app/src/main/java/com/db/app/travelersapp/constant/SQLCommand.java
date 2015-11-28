package com.db.app.travelersapp.constant;

public abstract class SQLCommand
{

    public static String checkRegion="select reg_id from region where reg_name=";
    public static String checkDest = "select dest_id from destination where dest_name=";
    public static String getHotel="select uni_type.uni_id as _id, uni_type.uni_name from hotel,uni_type,destination where hotel.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";
    public static String getRestaurant="select uni_type.uni_id as _id, uni_type.uni_name from restaurant r,uni_type,destination where r.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";
    public static String getEntertainment="select uni_type.uni_id as _id, uni_type.uni_name from entertainment e,uni_type,destination where e.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";
    public static String getDestination = "select d.dest_id as _id, d.dest_name from destination d, region r where r.reg_id=d.reg_id and r.reg_name=";
    public static String getEmergency = "select e.emer_id as _id,e.emer_name,e.emer_addr,e.emer_contact from emergency e, destination d where e.dest_id = d.dest_id and d.dest_name=";

}
