package org.cytoscape.cyrestbestpracticessample.internal;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

/*
 * This interface includes several JAX-RS annotations (Path, GET, and Produces) which help turn any implementation of it
 * into REST endpoints.
 * 
 * In addition, there is a single Swagger annotation (Api), which indicates that Swagger should include the endpoints in
 * the generated Swagger document (available at localhost:PORT/v1/swagger.json).
 * 
 * Note that you can add these annotations directly to an implementation as well; in this example we've merely separated
 * our API from our implementation.
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api
@Path("/cyrestbestpractices/v1/classroom/")
public interface ClassroomResource {

	@ApiOperation(value = "Get the current the teacher",
			notes = "Returns the current teacher.")
	@Path("teacher")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getTeacher();
    
	@ApiOperation(value = "Replace the teacher",
	notes = "Replaces the classes teacher.\n\nYou can use this to 'edit' the teacher's information by replacing their "
			+ " entire record with a newer one.")
    @Path("teacher")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person putTeacher(Person teacher);
    
	@ApiOperation(value = "Get a Student",
	notes = "Returns a list of IDs for all students enrolled in the class.",
			response = Integer.class, responseContainer="List")
    @Path("students/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getStudents();
   
	@ApiOperation(value = "Add a new Student",
			notes = "Adds a new Student to the class.\n\nNote that a new student will be automatically assigned an id, "
					+ "which is returned as part of the response body.",
					response = Student.class)
    @Path("students/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student postStudent(Person person);
    
	@ApiOperation(value = "Get a Student",
			notes = "Returns a particular student enrolled in the class.\n\nFor a list of all students, see `GET /cyrestbestpractices/v1/classroom/students`")
			@ApiResponses(value = { 
					@ApiResponse(code = 200, message = "Student added to class", response = Student.class),
					@ApiResponse(code = 404, message = "Student is not enrolled in class", response = SimpleMessage.class),
			})
    @Path("students/{studentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") Integer studentId);
    
	@ApiOperation(value = "Delete a Student",
			notes = "Removes a Student from the class.",
					response = Student.class)
			@ApiResponses(value = { 
		    	@ApiResponse(code = 200, message = "Student deleted", response = Student.class),
		    	@ApiResponse(code = 404, message = "Student is not enrolled in class", response = SimpleMessage.class),
		    })
    @Path("students/{studentId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") Integer studentId);
    
    @ApiOperation(value = "Replace a Student",
    		notes = "Replaces a student in the class.\n\nThis replaces the Student with the ID in the path. You can use"
    			+ "this to 'edit' a student by replacing their entire record with a newer one.",
    				response = Student.class, responseContainer="List")
    		@ApiResponses(value = { 
    				@ApiResponse(code = 200, message = "Student replaced", response = Student.class),
    				@ApiResponse(code = 404, message = "Student is not enrolled in class", response = SimpleMessage.class),
    		})
    @Path("students/{studentId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student putStudent(@PathParam("studentId") Integer studentId, Student student);
    
}