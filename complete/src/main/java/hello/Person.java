package hello;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;

	public String getFirstName() {
		Subsegment subsegment = AWSXRay.beginSubsegment("getFirstName");
		addMetadata();
		try {
			return firstName;
		} finally {
			AWSXRay.endSubsegment();
		}
	}

	public void setFirstName(String firstName) {
		Subsegment subsegment = AWSXRay.beginSubsegment("setFirstName");
		addMetadata();
		try {
			this.firstName = firstName;
		} finally {
			AWSXRay.endSubsegment();
		}

	}

	public String getLastName() {
		Subsegment subsegment = AWSXRay.beginSubsegment("getLastName");
		addMetadata();
		try {
			return lastName;
		} finally {
			AWSXRay.endSubsegment();
		}
	}

	public void setLastName(String lastName) {
		Subsegment subsegment = AWSXRay.beginSubsegment("setLastName");
		addMetadata();
		try {
			this.lastName = lastName;
		} finally {
			AWSXRay.endSubsegment();
		}
	}

	private void addMetadata() {
		//TODO: change to using interceptor
		Segment document = AWSXRay.getCurrentSegment();
		document.putAnnotation("version", "githashjajdghkdjgl");
	}
}
