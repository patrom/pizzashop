package be.ordina.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import be.ordina.domain.Pizza;

@WebService
//@SOAPBinding(style=Style.DOCUMENT) // default
public interface PizzaOrderWebService {

	@WebMethod(operationName="getPizza")
	@WebResult( name="PizzaRes") 
	public Pizza findPizza( @WebParam(name="id") Long id ); 

}
