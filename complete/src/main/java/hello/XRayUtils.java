package hello;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;

/**
 * Created by Gagan on 12/06/2017.
 */
public  class XRayUtils {
    public static void addMetadata() {
        //TODO: change to using interceptor
        Segment document = AWSXRay.getCurrentSegment();
        document.putAnnotation("version", VersionNumber.getVersionNumber());
    }
}
