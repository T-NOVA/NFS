package eu.tnova.nfs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-11T08:48:58.527+0100")
@StaticMetamodel(AssuranceParameters.class)
public class AssuranceParameters_ {
	public static volatile SingularAttribute<AssuranceParameters, Integer> DBId;
	public static volatile SingularAttribute<AssuranceParameters, String> paramId;
	public static volatile SingularAttribute<AssuranceParameters, Integer> value;
	public static volatile SingularAttribute<AssuranceParameters, String> unit;
	public static volatile SingularAttribute<AssuranceParameters, String> formula;
	public static volatile ListAttribute<AssuranceParameters, Violation> violations;
	public static volatile SingularAttribute<AssuranceParameters, Penalty> penalty;
}
