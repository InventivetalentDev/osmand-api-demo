package net.osmand.aidl.maplayer.point;

import android.os.Parcel;
import android.os.Parcelable;

import net.osmand.aidl.map.ALatLon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AMapPoint implements Parcelable {

	/**
	 * Key for point`s image URI. Image will be displayed on map and in the context menu, if exists.
	 */
	public static final String POINT_IMAGE_URI_PARAM = "point_image_uri_param";
	/**
	 * Key for point`s speed. Speed will be displayed in the context menu, if exists. The value should be set in meters per second.
	 */
	public static final String POINT_SPEED_PARAM = "point_speed_param";
	/**
	 * Key for drawable resource name in OsmAnd, which will be used as point`s type icon and displayed in the context menu, if exists.
	 */
	public static final String POINT_TYPE_ICON_NAME_PARAM = "point_type_icon_name_param";

	private String id;
	private String shortName;
	private String fullName;
	private String typeName;
	private int color;
	private ALatLon location;
	private List<String> details = new ArrayList<>();
	private Map<String, String> params = new HashMap<>();

	public AMapPoint(String id, String shortName, String fullName, String typeName, int color,
					 ALatLon location, List<String> details, Map<String, String> params) {
		this.id = id;
		this.shortName = shortName;
		this.fullName = fullName;
		this.typeName = typeName;
		this.color = color;
		this.location = location;
		if (details != null) {
			this.details.addAll(details);
		}
		if (params != null) {
			this.params.putAll(params);
		}
	}

	public AMapPoint(Parcel in) {
		readFromParcel(in);
	}

	public static final Creator<AMapPoint> CREATOR = new
			Creator<AMapPoint>() {
				public AMapPoint createFromParcel(Parcel in) {
					return new AMapPoint(in);
				}

				public AMapPoint[] newArray(int size) {
					return new AMapPoint[size];
				}
			};

	public String getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getColor() {
		return color;
	}

	public ALatLon getLocation() {
		return location;
	}

	public List<String> getDetails() {
		return details;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(id);
		out.writeString(shortName);
		out.writeString(fullName);
		out.writeString(typeName);
		out.writeInt(color);
		out.writeParcelable(location, flags);
		out.writeStringList(details);
		out.writeMap(params);
	}

	private void readFromParcel(Parcel in) {
		id = in.readString();
		shortName = in.readString();
		fullName = in.readString();
		typeName = in.readString();
		color = in.readInt();
		location = in.readParcelable(ALatLon.class.getClassLoader());
		in.readStringList(details);
		in.readMap(params, HashMap.class.getClassLoader());
	}

	public int describeContents() {
		return 0;
	}
}
