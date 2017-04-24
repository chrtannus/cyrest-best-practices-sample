# CyREST Best Practices Example

This is a simple App intended to demonstrate how to register basic endpoints with CyREST and best practices for documenting them in Swagger.

# Necessary Dependencies

Within your POM files, you will need to add two dependencies. It is important that you set the scope for these dependencies to 'provided'. This ensures that CyREST and your App are using the same annotations; using a scope aside from 'provided' could cause your App to import annotations that CyREST cannot recognize.

This is the dependency for JAX-RS annotations:
```
<dependency>
	<groupId>javax.ws.rs</groupId>
	<artifactId>javax.ws.rs-api</artifactId>
	<version>2.0</version>
	<scope>provided</scope>
</dependency>
```

This is the dependency for Swagger annotations:

```
<dependency>
	<groupId>io.swagger</groupId>
	<artifactId>swagger-annotations</artifactId>
	<version>1.5.7</version>
	<scope>provided</scope>
</dependency>
```

# Adding Endpoints to CyREST

To add an endpoint to CyREST, you must register an instance of a Java Object that has methods annotated using JAX-RS.

In this Sample App, all the necessary classes to illustrate this, with inline documentation explaining the annotations used, can be found in the package ```org.cytoscape.cyrestbestpracticessample.internal``` 

The ```ClassroomResource``` interface contains our JAX-RS annotations, which its implementation, ```ClassroomResourceImpl```, inherits.

We register an instance of ```ClassroomResourceImpl``` in ```CyActivator```. This exposes the resource to Cytoscape's OSGi infrastructure, where it can be recognized by CyREST. The resulting endpoints will be rooted at ```localhost:port//cyrestbestpractices/v1/classroom/```.


# Adding Swagger Documentation

In some instances, Swagger can introspect your classes and methods to automatically generate documentation, but far richer documentation can be achieved with the whole set of Swagger annotations, which can be found [here](https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X).
